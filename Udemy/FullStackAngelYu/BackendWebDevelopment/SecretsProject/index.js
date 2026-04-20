import express from "express";
import axios from "axios";
const port = 3000;

const app = express();
const baseURL = "https://secrets-api.appbrewery.com/random";
app.use(express.static("public"));

app.get("/", async (req, res) => {
  try {
    const response = await axios.get(baseURL);
    const result = response.data;
    console.log(result);
    res.render("index.ejs", { secret: result.secret, user: result.username });
  } catch (error) {
    console.error("Failed to make request:", error.message);
    res.render("index.ejs", {
      error: error.message,
    });
  }
});

app.listen(port, () => {
  console.log(`Server listening on ${port}`);
});
