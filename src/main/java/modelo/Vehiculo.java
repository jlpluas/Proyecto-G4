/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import static ec.edu.espol.util.Util.nextID;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author romiy
 */
public class Vehiculo implements Serializable{
    private int id;
    private String placa; 
    private String marca; 
    private String modelo; 
    private String tipo_motor; 
    private int año; 
    private int recorrido; 
    private String color; 
    private String tipo_comb; 
    private int precio;
    private Usuario usuario;
    private String imagen;

    public Vehiculo(int id, String placa, String marca, String modelo, String tipo_motor, int año, int recorrido, String color, String tipo_comb, int precio, Usuario usuario, String imagen) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_comb = tipo_comb;
        this.precio = precio;
        this.usuario = usuario;
        this.imagen= imagen;
    }

    public Vehiculo() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo_motor() {
        return tipo_motor;
    }

    public void setTipo_motor(String tipo_motor) {
        this.tipo_motor = tipo_motor;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(int recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo_comb() {
        return tipo_comb;
    }

    public void setTipo_comb(String tipo_comb) {
        this.tipo_comb = tipo_comb;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    @Override
    public String toString() {
        return "placa=" + placa + "\n marca=" + marca + "\n modelo=" + modelo + "\n tipo_motor=" + tipo_motor + "\n año=" + año + "\n recorrido=" + recorrido + "\n color=" + color + "\n tipo_comb=" + tipo_comb + "\n precio=" + precio;
    }
    

    public static void saveListToFileSer(String nfile, ArrayList<Vehiculo> vehiculos) {
    
    try (ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(nfile))) {
        fout.writeObject(vehiculos); 
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}

    public static ArrayList<Vehiculo> readListFromFileSer(String nfile){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nfile))){ 
            vehiculos = (ArrayList<Vehiculo>)in.readObject();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        return vehiculos;
    }
}