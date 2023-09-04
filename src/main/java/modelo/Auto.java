/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Vehiculo;
import modelo.Vendedor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Auto extends Vehiculo implements Serializable{
    private String vidrios;
    private String transmision; 

    public Auto(int id, String placa, String marca, String modelo, String tipo_motor, int año, int recorrido, String color, String tipo_comb, int precio, Usuario usuario,String imagen, String vidrios, String transmision) {
        super(id, placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_comb, precio, usuario, imagen);
        this.vidrios = vidrios;
        this.transmision = transmision;
    }

    public Auto() {
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }


    @Override
    public String toString() {
        return super.toString()+ "\n vidrios=" + vidrios + "\n transmision=" + transmision;
    }
    
    
}
