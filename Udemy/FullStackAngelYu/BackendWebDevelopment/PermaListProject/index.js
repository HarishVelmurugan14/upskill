import express from "express";
import bodyParser from "body-parser";
import pg from "pg";
const app = express();
const port = 3000;

const db = new pg.Client({
  user: "postgres",
  host: "localhost",
  database: "postgres",
  password: "root",
  port: 5432,
});
db.connect();

async function setSchema(req, res, next) {
  await db.query("SET search_path TO permalist");
  next();
}

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));
app.use(setSchema);

let items = [
  { id: 1, title: "Buy milk" },
  { id: 2, title: "Finish homework" },
];

async function fetchItems() {
  const itemsList =  await db.query("select * from items i order by title asc");
  return itemsList.rows;
}

app.get("/", async (req, res) => {
  const items = await fetchItems();
  res.render("index.ejs", {
    listTitle: "Today",
    listItems: items,
  });
});

app.post("/add", async (req, res) => {
  const item = req.body.newItem;
  await db.query("INSERT INTO items (title) VALUES ($1)", [item]);
  res.redirect("/");
});

app.post("/edit", async (req, res) => {
  const itemTitle =  String(req.body.updatedItemTitle); 
  const itemID = parseInt(req.body.updatedItemId);
  await db.query("UPDATE items set title = $1 where id = $2", [itemTitle, itemID]);
  res.redirect("/");
});

app.post("/delete", async(req, res) => {
  await db.query(`delete from items where id = ${parseInt(req.body.deleteItemId)}`);
  res.redirect("/");
});

app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
