package com.example.house.proyectcar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.house.proyectcar.Clases.Cliente;
import com.example.house.proyectcar.Clases.Vehiculo;
import com.example.house.proyectcar.Clases.Venta;

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
                        "codigo TEXT PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT, " +
                        "apellido TEXT, " +
                        "direccion TEXT);";

        private static final String TABLA_VEHICULO =
                "CREATE TABLE vehiculo (" +
                        "patente INTEGER PRIMARY KEY, " +
                        "tipo TEXT, " +
                        "marca TEXT, " +
                        "modelo TEXT);";

        private static final String TABLA_VENTA =
                "CREATE TABLE venta (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "fecha String, " +
                        "nombre String, " +
                        "tipo String, " +
                        "valor String);";

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

        private static final String INSERTAR_VENTA =
                "INSERT INTO venta (id, fecha, nombre, tipo, valor) VALUES (" +
                        "'09-10-2017', " +
                        "'carlos', " +
                        "'camioneta', " +
                        "'$1.000.000');";






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
    //Inicio C.R.U.D cliente
        public void insertarDatos(Cliente cliente)
        {
            SQLiteDatabase db= getWritableDatabase();

            if(db != null)
            {
                db.execSQL("INSERT INTO cliente "
                        + "(nombre, apellido, direccion, codigo)"
                        + "VALUES ("
                        + "'" + cliente.getNombre() + "',"
                        + "'" + cliente.getApellido() + "',"
                        + "'" + cliente.getDireccion() + "',"
                        + "'" + cliente.getCodigo() + "');");
            }

            db.close();
        }

        public void actualizarCliente(Cliente cliente)
        {
            SQLiteDatabase db= getWritableDatabase();

            if(db != null)
            {
                db.execSQL("UPDATE cliente "
                        + " SET "
                        + " nombre = '" + cliente.getNombre()
                        + " apellido = '" + cliente.getApellido()
                        + " direccion = '" + cliente.getDireccion()
                        + "'WHERE codigo = '"+cliente.getCodigo() +"';");

            }

            db.close();
        }

        public void eliminarCliente(String codigo)
        {
            SQLiteDatabase db= getWritableDatabase();

            if(db != null)
            {
                db.execSQL("DELETE FROM cliente "
                        + "WHERE codigo = '" + codigo + "';");
            }

            db.close();
        }

        public void eliminarTodosLosCliente(String codigo)
        {
            SQLiteDatabase db= getWritableDatabase();

            if(db != null)
            {
                db.execSQL("DELETE FROM cliente ;");
            }

            db.close();
        }

        public Cliente buscarCliente(String codigo)
        {
            SQLiteDatabase db= getWritableDatabase();
            Cliente auxCliente = new Cliente();

            Cursor auxCursor = db.rawQuery("SELECT * FROM cliente "
                    + "WHERE codigo = '" + codigo + "';",null);

            auxCursor.moveToFirst();
            if(auxCursor != null)
            {
                auxCliente.setNombre(auxCursor.getString(0));
                auxCliente.setApellido(auxCursor.getString(1));
                auxCliente.setDireccion(auxCursor.getString(2));
                auxCliente.setCodigo(auxCursor.getString(3));
            }
            else
            {
                auxCliente.setNombre("");
                auxCliente.setApellido("");
                auxCliente.setDireccion("");
                auxCliente.setCodigo("");
            }
            auxCursor.close();
            db.close();
            return auxCliente;
        }
//Fin C.R.U.D cliente

//Inicio C.R.U.D vehiculo
        public void insertarVehiculo(Vehiculo vehiculo)
        {
            SQLiteDatabase db= getWritableDatabase();

            if(db != null)
            {
                db.execSQL("INSERT INTO vehiculo "
                        + "(patente, tipo, marca, modelo)"
                        + "VALUES ("
                        + "'" + vehiculo.getPatente() + "',"
                        + "'" + vehiculo.getTipo() + "',"
                        + "'" + vehiculo.getMarca() + "',"
                        + "'" + vehiculo.getModelo() + "');");
            }

            db.close();
        }

    public void actualizarVehiculo(Vehiculo vehiculo)
    {
        SQLiteDatabase db= getWritableDatabase();

        if(db != null)
        {
            db.execSQL("UPDATE vehiculo "
                    + " SET "
                    + " patente = '" + vehiculo.getPatente()
                    + " tipo = '" + vehiculo.getTipo()
                    + " marca = '" + vehiculo.getMarca()
                    + " marca = '" + vehiculo.getModelo()
                    + "'WHERE patente = '"+vehiculo.getPatente() +"';");

        }

        db.close();
    }

    public void eliminarVehiculo(String patente)
    {
        SQLiteDatabase db= getWritableDatabase();

        if(db != null)
        {
            db.execSQL("DELETE FROM vehiculo "
                    + "WHERE patente = '" + patente + "';");
        }

        db.close();
    }

    public void eliminarTodosLosVehiculos(String patente)
    {
        SQLiteDatabase db= getWritableDatabase();

        if(db != null)
        {
            db.execSQL("DELETE FROM vehiculo ;");
        }

        db.close();
    }

    public Vehiculo buscarVehiculo(String patente)
    {
        SQLiteDatabase db= getWritableDatabase();
        Vehiculo auxVehiculo = new Vehiculo();

        Cursor auxCursor = db.rawQuery("SELECT * FROM vehiculo "
                + "WHERE patente = '" + patente + "';",null);

        auxCursor.moveToFirst();
        if(auxCursor != null)
        {
            auxVehiculo.setPatente(auxCursor.getString(0));
            auxVehiculo.setTipo(auxCursor.getString(1));
            auxVehiculo.setMarca(auxCursor.getString(2));
            auxVehiculo.setModelo(auxCursor.getString(3));
        }
        else
        {
            auxVehiculo.setPatente("");
            auxVehiculo.setTipo("");
            auxVehiculo.setMarca("");
            auxVehiculo.setModelo("");
        }
        auxCursor.close();
        db.close();
        return auxVehiculo;
    }
    //Fin C.R.U.D vehiculo

    //Inicio C.R.U.D venta

    public void insertarVenta(Venta venta)
    {
        SQLiteDatabase db= getWritableDatabase();

        if(db != null)
        {
            db.execSQL("INSERT INTO venta "
                    + "(fecha, nombre, tipo, valor)"
                    + "VALUES ("
                    + "'" + venta.getFecha() + "',"
                    + "'" + venta.getNombre() + "',"
                    + "'" + venta.getTipo() + "',"
                    + "'" + venta.getValor() + "');");
        }

        db.close();
    }

    public void eliminarVenta(String id)
    {
        SQLiteDatabase db= getWritableDatabase();

        if(db != null)
        {
            db.execSQL("DELETE FROM venta "
                    + "WHERE id = '" + id + "';");
        }

        db.close();
    }

    public Venta buscarVenta(String id)
    {
        SQLiteDatabase db= getWritableDatabase();
        Venta auxVenta = new Venta();

        Cursor auxCursor = db.rawQuery("SELECT * FROM venta "
                + "WHERE id = '" + id + "';",null);

        auxCursor.moveToFirst();
        if(auxCursor != null)
        {
            auxVenta.setId(auxCursor.getString(0));
            auxVenta.setNombre(auxCursor.getString(1));
            auxVenta.setTipo(auxCursor.getString(2));
            auxVenta.setFecha(auxCursor.getString(3));
            auxVenta.setValor(auxCursor.getString(3));
        }
        else
        {
            auxVenta.setId("");
            auxVenta.setNombre("");
            auxVenta.setTipo("");
            auxVenta.setFecha("");
            auxVenta.setValor("");
        }
        auxCursor.close();
        db.close();
        return auxVenta;
    }
//Fin C.R.U.D venta

    public void ejecutarSentencia(String sentencia)
        {
            SQLiteDatabase db = getWritableDatabase();

            if(db != null)
            {
                db.execSQL(sentencia);
            }

            db.close();
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

    public List<Cliente> retornaCliente()
    {
        SQLiteDatabase db= getWritableDatabase();

        List<Cliente> auxListaCliente = new ArrayList<>();
        Cursor auxCursor = db.rawQuery("SELECT * FROM cliente;", null);

        auxCursor.moveToFirst();

        do {
            Cliente auxCliente = new Cliente();
            auxCliente.setNombre(auxCursor.getString(0));
            auxCliente.setApellido(auxCursor.getString(1));
            auxCliente.setDireccion(auxCursor.getString(2));
            auxCliente.setCodigo(auxCursor.getString(3));
            auxListaCliente.add(auxCliente);

        }while (auxCursor.moveToNext());

        auxCursor.close();
        db.close();
        return auxListaCliente;


    }



}
