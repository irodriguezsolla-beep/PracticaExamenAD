package Services;

import Connector.DBconnector;
import Models.MercadoFichajes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MercadoFichajesServices {

    // Engadir
    public static void agragarMercado(MercadoFichajes m) {
        // Engadido IdXogador e homoxeneizados os nomes das columnas (PrezoBase, etc.)
        String sql = "INSERT INTO MercadoFichajes (IdXogador, PrezoBase, PrezoClausula, PorcentaxeComision) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBconnector.conexion();
             PreparedStatement insert = conn.prepareStatement(sql)) {

            insert.setInt(1, m.getIdXogador());
            insert.setDouble(2, m.getPrecioBase());
            insert.setDouble(3, m.getPrecioClausula());
            insert.setInt(4, m.getPorcentajeComision());

            insert.executeUpdate();
            System.out.println("Engadido ao mercado de fichaxes.");
        } catch (SQLException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
    }

    // Update
    public static void updateMercado(MercadoFichajes m) {
        // Sintaxe de UPDATE corrixida con cláusula WHERE
        String sql = "UPDATE MercadoFichajes SET IdXogador = ?, PrezoBase = ?, PrezoClausula = ?, PorcentaxeComision = ? WHERE Id = ?";

        try (Connection conn = DBconnector.conexion();
             PreparedStatement update = conn.prepareStatement(sql)) {

            update.setInt(1, m.getIdXogador());
            update.setDouble(2, m.getPrecioBase());
            update.setDouble(3, m.getPrecioClausula());
            update.setInt(4, m.getPorcentajeComision());
            update.setInt(5, m.getId());

            update.executeUpdate();
            System.out.println("Actualización do mercado completada.");
        } catch (SQLException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
    }

    // Listar
    public static List<MercadoFichajes> listarMercado() {
        // Retirado o 'VALUES (?, ?, ...)' que era sintaxe SQL inválida nun SELECT
        String sql = "SELECT * FROM MercadoFichajes";
        List<MercadoFichajes> toRet = new ArrayList<>();

        try (Connection conn = DBconnector.conexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                toRet.add(new MercadoFichajes(
                        rs.getInt("Id"),
                        rs.getInt("IdXogador"),
                        rs.getDouble("PrezoBase"),
                        rs.getDouble("PrezoClausula"),
                        rs.getInt("PorcentaxeComision")
                ));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
        return toRet;
    }

    // Delete
    public static void borrarMercado(MercadoFichajes m) {
        // Cambiado WHERE IdXogador por WHERE Id
        String sql = "DELETE FROM MercadoFichajes WHERE Id = ?";

        try (Connection conn = DBconnector.conexion();
             PreparedStatement delete = conn.prepareStatement(sql)) {

            delete.setInt(1, m.getId());

            delete.executeUpdate();
            System.out.println("Borrado do mercado completado.");
        } catch (SQLException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
    }

    // Leer por ID
    public static MercadoFichajes readPreciojugador(int id) {
        String sql = "SELECT * FROM MercadoFichajes WHERE Id = ?";

        try (Connection conn = DBconnector.conexion();
             PreparedStatement toRead = conn.prepareStatement(sql)) {

            // Asignar o parámetro ANTES de executar o ResultSet
            toRead.setInt(1, id);

            try (ResultSet rs = toRead.executeQuery()) {
                if (rs.next()) {
                    return new MercadoFichajes(
                            rs.getInt("Id"),
                            rs.getInt("IdXogador"),
                            rs.getDouble("PrezoBase"),
                            rs.getDouble("PrezoClausula"),
                            rs.getInt("PorcentaxeComision")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
        return null; // Devolve null se non existe o rexistro
    }
}
