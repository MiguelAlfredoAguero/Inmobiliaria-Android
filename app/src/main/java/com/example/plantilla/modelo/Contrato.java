package com.example.plantilla.modelo;

import com.example.plantilla.modelo.auxiliar.Garante;

import java.io.Serializable;
import java.util.Objects;

public class Contrato implements Serializable {

    private int id;
    private String desde;
    private String hasta;
    private double precio;
    private Inquilino inquilino;
    private Inmueble inmueble;
    private Garante garante;

    public Contrato() {}
    public Contrato(int id, String desde, String hasta, double precio, Inquilino inquilino, Inmueble inmueble, Garante garante) {
        this.id = id;
        this.desde = desde;
        this.hasta = hasta;
        this.precio = precio;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
        this.garante = garante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Garante getGarante() {
        return garante;
    }

    public void setGarante(Garante garante) {
        this.garante = garante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return id == contrato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
