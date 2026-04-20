import express from "express";
import bodyParser from "body-parser";
import axios from "axios";

const app = express();
const port = 3000;

app.use(express.static("public"));
app.use(bodyParser.urlencoded({ extended: true }));

let urlToBeUsed = "https://bored-api.appbrewery.com/random";
app.get("/", async (req, res) => {
  try {
    const response = await axios.get(urlToBeUsed);
    let result;
    console.log(typeof response);
    if (Array.isArray(response.data)) { 
      console.log("Array");

      const min = 0;
      const max = response.data.length;
      const randomInteger = Math.floor(Math.random() * (max - min + 1)) + min;
     result = response.data[randomInteger];
    } else { 
      console.log("Object");
      result = response.data;
    }

    console.log(`RESULT ${result}`);
    res.render("index.ejs", { data: result });
  } catch (error) {
    console.error("Failed to make request:", error.message);
    res.render("index.ejs", {
      error: "No activities that match your criteria.",
    });
  }
});

app.post("/", async (req, res) => {
if(req.body.type || req.body.participants){
    urlToBeUsed = `https://bored-api.appbrewery.com/filter?`;
  if(req.body.type){
    urlToBeUsed += `type=${req.body.type}&`;
  }
  if(req.body.participants){
    urlToBeUsed += `participants=${req.body.participants}`;
  }
}else{
  urlToBeUsed = "https://bored-api.appbrewery.com/random";
}

   console.log(`REQUEST ${req.body}`);

  res.redirect("/");
});

app.listen(port, () => {
  console.log(`Server running on port: ${port}`);
});
