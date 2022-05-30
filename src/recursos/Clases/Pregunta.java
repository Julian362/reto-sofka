
package recursos.Clases;

public class Pregunta extends Categoria{
    
    private String Problema, Respuesta_correcta, Respuesta_incorrecta1, Respuesta_incorrecta2, Respuesta_incorrecta3;
    private int Id;
    public Pregunta(int Id,String Problema, String Respuesta_correcta, String Respuesta_incorrecta1, String Respuesta_incorrecta2, String Respuesta_incorrecta3) {
        this.Id = Id;
        this.Problema = Problema;
        this.Respuesta_correcta = Respuesta_correcta;
        this.Respuesta_incorrecta1 = Respuesta_incorrecta1;
        this.Respuesta_incorrecta2 = Respuesta_incorrecta2;
        this.Respuesta_incorrecta3 = Respuesta_incorrecta3;
    }

    public Pregunta(int Id,String Problema, String Respuesta_correcta, String Respuesta_incorrecta1, String Respuesta_incorrecta2, String Respuesta_incorrecta3, String Nombre, int Nivel) {
        super(Nombre, Nivel);
        this.Id = Id;
        this.Problema = Problema;
        this.Respuesta_correcta = Respuesta_correcta;
        this.Respuesta_incorrecta1 = Respuesta_incorrecta1;
        this.Respuesta_incorrecta2 = Respuesta_incorrecta2;
        this.Respuesta_incorrecta3 = Respuesta_incorrecta3;
    }

    public Pregunta() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }


    public String getProblema() {
        return Problema;
    }

    public void setProblema(String Problema) {
        this.Problema = Problema;
    }

    public String getRespuesta_correcta() {
        return Respuesta_correcta;
    }

    public void setRespuesta_correcta(String Respuesta_correcta) {
        this.Respuesta_correcta = Respuesta_correcta;
    }

    public String getRespuesta_incorrecta1() {
        return Respuesta_incorrecta1;
    }

    public void setRespuesta_incorrecta1(String Respuesta_incorrecta1) {
        this.Respuesta_incorrecta1 = Respuesta_incorrecta1;
    }

    public String getRespuesta_incorrecta2() {
        return Respuesta_incorrecta2;
    }

    public void setRespuesta_incorrecta2(String Respuesta_incorrecta2) {
        this.Respuesta_incorrecta2 = Respuesta_incorrecta2;
    }

    public String getRespuesta_incorrecta3() {
        return Respuesta_incorrecta3;
    }

    public void setRespuesta_incorrecta3(String Respuesta_incorrecta3) {
        this.Respuesta_incorrecta3 = Respuesta_incorrecta3;
    }
    
    
}
