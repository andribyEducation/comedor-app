package com.ucv.models;

public class Usuario {
    private String cedula;
    private String correo;
    private String tipo; 
    private String rol;
    private String nombre;
    private String apellido;
    private double saldo = 0.0;

    private static Usuario usuarioActual; // Instancia global

    public Usuario(String cedula, String correo, String tipo, String nombre, String apellido) {
        this.cedula = cedula;
        this.correo = correo;
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Getters
    public String getCedula() {
        return cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getID() {
        return cedula;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    // Métodos estáticos para la instancia global
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cedula='" + cedula + '\'' +
                ", correo='" + correo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
