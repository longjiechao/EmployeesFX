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
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
            st.executeUpdate("DELETE FROM employees WHERE emp_no = "+emo_no); 
            System.out.println("Entrada Eliminado!");
        }catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
    
    public boolean checkById(int emp_no) throws SQLException{
        boolean encontrado;
        Statement st;
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE emp_no = " + emp_no);
        if (!rs.next()) {
            System.out.println("no data");
            encontrado = false;
        }else{
            encontrado = true;
        }
        
        return encontrado;
    }
    
    public ArrayList<String> getIdElement(int emp_no) throws SQLException{
        ArrayList<String> al = new ArrayList<String>();
        
        Statement st;
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE emp_no = " + emp_no);

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while(rs.next()) {
            String columna = "";
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                Object object = rs.getObject(columnIndex);
                if(object != null){
                    al.add(object.toString());
                }
                //System.out.printf("%s, ", object == null ? "NULL" : object.toString());
            }
            System.out.println(columna);
            Text txt = new Text(columna);
        }
        
        return al;
    }
    
    public void modificar(int emo_no, LocalDate birth_date, String first_name, String last_name, char gender, LocalDate hire_date){
        try{
            Statement st;
            st = con.createStatement();
            System.out.println("UPDATE employees SET birth_date = '" +birth_date.toString()+"', first_name = '" +first_name+"', last_name = '" +last_name+"', gender = '" +gender+"', hire_date = '"+hire_date+"' WHERE emp_no = "+emo_no+";");
            st.executeUpdate("UPDATE employees SET birth_date = '" +birth_date.toString()+"', first_name = '" +first_name+"', last_name = '" +last_name+"', gender = '" +gender+"', hire_date = '"+hire_date+"' WHERE emp_no = "+emo_no+";"); 
            System.out.println("Entrada Actualizada!");
        }catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
    
    public void createModificar(){
        
    }
    
    //filtra el listado por apellidos
    public ResultSet listarXApellido(String last_name) throws SQLException{
        Statement st;
        st = con.createStatement();
        ResultSet rs;
        rs = st.executeQuery("select * from employees WHERE last_name LIKE '%" + last_name + "%' ORDER BY last_name, first_name");
        return rs;
    }

    //mostrat todo
    public ResultSet listar() throws SQLException{
        Statement st;
        st = con.createStatement();
        ResultSet rs;
        rs = st.executeQuery("select * from employees");
        return rs;
    }
    
    public TableView getTabla(){
        //Crear tabla
        TableView table = new TableView();
        table.setPlaceholder(new Label("No rows to display"));
        
        TableColumn<Employee, String> idTable = new TableColumn<>("ID");
        idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<Employee, String> bdayTable = new TableColumn<>("Birthday");
        bdayTable.setCellValueFactory(new PropertyValueFactory<>("bday"));
        
        TableColumn<Employee, String> fnameTable = new TableColumn<>("First Name");
        fnameTable.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        
        TableColumn<Employee, String> lnameTable = new TableColumn<>("Last Name");
        lnameTable.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        
        TableColumn<Employee, String> genderTable = new TableColumn<>("Gender");
        genderTable.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
        TableColumn<Employee, String> contractTable = new TableColumn<>("Hire Date");
        contractTable.setCellValueFactory(new PropertyValueFactory<>("hire_date"));
        
        TableColumn<Employee, Button> editTable = new TableColumn<>("button");
        editTable.setCellValueFactory(new PropertyValueFactory<>("button"));
        
        table.getColumns().addAll(idTable, bdayTable, fnameTable, lnameTable, genderTable, contractTable, editTable);
        
        return table;
    }
}