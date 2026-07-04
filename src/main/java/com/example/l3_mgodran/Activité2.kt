package com.example.l3_mgodran

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activité2 : AppCompatActivity() {

    // Ce qui est réellement affiché à l'écran, ex : "7+8"
    var expression = ""
    // Le nombre en train d'être saisi (avant ou après l'opérateur), ex : "8"
    var nombreActuel = ""
    var premierNombre = ""
    var operateur = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activite2)

        // Récupérer le nom et prénom envoyés depuis MainActivity
        val nom = intent.getStringExtra("NOM")
        val prenom = intent.getStringExtra("PRENOM")

        // Utilise getString() pour respecter la langue du téléphone
        val textBonjour = findViewById<TextView>(R.id.textView3)
        textBonjour.text = "${getString(R.string.bonjour)} $prenom $nom !"

        // Bouton Retour
        findViewById<Button>(R.id.button5).setOnClickListener {
            finish()
        }

        val ecran = findViewById<TextView>(R.id.tvEcran)

        // Chiffres
        val chiffres = mapOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2",
            R.id.btn3 to "3", R.id.btn4 to "4", R.id.btn5 to "5",
            R.id.btn6 to "6", R.id.btn7 to "7", R.id.btn8 to "8",
            R.id.btn9 to "9"
        )

        for ((id, chiffre) in chiffres) {
            findViewById<Button>(id).setOnClickListener {
                nombreActuel += chiffre
                expression += chiffre
                ecran.text = expression
            }
        }

        // Opérateurs
        val operateurs = mapOf(
            R.id.btnAdd to "+", R.id.btnSub to "-",
            R.id.btnMul to "*", R.id.btnDiv to "/"
        )

        for ((id, op) in operateurs) {
            findViewById<Button>(id).setOnClickListener {
                if (nombreActuel.isNotEmpty()) {
                    premierNombre = nombreActuel
                    operateur = op
                    expression = nombreActuel + op   // ex : "7+"
                    ecran.text = expression
                    nombreActuel = ""
                }
            }
        }

        // Égal
        findViewById<Button>(R.id.btnEgal).setOnClickListener {
            if (premierNombre.isNotEmpty() && operateur.isNotEmpty() && nombreActuel.isNotEmpty()) {
                val n1 = premierNombre.toDoubleOrNull() ?: 0.0
                val n2 = nombreActuel.toDoubleOrNull() ?: 0.0

                val resultat = when (operateur) {
                    "+" -> n1 + n2
                    "-" -> n1 - n2
                    "*" -> n1 * n2
                    "/" -> if (n2 != 0.0) n1 / n2 else {
                        expression = "Erreur"
                        ecran.text = expression
                        nombreActuel = ""
                        premierNombre = ""
                        operateur = ""
                        return@setOnClickListener
                    }
                    else -> 0.0
                }

                val resultatStr = if (resultat == resultat.toLong().toDouble())
                    resultat.toLong().toString()
                else
                    resultat.toString()

                expression = resultatStr
                ecran.text = expression
                nombreActuel = resultatStr
                premierNombre = ""
                operateur = ""
            }
        }

        // Bouton C (effacer)
        findViewById<Button>(R.id.btnC).setOnClickListener {
            expression = ""
            nombreActuel = ""
            premierNombre = ""
            operateur = ""
            ecran.text = "0"
        }
    }
}