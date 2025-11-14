package com.example.practica_6;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText correo;
    private EditText contra;
    private Button btnContinuar;
    private Switch switchRecordar;
    private TextView alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        correo = findViewById(R.id.campoCorreo);
        correo.setText("");
        contra = findViewById(R.id.campoContra);
        contra.setText("");
        btnContinuar = findViewById(R.id.btnContinuar);
        switchRecordar = findViewById(R.id.switchRecordar);
        alerta = findViewById(R.id.textoAlerta);

        btnContinuar.setOnClickListener(v -> {

            String stringCorreo = correo.getText().toString();
            String stringContra = contra.getText().toString();

            if (stringCorreo.equals("correo@correo.com") && stringContra.equals("123")){
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra("dato", "correo@correo.com");
                startActivity(intent);

            } else {
                alerta.setText("Usuario y contraseña incorrectos");
                alerta.setTextColor(Color.RED);
            }

        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        correo.setText("");
        contra.setText("");
    }
}