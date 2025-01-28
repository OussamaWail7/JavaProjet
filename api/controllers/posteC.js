const fs = require('fs')
const path = require("path");
var aes256 = require('aes256');
var mysql = require('mysql');
var Excel = require('exceljs');

const {
    resolve
} = require('path');


var cipher, KEY
KEY = "OUSSAMAOUSSAMAOUS"
cipher = aes256.createCipher(KEY);

var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "",
    database: "dbposte"
});

function verification(cookie, resolve) {
    if (cookie != undefined) {
        var cookie = Decrypt(cookie)
        let obj = JSON.parse(cookie)
        con.query('SELECT * FROM user where username = "' + obj.name + '" && password = "' + obj.pass + '"', (err, result) => {
            if (err) {
                throw err
            } else {
                resolve(true)
            }
        });
    } else {
        resolve(false)
    }


}

function Crypt(session) {
    return cipher.encrypt(session);
}

function Decrypt(session) {
    return cipher.decrypt(session);
}


exports.login = (req, res) => {
    var username = req.body.username
    var password = req.body.password
    const promise1 = new Promise((resolve, reject) => {
        log(username, hash(password), resolve)

    });

    promise1.then((obj) => {
        console.log(req.headers.referer)
        if (obj.value) {
            str = JSON.stringify(obj.session)
            ses = Crypt(str)
            res.cookie('session', ses);
            res.redirect(req.headers.referer)
        } else {
            res.redirect(req.headers.referer)
        }
    });
}

exports.home = (req, res) => {
    res.sendFile(path.join(__dirname + '../../../HomePage.html'));
}

exports.user = (req, res) => {
    res.sendFile(path.join(__dirname + '../../../user.html'));
}

exports.insert = (req, res) => {
    const promi = new Promise((resolve, reject) => {
        if (req.cookies.session != "") {
            verification(req.cookies.session, resolve)
        } else {
            resolve(false)
        }
    });

    promi.then((val) => {
        if (val) {
            res.sendFile(path.join(__dirname + '../../../insertpage.html'));
        } else {
            res.sendFile(path.join(__dirname + '../../../Login.html'));

        }

    })
}

hash = (secret) => {

    const crypto = require('crypto');

    const hash = crypto.createHmac('sha256', secret).update('I love cupcakes')
        .digest('hex');

    return hash

}

function log(user, pass, resolve) {

    con.query('SELECT * FROM user where username = "' + user + '" && password = "' + pass + '"', (err, result) => {

        if (err) {
            throw err;
        } else {
            let obj
            if (result.length != 0) {
                obj = {
                    id: result[0].id,
                    name: result[0].username,
                    pass: result[0].password
                }
                obj = {
                    session: obj,
                    value: true
                };
            } else {
                obj = {
                    session: null,
                    value: false
                }
            }
            resolve(obj)

        }
    });


}

exports.operation = (req, res) => {
    const promi = new Promise((resolve, reject) => {
        if (req.cookies.session != "") {
            verification(req.cookies.session, resolve)
        } else {
            resolve(false)
        }
    });

    promi.then((val) => {
        if (val) {
            con.query('SELECT * FROM personne', (err, result) => {

                if (err) {
                    throw err;
                } else {
                    res.render('operation', {
                        data: result
                    });
                }
            });
        } else {
            res.sendFile(path.join(__dirname + '../../../Login.html'));

        }
    })
}

exports.save = (req, res) => {
    let obj = req.body
    let grade_s = obj.grade_s.replace(/ /g, "")
    let index = grade_s.indexOf('|')
    obj.grade_vc = grade_s.substring(0, index)
    obj.grade_int = grade_s.substring((index + 1), grade_s.length)
    let sql = "INSERT INTO personne (nom ,prenom ,grade ,grade_id ,ccp ,cle ) VALUES ('" + obj.name + "','" + obj.prenom + "','" + obj.grade_vc + "','" + obj.grade_int + "','" + obj.ccp + "','" + obj.cle + "')";
    console.log(sql)
    con.query(sql, (err, result) => {

        if (err) {
            throw err;
        } else {
            obj.id = result.insertId
            el = {
                statut: true,
                obj
            }
            res.send(el)
        }
    });

}

exports.editper = (req, res) => {
    let obj = req.body
    let grade_s = obj.grade_vc.replace(/ /g, "")
    let index = grade_s.indexOf('|')
    obj.grade_vc = grade_s.substring(0, index)
    obj.grade_int = grade_s.substring((index + 1), grade_s.length)
    console.log(obj.grade_int)
    sql = "UPDATE personne SET nom='" + obj.nom + "', prenom='" + obj.prenom + "', grade='" + obj.grade_vc + "',grade_id='" + obj.grade_int + "', ccp='" + obj.ccp + "', cle='" + obj.cle + "' WHERE id= " + obj.id;
    console.log(sql)
    con.query(sql, (err, result) => {

        if (err) {
            throw err;
        } else {
            el = {
                statut: true,
                obj
            }
            res.send(el)
        }
    });
}

exports.del = (req, res) => {
    let id = req.body.id
    console.log(req.body)
    var prom = new Promise((resolve, reject) => {
        let sql = "DELETE FROM mission WHERE idpersonne= " + id
        con.query(sql, (err, result) => {

            if (err) {
                throw err;
            } else {
                val = true
                resolve(val)
            }
        });

    })

    prom.then((val) => {

        if (val) {
            sql = "DELETE FROM personne WHERE id= " + id;
            console.log(sql)

            con.query(sql, (err, result) => {

                if (err) {
                    throw err;
                } else {
                    el = {
                        statut: true,
                        id
                    }
                    res.send(el)
                }
            });
        }
    })

}

exports.addmission = (req, res) => {
    let obj = req.body
    console.log(obj)
    let destination = obj.destination.replace(/ /g, "")
    let index = destination.indexOf('|')
    obj.destination = destination.substring(0, index)
    obj.distance = destination.substring((index + 1), destination.length)
    let date = obj.date_depart
    obj.date_depart = {}
    let inde = date.indexOf('T')
    obj.date_depart.date = date.substring(0, inde)
    obj.date_depart.heure = date.substring((inde + 1), date.length)
    inde = date.indexOf('T')
    date = obj.date_retour
    obj.date_retour = {}
    obj.date_retour.date = date.substring(0, inde)
    obj.date_retour.heure = date.substring((inde + 1), date.length)
    obj.base7 = 0
    obj.base9 = 0
    if (obj.distance.substring(0, obj.distance.length - 2) >= 50) {
        if (obj.date_depart.date === obj.date_retour.date) {
            if (obj.date_retour.heure > "11:00" && obj.date_depart.heure < "14:30") {
                obj.base7++
            }
            if (obj.date_retour.heure > "18:00" && obj.date_depart.heure < "21:00") {
                obj.base7++
            }
            if (obj.date_depart.heure < "06:00") obj.base9++
            // hna ndiro lmbat
            let date = new Date(obj.date_depart.date);
            jour = date.getDay();
            obj.base11 = (jour != 5 || jour != 6) ? 1 : 0

        } else {
            let date1 = Date.parse(obj.date_depart.date)
            let date2 = Date.parse(obj.date_retour.date)
            let date = date2 - date1
            let numj = date / (24 * 60 * 60 * 1000) - 1
            if (obj.date_depart.heure < "14:30") obj.base7++
            if (obj.date_depart.heure < "21:00") obj.base7++
            if (obj.date_retour.heure > "11:00") obj.base7++
            if (obj.date_retour.heure > "18:00") obj.base7++

            obj.base9 = numj + 1
            obj.base7 = obj.base7 + numj * 2
            var from = new Date(obj.date_depart.date);
            var to = new Date(obj.date_retour.date);
            var days = ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'];
            var resultat = [];
            while (from <= to) {
                jour = days[from.getDay()];
                resultat.push(jour);
                from = new Date(from.setDate(from.getDate() + 1));
            };
            result = resultat.filter(jour => (jour == "Vendredi" || jour == "Samedi"))
            obj.base11 = resultat.length - result.length

        }


    } else {
        obj.base7 = 0
        obj.base9 = 0
        obj.base11 = 0
    }

    let sql = "INSERT INTO `dbposte`.`mission` (`idpersonne`, `base7`, `base9`, `base11`, `date_depart`, `heure_depart`, `date_retour`, `heure_retour` , `destination` , `distance`) VALUES ('" + obj.id + "', '" + obj.base7 + "', '" + obj.base9 + "', '" + obj.base11 + "', '" + obj.date_depart.date + "', '" + obj.date_depart.heure + "', '" + obj.date_retour.date + "', '" + obj.date_retour.heure + "' , '" + obj.destination + "' , '" + obj.distance + "');"
    con.query(sql, (err, result) => {
        if (err) {
            throw err
        } else {
            res.send("mebarki")
        }
    });
    console.log(obj)

}

exports.file = (req, res) => {
    let sql = "SELECT * FROM personne,mission where personne.id = mission.idpersonne && personne.id =" + req.body.id

    con.query(sql, (err, result) => {
        if (err) {
            throw err
        } else {
            let promi = new Promise((resolve, reject) => {
                ExportXlsx(result, resolve)
            });

            promi.then(() => {
                res.redirect('/xlsx');
            })

        }
    });


}

function ExportXlsx(data, resolve) {
    var workbook = new Excel.Workbook();
    workbook.xlsx.readFile('Example.xlsx').then(function (workbook) {
        var worksheet = workbook.getWorksheet('Sheet1');
        var datarow = worksheet.getRow(5);
        datarow.getCell(2).value = 'MR: ' + data[0].nom.toUpperCase() + ' ' + data[0].prenom.toUpperCase();
        datarow.getCell(6).value = data[0].grade;
        datarow.getCell(9).value = data[0].ccp;
        datarow.getCell(11).value = data[0].cle;
        datarow.commit();
        var row4 = worksheet.getRow(4);
        row4.getCell(2).value = "Mois  de " + getText(data[0].date_depart);
        row4.commit();
        topass = 0;
        data.forEach((obj, index) => {
            workonline = index + topass + 8;
            row8plus = worksheet.getRow(workonline);
            row8plus.getCell(1).value = obj.idmission;
            row8plus.getCell(2).value = getDateFormat(obj.date_depart);
            row8plus.getCell(3).value = obj.heure_depart;
            if (obj.date_depart != obj.date_retour) {
                topass += 1;
                workonline = index + topass + 8;
                row9plus = worksheet.getRow(workonline);
                row9plus.getCell(1).value = obj.idmission;
                row9plus.getCell(2).value = getDateFormat(obj.date_retour);
                row9plus.getCell(4).value = obj.heure_retour;
                row9plus.getCell(6).value = obj.destination;
                row9plus.getCell(8).value = obj.base7;
                row9plus.getCell(10).value = obj.base9;
                row9plus.getCell(12).value = obj.base11;
                obj.grade_id < 15 ? row9plus.getCell(7).value = 700 : row9plus.getCell(7).value = 800;
                obj.grade_id < 15 ? row9plus.getCell(9).value = 1600 : row9plus.getCell(9).value = 2600;
                row9plus.getCell(11).value = 350;
                row9plus.getCell(13).value = row9plus.getCell(7).value * row9plus.getCell(8).value + row9plus.getCell(9).value * row9plus.getCell(10).value - (row9plus.getCell(11).value * row9plus.getCell(12).value);
                row9plus.getCell(15).value = row9plus.getCell(13).value;
                row9plus.getCell(16).value = "VA";
                row9plus.getCell(18).value = row9plus.getCell(13).value;
                worksheet.mergeCells(workonline - 1, 1, workonline, 1);
                row9plus.commit();
            } else {
                row8plus.getCell(4).value = obj.heure_retour;
                row8plus.getCell(5).value = obj.destination;
                row8plus.getCell(8).value = obj.base7;
                obj.grade_id < 15 ? row8plus.getCell(7).value = 700 : row8plus.getCell(7).value = 800;
                obj.grade_id < 15 ? row8plus.getCell(9).value = 1600 : row8plus.getCell(9).value = 2600;
                row8plus.getCell(11).value = 350;
                row8plus.getCell(10).value = obj.base9;
                row8plus.getCell(12).value = obj.base11;
                row8plus.getCell(13).value = row8plus.getCell(7).value * row8plus.getCell(8).value + row8plus.getCell(9).value * row8plus.getCell(10).value - (row8plus.getCell(11).value * row8plus.getCell(12).value);
                row8plus.getCell(15).value = row8plus.getCell(13).value;
                row8plus.getCell(16).value = "VA";
                row8plus.getCell(18).value = row8plus.getCell(13).value;
            }
            row8plus.commit();
        });
        var Sumrow = worksheet.getRow(39);
        Sumrow.getCell(18).value = {
            formula: 'SUM(R8:R' + workonline + ')'
        };
        Sumrow.getCell(15).value = {
            formula: 'SUM(R8:R' + workonline + ')'
        };
        Sumrow.getCell(13).value = {
            formula: 'SUM(R8:R' + workonline + ')'
        };
        Sumrow.commit();
        nombreofdefineslines = 38;
        linestodelete = nombreofdefineslines - workonline;
        if (linestodelete > 0)
            worksheet.spliceRows(workonline + 1, linestodelete);
        workonline += 2;
        var textline = worksheet.getRow(workonline);
        textline.getCell(8).value = "Dinars Algériens."
        textline.commit();
        if (workonline + 6 <= 38)
            worksheet.mergeCells(workonline + 6, 1, 38, 19);
        resolve(workbook.xlsx.writeFile('new.xlsx'));
    })
}


function getDateFormat(date) {
    date = new Date(date);
    day = date.getDate();
    day < 10 ? day = '0' + day : null
    month = parseInt(date.getMonth() + 1)
    month < 10 ? month = '0' + month : null
    return day + '-' + month + '-' + date.getFullYear();
}

function getText(date) {
    date = new Date(date);
    month = parseInt(date.getMonth() + 1);
    switch (month) {
        case 1:
            month = 'Janvier';
            break;
        case 2:
            month = 'Février';
            break;
        case 3:
            month = 'Mars';
            break;
        case 4:
            month = 'Avril';
            break;
        case 5:
            month = 'Mai';
            break;
        case 6:
            month = 'Juin';
            break;
        case 7:
            month = 'Juillet';
            break;
        case 8:
            month = 'Aout';
            break;
        case 9:
            month = 'Septembre';
            break;
        case 10:
            month = 'Octobre';
            break;
        case 11:
            month = 'Novembre';
            break;
        case 12:
            month = 'Décembre ';
            break;
    }
    return month + ' ' + date.getFullYear();
}



exports.info = (req, res) => {
    res.sendFile(path.join(__dirname + '../../../information.html'));
}

exports.hist = (req, res) => {
    const promi = new Promise((resolve, reject) => {
        if (req.cookies.session != "") {
            verification(req.cookies.session, resolve)
        } else {
            resolve(false)
        }
    });

    promi.then((val) => {
        if (val) {
            let sql = "SELECT * FROM personne , mission where personne.id = mission.idpersonne"
            con.query(sql, (err, result) => {

                if (err) {
                    throw err;
                } else {
                    res.render('historique', {
                        data: result
                    });
                }
            });
        } else {
            res.sendFile(path.join(__dirname + '../../../Login.html'));

        }
    })
}