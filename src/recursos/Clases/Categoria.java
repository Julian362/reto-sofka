
package recursos.Clases;


public class Categoria {
    
    private String Nombre;
    private int Nivel;

    public Categoria() {
    }

    
    public Categoria(String Nombre, int Nivel) {
        this.Nombre = Nombre;
        this.Nivel = Nivel;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getNivel() {
        return Nivel;
    }

    public void setNivel(int Nivel) {
        this.Nivel = Nivel;
    }
    
    
}
