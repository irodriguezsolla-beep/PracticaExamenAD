package Services;

import Connector.DBconnector;
import Models.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JugadorServices {

    // Engadir
    public static void agregarJugador(Jugador j) {
        String sql = "INSERT INTO Xogador (Nome, Posicion, AnoNacemento, Descricion) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBconnector.conexion();
             PreparedStatement insert = conn.prepareStatement(sql)) {

            insert.setString(1, j.getNome());
            insert.setString(2, j.getPosicion());
            insert.setInt(3, j.getAnoNacemento());
            insert.setString(4, j.getDescripcion());

            insert.executeUpdate();
            System.out.println("Xogador engadido correctamente.");
        } catch (SQLException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
    }

    // Update
    public static void updateJugador(Jugador j) {
        // Sintaxe correcta de UPDATE con cláusula WHERE
        String sql = "UPDATE Xogador SET Nome = ?, Posicion = ?, AnoNacemento = ?, Descricion = ? WHERE Id = ?";

        try (Connection conn = DBconnector.conexion();
             PreparedStatement update = conn.prepareStatement(sql)) {

            update.setString(1, j.getNome());
            update.setString(2, j.getPosicion());
            update.setInt(3, j.getAnoNacemento());
            update.setString(4, j.getDescripcion());
            update.setInt(5, j.getId());

            update.executeUpdate();
            System.out.println("Actualización completada.");
        } catch (SQLException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
    }

    // Listar
    public static List<Jugador> listarJugador() {
        String sql = "SELECT * FROM Xogador";
        List<Jugador> toRet = new ArrayList<>();

        try (Connection conn = DBconnector.conexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                toRet.add(new Jugador(
                        rs.getInt("Id"),
                        rs.getString("Nome"),
                        rs.getString("Posicion"),
                        rs.getInt("AnoNacemento"),
                        rs.getString("Descricion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
        return toRet;
    }

    // Delete
    public static void borrarJugador(Jugador j) {
        // Cambiado a cláusula WHERE para borrar por ID
        String sql = "DELETE FROM Xogador WHERE Id = ?";

        try (Connection conn = DBconnector.conexion();
             PreparedStatement delete = conn.prepareStatement(sql)) {

            delete.setInt(1, j.getId());

            delete.executeUpdate();
            System.out.println("Borrado completado.");
        } catch (SQLException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
    }

    // Read por Nome
    public static Jugador readJugadorNome(String nome) {
        String sql = "SELECT * FROM Xogador WHERE Nome = ?";

        try (Connection conn = DBconnector.conexion();
             PreparedStatement toRead = conn.prepareStatement(sql)) {

            // Asignar o parámetro ANTES de executar a consulta
            toRead.setString(1, nome);

            try (ResultSet rs = toRead.executeQuery()) {
                if (rs.next()) {
                    return new Jugador(
                            rs.getInt("Id"),
                            rs.getString("Nome"),
                            rs.getString("Posicion"),
                            rs.getInt("AnoNacemento"),
                            rs.getString("Descricion")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
        return null; // Devolve null se non se atopa nada
    }
}