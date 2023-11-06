package com.example.login_database;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText UsuarioEditText, ContrasenaEditText;
    private Button EntrarButton;
    //nuevas variables
    private EditText txt_login_password, txt_login_usuario;
    private List lista;
    private int usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UsuarioEditText = findViewById(R.id.usuarioTxt);

        EntrarButton = (Button) findViewById(R.id.Firentrarbtn);
        EntrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase conexion = new DataBase(MainActivity.this, DataBase.DB_NAME, null, DataBase.v_db);
                SQLiteDatabase db = conexion.getWritableDatabase();
                String Consulta="";
                if (db!= null){
                    try {
                        Consulta= "SELECT idUsuario FROM Uduarios WHERE User = '" + txt_login_usuario.getText() +
                                "'AND Pass '" + txt_login_password.getText()+ "'";
                        Cursor c = db.rawQuery(Consulta, null);

                        if (c.getCount()>0) {
                            while (c.moveToNext()) {
                                int column = c.getColumnIndex("iduser");
                                usuario = Integer.parseInt(c.getString(column));
                            }
                            if (usuario != 0) {
                                Toast.makeText(MainActivity.this, "Bienvenido" + txt_login_usuario.getText(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(MainActivity.this, "Usuario invalido" + usuario, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    catch(Exception e){
                        Toast.makeText(MainActivity.this, "Problemas al acceder a la base de datos", Toast.LENGTH_SHORT).show();
                    }
                }
                conexion.close();

            }
        });
    }
            public void AbrirVentana (View view){



    }
}





