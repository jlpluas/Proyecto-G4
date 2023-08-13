/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author romiy
 */
public class Menu {
    public static void medotosVendedor(){
//        System.out.println("""
//                           1. Registrar un nuevo vendedor
//                           2. Registrar un nuevo vehiculo
//                           3. Ver ofertas realizadas
//                           4. Regresar""");
        Scanner sc= new Scanner(System.in);
        int miniopcion= Integer.parseInt(sc.nextLine()); 
        while(miniopcion != 4) {
            if(miniopcion == 1)
                Vendedor.nextVendedor(sc,"Vendedores.txt");
            else if(miniopcion == 2)
                Vendedor.registrarVehiculo(sc, "Vendedores.txt","Vehiculos.txt");
            else if(miniopcion == 3)
                Vendedor.aceptarOferta(sc,"Vendedores.txt","Vehiculos.txt");
//            System.out.println("""
//                           1. Registrar un nuevo vendedor
//                           2. Registrar un nuevo vehiculo
//                           3. Ver ofertas realizadas
//                           4. Regresar""");
            sc.nextLine();
            miniopcion=Integer.parseInt(sc.nextLine());
        }
    }
           
    public static void metodosComprador(){
//        System.out.println("""
//                           1. Registrar un nuevo comprador
//                           2. Ofertar por Vehiculo
//                           3. Eliminar oferta
//                           4. Regresar""");
        Scanner sc= new Scanner(System.in);
        int miniopcion= Integer.parseInt(sc.nextLine());
        while(miniopcion != 4) {
            if (miniopcion==1){
                Comprador.nextComprador(sc,"Compradores.txt");
            }
            else if (miniopcion==2){
                Comprador.ofertarVehiculo(sc,"Vehiculos.txt", "Vendedores.txt");
            }
            else if (miniopcion ==3){
                Comprador.eliminarOferta(sc,"Vendedores.txt","Vehiculos.txt","Ofertas.txt");
            }
//            System.out.println("""
//                           1. Registrar un nuevo comprador
//                           2. Ofertar por Vehiculo
//                           3. Eliminar oferta
//                           4. Regresar""");
            sc.nextLine();
            miniopcion=Integer.parseInt(sc.nextLine());
        }
    }
}
