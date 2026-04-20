const express = require("express");
const bodyParser = require("body-parser");

const app = express();
app.use(bodyParser.urlencoded({ extended: true }));

app.get("/", function (req, res) {
  res.sendFile(__dirname + "/index.html");
});

app.get("/bmicalc", function (req, res) {
  res.sendFile(__dirname + "/bmiCalculator.html");
});

app.post("/", function (req, res) {
  var result = Number(req.body.num1) + Number(req.body.num2);
  res.send("The result of calc " + result);
});

app.post("/bmicalc", function (req, res) {
  var result = req.body.weight / (req.body.height * req.body.height);
  res.send("Your BMI is " + result);
});

app.listen(3000, function (req, res) {
  console.log("Server running on port 3000");
});
