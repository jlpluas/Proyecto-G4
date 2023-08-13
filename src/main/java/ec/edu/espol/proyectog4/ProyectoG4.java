/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.proyectog4;

import ec.edu.espol.util.Util;
import java.util.Scanner;

/**
 *
 * @author romiy
 */
public class ProyectoG4 {

    public static void main(String[] args) {
//        System.out.println("""
//                           Menú de Opciones:
//                           1. Métodos de Vendedor
//                           2. Métodos de Comprador
//                           3. Salir""");
        Scanner sc= new Scanner(System.in);
        sc.useDelimiter("\n");
        Util.crearArchivo("Vendedores.txt");
        Util.crearArchivo("Vehiculos.txt");
        int opcion= Integer.parseInt(sc.nextLine());
        while(opcion != 3){
            if (opcion==1){
                Menu.medotosVendedor();
            }
            else if(opcion==2){
                Menu.metodosComprador();
            }
            else if(opcion==3){
                System.out.println("Terminado con éxito");
                }
//            System.out.println("""
//                           Menú de Opciones:
//                           1. Métodos de Vendedor
//                           2. Métodos de Comprador
//                           3. Salir""");
            System.out.println("Ingrese una opcion");
            opcion=Integer.parseInt(sc.nextLine());   
        }       
    }   
}    
