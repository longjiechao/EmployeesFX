/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

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
    
    public void altas(int emo_no, LocalDate birth_date, String first_name, String last_name, char gender, LocalDate hire_date){
        
    }
    public void bajas(int emo_no){
        
    }
    public void modificar(int emo_no, LocalDate birth_date, String first_name, String last_name, char gender, LocalDate hire_date){
        
    }
    public void listarXFecha(){
        
    }
    public void listarXApellido(){
        
    }
}