/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectog4;

import static ec.edu.espol.util.Util.getSHA;
import static ec.edu.espol.util.Util.nextID;
import static ec.edu.espol.util.Util.toHexString;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author romiy
 */
public class Usuario {
    private int id;
    private String nombres;
    private String apellidos;
    private String organizacion;
    private String correo_electronico;
    private String clave;

    public Usuario(int id, String nombres, String apellidos, String organizacion, String correo_electronico, String clave) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo_electronico = correo_electronico;
        this.clave = clave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    @Override
    public String toString() {
        return "Usuario {" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", organizacion=" + organizacion + ", correo_electronico=" + correo_electronico + ", clave=" + clave;
    }
    
    public void saveArchivo(String nfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true))){
            pw.println(this.id+"|"+this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo_electronico+"|"+this.clave);
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<String> readFileCorreos(String nfile) {
        ArrayList<String> correos = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nfile))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                String correo_elec = tokens[4];
                correos.add(correo_elec);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return correos;
    }

    public static ArrayList<String> readFileClaves(String nfile){
        ArrayList<String> claves = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nfile))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                String correo_elec = tokens[5];
                claves.add(correo_elec);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return claves;
    }

    public static boolean validarCredenciales(String correo, String cv, String nfile){
        Boolean correoin = false;
        ArrayList<String> correos_dados = readFileCorreos(nfile);
        for (String c : correos_dados) {
            if(c.equals(correo)==true)
                correoin = true; 
        }
        Boolean clavein = false;
        ArrayList<String> claves = readFileClaves(nfile);
        try{
            String password = toHexString(getSHA(cv));
            for (String c : claves) {
                if(c.equals(password)==true)
                    clavein = true;
            }
        }catch (NoSuchAlgorithmException e){
                System.out.println("Exception thrown for incorrect algorithm: " + e.getMessage());
            }
        return correoin && clavein;
    }

    public static Vendedor searchByCorreo(ArrayList<Vendedor> vendedores, String correo){
        for(Vendedor x: vendedores){
            if(x.getCorreo_electronico().equals(correo)) {
                return x;
            } else {
                System.out.println("Vendedor no encontrado");
            }
        }
        return null;
    }    
    
    public static void nextUsuario(Scanner sc, String nfileUsuarios) {
        int id_usuario = nextID(nfileUsuarios);
        sc.useDelimiter("\n");
        sc.useLocale(Locale.US);
        System.out.println("Ingrese nombres");
        String n = sc.next();
        System.out.println("Ingrese apellidos");
        String ape = sc.next();
        System.out.println("Ingrese organizacion");
        String org = sc.next();
        System.out.println("Ingrese clave");
        String cv = sc.next();
        String password;
        try {
            password = toHexString(getSHA(cv));
            System.out.println("Ingrese correo");
            String correo = sc.next();
            ArrayList<String> correos_dados = readFileCorreos(nfileUsuarios);
            if(!correos_dados.isEmpty()){
                boolean correoin = false; 
                for (int i = 0;i<correos_dados.size();i++) {
                    if (correos_dados.get(i).equals(correo)) {
                    System.out.println("Correo ya registrado");
                    correoin = true;
                    }
                }
                if(correoin == false){
                    Vendedor v = new Vendedor(id_usuario, n, ape, org, correo, password);
                    v.saveArchivo(nfileUsuarios);
                    System.out.println("Usuario registrado");
                }
            }else{
                Vendedor v = new Vendedor(id_usuario,n,ape,org,correo,password);
                v.saveArchivo(nfileUsuarios);
                System.out.println("Primer Usuario registrado!");
            }                
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e.getMessage());
        }
    }    
}
