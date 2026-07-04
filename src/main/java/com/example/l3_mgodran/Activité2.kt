package com.example.l3_mgodran

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activité2 : AppCompatActivity() {

    var premierNombre = ""
    var deuxiemeNombre = ""
    var operateur = ""
    var nouveauNombre = true

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
                if (nouveauNombre) {
                    ecran.text = chiffre
                    nouveauNombre = false
                } else {
                    ecran.text = ecran.text.toString() + chiffre
                }
            }
        }

        // Opérateurs
        val operateurs = mapOf(
            R.id.btnAdd to "+", R.id.btnSub to "-",
            R.id.btnMul to "*", R.id.btnDiv to "/"
        )

        for ((id, op) in operateurs) {
            findViewById<Button>(id).setOnClickListener {
                premierNombre = ecran.text.toString()
                operateur = op
                nouveauNombre = true
            }
        }

        // Égal
        findViewById<Button>(R.id.btnEgal).setOnClickListener {
            deuxiemeNombre = ecran.text.toString()
            val n1 = premierNombre.toDoubleOrNull() ?: 0.0
            val n2 = deuxiemeNombre.toDoubleOrNull() ?: 0.0

            val resultat = when (operateur) {
                "+" -> n1 + n2
                "-" -> n1 - n2
                "*" -> n1 * n2
                "/" -> if (n2 != 0.0) n1 / n2 else {
                    ecran.text = "Erreur"
                    return@setOnClickListener
                }
                else -> 0.0
            }

            ecran.text = if (resultat == resultat.toLong().toDouble())
                resultat.toLong().toString()
            else
                resultat.toString()

            premierNombre = ecran.text.toString()
            nouveauNombre = true
        }
    }
}