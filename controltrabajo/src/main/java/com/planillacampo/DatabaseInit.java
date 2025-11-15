package com.planillacampo;

public class DatabaseInit {

    /**
     * Método público que el main invoca para asegurarse de que las tablas existan.
     */
    public static void crearTablas() {
        CrearTablas.crear();
    }
}
