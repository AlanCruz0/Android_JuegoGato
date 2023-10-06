package com.example.juegogato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private char[][] tablero = new char[3][3]; // Representa el tablero
    private char turnoActual = 'X'; // Jugador actual ('X' o 'O')
    private boolean juegoTerminado = false;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private int victoriasX = 0;
    private int victoriasO = 0;
    private TextView textViewVictoriasX;
    private TextView textViewVictoriasO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar botones
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        textViewVictoriasX = findViewById(R.id.textView);
        textViewVictoriasO = findViewById(R.id.textView2);


        // Configurar clics en los botones del tablero
        configurarBoton(btn1, 0, 0);
        configurarBoton(btn2, 0, 1);
        configurarBoton(btn3, 0, 2);
        configurarBoton(btn4, 1, 0);
        configurarBoton(btn5, 1, 1);
        configurarBoton(btn6, 1, 2);
        configurarBoton(btn7, 2, 0);
        configurarBoton(btn8, 2, 1);
        configurarBoton(btn9, 2, 2);

        // Inicializar el tablero
        reiniciarTablero();
    }
    // Método para reiniciar el tablero
    private void reiniciarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }

        // Habilitar los botones y limpiar el texto
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);

        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        // Reiniciar el turno
        //turnoActual = 'X';
        juegoTerminado = false;
    }
    private void configurarBoton(final Button button, final int fila, final int columna) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tablero[fila][columna] == ' ' && !juegoTerminado) {
                    // Realizar movimiento en la matriz del tablero
                    tablero[fila][columna] = turnoActual;
                    button.setText(String.valueOf(turnoActual));
                    button.setEnabled(false);

                    // Verificar si alguien ha ganado
                    if (verificarGanador(fila, columna)) {
                        juegoTerminado = true;
                        Toast.makeText(MainActivity.this, turnoActual + " ha ganado", Toast.LENGTH_SHORT).show();
                        if (turnoActual == 'X') {
                            victoriasX++;
                        } else {
                            victoriasO++;
                        }
                        textViewVictoriasX.setText("X = " + victoriasX);
                        textViewVictoriasO.setText("O = " + victoriasO);
                        reiniciarTablero();
                        Intent intent = new Intent(MainActivity.this, marcador.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("victoriasX", victoriasX);
                        bundle.putInt("victoriasO", victoriasO);
                        bundle.putString("ganador", (turnoActual == 'X') ? "X" : "O");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if (tableroCompleto()) {
                        juegoTerminado = true;
                        reiniciarTablero();
                        Toast.makeText(MainActivity.this, "Empate", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, marcador.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("victoriasX", victoriasX);
                        bundle.putInt("victoriasO", victoriasO);
                        bundle.putString("ganador", "Empate");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        // Cambiar el turno
                        turnoActual = (turnoActual == 'X') ? 'O' : 'X';
                    }
                }
            }
        });
    }
    private boolean verificarGanador(int fila, int columna) {
        // Verificar en la fila actual
        if (tablero[fila][0] == turnoActual &&
                tablero[fila][1] == turnoActual &&
                tablero[fila][2] == turnoActual) {
            return true;
        }

        // Verificar en la columna actual
        if (tablero[0][columna] == turnoActual &&
                tablero[1][columna] == turnoActual &&
                tablero[2][columna] == turnoActual) {
            return true;
        }

        // Verificar en la diagonal principal
        if (fila == columna &&
                tablero[0][0] == turnoActual &&
                tablero[1][1] == turnoActual &&
                tablero[2][2] == turnoActual) {
            return true;
        }

        // Verificar en la diagonal secundaria
        if (fila + columna == 2 &&
                tablero[0][2] == turnoActual &&
                tablero[1][1] == turnoActual &&
                tablero[2][0] == turnoActual) {
            return true;
        }

        return false;
    }
    private boolean tableroCompleto() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    // Todavía hay casillas vacías, el juego no está completo
                    return false;
                }
            }
        }
        // No hay casillas vacías, el juego está completo
        return true;
    }

}