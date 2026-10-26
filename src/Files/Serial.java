package Files;

import Models.Jugador;

import java.io.*;
import java.util.List;

public class Serial {
    //Serial
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



}
