package MetodosOrdenacion.Combinacion;

public class RecuentoCategoria {
    private String nombre;
    private int recuento;
    private double puntuacionTotal;

    public RecuentoCategoria(String nombre, int recuento) {
        this.nombre = nombre;
        this.recuento = recuento;
    }

    public void aumentaRecuento() {
        this.recuento++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPuntuacionTotal(){
        if (recuento <= 25){
            puntuacionTotal = 0.0;
        }
        else {
            if (recuento <= 35){
                puntuacionTotal = 0.1/3;
            }
            else {
                if (recuento <= 45){
                    puntuacionTotal = 0.2/3;
                }
                else {
                    puntuacionTotal = 0.1;
                }
            }
        }
    }

    public double getPuntuacionTotal() {
        return puntuacionTotal;
    }
}
