package com.example.login_database;
import android.content.ContentValues;
import  android.content.Context;
import  android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteDatabase.CursorFactory;
import  android.database.sqlite.SQLiteOpenHelper;
public class DataBase extends SQLiteOpenHelper{
    //se definen los datos de la BD
    public static String DB_PATH = "data/data/com.example.app/databases/DataBase"; //ruta de la base de Datos
    public static String DB_NAME = "BDLibroTec";
    private final Context myContext;
    public static  int v_db = 1;

    String sqlCreateUsuario = "CREATE TABLE " + StringBasedeDatos.USUARIOS.NOMBRE_TABLA + " (" +
            StringBasedeDatos.USUARIOS.CAMPO_IDUSER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            StringBasedeDatos.USUARIOS.CAMPO_PASSWORD + " TEXT NOT NULL, " +
            StringBasedeDatos.USUARIOS.CAMPO_NAMEUSER + " TEXT NOT NULL, " +
            StringBasedeDatos.USUARIOS.CAMPO_NOMBRE + " TEXT NOT NULL);";


    String sqlCreateLibro = "CREATE TABLE " + StringBasedeDatos.LIBROS.NOMBRE_TABLA + " (" +
            StringBasedeDatos.LIBROS.CAMPO_IDLIBRO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            StringBasedeDatos.LIBROS.CAMPO_NOMBREL + " TEXT NOT NULL, " +
            StringBasedeDatos.LIBROS.CAMPO_AUTOR + " TEXT, " +
            StringBasedeDatos.LIBROS.CAMPO_GENERO + " TEXT);";
    //String sqlUpdate = "DROP DATABASE" + DB_NAME;
    public DataBase (Context context,  String name, CursorFactory factory, int version){
        super (context, name, factory, version);
        this.myContext = context;
    }

    public void  onCreate (SQLiteDatabase db){
        if (db.isReadOnly())
        {
            db=getWritableDatabase();
        }
        db.execSQL(sqlCreateUsuario);
        db.execSQL(sqlCreateLibro);

        ContentValues values = new ContentValues();
        values.put(StringBasedeDatos.USUARIOS.CAMPO_PASSWORD, "12345");
        values.put(StringBasedeDatos.USUARIOS.CAMPO_NAMEUSER, "baltazar");
        values.put(StringBasedeDatos.USUARIOS.CAMPO_NOMBRE, "nombre_field_de_prueba");
        long usuarioId = db.insert(StringBasedeDatos.USUARIOS.NOMBRE_TABLA, null, values);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        if(NewVersion > oldVersion){
            // db.execSQL(sqlUpdate);
        }
    }



}
