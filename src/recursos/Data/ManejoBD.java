package recursos.Data;

import recursos.Clases.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManejoBD {

    private String CadenaConexion;

    private Connection Conexion;

    public ManejoBD() {
        try {
            this.CadenaConexion = "jdbc:sqlite:database.db";
            this.Conexion = DriverManager.getConnection(CadenaConexion);
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error al intentar conectar a la base de datos: " + ex.getMessage());
        }
    }

    public Connection getConexion() {
        return this.Conexion;
    }

    public String getCadenaConexion() {
        return this.CadenaConexion;
    }

    public void insertPregunta(Pregunta pregunta) {

        try {
            String sql ="INSERT INTO Pregunta (Problema,Respuesta_correcta,Respuesta_incorrecta_1,Respuesta_incorrecta_2,"
                    + "Respuesta_incorrecta_3,fk_categoria )"+ "VALUES "
                        + "('"+pregunta.getProblema()+"','"+
                        pregunta.getRespuesta_correcta()+"','"+
                        pregunta.getRespuesta_incorrecta1()+"','"+
                        pregunta.getRespuesta_incorrecta2()+"','"+
                        pregunta.getRespuesta_incorrecta3()+"','"+
                        pregunta.getNombre()+"');";
            Statement stmt = this.Conexion.createStatement();
            stmt.executeUpdate(sql);
        } catch (

        SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
        }

    }

    public ArrayList<Pregunta> selectAllPreguntas() {

        ArrayList<Pregunta> ArrayPregunta = new ArrayList<Pregunta>();
        Pregunta pregunta = new Pregunta();
        try {
            String sql = "SELECT * FROM Pregunta inner join Categoria on Categoria.Nombre = Pregunta.fk_categoria ORDER BY Categoria.Nivel;";
            Statement stmt = this.Conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pregunta = new Pregunta(rs.getInt("Id"), rs.getString("Problema"), rs.getString("Respuesta_correcta"),
                        rs.getString("Respuesta_incorrecta_1"), rs.getString("Respuesta_incorrecta_2"), rs.getString("Respuesta_incorrecta_3"),rs.getString("Nombre"),rs.getInt("Nivel"));
                ArrayPregunta.add(pregunta);
            }
            return ArrayPregunta;
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
            
        }
        return null;
    }
    
    public Pregunta selectPregunta(String problema) {
        Pregunta pregunta = null;
        try {
            String sql = "SELECT * FROM Pregunta inner join Categoria on Categoria.Nombre = Pregunta.fk_categoria WHERE Problema ='"
                    + problema + "';";
            Statement stmt = this.Conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                pregunta = new Pregunta(rs.getInt("Id"), rs.getString("Problema"), rs.getString("Respuesta_correcta"),
                        rs.getString("Respuesta_incorrecta_1"), rs.getString("Respuesta_incorrecta_2"), rs.getString("Respuesta_incorrecta_3"),rs.getString("Nombre"),rs.getInt("Nivel"));
            }
            return pregunta;
        } catch (

        SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
            return null;
        }
    }

    public void updatePregunta(String nombre, Pregunta preguntaActualizada) {
        try (Statement stmt = Conexion.createStatement()) {
            Pregunta pregunta = selectPregunta(nombre);
            String sql = "UPDATE Pregunta SET" + " Id = " + String.valueOf(preguntaActualizada.getId()) + ",Problema = '"
                    + preguntaActualizada.getProblema() + "',Respuesta_correcta = '" + preguntaActualizada.getRespuesta_correcta()
                    + "',Respuesta_incorrecta_1 = '" + preguntaActualizada.getRespuesta_incorrecta1() + "',Respuesta_incorrecta_2 = '"
                    + preguntaActualizada.getRespuesta_incorrecta2() + "',Respuesta_incorrecta_3 = " + preguntaActualizada.getRespuesta_incorrecta3()
                    +"',fk_categoria = "+ pregunta.getNombre()+"'"
                    + " WHERE Id = '" + pregunta.getId() + "'" + " AND Problema = '" + pregunta.getProblema()
                    + "'" + " AND Respuesta_incorrecta_1 = '" + pregunta.getRespuesta_incorrecta1() + "'" + " AND Respuesta_incorrecta_2 = '"
                    + pregunta.getRespuesta_incorrecta2() + "'" + " AND pregunta.getRespuesta_incorrecta3() = '" + pregunta.getRespuesta_incorrecta3() 
                    + "' AND"+"',fk_categoria = "+pregunta.getNombre()+"';";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
        }
    }

    public void deletePregunta(String nombre) {
        try {
            Pregunta pregunta = selectPregunta(nombre);
            String sql = "DELETE FROM Pregunta WHERE Id = " + String.valueOf(pregunta.getId())+ " AND Problema = '"
                    + pregunta.getProblema()+ "' AND  Respuesta_correcta = '" + pregunta.getRespuesta_correcta()
                    + "' AND  Respuesta_incorrecta_1 = '" + pregunta.getRespuesta_incorrecta1() + "' AND  Respuesta_incorrecta_2 = '"
                    + pregunta.getRespuesta_incorrecta2() + "' AND  Respuesta_incorrecta_3 = '" + pregunta.getRespuesta_incorrecta3()
                    +"' AND  fk_categoria = '"+ pregunta.getNombre() + "';";
            Statement stmt = Conexion.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
        }
    }

    public void insertCategoria(Categoria categoria) {

        try {
            String sql ="INSERT INTO Categoria (Nombre, Nivel)"+ "VALUES "
                        + "('"+categoria.getNombre()+"','"+
                        categoria.getNivel()+"');"
                    +"INSERT INTO Premio (Cantidad, fk_categoria)"+ "VALUES "
                    + "('"+(categoria.getNivel()*10)+"','"+
                        categoria.getNombre()+"');";
            Statement stmt = this.Conexion.createStatement();
            stmt.executeUpdate(sql);
        } catch (

        SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
        }

    }
    
    public Categoria selectCategoria(String nombre) {
        Categoria categoria = null;
        try {
            String sql = "SELECT * FROM Categoria WHERE Nombre ='"
                    + nombre + "';";
            Statement stmt = this.Conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                categoria = new Categoria(rs.getString("Nombre"), rs.getInt("Nivel"));
            }
            return categoria;
        } catch (

        SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
            return null;
        }
    }
    
    public ArrayList<Categoria> selectAllCategorias(){
    ArrayList<Categoria> ArrayCategoria = new ArrayList<Categoria>();
    Categoria categoria = new Categoria();
    try {
        String sql = "SELECT * FROM Categoria;";
        Statement stmt = this.Conexion.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            categoria = new Categoria(rs.getString("Nombre"),rs.getInt("Nivel"));
            ArrayCategoria.add(categoria);
        }
        return ArrayCategoria;
    } catch (SQLException ex) {
        System.out.println("Error al ejecutar el comando: " + ex.getMessage());
        return null;
    }
}
    
    public ObservableList<String> selectAllCategoriasString(){
        ObservableList<String> categorias = FXCollections.observableArrayList();
        ArrayList<Categoria> ArrayCategoria = new ArrayList<Categoria>();
        Categoria categoria = new Categoria();
        try {
            String sql = "SELECT * FROM Categoria;";
            Statement stmt = this.Conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                categoria = new Categoria(rs.getString("Nombre"),rs.getInt("Nivel"));
                ArrayCategoria.add(categoria);
                categorias.add(categoria.getNombre()+" : "+categoria.getNivel());
            }
            
            return categorias;
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
            return null;
    }
}
    
    public ObservableList<String> selectAllPreguntasString(String Categoria){
        ObservableList<String> preguntas = FXCollections.observableArrayList();
        ArrayList<Pregunta> ArrayPregunta = new ArrayList<Pregunta>();
        Pregunta pregunta = new Pregunta();
        try {
            String sql = "SELECT * FROM Pregunta inner join Categoria on Categoria.Nombre = Pregunta.fk_categoria ORDER BY fk_categoria;";
            Statement stmt = this.Conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pregunta = new Pregunta(rs.getInt("Id"), rs.getString("Problema"), rs.getString("Respuesta_correcta"),
                        rs.getString("Respuesta_incorrecta_1"), rs.getString("Respuesta_incorrecta_2"), rs.getString("Respuesta_incorrecta_3"),rs.getString("Nombre"),rs.getInt("Nivel"));
                ArrayPregunta.add(pregunta);
                if(Categoria.contains(pregunta.getNombre())){
                    preguntas.add(pregunta.getProblema());
                }

            }
            return preguntas;
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
            
        }
        return null;
    }
    
    public ArrayList<Premio> selectAllPremio() {

        ArrayList<Premio> ArrayPremio = new ArrayList<Premio>();
        Premio premio = new Premio();
        try {
            String sql = "SELECT * FROM Premio ORDER BY fk_categoria;";
            Statement stmt = this.Conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                premio = new Premio(rs.getInt("Cantidad"));
                ArrayPremio.add(premio);
            }
            return ArrayPremio;
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
            return null;
        }
    }
    
    public ArrayList<Jugador> selectAllJugador() {

        ArrayList<Jugador> ArrayJugador = new ArrayList<Jugador>();
        Jugador jugador = new Jugador();
        try {
            String sql = "SELECT Nombre, Puntaje FROM Historico ORDER BY Puntaje;";
            Statement stmt = this.Conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                jugador = new Jugador(rs.getString("Nombre"),rs.getInt("Puntaje"));
                ArrayJugador.add(jugador);
            }
            return ArrayJugador;
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
            return null;
        }
    }
    
    public Jugador selectJugador(String nombre) {

        Jugador jugador = new Jugador();
        try {
            String sql = "SELECT Nombre, Puntaje FROM Historico WHERE Nombre = '"+nombre+"' ORDER BY Puntaje;";
            Statement stmt = this.Conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                jugador = new Jugador(rs.getString("Nombre"),rs.getInt("Puntaje"));
            }
            return jugador;
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
            return null;
        }
    }
    
    public void insertHistorico(Jugador Jugador, int IdPremio) {

        try {
            String sql ="INSERT INTO Historico ( Nombre, Puntaje, fk_premio ) VALUES "
                        + "('"+Jugador.getNombre()+"','"+
                        Jugador.getPuntaje()+"','"+
                        IdPremio+"');";
            Statement stmt = this.Conexion.createStatement();
            stmt.executeUpdate(sql);
        } catch (

        SQLException ex) {
            System.out.println("Error al ejecutar el comando: " + ex.getMessage());
        }

    }
    
    
}
