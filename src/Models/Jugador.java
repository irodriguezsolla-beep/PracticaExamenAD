package Models;

import java.io.Serializable;

public class Jugador implements Serializable {
    int id;
    String Nome;
    String Posicion;
    int AnoNacemento;
    String Descripcion;

    public Jugador(int id, String Nome, String Posicion, int AnoNacemento, String Descripcion){
        this.id = id;
        this.Nome = Nome;
        this.Posicion = Posicion;
        this.AnoNacemento = AnoNacemento;
        this.Descripcion = Descripcion;
    }

    public Jugador(String Nome, String Posicion, int AnoNacemento, String Descripcion){
        this.Nome = Nome;
        this.Posicion = Posicion;
        this.AnoNacemento = AnoNacemento;
        this.Descripcion = Descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getPosicion() {
        return Posicion;
    }

    public void setPosicion(String posicion) {
        Posicion = posicion;
    }

    public int getAnoNacemento() {
        return AnoNacemento;
    }

    public void setAnoNacemento(int anoNacemento) {
        AnoNacemento = anoNacemento;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
