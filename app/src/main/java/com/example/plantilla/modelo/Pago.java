package com.example.plantilla.modelo;

import java.io.Serializable;

public class Pago implements Serializable {

    private int id;
    private int numero;
    private String fecha;
    private double importe;
    private Contrato contrato;

    public Pago() {}

    public Pago(int id, int numero, String fecha, double importe, Contrato contrato) {
        this.id = id;
        this.numero = numero;
        this.fecha = fecha;
        this.importe = importe;
        this.contrato = contrato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

}
