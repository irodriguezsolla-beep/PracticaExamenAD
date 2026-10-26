import Connector.DBconnector;
import Models.Jugador;
import Models.MercadoFichajes;
import Services.JugadorServices;
import Services.MercadoFichajesServices;
import Files.Serial;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //Crear Jugadores
    Jugador j1 = new Jugador("Lionel Messi","Delantero",1987," Leyenda do fútbol mundial, especialista en regates, pases e lanzamentos de falta.");
    Jugador j2 = new Jugador("Kylian Mbappé","Delantero",1998," Delantero de gran velocidade e potencia anotadora no área rival.");
    Jugador j3 = new Jugador("Erling Haaland","Delantero", 2000,"  Delantero centro con gran capacidade goleadora e impoñente físico.");
    Jugador j4 = new Jugador("Jude Bellingham","Centrocampista", 2003,"  Centrocampista todoterreno con gran capacidade de chegada ao área.");
    Jugador j5 = new Jugador("Luka Modrić","Centrocampista",1985,"  Centrocampista organizador de xogo, visión táctica e calidade técnica.");

    //Añadir a Jugadores
    JugadorServices.agregarJugador(j1);
    JugadorServices.agregarJugador(j2);
    JugadorServices.agregarJugador(j3);
    JugadorServices.agregarJugador(j4);
    JugadorServices.agregarJugador(j5);

    //Liestar jugadores
    List<Jugador> Jugadores =JugadorServices.listarJugador();
    for (Jugador j: Jugadores){
        System.out.println("Id:"+ j.getId());
    }

    //Añadir Mercado
    MercadoFichajes m1 = new MercadoFichajes(Jugadores.get(0).getId(),50.00,80.00,10);
    MercadoFichajes m2 = new MercadoFichajes(Jugadores.get(1).getId(),120.00,180.00 ,12);
    MercadoFichajes m3 = new MercadoFichajes(Jugadores.get(2).getId(),110.00,170.00,15);
    MercadoFichajes m4 = new MercadoFichajes(Jugadores.get(3).getId(),90.00,130.00,8);
    MercadoFichajes m5 = new MercadoFichajes(Jugadores.get(4).getId(),10.00,20.00,5);

    MercadoFichajesServices.agragarMercado(m1);
    MercadoFichajesServices.agragarMercado(m2);
    MercadoFichajesServices.agragarMercado(m3);
    MercadoFichajesServices.agragarMercado(m4);
    MercadoFichajesServices.agragarMercado(m5);


    //Serializar
    String rutaSerializado = "JugadoresSerealiazados";
    Serial.Serial(Jugadores,rutaSerializado);



    System.out.println("OFERTAS DE FICHAXES");
    List<MercadoFichajes> mercado = MercadoFichajesServices.listarMercado();
    for (MercadoFichajes m: mercado){
        m.setPorcentajeComision(m.getPorcentajeComision()+15);
        MercadoFichajesServices.updateMercado(m);
        System.out.println("Actualizado: "+ m.getId());
    }

    Jugador Messi = JugadorServices.readJugadorNome("Lionel Messi");
    MercadoFichajes M = MercadoFichajesServices.readPreciojugador(Messi.getId());
    System.out.println(Messi.getNome()+": "+ "Precentaje: " + M.getPorcentajeComision());



    List<Jugador> jugaores = Serial.DeSerial(rutaSerializado);
    for (Jugador j:jugaores){
        System.out.println(j.getNome());
    }
    Serial.escribirJugadoresEnTxt(jugaores,"jugadores.txt");


}
