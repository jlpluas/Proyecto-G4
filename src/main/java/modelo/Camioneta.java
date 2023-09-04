/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Camioneta extends Vehiculo implements Serializable{
    private String traccion;

    public Camioneta(String traccion, int id, String placa, String marca, String modelo, String tipo_motor, int año, int recorrido, String color, String tipo_comb, int precio, Usuario usuario, String imagen) {
        super(id, placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_comb, precio,usuario,imagen);
        this.traccion = traccion;
    }

    public Camioneta() {
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n traccion=" + traccion;
    }
    
}
