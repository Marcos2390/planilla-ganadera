package com.planillacampo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistroService {

    public void agregarAnimal(Animales animal) {
        String sql = "INSERT INTO Animales(numeroCaravana, categoria, raza, edad) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionAlBase.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, animal.getNumerocaravana());
            ps.setString(2, animal.getCategoria());
            ps.setString(3, animal.getRaza());
            ps.setInt(4, animal.getEdad());

            ps.executeUpdate();
            System.out.println("✔ Animal registrado");

        } catch (Exception e) {
            System.out.println("❌ Error al guardar animal: " + e.getMessage());
        }
    }

    public void eliminarAnimal(int numeroCaravana) {
        String sql = "DELETE FROM Animales WHERE numeroCaravana = ?";
        try (Connection con = ConexionAlBase.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numeroCaravana);
            int filas = ps.executeUpdate();

            if (filas > 0)
                System.out.println("✔ Animal eliminado");
            else
                System.out.println("⚠ No existe animal con ese número de caravana");

        } catch (Exception e) {
            System.out.println("❌ Error al eliminar: " + e.getMessage());
        }
    }

    public boolean existeCaravana(int numeroCaravana) {
        String sql = "SELECT COUNT(*) FROM Animales WHERE numeroCaravana = ?";
        try (Connection con = ConexionAlBase.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numeroCaravana);
            ResultSet rs = ps.executeQuery();

            return rs.getInt(1) > 0; // Si es > 0, ya existe

        } catch (Exception e) {
            System.out.println("❌ Error al verificar caravana: " + e.getMessage());
            return false; // en error devolvemos false para no bloquear la app
        }
    }

    public List<Animales> listarAnimales() {
        List<Animales> lista = new ArrayList<>();
        String sql = "SELECT * FROM Animales";

        try (Connection con = ConexionAlBase.conectar();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Animales(
                        rs.getInt("numeroCaravana"),
                        rs.getString("categoria"),
                        rs.getString("raza"),
                        rs.getInt("edad")));
            }

        } catch (Exception e) {
            System.out.println("❌ Error al listar animales: " + e.getMessage());
        }
        return lista;
    }

    public void registrarVacuna(Sanidad s) {
        String sql = "INSERT INTO Sanidad(idAnimal, vacuna, fecha) VALUES (?, ?, ?)";
        try (Connection con = ConexionAlBase.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.getIdAnimal());
            ps.setString(2, s.getVacuna());
            ps.setString(3, s.getFecha());
            ps.executeUpdate();
            System.out.println("✔ Registro sanitario guardado");

        } catch (Exception e) {
            System.out.println("❌ Error al registrar sanidad: " + e.getMessage());
        }
    }

    public void listarSanidad() {
        String sql = "SELECT * FROM Sanidad";

        try (Connection con = ConexionAlBase.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            boolean vacio = true;

            while (rs.next()) {
                vacio = false;
                System.out.println(
                        "N° Caravana: " + rs.getInt("idAnimal") +
                                ", Vacuna: " + rs.getString("vacuna") +
                                ", Fecha: " + rs.getString("fecha"));
            }

            if (vacio)
                System.out.println("No hay registros sanitarios.");

        } catch (Exception e) {
            System.out.println("❌ Error al listar sanidad: " + e.getMessage());
        }
    }

    public void registrarMovimiento(Movimientos m) {
        String sql = "INSERT INTO Movimientos(idAnimal, tipo, destino, fecha) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionAlBase.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getIdAnimal());
            ps.setString(2, m.getTipo());
            ps.setString(3, m.getDestino());
            ps.setString(4, m.getFecha());
            ps.executeUpdate();
            System.out.println("✔ Movimiento registrado");

        } catch (Exception e) {
            System.out.println("❌ Error al registrar movimiento: " + e.getMessage());
        }
    }

    public void listarMovimientos() {
        String sql = "SELECT * FROM Movimientos";

        try (Connection con = ConexionAlBase.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            boolean vacio = true;

            while (rs.next()) {
                vacio = false;
                System.out.println(
                        "N° Caravana: " + rs.getInt("idAnimal") +
                                ", Tipo: " + rs.getString("tipo") +
                                ", Destino: " + rs.getString("destino") +
                                ", Fecha: " + rs.getString("fecha"));
            }

            if (vacio)
                System.out.println("No hay movimientos registrados.");

        } catch (Exception e) {
            System.out.println("❌ Error al listar movimientos: " + e.getMessage());
        }
    }
}
