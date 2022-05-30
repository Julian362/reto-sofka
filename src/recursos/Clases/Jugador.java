
package recursos.Clases;


public class Jugador {
    
    private String Nombre="";
    private int Puntaje;

    public Jugador(String Nombre, int Puntaje) {
        this.Nombre = Nombre;
        this.Puntaje = Puntaje;
    }

    public Jugador() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(int Puntaje) {
        this.Puntaje = Puntaje;
    }
    
    
}
