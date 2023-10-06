package com.example.juegogato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class marcador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcador);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int victoriasX = bundle.getInt("victoriasX", 0);
            int victoriasO = bundle.getInt("victoriasO", 0);
            String ganador = bundle.getString("ganador", "");

            TextView textViewVictoriasX = findViewById(R.id.txtvicx);
            TextView textViewVictoriasO = findViewById(R.id.txtvico);
            TextView textViewGanador = findViewById(R.id.txtres);

            textViewVictoriasX.setText("X = " + victoriasX);
            textViewVictoriasO.setText("O = " + victoriasO);

            if (ganador.equals("Empate"))
                textViewGanador.setText("Empate");
            else
                textViewGanador.setText("Ganador: " + ganador);
        }

        Button buttonRegresar = findViewById(R.id.button10);
        buttonRegresar.setOnClickListener(v -> finish());
    }
}