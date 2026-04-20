import "dotenv/config";
import express from "express";
import bodyParser from "body-parser";
import mongoose from "mongoose";
// import encrypt from "mongoose-encryption";
// import md5 from "md5";
// import bcrypt from "bcrypt";
import session from "express-session";
import passport from "passport";
import passportLocalMongosse from "passport-local-mongoose";

const app = express();
const port = 3000;
// const saltRounds = 10;

app.use(express.static("public"));
app.use(bodyParser.urlencoded({ extended: true }));
app.set("trust proxy", 1);
app.use(
  session({
    secret: "harishvelmurugan",
    resave: false,
    saveUninitialized: true,
    cookie: { secure: true },
  })
);

app.use(passport.initialize());
app.use(passport.session());

mongoose
  .connect("mongodb://127.0.0.1:27017/development")
  .then(() => console.log("Connected!"));

const userSchema = new mongoose.Schema({
  username: { type: String },
  password: { type: String },
});
//userSchema.plugin(encrypt, { secret: process.env.SECRET, encryptedFields: ["password"]});
userSchema.plugin(passportLocalMongosse);

const User = mongoose.model("User", userSchema);

passport.use(User.createStrategy());
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());

app.get("/", (req, res) => {
  res.render("home.ejs");
});

app.get("/register", (req, res) => {
  res.render("register.ejs");
});

app.post("/register", async (req, res) => {
  User.register(
    new User({ username: req.body.username }),
    req.body.password,
    function (err, user) {
      if (err) {
        console.log(err);
        res.redirect("/register");
      } else {
        //A new user was saved
        console.log(user + " Registered");
        passport.authenticate("local")(req, res, function () {
          res.redirect("/secrets");
        });
      }
    }
  );
});

// app.post("/register", async (req, res) => {
//   bcrypt.hash(req.body.password, saltRounds, function (err, hash) {
//     const newUser = new User({
//       email: req.body.username,
//       password: hash,
//     });
//     newUser
//       .save()
//       .then((savedUser) => {
//         console.log("User saved successfully:", savedUser);
//         res.render("secrets.ejs");
//       })
//       .catch((error) => {
//         console.error("Error saving user:", error);
//         res.send("ERROR");
//       });
//   });

//   const newUser = new User({
//     email: req.body.username,
//     password: md5(req.body.password),
//   });
//   newUser
//     .save()
//     .then((savedUser) => {
//       console.log("User saved successfully:", savedUser);
//       res.render("secrets.ejs");
//     })
//     .catch((error) => {
//       console.error("Error saving user:", error);
//       res.send("ERROR");
//     });
// });

app.get("/login", (req, res) => {
  res.render("login.ejs");
});

app.post("/login", (req, res) => {
  const user = new User({
    username: req.body.username,
    password: req.body.password,
  });

  req.login(user, function(err) {
    if (err) { return next(err); }
    return res.redirect("/secrets");
  });
});

// app.post("/login", (req, res) => {
//   const username = req.body.username;
//   //   const password = md5(parseInt(req.body.password));
//   const password = req.body.password;

//   User.findOne({ email: username })
//     .then((foundUser) => {
//       //console.log("Found user", foundUser);
//       console.log("Match found");
//       bcrypt.compare(password, foundUser.password, function (err, result) {
//         //   if (foundUser.password === password) {
//         if (result) {
//           res.render("secrets.ejs");
//         } else {
//           console.log("No match");
//         }
//       });
//     })
//     .catch((error) => {
//       console.error("Error finding user:", error);
//       res.send.isAuthenticated
//     });
// });

app.get("/secrets", (req, res) => {
  console.log(req.isAuthenticated());
  if (req.isAuthenticated()) {
    res.render("secrets.ejs");
  } else {
    res.redirect("/login");
  }
});

app.get("/logout", (req, res) => {
  req.session.destroy(function (err) {
    res.redirect("/login"); //Inside a callback… bulletproof!
  });
});

app.listen(port, () => {
  console.log(`Server running on port: ${port}`);
});
