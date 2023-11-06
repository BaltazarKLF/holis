package com.example.login_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private Button buttonadd;
    public SQLiteDatabase db;
    private EditText etLibro, etAutor, etGenero;

    private Button Atrasbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.libro_editoria);

        //boton para ir atras bbs
        Atrasbtn = (Button) findViewById(R.id.btnatras);
        Atrasbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(i);

            }
        });

        buttonadd = findViewById(R.id.btnadd);
        etLibro = findViewById(R.id.editText_libro);
        etAutor = findViewById(R.id.editText_libro1);
        etGenero = findViewById(R.id.editText_libro2);

      // onclick listener para el boton add
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_libro = etLibro.getText().toString();
                String autor = etAutor.getText().toString();
                String genero = etGenero.getText().toString();

                // Inicializa ContentValues para agregar datos a la base de datos
                ContentValues insert = new ContentValues();
                insert.put("nombre_libro", nombre_libro); // Aquí debería ser el nombre de la columna de la tabla en la base de datos
                insert.put("autor", autor); // Cambia esto también
                insert.put("genero", genero); // Y esto

                // Inserta los valores en la base de datos
                db.insert("LIBROS", null, insert);
                db.close();

                // Limpia los campos de entrada
                etLibro.setText("");
                etAutor.setText("");
                etGenero.setText("");

                Toast.makeText(ThirdActivity.this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
