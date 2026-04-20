const express = require("express");
const app = express();
const https = require("https");

app.listen(3000, function () {
  console.log("Server is starting...");
});

app.get("/", function (req, res) {
  const url =
    "https://api.openweathermap.org/data/2.5/weather?q=guduvancheri&appid=7c363ef4415074581558a62735e51d82&units=metric";
  https.get(url, function (response) {
    console.log(response.statusCode);
    response.on("data", function (data) {
      const weatherData = JSON.parse(data);

      const logValue =
        "The current weather in guduvancheri is: " + weatherData.main.temp;
      res.send(logValue);
    });
  });
});
