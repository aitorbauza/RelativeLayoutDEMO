package com.example.relativelayoutdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Asegúrate de que el XML se llame activity_main.xml

        // Obtener las referencias de los elementos de la interfaz
        EditText entry = findViewById(R.id.entry);
        Button okButton = findViewById(R.id.ok);
        Button cancelButton = findViewById(R.id.cancel);

        // Configurar el comportamiento del botón OK
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = entry.getText().toString().trim(); // Obtener la URL introducida

                if (!url.isEmpty()) {
                    // Asegurarse de que la URL tenga un esquema http:// o https://
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "http://" + url; // Agregar el esquema http:// si no está presente
                    }

                    // Intent para abrir la URL en el navegador
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                    // Verificar si hay una aplicación para manejar la URL
                    if (isValidUrl(url)) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "No application can handle this request.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a URL!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar el comportamiento del botón Cancel
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entry.setText(""); // Limpiar el campo de texto
                Toast.makeText(MainActivity.this, "Input cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidUrl(String url) {
        try {
            Uri.parse(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
