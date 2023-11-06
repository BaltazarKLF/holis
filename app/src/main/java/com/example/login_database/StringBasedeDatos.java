package com.example.login_database;

import android.net.wifi.WifiManager;
import android.provider.BaseColumns;

public final class StringBasedeDatos {
    public StringBasedeDatos() {}

    public static abstract class USUARIOS  implements BaseColumns{
        public static final String NOMBRE_TABLA = "Users";
        public static final String CAMPO_IDUSER = "id_user";
        public static final String CAMPO_PASSWORD = "pass_user";
        public static final String CAMPO_NAMEUSER = "name_user";
        public static final String CAMPO_NOMBRE = "Name_field";
    }

    public static abstract class LIBROS implements BaseColumns{
        public static final String NOMBRE_TABLA = "Books";
        public static final String CAMPO_IDLIBRO = "id_libro";
        public static final String CAMPO_NOMBREL = "nombre_libro";
        public static final String CAMPO_AUTOR = "Author";
        public static final String CAMPO_GENERO = "genero";
    }
}
