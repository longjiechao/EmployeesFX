/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesfx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author LJChao-PC
 */
public class UI {
    
    private Stage stage;
    private Scene scene;
    private int altura;
    private int anchura;
    
    public UI(Stage primaryStage, int altura, int anchura){
        this.stage = primaryStage;
        this.altura = altura;
        this.anchura = anchura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAnchura() {
        return anchura;
    }

    public void setAnchura(int anchura) {
        this.anchura = anchura;
    }
    
    private void setStage(Scene scene){
        stage.setScene(scene);
        stage.show();
    }
    
    public void pantallaPrincipal(){
        BorderPane bp = new BorderPane();
        
        Text txt = new Text("Empleados");
        bp.setTop(txt);
        
        VBox vb = new VBox();
        HBox hb = new HBox();
        Button button = new Button("Alta");
        hb.getChildren().add(button);
        button = new Button("Baja");
        hb.getChildren().add(button);
        vb.getChildren().add(hb);
        
        
        hb = new HBox();
        button = new Button("Eliminar");
        hb.getChildren().add(button);
        button = new Button("Lista");
        hb.getChildren().add(button);
        vb.getChildren().add(hb);
        bp.setCenter(vb);
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
    
    public void pantallaLista(){
        BorderPane bp = new BorderPane();
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
    
    public void filtrarXFecha(){
        
    }
    
    public void filtrarXApellido(){
        
    }
    
    public void pantallaAltas(){
        BorderPane bp = new BorderPane();
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
    
    public void pantallaBajas(){
        BorderPane bp = new BorderPane();
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
    
    public void pantallaModificar(){
        BorderPane bp = new BorderPane();
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
    
    public void pantallaEliminar(){
        BorderPane bp = new BorderPane();
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
}