package com.planillacampo;

public class Movimientos {
    private int idAnimal;
    private String tipo;
    private String destino;
    private String fecha;

    public Movimientos(int idAnimal, String tipo, String destino, String fecha) {
        this.idAnimal = idAnimal;
        this.tipo = tipo;
        this.destino = destino;
        this.fecha = fecha;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDestino() {
        return destino;
    }

    public String getFecha() {
        return fecha;
    }
}