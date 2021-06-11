/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesfx;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author LJ
 */
public class Employee {
    
    private int id;
    private LocalDate bday;
    private String first_name;
    private String last_name;
    private String gender;
    private LocalDate hire_date;
    private String dept;
    private Button button;

    public Employee(int id, LocalDate bday, String first_name, String last_name, String gender, LocalDate hire_date, String dept) {
        this.id = id;
        this.bday = bday;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.hire_date = hire_date;
        this.dept = dept;
        this.button = new Button("Editar");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBday() {
        return bday;
    }

    public void setBday(LocalDate bday) {
        this.bday = bday;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
    
    
}
