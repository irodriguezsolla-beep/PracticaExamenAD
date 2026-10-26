[Simulacro Exame Acceso a Datos - Futbol.pdf](File/Simulacro%20Exame%20Acceso%20a%20Datos%20-%20Futbol.pdf)

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

public static void Serial(List<Jugador> Jugadores,String ruta){
File file = new File(ruta);

        try (FileOutputStream FoS = new FileOutputStream(file);
             ObjectOutputStream OoS = new ObjectOutputStream(FoS);){

            OoS.writeObject(Jugadores);
            System.out.println("Serial guardado");

        }catch (Exception e){
            System.out.println("[ERROR]: " + e);
        }
    }
    //Deserial
    public static List<Jugador> DeSerial(String ruta){
        File file = new File(ruta);
        List<Jugador> ds = null;

        try (FileInputStream FiS = new FileInputStream(file);
             ObjectInputStream OiS = new ObjectInputStream(FiS)){

            ds = (List<Jugador>) OiS.readObject();
            System.out.println("Serial extraido");

        }catch (Exception e){
            System.out.println("[ERROR]: " + e);
        }
        return ds;
    }

    //txt
    public static void escribirJugadoresEnTxt(List<Jugador> listaJugadores, String ruta) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta))){

            for(Jugador j : listaJugadores){
                dos.writeUTF(j.getNome()+","+j.getPosicion()+","+j.getAnoNacemento()+","+j.getDescripcion());
            }
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
List<Jugador> Jugadores =JugadorServices.listarJugador();
for (Jugador j: Jugadores){
System.out.println("Id:"+ j.getId());
}
System.out.println("----------------------------------------");
//Añadir Mercado
MercadoFichajes m1 = new MercadoFichajes(Jugadores.get(0).getId(),50.00,80.00,10);
MercadoFichajes m2 = new MercadoFichajes(Jugadores.get(1).getId(),120.00,180.00 ,12);
MercadoFichajes m3 = new MercadoFichajes(Jugadores.get(2).getId(),110.00,170.00,15);
MercadoFichajes m4 = new MercadoFichajes(Jugadores.get(3).getId(),90.00,130.00,8);
MercadoFichajes m5 = new MercadoFichajes(Jugadores.get(4).getId(),10.00,20.00,5);

public static void lerCadea(String nomeFicheiro) {
File ficheiro = new File(nomeFicheiro);

        if (!ficheiro.exists()) {
            System.out.println("O ficheiro " + nomeFicheiro + " non existe.");
            return;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(ficheiro))) {

            while (dis.available() > 0) {
                System.out.println("quedan: " + dis.available() + " bytes por ler");
                String textoLeido = dis.readUTF();
                System.out.println("cadea: " + textoLeido);
            }

            System.out.println("Xa non queda nada por ler");

        } catch (IOException Error) {
            System.out.println("Erro ao ler do ficheiro: " + Error.getMessage());
        }
    }

public static void copiabyte(String ruta, String fileName, String destFileName){

    File fichero = new File(ruta,fileName);

    if (fichero.exists()){
        File ficheroDestino = new File(ruta, destFileName);
        try( FileInputStream entrada = new FileInputStream(fichero);
             FileOutputStream salida = new FileOutputStream(ficheroDestino)){

            int byteLeido;
            while ((byteLeido = entrada.read()) != -1) {
                salida.write(byteLeido);
            }

            System.out.println("Ficheiro copiado correctamente en: " + destFileName);

        }catch (IOException e){
            System.out.println("Erro ao copiar o ficheiro: " + e.getMessage());
        }
    }else{
        System.out.println("O ficheiro " + fileName + " non existe na ruta: " + ruta);
    }

}