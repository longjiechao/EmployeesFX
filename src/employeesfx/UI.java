/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesfx;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
    
    TextField emo_no;
    DatePicker birth_date;
    TextField first_name;
    TextField last_name;
    ComboBox<String> gender;
    DatePicker hire_date;
    ComboBox<String> departments;
    
    TextField dpLname;
    
    ConnectionProperties con;
    
    public UI(Stage primaryStage, int altura, int anchura){
        this.stage = primaryStage;
        this.altura = altura;
        this.anchura = anchura;
        try {
            con = new ConnectionProperties();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getAltura(){
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
        bp.setAlignment(txt, Pos.CENTER);
        //Ir a Pantalla Alta
        VBox vb = new VBox();
        HBox hb = new HBox();
        Button button = new Button("Alta");
        button.setMaxWidth(100);
        button.setStyle("-fx-background-color: #54c242; ");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pantallaAltas();
            }
        });
        //Ir a Pantalla Baja
        hb.getChildren().add(button);
        button = new Button("Baja");
        button.setStyle("-fx-background-color: #f93324; ");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pantallaBajas();
            }
        });
        hb.getChildren().add(button);
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        vb.getChildren().add(hb);
        vb.setAlignment(Pos.CENTER);
        
        
        //Ir a Pantalla Modificar
        hb = new HBox();
        button = new Button("Modificar");
        button.setStyle("-fx-background-color: #f5a44d; ");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pantallaModificar();
            }
        });
        //Ir a Pantalla Lista
        hb.getChildren().add(button);
        button = new Button("Lista");
        button.setStyle("-fx-background-color: #64d2ff; ");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    pantallaLista();
                } catch (SQLException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        hb.getChildren().add(button);
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        vb.getChildren().add(hb);
        vb.setSpacing(5);
        vb.setAlignment(Pos.CENTER);
        bp.setCenter(vb);
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
    
    public void pantallaAltas(){
        BorderPane bp = new BorderPane();
        VBox vb = new VBox();
        HBox hb;
        Label label;
        Button button = new Button("Atrás");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pantallaPrincipal();
            }
        });
        bp.setTop(button);
        
        label = new Label("Número de empleado");
        emo_no = new TextField();
        // force the field to be numeric only
        emo_no.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    emo_no.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        emo_no.setPromptText("Introduzca el Número de Empleado");
        hb = new HBox();
        hb.getChildren().addAll(label, emo_no);
        vb.getChildren().add(hb);
        
        label = new Label("Fecha de nacimiento");
        birth_date = new DatePicker();
        birth_date.setPromptText("Introduzca el Día de Cumpleaños");
        hb = new HBox();
        hb.getChildren().addAll(label, birth_date);
        vb.getChildren().add(hb);
        
        label = new Label("Nombre");
        first_name = new TextField();
        first_name.setPromptText("Introduzca el Nombre");
        hb = new HBox();
        hb.getChildren().addAll(label, first_name);
        vb.getChildren().add(hb);
        
        label = new Label("Apellido");
        last_name = new TextField();
        last_name.setPromptText("Introduzca el Apellido");
        hb = new HBox();
        hb.getChildren().addAll(label, last_name);
        vb.getChildren().add(hb);
        
        label = new Label("Sexo");
        gender = new ComboBox<String>();
        gender.getItems().addAll("Hombre", "Mujer");
        gender.getSelectionModel().selectFirst();
        hb = new HBox();
        hb.getChildren().addAll(label, gender);
        vb.getChildren().add(hb);
        
        label = new Label("Fecha de contrato");
        hire_date  = new DatePicker();
        hire_date.setPromptText("Introduzca la Fecha de contratación");
        hb = new HBox();
        hb.getChildren().addAll(label, hire_date);
        vb.getChildren().add(hb);
        
        //Botón de añadir
        Button bt = new Button("Añadir");
        bt.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                int num = 0;
                char sexo;
                boolean errores = false;
                
                if(gender.getValue().compareTo("Mujer") == 0){
                    sexo = 'F';
                }else{
                    sexo = 'M';
                }
                
                if(emo_no.getText().trim().isEmpty()){
                    System.out.println("Campo Número Empleado obligatorio");
                    errores = true;
                }else{
                    num = Integer.parseInt(emo_no.getText());
                }
                if(birth_date.getValue() == null){
                    System.out.println("Campo Fecha de Nacimiento obligatorio");
                    errores = true;
                }
                if(first_name.getText().trim().isEmpty()){
                    System.out.println("Campo Nombre obligatorio");
                    errores = true;
                }
                if(last_name.getText().trim().isEmpty()){
                    System.out.println("Campo Apellido obligatorio");
                    errores = true;
                }
                if(hire_date.getValue() == null){
                    System.out.println("Campo Fecha de Contratación obligatorio");
                    errores = true;
                }
                
                if(errores == false){
                    try {
                        con.altas(num, birth_date.getValue(), first_name.getText(), last_name.getText(), sexo, hire_date.getValue());
                    } catch (SQLException ex) {
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        vb.getChildren().add(bt);
        
        bp.setCenter(vb);
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
    
    public void pantallaBajas(){
        BorderPane bp = new BorderPane();
        
        Button button = new Button("Atrás");
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                pantallaPrincipal();
            }
        });
        bp.setTop(button);
        
        VBox vb = new VBox();
        HBox hb;
        
        Label label = new Label("Número de empleado");
        emo_no = new TextField();
        // force the field to be numeric only
        emo_no.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    emo_no.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        emo_no.setPromptText("Número de Empleado");
        hb = new HBox();
        hb.getChildren().addAll(label, emo_no);
        vb.getChildren().add(hb);
        
        //Botón de Borrar
        Button bt = new Button("Borrar");
        bt.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                if(emo_no.getText().trim().isEmpty()){
                    System.out.println("Campo Número de Empleado obligatorio");
                }else{
                    int num = Integer.parseInt(emo_no.getText());
                    try {
                        if(con.checkById(num)){
                            con.bajas(num);
                        }else{
                            System.out.println("Empleado no encontrado...");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }  
            }
        });
        
        vb.getChildren().add(bt);
        
        bp.setCenter(vb);
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
    
    public void pantallaModificar(){
        BorderPane bp = new BorderPane();
        
        Button button = new Button("Atrás");
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                pantallaPrincipal();
            }
        });
        bp.setTop(button);
        
        VBox vb = new VBox();
        HBox hb;
        Label label = new Label("Número de empleado");
        emo_no = new TextField();
        // force the field to be numeric only
        emo_no.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    emo_no.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        emo_no.setPromptText("Número de Empleado");
        hb = new HBox();
        hb.getChildren().addAll(label, emo_no);
        vb.getChildren().add(hb);
        
        //Botón de Modificar
        Button bt = new Button("Selección para modificar");
        bt.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                if(emo_no.getText().trim().isEmpty()){
                    System.out.println("Campo Número de Empleado obligatorio");
                }else{
                    int num = Integer.parseInt(emo_no.getText());
                    try {
                        if(con.checkById(num)){
                            pantallaModificar2(num, "mod");
                        }else{
                            System.out.println("Empleado no encontrado...");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }  
            }
        });
        
        vb.getChildren().add(bt);
        bp.setCenter(vb);
        
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
    
    public void pantallaModificar2(int id, String type) throws SQLException{
        ArrayList<String> idElements = con.getIdElement(id);
        for(int i = 0; i < idElements.size(); i++){
            System.out.println(i+ ": "+idElements.get(i));
        }
        
        BorderPane bp = new BorderPane();
        Button button = new Button("Atrás");
        if(type.compareTo("list")==0){
            button.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent e){
                    try {
                        pantallaLista();
                    } catch (SQLException ex) {
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }else{
            button.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent e){
                    pantallaModificar();
                }
            });
        }
        
        
        bp.setTop(button);
        
        VBox vb = new VBox();
        HBox hb;
        Label label;
        LocalDate newDate;
        label = new Label("Fecha de nacimiento");
        birth_date = new DatePicker();
        birth_date.setPromptText("Introduzca el Día de Cumpleaños");
        newDate = LocalDate.parse(idElements.get(1));
        birth_date.setValue(newDate);
        hb = new HBox();
        hb.getChildren().addAll(label, birth_date);
        vb.getChildren().add(hb);
        
        label = new Label("Nombre");
        first_name = new TextField();
        first_name.setPromptText("Introduzca el Nombre");
        first_name.setText(idElements.get(2));
        hb = new HBox();
        hb.getChildren().addAll(label, first_name);
        vb.getChildren().add(hb);
        
        label = new Label("Apellido");
        last_name = new TextField();
        last_name.setPromptText("Introduzca el Apellido");
        last_name.setText(idElements.get(3));
        hb = new HBox();
        hb.getChildren().addAll(label, last_name);
        vb.getChildren().add(hb);
        
        label = new Label("Sexo");
        gender = new ComboBox<String>();
        gender.getItems().addAll("Hombre", "Mujer");
        
        if(idElements.get(4).compareTo("M") == 0){
            gender.getSelectionModel().select(0);
        }else{
            gender.getSelectionModel().select(1);
        }
        hb = new HBox();
        hb.getChildren().addAll(label, gender);
        vb.getChildren().add(hb);
        
        label = new Label("Fecha de contrato");
        hire_date  = new DatePicker();
        hire_date.setPromptText("Introduzca la Fecha de contratación");
        newDate = LocalDate.parse(idElements.get(5));
        hire_date.setValue(newDate);
        hb = new HBox();
        hb.getChildren().addAll(label, hire_date);
        vb.getChildren().add(hb);
        
        //Dept
        
        
        //Botón de añadir
        Button bt = new Button("Modificar");
        bt.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                char sexo;
                boolean errores = false;
                if(gender.getValue().compareTo("Mujer") == 0){
                    sexo = 'F';
                }else{
                    sexo = 'M';
                }
                
                if(birth_date.getValue() == null){
                    System.out.println("Campo Fecha de Nacimiento obligatorio");
                    errores = true;
                }
                if(first_name.getText().trim().isEmpty()){
                    System.out.println("Campo Nombre obligatorio");
                    errores = true;
                }
                if(last_name.getText().trim().isEmpty()){
                    System.out.println("Campo Apellido obligatorio");
                    errores = true;
                }
                if(hire_date.getValue() == null){
                    System.out.println("Campo Fecha de Contratación obligatorio");
                    errores = true;
                }
                
                if(errores == false){
                    con.modificar(id, birth_date.getValue(), first_name.getText(), last_name.getText(), sexo, hire_date.getValue());
                    if(type.compareTo("list")==0){
                            try {
                                pantallaLista();
                            } catch (SQLException ex) {
                                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }else{
                        pantallaModificar();
                    }
                }
            }
        });
        
        vb.getChildren().add(bt);
        
        bp.setCenter(vb);
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
    }
    
    public void pantallaLista() throws SQLException{
        BorderPane bp = new BorderPane();
        HBox hb = new HBox();
        Button button = new Button("Atrás");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                pantallaPrincipal();
            }
        });
        
        //tabla
        dpLname = new TextField();
        Button btDP = new Button("Filtrar");
        btDP.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                ScrollPane sp = new ScrollPane();
                sp.setFitToHeight(true);
                sp.setFitToWidth(true);
                VBox vb = new VBox();
                ResultSet rs;
                try {
                    if(dpLname.getText().isEmpty()){ 
                        rs = con.listar();
                    }else{
                        rs = con.listarXApellido(dpLname.getText());
                    }
                    
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    TableView table = getTabla();
                    while(rs.next()) {
                        ArrayList<String> name = new ArrayList<String>();
                        for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                            Object object = rs.getObject(columnIndex);
                            if(object != null){
                                name.add(object.toString());
                            }
                        }
                        int num = Integer.parseInt((name.get(0)));
                        System.out.println(num + name.get(1) + name.get(2) + name.get(3) + name.get(4) + name.get(5));
                        Employee emp = new Employee(num, LocalDate.parse(name.get(1)), name.get(2), name.get(3), name.get(4), LocalDate.parse(name.get(5)));
                        emp.getButton().setOnAction(new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent e) {
                                try {
                                    pantallaModificar2(emp.getId(), "list");
                                } catch (SQLException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println(emp.getId());
                            }
                        });;
                        table.getItems().add(emp);
                    }
                    vb.getChildren().add(table);
                    sp.setContent(vb);
                    bp.setCenter(sp);
                } catch (SQLException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        hb.getChildren().addAll(button, dpLname, btDP);
        hb.setHgrow(dpLname, Priority.ALWAYS);
        bp.setTop(hb);
        
        
        this.scene = new Scene(bp, altura, anchura);
        this.setStage(scene);
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
        
        TableColumn<Employee, Button> editTable = new TableColumn<>("");
        editTable.setCellValueFactory(new PropertyValueFactory<>("button"));
        
        table.getColumns().addAll(idTable, bdayTable, fnameTable, lnameTable, genderTable, contractTable, editTable);
        
        return table;
    }
}