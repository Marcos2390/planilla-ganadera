package com.planillacampo;

import java.sql.Connection;
import java.sql.Statement;

public class CrearTablas {

    public static void crear() {
        try (Connection conn = ConexionAlBase.conectar();
                Statement stmt = conn.createStatement()) {

            // Activar integridad referencial en SQLite
            stmt.execute("PRAGMA foreign_keys = ON;");

            // Tabla Animales (con id autoincrement y numeroCaravana como campo propio)
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS Animales (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            numeroCaravana INTEGER,
                            categoria TEXT,
                            raza TEXT,
                            edad INTEGER
                        );
                    """);

            // Tabla Sanidad (referencia al id de Animales)
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS Sanidad (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            idAnimal INTEGER NOT NULL,
                            vacuna TEXT,
                            fecha TEXT,
                            FOREIGN KEY(idAnimal) REFERENCES Animales(id)
                        );
                    """);

            // Tabla Movimientos (referencia al id de Animales)
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS Movimientos (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            idAnimal INTEGER NOT NULL,
                            tipo TEXT,
                            destino TEXT,
                            fecha TEXT,
                            FOREIGN KEY(idAnimal) REFERENCES Animales(id)
                        );
                    """);

            System.out.println("✔ Tablas creadas/verificadas correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Error creando tablas: " + e.getMessage());
        }
    }
}