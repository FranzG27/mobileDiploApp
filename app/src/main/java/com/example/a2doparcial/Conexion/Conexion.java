package com.example.a2doparcial.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexion extends SQLiteOpenHelper {
    private static final String DB="Parcial2.bd";
    private static final int VERSION=1;
    private static final String CATEGORIA="CATEGORIA";
    private static final String CLIENTE="CLIENTE";
    private static final String REPARTIDOR="REPARTIDOR";

    private static final String PRODUCTO="PRODUCTO";
    private static final String COTIZACION="COTIZACION";
    private static final String DETALLE="DETALLE";

    public Conexion(@Nullable Context contexto) {
        super(contexto, DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

         sqLiteDatabase.execSQL("CREATE TABLE " + CATEGORIA + "(" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "NOMBRE TEXT " +
                                 ")");

        sqLiteDatabase.execSQL("CREATE TABLE " + CLIENTE + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE TEXT NOT NULL," +
                "TELEFONO TEXT NOT NULL," +
                "DIRECCION TEXT NOT NULL)"
                );

        sqLiteDatabase.execSQL("CREATE TABLE " + REPARTIDOR + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE TEXT NOT NULL," +
                "TELEFONO TEXT NOT NULL," +
                "PLACA TEXT NOT NULL)"
        );

        sqLiteDatabase.execSQL("CREATE TABLE " + PRODUCTO + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE TEXT NOT NULL," +
                "PRECIO NUMERIC NOT NULL," +
                "IMAGEN BLOB," +
                "ID_CATEGORIA INTEGER NOT NULL, FOREIGN KEY(ID_CATEGORIA) REFERENCES CATEGORIA)"
        );

        sqLiteDatabase.execSQL("CREATE TABLE " + COTIZACION + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FECHA TEXT NOT NULL," +
                "TOTAL NUMERIC," +
                "ID_CLIENTE INTEGER NOT NULL, FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTE)"
        );

        sqLiteDatabase.execSQL("CREATE TABLE " + DETALLE + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CANTIDAD INTEGER NOT NULL," +
                "ID_PRODUCTO INTEGER NOT NULL," +
                "ID_COTIZACION INTEGER NOT NULL, " +
                "FOREIGN KEY(ID_COTIZACION) REFERENCES COTIZACION, " +
                "FOREIGN KEY(ID_PRODUCTO) REFERENCES PRODUCTO)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // sqLiteDatabase.execSQL("DROP TABLE " + CATEGORIA);
        onCreate(sqLiteDatabase);
        //sqLiteDatabase.execSQL(PRODUCTO);
        //sqLiteDatabase.execSQL(COTIZACION);
        //sqLiteDatabase.execSQL(DETALLE);

        //sqLiteDatabase.execSQL("DROP TABLE " + CATEGORIA);
        //sqLiteDatabase.execSQL(CATEGORIA);
        // onCreate(sqLiteDatabase);

        //sqLiteDatabase.execSQL("DROP TABLE " + TIPO);
        // sqLiteDatabase.execSQL(TIPO);
        //onCreate(sqLiteDatabase);

        //sqLiteDatabase.execSQL("DROP TABLE " + CLIENTE);
        //sqLiteDatabase.execSQL(CLIENTE);
        //onCreate(sqLiteDatabase);

        //sqLiteDatabase.execSQL("DROP TABLE " + REPARTIDOR);
        //sqLiteDatabase.execSQL(REPARTIDOR);
        // onCreate(sqLiteDatabase);
    }
}
