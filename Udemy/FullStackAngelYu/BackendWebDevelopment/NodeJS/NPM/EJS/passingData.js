import express from 'express';
import bodyparser from 'body-parser';
const app = express();
const port = 3000;
const init = "ENTER YOUR NAME : ";
let nameValue = "Test";
app.use(bodyparser.urlencoded({ extended: true }));
app.listen(port, () => {
    console.log(port);
    console.log(`Passing data using EJS Templates listening on port ${port}`);
});


app.get('/', (req, res) => {
    res.render('passingData.ejs', {neededHeading : init});
});

app.post('/', (req, res) => {
    nameValue = `YOUR NAME IS ${req.body["first_name"]} ${req.body["last_name"]}`;
    res.render('passingData.ejs', {neededHeading : nameValue});
});