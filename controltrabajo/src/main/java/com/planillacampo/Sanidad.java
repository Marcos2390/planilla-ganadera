package com.planillacampo;

public class Sanidad {
    private int idAnimal;
    private String vacuna;
    private String fecha;

    public Sanidad(int idAnimal, String vacuna, String fecha) {
        this.idAnimal = idAnimal;
        this.vacuna = vacuna;
        this.fecha = fecha;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public String getVacuna() {
        return vacuna;
    }

    public String getFecha() {
        return fecha;
    }
}