package com.example.house.proyectcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.house.proyectcar.Clases.Cliente;

import java.util.Iterator;
import java.util.List;

public class ClienteMantenedor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_mantenedor);
    }

    public void guardarCliente(Cliente cliente)
    {
        try {
            EditText auxNombre = (EditText) findViewById(R.id.txt_nombre);
            EditText auxApellido = (EditText) findViewById(R.id.txt_apellido);
            EditText auxDireccion = (EditText) findViewById(R.id.txt_direccion);
            EditText auxCodigo = (EditText) findViewById(R.id.txt_codigo);

            Cliente auxCliente = new Cliente();
            auxCliente.setNombre(auxNombre.getText().toString());
            auxCliente.setApellido(auxApellido.getText().toString());
            auxCliente.setDireccion(auxDireccion.getText().toString());
            auxCliente.setCodigo(auxCodigo.getText().toString());

            OpenHelperDB auxDb = new OpenHelperDB(this);

            auxDb.insertarDatos(auxCliente);
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

    public void eliminarCliente(String codigo)
    {
        try {
            EditText auxNombre = (EditText) findViewById(R.id.txt_nombre);
            EditText auxApellido = (EditText) findViewById(R.id.txt_apellido);
            EditText auxDireccion = (EditText) findViewById(R.id.txt_direccion);
            EditText auxCodigo = (EditText) findViewById(R.id.txt_codigo);

            Cliente auxCliente = new Cliente();
            auxCliente.setNombre(auxNombre.getText().toString());
            auxCliente.setApellido(auxApellido.getText().toString());
            auxCliente.setDireccion(auxDireccion.getText().toString());
            auxCliente.setCodigo(auxCodigo.getText().toString());

            OpenHelperDB auxDb = new OpenHelperDB(this);

            auxDb.eliminarCliente(codigo);
            this.mensaje("Cliente eliminado");
            auxNombre.setText("");
            auxApellido.setText("");
            auxDireccion.setText("");
            auxCodigo.setText("");

            auxNombre.requestFocus();

        }catch (Exception ex)
        {
            this.mensaje("Error al eliminar cliente(" + ex.getMessage() + ")");
        }
    }

    public void modificarCliente()
    {
        try {
            EditText auxNombre = (EditText) findViewById(R.id.txt_nombre);
            EditText auxApellido = (EditText) findViewById(R.id.txt_apellido);
            EditText auxDireccion = (EditText) findViewById(R.id.txt_direccion);
            EditText auxCodigo = (EditText) findViewById(R.id.txt_codigo);

            Cliente auxCliente = new Cliente();
            auxCliente.setNombre(auxNombre.getText().toString());
            auxCliente.setApellido(auxApellido.getText().toString());
            auxCliente.setDireccion(auxDireccion.getText().toString());
            auxCliente.setCodigo(auxCodigo.getText().toString());

            OpenHelperDB auxDb = new OpenHelperDB(this);

            auxDb.actualizarCliente(auxCliente);
            this.mensaje("Cliente modificado");
            auxNombre.setText("");
            auxApellido.setText("");
            auxDireccion.setText("");
            auxCodigo.setText("");

            auxNombre.requestFocus();

        }catch (Exception ex)
        {
            this.mensaje("Error al modificar cliente(" + ex.getMessage() + ")");
        }
    }

    public void listarCliente()
    {
        OpenHelperDB auxNegocio = new OpenHelperDB(this);

        List<Cliente> auxLista = auxNegocio.retornaCliente();

        String[] listaString = new String[auxLista.size()];

        Iterator iter = auxLista.iterator();

        int pos = 0;

        while (iter.hasNext())
        {
            Cliente auxCliente = new Cliente();
            auxCliente =(Cliente)iter.next();

            listaString[pos] = auxCliente.getNombre() + " " + auxCliente.getApellido() + " " + auxCliente.getDireccion() + " " + auxCliente.getCodigo() ;
            pos++;
        }

        ListView auxListView = (ListView) findViewById(R.id.listaCliente);

        auxListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaString));
    }

    public void mensaje(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}

