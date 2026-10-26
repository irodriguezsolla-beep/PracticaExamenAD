package Files;

import Models.Jugador;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
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

    //XML
    public static void toXML(List<Jugador> listaJugadores, String ruta) {
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        try{
            FileWriter fw = new FileWriter(ruta);
            XMLStreamWriter xsw = xof.createXMLStreamWriter(fw);
            xsw.writeStartDocument("UTF-8", "1.0");
            xsw.writeCharacters("\n");

            xsw.writeStartElement("Jugadores");
            xsw.writeCharacters("\n");

            for (Jugador j : listaJugadores) {
                xsw.writeCharacters("  ");
                xsw.writeStartElement("Jugador");
                xsw.writeAttribute("id", String.valueOf(j.getId()));
                xsw.writeCharacters("\n");

                xsw.writeCharacters("    ");
                xsw.writeStartElement("Nome");
                xsw.writeCharacters(j.getNome() != null ? j.getNome() : "");
                xsw.writeEndElement();
                xsw.writeCharacters("\n");

                xsw.writeCharacters("    ");
                xsw.writeStartElement("Posición");
                xsw.writeCharacters(j.getPosicion() != null ? j.getPosicion() : "");
                xsw.writeEndElement();
                xsw.writeCharacters("\n");

                xsw.writeCharacters("    ");
                xsw.writeStartElement("AnoNacemento");
                xsw.writeCharacters(String.valueOf(j.getAnoNacemento()));
                xsw.writeEndElement();
                xsw.writeCharacters("\n");

                xsw.writeCharacters("    ");
                xsw.writeStartElement("descripcion");
                xsw.writeCharacters(j.getDescripcion() != null ? j.getDescripcion() : "");
                xsw.writeEndElement();
                xsw.writeCharacters("\n");

                xsw.writeCharacters("  ");
                xsw.writeEndElement();
                xsw.writeCharacters("\n");
            }

            xsw.writeEndElement();
            xsw.writeCharacters("\n");
            xsw.writeEndDocument();

            xsw.flush();
            xsw.close();
            fw.close();
            System.out.println("Arquivo XML xerado correctamente en: " + ruta);

        } catch (Exception e) {
            System.err.println("Erro : " + e.getMessage());
        }

    }

}
