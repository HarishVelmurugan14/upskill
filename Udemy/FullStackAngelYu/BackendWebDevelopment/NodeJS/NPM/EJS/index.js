import express from 'express';
const app = express();
const port = 3000;
app.listen(port, () => {
    console.log(`listening on port ${port}`);
});

let entireContent = "Hi there! It is a weekday. Work properly";
function findContent(req,res,next) {
    const d = new Date();
    let day = d.getDay();
    if(day === 0 || day === 6){
        entireContent = "Hi there! It is a weekend. Have Fun";
    }
    next();
}

app.use(findContent);
app.get('/', (req, res) => {
    res.render('render.ejs', {entireContent: entireContent});
});

