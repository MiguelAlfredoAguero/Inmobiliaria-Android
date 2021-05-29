package com.example.plantilla.modelo;

import com.example.plantilla.modelo.auxiliar.Persona;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int inquilinoId;
    private Long dni;
    private String nombre;
    private String apellido;
    private String lugarDeTrabajo;
    private String email;
    private String telefono;
    private String garanteNombre;
    private String garanteTelefono;

    private Persona persona;

    public Inquilino() {}

    public Inquilino(int inquilinoId, Long dni, String nombre, String apellido, String lugarDeTrabajo, String email, String telefono, String garanteNombre, String garanteTelefono, Persona persona) {
        this.inquilinoId = inquilinoId;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.lugarDeTrabajo = lugarDeTrabajo;
        this.email = email;
        this.telefono = telefono;
        this.garanteNombre = garanteNombre;
        this.garanteTelefono = garanteTelefono;

        this.persona = persona;
    }

    public int getInquilinoId() {
        return inquilinoId;
    }

    public void setInquilinoId(int inquilinoId) {
        this.inquilinoId = inquilinoId;
    }

    public Long getdni() {
        return dni;
    }

    public void setdni(Long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLugarDeTrabajo() {
        return lugarDeTrabajo;
    }

    public void setLugarDeTrabajo(String lugarDeTrabajo) {
        this.lugarDeTrabajo = lugarDeTrabajo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGaranteNombre() {
        return garanteNombre;
    }

    public void setGaranteNombre(String garanteNombre) {
        this.garanteNombre = garanteNombre;
    }

    public String getGaranteTelefono() {
        return garanteTelefono;
    }

    public void setGaranteTelefono(String garanteTelefono) {
        this.garanteTelefono = garanteTelefono;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
