package com.example.l3_mgodran;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActiviteBienvenue extends AppCompatActivity implements
        View.OnClickListener,
        AdapterView.OnItemClickListener {

    ArrayList<String> data;
    ArrayAdapter<String> matching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_bienvenue);

        data = new ArrayList<String>();
        matching = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        ListView liste = (ListView) findViewById(R.id.maListe);
        liste.setAdapter(matching);
        liste.setOnItemClickListener(this);

        Button btAjout = findViewById(R.id.btAjouter);
        btAjout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btAjouter) {
            EditText chAjout = (EditText) findViewById(R.id.chAjout);
            String t = chAjout.getText().toString();
            data.add(t);
            matching.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v("TEST", "Vous avez cliqué sur l'élément " + position + " de la liste");
        Toast.makeText(this, "Vous avez cliqué sur : " + data.get(position), Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Élément sélectionné");
        builder.setMessage("Vous avez cliqué sur : " + data.get(position));

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.remove(position);
                matching.notifyDataSetChanged();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}