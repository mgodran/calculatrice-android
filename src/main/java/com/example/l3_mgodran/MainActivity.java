package com.example.l3_mgodran;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            return insets;
        });

        // Bouton Valider → aller sur Activité2 avec le nom et prénom
        Button btnValider = findViewById(R.id.button);
        btnValider.setOnClickListener(v -> {
            EditText nom = findViewById(R.id.editTextText);
            EditText prenom = findViewById(R.id.editTextText2);

            Intent intent = new Intent(MainActivity.this, Activité2.class);
            intent.putExtra("NOM", nom.getText().toString());
            intent.putExtra("PRENOM", prenom.getText().toString());
            startActivity(intent);
        });

        // Bouton Annuler → vider les champs
        Button btnAnnuler = findViewById(R.id.button2);
        btnAnnuler.setOnClickListener(v -> {
            EditText nom = findViewById(R.id.editTextText);
            EditText prenom = findViewById(R.id.editTextText2);
            nom.setText("");
            prenom.setText("");
        });
    }
}