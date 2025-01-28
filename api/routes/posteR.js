module.exports = function (app) {
    var path = require('path')
    var ejs = require('ejs');
    var poste = require('../controllers/posteC.js');
    var bodyParser = require('body-parser');
    var fs = require('fs');
    // var templateString = null;
    // var templateString = fs.readFileSync('Operation.ejs', 'utf-8');
    var urlencodedparser = bodyParser.urlencoded({
        extended: false
    });

    app.post('/login', urlencodedparser, poste.login)
    app.get('/', poste.home)
    app.get('/insert', poste.insert)
    app.get('/operation', poste.operation)
    // app.get('/edit/:id', poste.edit)
    app.post('/save', poste.save)
    app.post('/editper', poste.editper)
    app.post('/delete', poste.del)
    app.post('/addmission', poste.addmission)
    app.post('/getfile', poste.file)
    app.get('/hist', poste.hist)
    app.get('/information', poste.info)
    app.get('/user', poste.user)
    app.get('/xlsx', (req, res) => {
        res.sendFile(path.join(__dirname + '../../../new.xlsx'));

    })


}