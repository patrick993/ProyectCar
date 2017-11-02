package com.example.house.proyectcar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.house.proyectcar.Normal.Cliente;
import com.example.house.proyectcar.Normal.Vehiculo;
import com.example.house.proyectcar.Normal.Venta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by house on 02-11-2017.
 */

public class OpenHelperDB extends SQLiteOpenHelper {


        private static final int VERSION_BASEDATOS = 1;
        private static final String NOMBRE_BASEDATOS = "VentaVehiculos.db";
        private static final String TABLA_CLIENTE =
                "CREATE TABLE cliente (" +
                        "id TEXT PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT, " +
                        "apellido TEXT, " +
                        "direccion TEXT);";

        private static final String TABLA_VEHICULO =
                "CREATE TABLE vehiculo (" +
                        "patente INTEGER PRIMARY KEY, " +
                        "tipo TEXT, " +
                        "marca TEXT, " +
                        "nombreCliente, " +
                        "modelo TEXT);";

        private static final String TABLA_VENTA =
                "CREATE TABLE venta (" +
                        "idVenta INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "valor INT, " +
                        "detalle TEXT, " +
                        "fecha DATE);";

        private static final String INSERTAR_VEHICULO_AUTO_1 =
                "INSERT INTO vehiculo (tipo, marca, modelo, patente, nombreCliente) VALUES (" +
                        "'auto', " +
                        "'chevrolet', " +
                        "'camaro', " +
                        "'elliot', " +
                        "'bbcl34');";

        private static final String INSERTAR_VEHICULO_AUTO_2 =
                "INSERT INTO vehiculo (tipo, marca, modelo, patente) VALUES (" +
                        "'moto', " +
                        "'yamaha', " +
                        "'xmax250', " +
                        "'elizabeth', " +
                        "'bbcl35');";

        private static final String INSERTAR_VEHICULO_AUTO_3 =
                "INSERT INTO vehiculo (tipo, marca, modelo, patente) VALUES (" +
                        "'camion', " +
                        "'mack', " +
                        "'gu813', " +
                        "'victor', " +
                        "'bbcl24');";

        private static final String INSERTAR_CLIENTE =
                "INSERT INTO cliente (id,nombre,apellido,direccion) VALUES (" +
                        "'jhon', " +
                        "'wick', " +
                        "'usa');";





        public OpenHelperDB(Context context) {
            super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLA_CLIENTE);
            db.execSQL(TABLA_VEHICULO);
            db.execSQL(TABLA_VENTA);
            db.execSQL(INSERTAR_CLIENTE);
            db.execSQL(INSERTAR_VEHICULO_AUTO_1);
            db.execSQL(INSERTAR_VEHICULO_AUTO_2);
            db.execSQL(INSERTAR_VEHICULO_AUTO_3);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLA_CLIENTE);
            onCreate(db);

        }

        public void ejecutarSentencia(String sentencia)
        {
            SQLiteDatabase db = getWritableDatabase();

            if(db != null)
            {
                db.execSQL(sentencia);
            }

            db.close();
        }

        public Boolean login(String sentencia, String usuario, String password)
        {
            SQLiteDatabase db = getWritableDatabase();

            Cursor cursor = db.rawQuery(sentencia,null);

            if(cursor.moveToFirst() == true)
            {
                String auxUsuario = cursor.getString(0);
                String auxPassword = cursor.getString(1);

                if(usuario.equals(auxUsuario) && password.equals(auxPassword))
                {
                    return true;
                }
            }

            return false;
        }

        public Boolean nombreUsuarioExiste(String sentencia, String usuario)
        {
            SQLiteDatabase db = getWritableDatabase();

            Cursor cursor = db.rawQuery(sentencia,null);

            if(cursor.moveToFirst() == true)
            {
                String auxUsuario = cursor.getString(0);

                if(usuario.equals(auxUsuario))
                {
                    return true;
                }
            }

            return false;
        }

        public List<Cliente> listarCliente (String sentencia)
        {
            SQLiteDatabase db = getWritableDatabase();
            List<Cliente> auxListaCliente = new ArrayList<>();

            Cursor cursor = db.rawQuery(sentencia,null);
            cursor.moveToFirst();

            int countCursor = cursor.getCount();

            if(countCursor > 0)
            {
                do
                {
                    Cliente auxClientes = new Cliente();
                    auxClientes.setCodigo(cursor.getString(0));
                    auxClientes.setNombre(cursor.getString(1));
                    auxClientes.setApellido(cursor.getString(2));
                    auxClientes.setDireccion(cursor.getString(3));
                    auxListaCliente.add(auxClientes);
                }
                while(cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return  auxListaCliente;
        }

        public List<Vehiculo> listarVehiculo(String sentencia)
        {
            SQLiteDatabase db = getWritableDatabase();
            List<Vehiculo> auxListaVehiculo = new ArrayList<>();

            Cursor cursor = db.rawQuery(sentencia,null);
            cursor.moveToFirst();

            int countCursor = cursor.getCount();

            if(countCursor > 0)
            {
                do
                {
                    Vehiculo auxVehiculo = new Vehiculo();
                    auxVehiculo.setModelo(cursor.getString(0));
                    auxVehiculo.setPatente(cursor.getString(1));
                    auxVehiculo.setMarca(cursor.getString(2));
                    auxVehiculo.setTipo(cursor.getString(3));
                    auxListaVehiculo.add(auxVehiculo);
                }
                while(cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return  auxListaVehiculo;
        }

        public List<Venta> listarVenta (String sentencia)
        {
            SQLiteDatabase db = getWritableDatabase();
            List<Venta> auxListaVenta = new ArrayList<>();

            Cursor cursor = db.rawQuery(sentencia,null);
            cursor.moveToFirst();

            int countCursor = cursor.getCount();

            if(countCursor > 0)
            {
                do
                {
                    Venta auxVenta = new Venta();
                    auxVenta.setId(cursor.getString(0));
                    auxVenta.setValor(cursor.getString(1));
                    auxVenta.setDetalle(cursor.getString(2));
                    auxVenta.setFecha(cursor.getString(3));
                    auxListaVenta.add(auxVenta);
                }
                while(cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return  auxListaVenta;
        }

        public List<String> estadisticaVehiculo(String sentencia)
        {
            SQLiteDatabase db = getWritableDatabase();
            List<String> auxEstadisticaVehiculo = new ArrayList<>();

            Cursor cursor = db.rawQuery(sentencia,null);
            cursor.moveToFirst();

            do
            {
                auxEstadisticaVehiculo.add(cursor.getString(0));
                auxEstadisticaVehiculo.add(cursor.getString(1));
                auxEstadisticaVehiculo.add(cursor.getString(2));
                auxEstadisticaVehiculo.add(cursor.getString(3));
                auxEstadisticaVehiculo.add(cursor.getString(4));
                auxEstadisticaVehiculo.add(cursor.getString(5));
            }
            while(cursor.moveToNext());

            cursor.close();
            db.close();

            return  auxEstadisticaVehiculo;
        }

        public String seleccionar(String sentencia)
        {
            SQLiteDatabase db = getWritableDatabase();

            Cursor cursor = db.rawQuery(sentencia,null);

            String seleccionar = "";

            if(cursor.moveToFirst() == true)
            {
                seleccionar = cursor.getString(0);

                return seleccionar;
            }

            return seleccionar;
        }
    }
