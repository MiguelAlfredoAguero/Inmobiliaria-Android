package com.example.plantilla.modelo;

import com.example.plantilla.modelo.auxiliar.TipoInmueble;
import com.example.plantilla.modelo.auxiliar.UsoInmueble;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;
    private UsoInmueble usoInmueble;
    private TipoInmueble tipoInmueble;
    private int ambientes;
    private double precio;
    private Propietario propietario;
    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.
    private boolean disponible;
    private String avatar;

    public Inmueble(int id, String direccion, UsoInmueble usoInmueble, TipoInmueble tipoInmueble, int ambientes, double precio, Propietario propietario, boolean disponible, String avatar) {
        this.id = id;
        this.direccion = direccion;
        this.usoInmueble = usoInmueble;
        this.tipoInmueble = tipoInmueble;
        this.ambientes = ambientes;
        this.precio = precio;
        this.propietario = propietario;
        this.disponible = disponible;
        this.avatar = avatar;
    }
    public Inmueble() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public UsoInmueble getUsoInmueble() {
        return usoInmueble;
    }

    public void setUsoInmueble(UsoInmueble usoInmueble) {
        this.usoInmueble = usoInmueble;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getavatar() {
        return avatar;
    }

    public void setavatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
