package com.example.house.proyectcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.house.proyectcar.Clases.Vehiculo;

public class VehiculoMantenedor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo_mantenedor);
    }

    public void guardarVehiculo(Vehiculo vehiculo)
    {
        try {
            EditText auxNombre = (EditText) findViewById(R.id.txt_nombre);
            EditText auxApellido = (EditText) findViewById(R.id.txt_apellido);
            EditText auxDireccion = (EditText) findViewById(R.id.txt_direccion);
            EditText auxCodigo = (EditText) findViewById(R.id.txt_codigo);

            Vehiculo auxVehiculo = new Vehiculo();
            auxVehiculo.setTipo(auxNombre.getText().toString());
            auxVehiculo.setMarca(auxApellido.getText().toString());
            auxVehiculo.setModelo(auxDireccion.getText().toString());
            auxVehiculo.setPatente(auxCodigo.getText().toString());

            OpenHelperDB auxDb = new OpenHelperDB(this);

            auxDb.insertarVehiculo(auxVehiculo);
            this.mensaje("Cliente guardado");
            auxNombre.setText("");
            auxApellido.setText("");
            auxDireccion.setText("");
            auxCodigo.setText("");

            auxNombre.requestFocus();

        }catch (Exception ex)
        {
            this.mensaje("Error al guardar cliente(" + ex.getMessage() + ")");
        }
    }

    public void eliminarVehiculo(String patente)
    {

    }

    public void modificarVehiculo()
    {

    }

    public void listarVehiculos()
    {

    }

    public void mensaje(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
