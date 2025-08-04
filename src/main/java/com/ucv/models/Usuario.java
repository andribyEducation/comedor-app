package com.ucv.models;

public class Usuario {
    private String cedula;
    private String correo;
    private String tipo; 
    private double saldo = -1;

    private static Usuario usuarioActual; // Instancia global

    public Usuario(String cedula, String correo, String tipo) {
        this.cedula = cedula;
        this.correo = correo;
        this.tipo = tipo;
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

<<<<<<< HEAD
    public int getSaldo() {
        return saldo;
=======
    public String getID() {
        return cedula;
    }

    public double getSaldo() {
        if(saldo != -1) {
            return saldo;
        } else {
            return 0; // Retorna 0 si el saldo no está definido
        }
>>>>>>> 4c1f5cc236c4cc52a8eac7d2fb57e7ca08d44111
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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
