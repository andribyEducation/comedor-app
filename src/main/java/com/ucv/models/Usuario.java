package com.ucv.models;

public class Usuario {
    private String cedula;
    private String correo;
    private String contraseña;

    public Usuario(String cedula, String correo, String contraseña) {
        this.cedula = cedula;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    // Getters
    public String getCedula() {
        return cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    // Setters
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
