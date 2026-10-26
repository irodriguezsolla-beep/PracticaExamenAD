package Models;

public class MercadoFichajes {
    int id;
    int idXogador;
    double PrecioBase;
    double PrecioClausula;
    int PorcentajeComision;

    public MercadoFichajes(int id,int idXogador,double PrecioBase, double PrecioClausula, int PorcentajeComision){
        this.id = id;
        this.idXogador = idXogador;
        this.PrecioBase = PrecioBase;
        this.PrecioClausula = PrecioClausula;
        this.PorcentajeComision = PorcentajeComision;
    }

    public MercadoFichajes(int idXogador,double PrecioBase, double PrecioClausula, int PorcentajeComision){
        this.idXogador = idXogador;
        this.PrecioBase = PrecioBase;
        this.PrecioClausula = PrecioClausula;
        this.PorcentajeComision = PorcentajeComision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdXogador() {
        return idXogador;
    }

    public void setIdXogador(int idXogador) {
        this.idXogador = idXogador;
    }

    public double getPrecioBase() {
        return PrecioBase;
    }

    public void setPrecioBase(double precioBase) {
        PrecioBase = precioBase;
    }

    public double getPrecioClausula() {
        return PrecioClausula;
    }

    public void setPrecioClausula(double precioClausula) {
        PrecioClausula = precioClausula;
    }

    public int getPorcentajeComision() {
        return PorcentajeComision;
    }

    public void setPorcentajeComision(int porcentajeComision) {
        PorcentajeComision = porcentajeComision;
    }
}

