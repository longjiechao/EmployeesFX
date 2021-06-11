/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesfx;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author LJChao-PC
 */
public class EmployeesFX extends Application{
    
    public void start(Stage primaryStage) throws SQLException{
        UI ui = new UI(primaryStage, 800, 400);
        ui.pantallaPrincipal();
    }
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        launch(args);
        //ConnectionProperties db = new ConnectionProperties();
    }
}