package com.example.login_database;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;


public class SecondActivity extends AppCompatActivity {
    private Button Agregarbtn, buscarbtn;
    private TextView etId;
    public SQLiteDatabase db;

    private Spinner spinner;
    private EditText etLibro, etAutor, etGenero;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        buscarbtn = findViewById(R.id.SecbotonBuscar);

        Agregarbtn = (Button) findViewById(R.id.Secbotonanadir);
        Agregarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(i);
            }
        });
        buscarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase conexion = new DataBase(SecondActivity.this, DataBase.DB_NAME, null, DataBase.v_db);
                SQLiteDatabase db = conexion.getWritableDatabase();
                String id_libro = etId.getText().toString();
                try {
                    Cursor fila = db.rawQuery(
                            "select lib_nombre, autor, genero, editorial from Books where id_libro="
                                    + id_libro, null);
                    // Continúa con el procesamiento de la consulta y muestra los resultados
                    if (fila.moveToFirst()) {
                        etLibro.setText(fila.getString(0));
                        etAutor.setText(fila.getString(1));
                        etGenero.setText(fila.getString(2));

                    } else {
                        // Toast.makeText(this, "No existe este libro", Toast.LENGTH_SHORT).show();
                        db.close();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.Secspinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
           public void onItemSelected(AdapterView parent, View view, int position, long id) {
                DataBase conexion = new DataBase(SecondActivity.this, DataBase.DB_NAME, null, DataBase.v_db);
                SQLiteDatabase db = conexion.getWritableDatabase();
                if (db != null) {
                    String Consulta = "select id_libro, nombre_libro from Books";
                    ArrayList lista = new ArrayList();
                    Cursor c = db.rawQuery(Consulta, null);

                    while (c.moveToNext()) {
                        int column = c.getColumnIndex("id_libro");
                       lista.add(c.getString(column));

                    }
                    ArrayAdapter adapter = new ArrayAdapter(SecondActivity.this, android.R.layout.simple_list_item_1, lista);

                    spinner.setAdapter(adapter);
                }
                conexion.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}


