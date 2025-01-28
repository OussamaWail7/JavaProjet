var express = require('express'),
  app = express();
var http = require('http');
var cookieParser = require('cookie-parser')
app.use(cookieParser())
app.use(express.json())
app.use(express.static('css'))
app.set('view engine', 'ejs');
app.use(express.static('javascript'))

var routes = require('./api/routes/posteR.js'); //importing route

routes(app)

http.createServer(app).listen(8080, () => {
  console.log('Listening...on port 8080')
});

module.exports = app;