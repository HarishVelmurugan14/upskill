const express = require("express");
const bodyParser = require("body-parser");
const app = express();
app.use(bodyParser.urlencoded({ extended: true }));

app.listen(3000, function () {
  console.log("Bmi Calculator app listening on port 3000!");
});

app.get("/", function (req, res) {
  res.send("The app is listening on port");
});
