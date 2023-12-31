/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

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

public class Oferta implements Serializable, Comparable<Oferta>{
    private double precio_oferta;
    private String correo;
    private Vehiculo vehiculo;
    public static ArrayList<Oferta> ofertasVehiculos=new ArrayList<>();

    public Oferta(double precio_oferta, String correo, Vehiculo vehiculo) {
        this.precio_oferta = precio_oferta;
        this.correo = correo;
        this.vehiculo = vehiculo;
    }

    public double getPrecio_oferta() {
        return precio_oferta;
    }

    public void setPrecio_oferta(int precio_oferta) {
        this.precio_oferta = precio_oferta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    
   @Override
   public String toString(){
       return vehiculo.getMarca()+vehiculo.getModelo()+this.precio_oferta;
   }
   
//    public static ArrayList<Double> precioVeh(String placa){
//        ArrayList<Double> precio= new ArrayList<>();
//        for(Oferta o: Oferta.ofertasVehiculos ){
//            if(o.getVehiculo().getPlaca().equals(placa) ){
//               precio.add(o.getPrecio_oferta());
//            }
//        }
//        return precio;
//    }
    

    
        public static void saveListToFileSer(String nfile, ArrayList<Oferta> ofertas){
        try(ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(nfile))){
            fout.writeObject(ofertas);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Oferta> readListFromFileSer(String nfile){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nfile))){ 
            ofertas = (ArrayList<Oferta>)in.readObject();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        return ofertas;
    }
    
    @Override
    public int compareTo(Oferta o){
        int salida = 0;
        if(this.precio_oferta<o.precio_oferta)
            salida = -1;
        else if(this.precio_oferta>o.precio_oferta)
            salida = 1;
        return salida;
    }
}
