package com.example.login_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txt_login_usuario, txt_login_password;
    private Button EntrarButton;
    private int usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_login_usuario = findViewById(R.id.FirusuarioTxt);
        txt_login_password = findViewById(R.id.Fircontrasenatxt);
        EntrarButton = findViewById(R.id.Firentrarbtn);

        EntrarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Botón 'Entrar' presionado");
                DataBase conexion = new DataBase(MainActivity.this, DataBase.DB_NAME, null, DataBase.v_db);
                SQLiteDatabase db = conexion.getWritableDatabase();
                String Consulta = "SELECT id_user FROM " + StringBasedeDatos.USUARIOS.NOMBRE_TABLA +
                        " WHERE " + StringBasedeDatos.USUARIOS.CAMPO_NAMEUSER + " = '" + txt_login_usuario.getText() +
                        "' AND " + StringBasedeDatos.USUARIOS.CAMPO_PASSWORD + " = '" + txt_login_password.getText() + "'";

                if (db != null) {
                    try {
                        Cursor c = db.rawQuery(Consulta, null);
                        Log.d("MainActivity", "cursor jalando");
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                int column = c.getColumnIndex("id_user");
                                usuario = Integer.parseInt(c.getString(column));
                            }
                            if (usuario != 0) {
                                Log.d("MainActivity", "Usuario válido");
                                Toast.makeText(MainActivity.this, "Bienvenido " + txt_login_usuario.getText(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                                startActivity(i);
                            } else {
                                Log.d("MainActivity", "Usuario inválido");
                                Toast.makeText(MainActivity.this, "Usuario inválido", Toast.LENGTH_SHORT).show();
                            }
                        }
                        c.close(); 
                    } catch (Exception e) {
                        Log.e("MainActivity", "Error al acceder a la base de datos", e);
                        Toast.makeText(MainActivity.this, "Problemas al acceder a la base de datos", Toast.LENGTH_SHORT).show();
                    } finally {
                        db.close();
                    }

                }
            }
        });
    }
}
