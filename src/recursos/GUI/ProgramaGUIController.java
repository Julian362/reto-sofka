package recursos.GUI;

import recursos.Data.ManejoBD;
import recursos.Clases.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.TilePane;

public class ProgramaGUIController {
    
    ManejoBD BD = new ManejoBD();
    /*
    @FXML
    private TextField NombreIngresarTextField, IRCAIngresarTextField, TipoDeAguaIngresarTextField,
            TipoDeCuerpoDeAguaIngresarTextField, IDIngresarTextField, MunicipioIngresarTextField, IDBuscaTextField,
            NombreEditTextField, IRCAEditTextField, TipoDeAguaEditTextField, TipoDeCuerpoDeAguaEditTextField,
            IDEditTextField, MunicipioEditTextField;

    @FXML
    private Button BtnIngresarTextField, BtnObtenerDatos, BtnProcesarDatos, EliminarEditTextField, EditarEditTextField;

    @FXML
    private TextArea ObtenerDatosTextArea, ProcesarDatosTextArea;

    @FXML
    void IngresarBtnOnAction(ActionEvent event) {

        if (BD.SELECT(Integer.parseInt(IDIngresarTextField.getText())) == null) {
            BD.INSERT(NombreIngresarTextField.getText(), Integer.parseInt(IDIngresarTextField.getText()),
                    MunicipioIngresarTextField.getText(), TipoDeAguaIngresarTextField.getText(),
                    TipoDeCuerpoDeAguaIngresarTextField.getText(), Integer.parseInt(IRCAIngresarTextField.getText()));
            clearIngresar();
            mostrarAlertInfo(event, "INGRESADO CORRECTAMENTE");
        } else {
            mostrarAlertInfo(event, "ID EN USO");
        }
    }

    public void clearEdit() {
        NombreEditTextField.clear();
        IDEditTextField.clear();
        MunicipioEditTextField.clear();
        TipoDeAguaEditTextField.clear();
        TipoDeCuerpoDeAguaEditTextField.clear();
        IRCAEditTextField.clear();
    }

    public void clearIngresar() {
        NombreIngresarTextField.clear();
        IDIngresarTextField.clear();
        MunicipioIngresarTextField.clear();
        TipoDeAguaIngresarTextField.clear();
        TipoDeCuerpoDeAguaIngresarTextField.clear();
        IRCAIngresarTextField.clear();
    }

    @FXML
    void ObtenerDatosBtnOnAction(ActionEvent event) {
        ObtenerDatosTextArea.clear();
        ArrayList<CuerpoDeAgua> ArrayCuerpo = new ArrayList<CuerpoDeAgua>();
        ArrayCuerpo = BD.SELECTALL();
        Iterator<CuerpoDeAgua> IteratorCuerpo = ArrayCuerpo.iterator();
        if (ArrayCuerpo.isEmpty()) {
            mostrarAlertInfo(event, "BASE DE DATOS VACIA NO HAY QUE OBTENER");
        }
        while (IteratorCuerpo.hasNext()) {
            CuerpoDeAgua cuerpo = IteratorCuerpo.next();
            ObtenerDatosTextArea.appendText(cuerpo.toString() + "\n");
        }
    }

    @FXML
    void ProcesarDatosBtnOnAction(ActionEvent event) {
        int cantidadDeCuerpos = 0;
        String[] salidas = { "", "", "", "" };
        double ircaMayores = 0.0;
        double promedio = 0.0;
        ProcesarDatosTextArea.clear();
        ArrayList<CuerpoDeAgua> ArrayCuerpo = new ArrayList<CuerpoDeAgua>();
        ArrayCuerpo = BD.SELECTALL();
        Iterator<CuerpoDeAgua> IteratorCuerpo = ArrayCuerpo.iterator();
        while (IteratorCuerpo.hasNext()) {
            CuerpoDeAgua cuerpo = IteratorCuerpo.next();
            salidas[0] += (cuerpo.toStringFirst() + "\n");
            promedio += cuerpo.getIrca();
            if (cuerpo.getIrca() > 14) {
                ircaMayores++;
            }
            salidas[1] = String.format("%.2f", ircaMayores).replace(",", ".");
            if (cuerpo.getNivelRiesgo().equals("BAJO")) {
                salidas[2] += (cuerpo.getNombre() + " ");
            }
            cantidadDeCuerpos++;
            salidas[3] = String.format("%.2f", (promedio / cantidadDeCuerpos));
        }
        if (ArrayCuerpo.isEmpty()) {
            mostrarAlertInfo(event, "BASE DE DATOS VACIA NO HAY QUE PROCESAR");
        } else {
            ProcesarDatosTextArea.setText(salidas[0] + salidas[1] + "\n" + salidas[2] + "\n" + salidas[3]);
        }
    }

    @FXML
    void BuscarBtnOnAction(ActionEvent event) {
        CuerpoDeAgua cuerpo = BD.SELECT(Integer.parseInt(IDBuscaTextField.getText()));
        if (cuerpo == null) {
            mostrarAlertInfo(event, "NO EXISTE REGISTRO CON EL ID :" + IDBuscaTextField.getText());
        } else {
            NombreEditTextField.setText(cuerpo.getNombre());
            IDEditTextField.setText(String.valueOf(cuerpo.getId()));
            MunicipioEditTextField.setText(cuerpo.getMunicipio());
            TipoDeCuerpoDeAguaEditTextField.setText(cuerpo.getTipoCuerpo());
            TipoDeAguaEditTextField.setText(cuerpo.getTipoAgua());
            IRCAEditTextField.setText(String.valueOf(cuerpo.getIrca()));
        }
    }

    @FXML
    void EditarBtnOnAction(ActionEvent event) {
        CuerpoDeAgua cuerpoActualizado = new CuerpoDeAgua(NombreEditTextField.getText(),
                Integer.parseInt(IDEditTextField.getText()), MunicipioEditTextField.getText(),
                TipoDeAguaEditTextField.getText(), TipoDeCuerpoDeAguaEditTextField.getText(),
                Integer.parseInt(IRCAEditTextField.getText()));
        System.out.println();
        BD.UPDATE(Integer.parseInt(IDBuscaTextField.getText()), cuerpoActualizado);
        CuerpoDeAgua cuerpo = BD.SELECT(Integer.parseInt(IDBuscaTextField.getText()));
        clearEdit();
        mostrarAlertInfo(event, "EDITADO CORRECTAMENTE");

        NombreEditTextField.setText(cuerpo.getNombre());
        IDEditTextField.setText(String.valueOf(cuerpo.getId()));
        MunicipioEditTextField.setText(cuerpo.getMunicipio());
        TipoDeCuerpoDeAguaEditTextField.setText(cuerpo.getTipoCuerpo());
        TipoDeAguaEditTextField.setText(cuerpo.getTipoAgua());
        IRCAEditTextField.setText(String.valueOf(cuerpo.getIrca()));
    }

    @FXML
    private void mostrarAlertInfo(ActionEvent event, String Mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informacion");
        alert.setContentText(Mensaje);
        alert.showAndWait();
    }

    @FXML
    void Eliminar(ActionEvent event) {
        BD.DELETE(Integer.parseInt(IDBuscaTextField.getText()));
        mostrarAlertInfo(event, "ELIMINADO CORRECTAMENTE");
        clearEdit();
    }
    */
    
    @FXML
    private Label AcomuladoLabel;

    @FXML
    private Button AgregarBtn;

    @FXML
    private Button BuscarBtn;

    @FXML
    private ComboBox<String> CategoriaComboBox;

    @FXML
    private TextField CategoriaEditTextField;

    @FXML
    private Button EliminarBtn;

    @FXML
    private Button FinalizarJuegoBtn;

    @FXML
    private Button IniciarJuegoBtn;


    @FXML
    private Button ObtenerDatosBtn;

    @FXML
    private TextArea ObtenerDatosTextArea;

    @FXML
    private ComboBox<String> PreguntaComboBox;

    @FXML
    private Label NombreLabel;
    
    @FXML
    private Label PreguntaLabel;

    @FXML
    private TextField PrguntaEditTextField;

    @FXML
    private Button RespuestaABtn;

    @FXML
    private Button RespuestaBBtn;

    @FXML
    private Button RespuestaCBtn;

    @FXML
    private TextField RespuestaCorrectaTextField;

    @FXML
    private Button RespuestaDBtn;

    @FXML
    private TextField RespuestaIncorrecta1TextField;

    @FXML
    private TextField RespuestaIncorrecta2TextField;

    @FXML
    private TextField RespuestaIncorrecta3TextField;
    
    @FXML
    private Tab configurarJuegoTab;
    
    @FXML
    private Button CargarCategoriasBtn;
    private Button[] botonesRespuestas= new Button[4];
    private String[] respuestasString = new String[4];
    private int respuestaCorrecta;
    Pregunta[] preguntas;
    int numeroRonda=0;
    Ronda ronda;
    int acomulado;
    Optional<String> nombreJugador; 
    
    @FXML
    void CategoriaComboBoxOnCategoria(ActionEvent event) {
        PreguntaComboBox.setItems(BD.selectAllPreguntasString(CategoriaComboBox.getValue()));
    }
    
    @FXML
    void CargarCategoriasBtnOnAction(ActionEvent event) {
        CategoriaComboBox.setItems(BD.selectAllCategoriasString());
        CargarCategoriasBtn.setVisible(false);
    }
    
    @FXML
    void configurarJuego() {
        CategoriaComboBox.setItems(BD.selectAllCategoriasString());
    }
    
    @FXML
    private void mostrarAlertInfo(ActionEvent event, String Mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informacion");
        alert.setContentText(Mensaje);
        alert.showAndWait();
    }

    void guardarHistorico(Jugador Jugador, int IdPremio){
        BD.insertHistorico(Jugador, (IdPremio-1));
    }
    
    @FXML
    void AgregarBtnOnAction(ActionEvent event) {
        Pregunta  pregunta = new Pregunta();
        pregunta.setProblema(PrguntaEditTextField.getText());
        if(BD.selectPregunta(pregunta.getProblema())==null){
            pregunta.setRespuesta_correcta(RespuestaCorrectaTextField.getText());
            pregunta.setRespuesta_incorrecta1(RespuestaIncorrecta1TextField.getText());
            pregunta.setRespuesta_incorrecta2(RespuestaIncorrecta2TextField.getText());
            pregunta.setRespuesta_incorrecta3(RespuestaIncorrecta3TextField.getText());
            pregunta.setNombre(CategoriaEditTextField.getText());
            pregunta.setNivel(6);
            if(BD.selectCategoria(pregunta.getNombre())==null){
                while((pregunta.getNivel()>5)||(pregunta.getNivel()<0)){
                    TextInputDialog dialog = new TextInputDialog("");
                    dialog.setTitle("Nivel - Reto Sofka");
                    dialog.setHeaderText("Niveles: 1-2-3-4-5, no ingrese alguna opción diferente");
                    dialog.setContentText("Por favor ingrese el numero del nivel:");
                    Optional<String> niveljugador;  
                    niveljugador = dialog.showAndWait();
                    Categoria categoria = new Categoria(pregunta.getNombre(), Integer.parseInt(niveljugador.get()));
                    BD.insertCategoria(categoria);
                    mostrarAlertInfo(event, "Categoria agregada");
                    pregunta.setNivel(Integer.parseInt(niveljugador.get()));
                }
            }
            BD.insertPregunta(pregunta);
            mostrarAlertInfo(event, "Pregunta agregada");
            limpiarPantallaConfJuego();  
        }
        else{
            mostrarAlertInfo(event, "Pregunta ya existente");
        }
        
        
    }

    @FXML
    void BuscarBtnOnAction(ActionEvent event) {
        Pregunta  pregunta = new Pregunta();
        pregunta = BD.selectPregunta(PreguntaComboBox.getValue());
        PrguntaEditTextField.setText(pregunta.getProblema());
        RespuestaCorrectaTextField.setText(pregunta.getRespuesta_correcta());
        RespuestaIncorrecta1TextField.setText(pregunta.getRespuesta_incorrecta1());
        RespuestaIncorrecta2TextField.setText(pregunta.getRespuesta_incorrecta2());
        RespuestaIncorrecta3TextField.setText(pregunta.getRespuesta_incorrecta3());
        CategoriaEditTextField.setText(pregunta.getNombre());
    }

    @FXML
    void EliminarBtnOnAction(ActionEvent event) {
        BD.deletePregunta(PrguntaEditTextField.getText());
        limpiarPantallaConfJuego();
        mostrarAlertInfo(event, "Eliminado Correctamente");
        
    }
    
    void limpiarPantallaConfJuego(){
        PrguntaEditTextField.clear();
        RespuestaCorrectaTextField.clear();
        RespuestaIncorrecta1TextField.clear();
        RespuestaIncorrecta2TextField.clear();
        RespuestaIncorrecta3TextField.clear();
        CategoriaEditTextField.clear();
    }
    
    void avanzarJuego(ActionEvent event){
        botonesRespuestas[0]= RespuestaABtn;
        botonesRespuestas[1]= RespuestaBBtn;
        botonesRespuestas[2]= RespuestaCBtn;
        botonesRespuestas[3]= RespuestaDBtn;
        ArrayList<Pregunta> arrayPregunta = BD.selectAllPreguntas();
        ArrayList<Categoria> arrayCategoria = BD.selectAllCategorias();
        Iterator<Pregunta> IteratorPregunta = arrayPregunta.iterator();
        PreguntaLabel.setText(preguntas[numeroRonda].getProblema());
        respuestasString[0]= preguntas[numeroRonda].getRespuesta_correcta();
        respuestasString[1]= preguntas[numeroRonda].getRespuesta_incorrecta1();
        respuestasString[2]= preguntas[numeroRonda].getRespuesta_incorrecta2();
        respuestasString[3]= preguntas[numeroRonda].getRespuesta_incorrecta3();
        respuestaCorrecta = (int) Math.floor(Math.random()*4);
        botonesRespuestas[respuestaCorrecta].setText(respuestasString[0]);
        int k=1;
        for (int i = 0; i < botonesRespuestas.length; i++) {
            if(i!=respuestaCorrecta){
                botonesRespuestas[i].setText(respuestasString[k]);
                k++;
            }
        }
        IniciarJuegoBtn.setVisible(false);
        FinalizarJuegoBtn.setVisible(true);
                
    }
    
    @FXML
    void IniciarJuegoBtnOnAction(ActionEvent event) {
        AcomuladoLabel.setText("00");
        numeroRonda=0;
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Nombre - Reto Sofka");
        dialog.setHeaderText("¿Con que nombre deseas Jugar?");
        dialog.setContentText("Por favor ingrese su nombre:");
        nombreJugador = dialog.showAndWait();
        Jugador jugador = BD.selectJugador(nombreJugador.get());
        
        while(jugador.getNombre().equals(nombreJugador.get())){
            dialog = new TextInputDialog("");
            dialog.setTitle("Nombre - Reto Sofka");
            dialog.setContentText("Por favor ingrese otro nombre,"+nombreJugador.get()+" ya esta en uso :");
            nombreJugador = dialog.showAndWait();
            jugador = BD.selectJugador(nombreJugador.get());
        }
        
        NombreLabel.setText(nombreJugador.get());
        botonesRespuestas[0]= RespuestaABtn;
        botonesRespuestas[1]= RespuestaBBtn;
        botonesRespuestas[2]= RespuestaCBtn;
        botonesRespuestas[3]= RespuestaDBtn;
        mostrarPantallaJuego();
        ArrayList<Pregunta> arrayPregunta = BD.selectAllPreguntas();
        if (arrayPregunta == null) {
            mostrarAlertInfo(event, "No existen preguntas en la base de datos");
        } 
        else {
            if (arrayPregunta.size()>=25){
                ArrayList<Categoria> arrayCategoria = BD.selectAllCategorias();
                Iterator<Pregunta> IteratorPregunta = arrayPregunta.iterator();
                ronda = new Ronda(arrayPregunta,arrayCategoria);
                ronda.setPremios(BD.selectAllPremio());
                preguntas = ronda.preguntasAzar();
                for (int i = 0; i < preguntas.length; i++) {
                    if (preguntas[i]==null){
                        mostrarAlertInfo(event, "El nivel "+(i+1)+" no tiene preguntas");
                    }
                }
                PreguntaLabel.setText(preguntas[0].getProblema());
                respuestasString[0]= preguntas[0].getRespuesta_correcta();
                respuestasString[1]= preguntas[0].getRespuesta_incorrecta1();
                respuestasString[2]= preguntas[0].getRespuesta_incorrecta2();
                respuestasString[3]= preguntas[0].getRespuesta_incorrecta3();
                respuestaCorrecta = (int) Math.floor(Math.random()*4);
                botonesRespuestas[respuestaCorrecta].setText(respuestasString[0]);
                int k=1;
                for (int i = 0; i < botonesRespuestas.length; i++) {
                    if(i!=respuestaCorrecta){
                        botonesRespuestas[i].setText(respuestasString[k]);
                        k++;
                    }
                }
                IniciarJuegoBtn.setVisible(false);
                FinalizarJuegoBtn.setVisible(false);
                
            }
            else{
                mostrarAlertInfo(event, "No exiten las preguntas necesarios, hay "+arrayPregunta.size()+" de minimo 25");
            }
        }
    }
    
    @FXML
    void FinalizarJuegoBtnOnAction(ActionEvent event) {
        limpiarPantallaJuego();       
        Jugador jugador = new Jugador(nombreJugador.get(), Integer.parseInt(AcomuladoLabel.getText()));
        guardarHistorico(jugador, preguntas[numeroRonda].getNivel());
         mostrarAlertInfo(event, "Gracias por participar");
    }

    @FXML
    void ObtenerDatosBtnOnAction(ActionEvent event) {
        ObtenerDatosTextArea.clear();
        ArrayList<Jugador> arrayJugadores = BD.selectAllJugador();
        Iterator<Jugador> IteratorJugadores = arrayJugadores.iterator();
        while (IteratorJugadores.hasNext()) {
            Jugador jugador = IteratorJugadores.next();
            ObtenerDatosTextArea.appendText(jugador.getNombre()+" : "+jugador.getPuntaje()+"\n");
        }

    }
    
    void limpiarPantallaJuego(){
        PreguntaLabel.setVisible(false);
        botonesRespuestas[0].setVisible(false);
        botonesRespuestas[1].setVisible(false);
        botonesRespuestas[2].setVisible(false);
        botonesRespuestas[3].setVisible(false);
        IniciarJuegoBtn.setVisible(true);
    }
    
    void mostrarPantallaJuego(){
        PreguntaLabel.setVisible(true);
        botonesRespuestas[0].setVisible(true);
        botonesRespuestas[1].setVisible(true);
        botonesRespuestas[2].setVisible(true);
        botonesRespuestas[3].setVisible(true);
    }

    @FXML
    void RespuestaABtnOnAction(ActionEvent event) {
        if(botonesRespuestas[respuestaCorrecta].equals(RespuestaABtn)){
            acomulado = Integer.parseInt(AcomuladoLabel.getText());
            AcomuladoLabel.setText(Integer.toString((ronda.getPremios().get(numeroRonda).getCantidad())+acomulado));
            numeroRonda++;
            if( numeroRonda==5){
                limpiarPantallaJuego();
                Jugador jugador = new Jugador(nombreJugador.get(), Integer.parseInt(AcomuladoLabel.getText()));
                guardarHistorico(jugador, preguntas[4].getNivel());
                mostrarAlertInfo(event, nombreJugador.get()+" Ganaste con "+AcomuladoLabel.getText());
            }
            else{
            avanzarJuego(event);
            }
        }
        else{
            mostrarAlertInfo(event, "Perdiste");
            limpiarPantallaJuego();
        }
    }

    @FXML
    void RespuestaBBtnOnAction(ActionEvent event) {
        if(botonesRespuestas[respuestaCorrecta].equals(RespuestaBBtn)){
            acomulado = Integer.parseInt(AcomuladoLabel.getText());
            AcomuladoLabel.setText(Integer.toString((ronda.getPremios().get(numeroRonda).getCantidad())+acomulado));
            numeroRonda++;
            if( numeroRonda==5){
                limpiarPantallaJuego();
                Jugador jugador = new Jugador(nombreJugador.get(), Integer.parseInt(AcomuladoLabel.getText()));
                guardarHistorico(jugador, preguntas[4].getNivel());
                mostrarAlertInfo(event, nombreJugador.get()+" Ganaste con "+AcomuladoLabel.getText());
            }
            else{
                avanzarJuego(event);
            }
        }
        else{
            mostrarAlertInfo(event, "Perdiste");
            limpiarPantallaJuego();
        }
    }

    @FXML
    void RespuestaCBtnOnAction(ActionEvent event) {
        if(botonesRespuestas[respuestaCorrecta].equals(RespuestaCBtn)){
            acomulado = Integer.parseInt(AcomuladoLabel.getText());
            AcomuladoLabel.setText(Integer.toString((ronda.getPremios().get(numeroRonda).getCantidad())+acomulado));
            numeroRonda++;
            if( numeroRonda==5){
                limpiarPantallaJuego();
                Jugador jugador = new Jugador(nombreJugador.get(), Integer.parseInt(AcomuladoLabel.getText()));
                guardarHistorico(jugador, preguntas[4].getNivel());
                mostrarAlertInfo(event, nombreJugador.get()+" Ganaste con "+AcomuladoLabel.getText());
            }
            else{
                mostrarAlertInfo(event, "Perdiste");
                avanzarJuego(event);
            }
        }
        else{
            mostrarAlertInfo(event, "Perdiste");
            limpiarPantallaJuego();
        }
    }

    @FXML
    void RespuestaDBtnOnAction(ActionEvent event) {
        if(botonesRespuestas[respuestaCorrecta].equals(RespuestaDBtn)){
            acomulado = Integer.parseInt(AcomuladoLabel.getText());
            AcomuladoLabel.setText(Integer.toString((ronda.getPremios().get(numeroRonda).getCantidad())+acomulado));
            numeroRonda++;
            if( numeroRonda==5){
                limpiarPantallaJuego();
                Jugador jugador = new Jugador(nombreJugador.get(), Integer.parseInt(AcomuladoLabel.getText()));
                guardarHistorico(jugador, preguntas[4].getNivel());
                mostrarAlertInfo(event, nombreJugador.get()+" Ganaste con "+AcomuladoLabel.getText());
            }
            else{
                avanzarJuego(event);
            }
        }
        else{
            mostrarAlertInfo(event, "Perdiste");
            limpiarPantallaJuego();
        }
    }
}

