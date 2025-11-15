package com.planillacampo;

public class Animales {
    private int numerocaravana;
    private String categoria;
    private String raza;
    private int edad;

    public Animales(int numerocaravana, String categoria, String raza, int edad) {
        this.numerocaravana = numerocaravana;
        this.categoria = categoria;
        this.raza = raza;
        this.edad = edad;
    }

    public int getNumerocaravana() {
        return numerocaravana;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "Numero de caravana " + numerocaravana + ", Categoria: " + categoria + ", Raza: " + raza + ", Edad: "
                + edad;
    }
}