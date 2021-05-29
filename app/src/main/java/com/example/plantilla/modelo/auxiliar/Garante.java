package com.example.plantilla.modelo.auxiliar;

public class Garante {
    private int Id;
    private Persona persona;
    private boolean activo;

    public Garante() {
    }

    public Garante(int id, Persona persona, boolean activo) {
        Id = id;
        this.persona = persona;
        this.activo = activo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
