package Connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnector {
    public static Connection conexion() {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://192.168.68.107:5432/probas";
            String usuario="postgres";
            String contrasena ="admin";

            conn = DriverManager.getConnection(url,usuario , contrasena );
        } catch (Exception e) {
            System.out.println("Erro na conexión: "+ e.getMessage());
        }
        return conn;
    }
}