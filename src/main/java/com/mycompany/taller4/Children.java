/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller4;

/**
 *
 * @author adria
 */
public class Children {
    
    private String Id;
    private String nombre;
    private String sexo;
    private int edad;
    private String grado;
    public Children sig;

    public Children(String Id, String nombre, String sexo, int edad, String grado) {
        this.Id = Id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.grado=grado;
        this.sig=null;
    }

    public Children() {
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Children getSig() {
        return sig;
    }

    public void setSig(Children sig) {
        this.sig = sig;
    }
    
    
    
    
    
}
