package com.example.sqlite;

public class Car {
    private Integer id;
    private String model;
    private String color;

    public Car(Integer id, String model, String color) {
        this.id = id;
        this.model = model;
        this.color = color;
    }
    public Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model=model;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
