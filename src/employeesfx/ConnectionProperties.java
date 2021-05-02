/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author LJChao-PC
 */
public class ConnectionProperties {
    static final String DB_URL = "jdbc:mysql://172.16.0.100:3306/employees";
    static final String DB_USER = "admin";
    static final String DB_PASS = "1234";
    private Connection con;
    
    public ConnectionProperties() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        try {
            con = DriverManager.getConnection(DB_URL + "?user=" + DB_USER +"&password=" + DB_PASS);
            System.out.println("Connexió exitosa a la base de dades!");
        } catch (SQLException e) {
            System.err.println("Error d'establiment de connexió: " + e.getMessage());
        }
    }
    
    public void altas(int emo_no, LocalDate birth_date, String first_name, String last_name, char gender, LocalDate hire_date) throws SQLException{
        try{
            Statement st;
            st = con.createStatement();
            System.out.println("INSERT INTO employees(emp_no, birth_date, first_name, last_name, gender, hire_date) VALUES(" +emo_no+", '" +birth_date.toString()+"','" +first_name+"','" +last_name+"','" +gender+"','"+hire_date+"')");
            int numFiles = st.executeUpdate("INSERT INTO employees(emp_no, birth_date, first_name, last_name, gender, hire_date) VALUES("+emo_no+", '"+birth_date.toString()+"','"+first_name+"','"+last_name+"','"+gender+"','"+hire_date+"')"); 
            System.out.println("Entrada/s creadas! Entrada afectades: " + numFiles);
        }catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
    public void bajas(int emo_no){
        try{
            Statement st;
            st = con.createStatement();
            System.out.println("DELETE FROM employees WHERE emp_no = "+emo_no+";");
            st.executeUpdate("DELETE FROM employees WHERE emp_no = "+emo_no); 
            System.out.println("Entrada Eliminado!");
        }catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
    public void modificar(int emo_no, LocalDate birth_date, String first_name, String last_name, char gender, LocalDate hire_date){
        
    }
    public void listarXFecha(LocalDate fechaInicial, LocalDate fechaFinal){
        
    }
    public void listarXApellido(){
        
    }
    
    public ScrollPane listar() throws SQLException{
        ScrollPane sp = new ScrollPane();
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        VBox vb = new VBox();
        Statement st;
        st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from employees");

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while(rs.next()) {
            String columna = "";
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                Object object = rs.getObject(columnIndex);
                if(object != null){
                    columna += object.toString() + ", ";
                }
                //System.out.printf("%s, ", object == null ? "NULL" : object.toString());
            }
            System.out.println(columna);
            Text txt = new Text(columna);
            vb.getChildren().add(txt);
            //System.out.printf("%n");
        }
        sp.setContent(vb);
        return sp;
    }
}