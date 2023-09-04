/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import static ec.edu.espol.util.Util.getSHA;
import static ec.edu.espol.util.Util.nextID;
import static ec.edu.espol.util.Util.toHexString;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author romiy
 */
public class Usuario implements Serializable{
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

    public Usuario(String correo_electronico, String clave) {
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

    public static void saveListToFileSer(String nfile, ArrayList<Usuario> usuarios){
        try(ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(nfile))){
            fout.writeObject(usuarios);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Usuario> readListFromFileSer(String nfile){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nfile))){ 
            usuarios = (ArrayList<Usuario>)in.readObject();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        return usuarios;
    } 
}
