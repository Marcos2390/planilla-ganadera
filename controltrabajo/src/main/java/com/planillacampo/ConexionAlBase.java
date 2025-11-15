package com.planillacampo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionAlBase {
    private static final String URL = "jdbc:sqlite:C:/Users/marco/Desktop/planilla rural/controltrabajo/planilla.db";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.out.println("❌ Error conectando a la base: " + e.getMessage());
            return null;
        }
    }
}
