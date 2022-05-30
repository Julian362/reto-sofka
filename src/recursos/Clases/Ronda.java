
package recursos.Clases;

import java.util.ArrayList;
import java.util.Iterator;


public class Ronda {
    
    
ArrayList<Pregunta> Preguntas;
ArrayList<Categoria> Categorias;
ArrayList<Premio> Premios;
Jugador Historico;

    public Ronda(ArrayList<Pregunta> Preguntas, ArrayList<Categoria> Categorias) {
        this.Preguntas = Preguntas;
        this.Categorias = Categorias;
    }

    public Pregunta[] preguntasAzar(){
        Pregunta[] preguntasSeleccionadas = new Pregunta[5];
        int i=0;
        Iterator<Pregunta> IteratorPreguntas = Preguntas.iterator();
        ArrayList<Pregunta> auxPregunta = new ArrayList<Pregunta>();
        Pregunta pregunta = IteratorPreguntas.next();
        while (IteratorPreguntas.hasNext()) {
            Pregunta preguntaSiguiente = IteratorPreguntas.next();
            if(((pregunta.getNivel()== preguntaSiguiente.getNivel())&&(pregunta.getNivel()==(i+1)))){
                if(!(auxPregunta.contains(pregunta))){
                auxPregunta.add(pregunta);
                }
                auxPregunta.add(preguntaSiguiente);
            }
            else{
                int numeroAzar = (int) Math.floor(Math.random()*auxPregunta.size());
                preguntasSeleccionadas[i]= auxPregunta.get(numeroAzar);
                i++;
                auxPregunta = new ArrayList<Pregunta>();
            }
            pregunta=preguntaSiguiente;        
        }
        int numeroAzar = (int) Math.floor(Math.random()*auxPregunta.size());
                preguntasSeleccionadas[i]= auxPregunta.get(numeroAzar);
        return preguntasSeleccionadas;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return Preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> Preguntas) {
        this.Preguntas = Preguntas;
    }

    public ArrayList<Categoria> getCategorias() {
        return Categorias;
    }

    public void setCategorias(ArrayList<Categoria> Categorias) {
        this.Categorias = Categorias;
    }

    public ArrayList<Premio> getPremios() {
        return Premios;
    }

    public void setPremios(ArrayList<Premio> Premios) {
        this.Premios = Premios;
    }

    public Jugador getHistorico() {
        return Historico;
    }

    public void setHistorico(Jugador Historico) {
        this.Historico = Historico;
    }
    
    
    
}
