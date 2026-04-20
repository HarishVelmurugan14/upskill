import express from "express";
import bodyParser from "body-parser";
import pg from "pg";

const app = express();
const port = 3000;

const db = new pg.Client({
  user: "postgres",
  host: "localhost",
  database: "Development",
  password: "root",
  port: 5432,
});
db.connect();

async function setSchema(req, res, next) {
  await db.query("SET search_path TO 'familytraveltracker';");
  next();
}

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));
app.use(setSchema);
let currentUserId = 1;
let color = "white";

async function checkVisited(currentUserId) {
  const result = await db.query(
    `SELECT users.id , country_code, name, color FROM visited_countries JOIN users ON users.id = user_id where user_id = ${currentUserId}`
  );
  let countries = [];
  color = "white";
  if (result.rowCount > 0) {
    color = result.rows[0].color;
  }
  result.rows.forEach((country) => {
    countries.push(country.country_code);
  });
  return countries;
}

function fetchUsers() {
  return db.query(`SELECT * FROM users`);
}

async function insertUser(name, color) {
  try {
    const insertResult = await db.query(
      `INSERT INTO users (name, color) VALUES ($1, $2) RETURNING id`,
      [name, color]
    );

    // Access the ID from the insertResult
    const insertedId = insertResult.rows[0].id;

    console.log(`User with ID ${insertedId} inserted successfully.`);
    return parseInt(insertedId);
  } catch (error) {
    console.error("Error inserting user:", error);
    throw error; // You can re-throw the error to handle it elsewhere if needed.
  }
}

app.get("/", async (req, res) => {
  const countries = await checkVisited(currentUserId);
  const users = (await fetchUsers()).rows;
  res.render("index.ejs", {
    countries: countries,
    total: countries.length,
    users: users,
    color: color,
  });
});
app.post("/add", async (req, res) => {
  console.log(req.body);
  console.log(currentUserId);
  const input = req.body["country"];
  console.log(input);
  try {
    const result = await db.query(
      "SELECT country_code FROM countries WHERE LOWER(country_name) == $1;",
      [input.toLowerCase()]
    );

    const data = result.rows[0];
    const countryCode = data.country_code;
    try {
      await db.query(
        "INSERT INTO visited_countries (country_code, user_id) VALUES ($1, $2)",
        [countryCode, currentUserId]
      );
      res.redirect("/");
    } catch (err) {
      console.log(err);
    }
  } catch (err) {
    console.log(err);
  }
});
app.post("/user", async (req, res) => {
  if (req.body.user > 0) {
    currentUserId = req.body.user;
    res.redirect("/");
  } else if (req.body.add === "new") {
    res.render("new.ejs");
  }
});

app.post("/new", async (req, res) => {
  currentUserId = await insertUser(req.body.name, req.body.color);
  res.redirect("/");
});

app.listen(port, () => {
  console.log(`Server running on http://localhost:${port}`);
});
