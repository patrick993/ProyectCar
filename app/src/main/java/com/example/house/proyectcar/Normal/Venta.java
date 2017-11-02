package com.example.house.proyectcar.Normal;

/**
 * Created by house on 01-11-2017.
 */

public class Venta {

    private String id;
    private String fecha;
    private Cliente nombre;
    private Vehiculo tipo;
    private String valor;
    private String detalle;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getNombre() {
        return nombre;
    }

    public void setNombre(Cliente nombre) {
        this.nombre = nombre;
    }

    public Vehiculo getTipo() {
        return tipo;
    }

    public void setTipo(Vehiculo tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
