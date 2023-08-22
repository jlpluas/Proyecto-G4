/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import ec.edu.espol.util.Util;
import static ec.edu.espol.util.Util.getSHA;
import static ec.edu.espol.util.Util.nextID;
import static ec.edu.espol.util.Util.toHexString;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


public class Comprador {
    private int id;
    private String nombres;
    private String apellidos;
    private String organizacion;
    private String correo_electronico;
    private String clave;

    public Comprador(int id, String nombres, String apellidos, String organizacion, String correo_electronico, String clave) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo_electronico = correo_electronico;
        this.clave = clave;
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
    public void saveArchivo(String nfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true))){
            pw.println(this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo_electronico+"|"+this.clave);
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<String> readFileCorreos(String nfile){
        ArrayList<String> correos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nfile))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                String correo_elec = tokens[4];
                correos.add(correo_elec);
            }
        }catch(Exception e){
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
    
        
    public static void nextComprador(Scanner sc,String nfile){
        int id_comprador = nextID(nfile);
        System.out.println("Ingrese nombres");
        String n = sc.next();
        System.out.println("Ingrese apellidos");
        String ape = sc.next();
        System.out.println("Ingrese organizacion");
        String org = sc.next();
        System.out.println("Ingrese clave");
        String cv = sc.next();
        String password;
        try{
            password = toHexString(getSHA(cv));
            System.out.println("Ingrese correo");
            String correo = sc.next();
            ArrayList<String> correos_dados = readFileCorreos(nfile);
            if(!correos_dados.isEmpty()){
                boolean correoin = false;
                for (String c : correos_dados) {
                    if (c.equals(correo)) {
                    System.out.println("Correo ya registrado");
                    correoin = true;
                    }
                }
                if(correoin == false){
                    Vendedor v = new Vendedor(id_comprador,n,ape,org,correo,password);
                    v.saveArchivo(nfile);
                    System.out.println("Comprador Registrado");
                }
            }else{
                Vendedor v = new Vendedor(id_comprador,n,ape,org,correo,password);
                v.saveArchivo(nfile);
            }                
        }catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e.getMessage());
        }
    }
    
    public static ArrayList<Vehiculo> identificarTipo(String nfile, String tipo,String fileVendedores){
        ArrayList<Vehiculo> listaMotos = new ArrayList <>();
        ArrayList<Vehiculo> listaCamionetas = new ArrayList <>();
        ArrayList<Vehiculo> listaAutos = new ArrayList <>();
        ArrayList<Vehiculo> listaTotal = new ArrayList <>();
        try(Scanner sc = new Scanner(new File(nfile))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                Vendedor v=Vendedor.searchByID(fileVendedores,Integer.parseInt(tokens[1]));
                if(tokens.length==11){
                    Vehiculo moto= new Vehiculo(Integer.parseInt(tokens[0]),tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Integer.parseInt(tokens[7]),tokens[8],tokens[9],Integer.parseInt(tokens[10]),v);
                    listaMotos.add(moto);
                    listaTotal.add(moto);
                }
                if(tokens.length==12){
                    Vehiculo camioneta= new Camioneta(tokens[11],Integer.parseInt(tokens[0]),tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Integer.parseInt(tokens[7]),tokens[8],tokens[9],Integer.parseInt(tokens[10]),v);
                    listaCamionetas.add(camioneta); 
                    listaTotal.add(camioneta);
                }
                if(tokens.length==13){
                    Vehiculo auto= new Auto(Integer.parseInt(tokens[0]),tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Integer.parseInt(tokens[7]),tokens[8],tokens[9],Integer.parseInt(tokens[10]),v,tokens[11],tokens[12]);
                    listaAutos.add(auto); 
                    listaTotal.add(auto);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        if(tipo.equals("moto"))
            return listaMotos;
        if(tipo.equals("camioneta"))
            return listaCamionetas;
        if(tipo.equals("auto"))
            return listaAutos;
        else
            return listaTotal;
    }
    
    public static ArrayList<Vehiculo> identificarRango(String nfile, String fileVendedores,String tipo,int recMin,int recMax,int anioMin,int anioMax,int preMin,int preMax){
        ArrayList<Vehiculo> vehiculos=identificarTipo(nfile, tipo,fileVendedores);
        ArrayList<Vehiculo> listaF= new ArrayList<>();
        for(Vehiculo v: vehiculos){
            if(v.getRecorrido()>=recMin && v.getRecorrido()<=recMax && v.getAño()>=anioMin && v.getAño()<=anioMax && v.getPrecio()>=preMin && v.getPrecio()<=preMax);
                listaF.add(v);
        }
        return listaF;
    }
    
    
    public static void ofertarVehiculo(Scanner sc, String nfile, String fileVendedores){
        System.out.println("¿Desea ingresar tipo de vehiculo? \n 1.Si \n 2.No :");
        int resp1=sc.nextInt();
        sc.nextLine();
        int tipo=0;
        if (resp1==1){
            System.out.println("Ingrese el tipo de vehiculo por sú numero: \n 1.Moto \n 2.Auto \n 3.Camioneta " );
            tipo=sc.nextInt();
            sc.nextLine();
        }
        else{
            tipo=0;
        }
        String tipoS="";
        if(tipo==0)
            tipoS+=" ";
        if(tipo==1)
            tipoS+="moto";
        if(tipo==2)
            tipoS+="auto";
        if(tipo==3)
            tipoS+="camioneta";
        
        
        System.out.println("¿Desea ingresar el recorrido mínimo del vehiculo? \n 1.Si \n 2.No :");
        int resp2=sc.nextInt();
        sc.nextLine();
        int recorridoMin=0;
        if (resp2==1){
            System.out.println("Ingrese el valor del recorrido mínimo del vehiculo: " );
            recorridoMin=sc.nextInt();
            sc.nextLine();
        }
        else{
            recorridoMin=0;
        }    
        
        System.out.println("¿Desea ingresar el recorrido máximo del vehiculo? \n 1.Si \n 2.No :");
        int resp3=sc.nextInt();
        sc.nextLine();
        int recorridoMax=0;
        if (resp3==1){
            System.out.println("Ingrese el valor del recorrido máximo del vehiculo: " );
            recorridoMax=sc.nextInt();
            sc.nextLine();
        }
        else{
            recorridoMax=Integer.MAX_VALUE;
        }
        
        System.out.println("¿Desea ingresar el año mínimo del vehiculo? \n 1.Si \n 2.No :");
        int resp4=sc.nextInt();
        sc.nextLine();
        int anioMin=0;
        if (resp4==1){
            System.out.println("Ingrese el año mínimo del vehiculo: " );
            anioMin=sc.nextInt();
            sc.nextLine();
        }
        else{
            anioMin=0;
        }
        
        System.out.println("¿Desea ingresar el año máximo del vehiculo? \n 1.Si \n 2.No :");
        int resp5=sc.nextInt();
        sc.nextLine();
        int anioMax=0;
        if (resp5==1){
            System.out.println("Ingrese el año máximo del vehiculo: " );
            anioMax=sc.nextInt();
            sc.nextLine();
        }
        else{
            anioMax=Integer.MAX_VALUE;
        }
        
        System.out.println("¿Desea ingresar el precio mínimo del vehiculo? \n 1.Si \n 2.No :");
        int resp6=sc.nextInt();
        sc.nextLine();
        int precioMin=0;
        if (resp6==1){
            System.out.println("Ingrese el valor del precio mínimo del vehiculo: " );
            precioMin=sc.nextInt();
            sc.nextLine();
        }
        else{
            precioMin=0;
        }
        
        System.out.println("¿Desea ingresar el precio máximo del vehiculo? \n 1.Si \n 2.No :");
        int resp7=sc.nextInt();
        sc.nextLine();
        int precioMax=0;
        if (resp7==1){
            System.out.println("Ingrese el valor del precio máximo del vehiculo: " );
            precioMax=sc.nextInt();
            sc.nextLine();
        }
        else{
            precioMax=Integer.MAX_VALUE;
        }
        System.out.println("Presione enter para mostrar los resultados");
        sc.nextLine();
        
        ArrayList<Vehiculo> listaSel= identificarRango(nfile,tipoS,fileVendedores,recorridoMin,recorridoMax,anioMin,anioMax,precioMin,precioMax);
        int i=0;
        int x= 0;
        while(x==0){
            System.out.println("RESULTADOS DE LA BÚSQUEDA");
            Vehiculo v=listaSel.get(i);
            v.toString();
            int resp=0;
            if(i==0){
                System.out.println("Ingrese el número de la opción que desea: \n 1.Realizar oferta \n 2.Ver siguiente vehiculo \n 9.Salir");
                resp=sc.nextInt();
            } 
            else if(i>0 && i<listaSel.size()-1){
                System.out.println("Ingrese el número de la opción que desea: \n 1.Realizar oferta \n 2.Ver siguiente vehiculo \n 3.Ver anterior vehiculo \n 9.Salir");
                resp=sc.nextInt();
            }
            else if(i==listaSel.size()-1){
                System.out.println("Ingrese el número de la opción que desea: \n 1.Realizar oferta \n 2.Ver anterior vehiculo \n 9.Salir");
                resp=sc.nextInt();
            }
            ArrayList<Oferta> ofertasVehiculo = new ArrayList<>(); 
            Oferta off;
            if (resp==1){
                System.out.println("Ingrese su oferta:");
                double ofertaIn=sc.nextDouble();
                System.out.println("Ingrese su correo: ");
                String correoIn=sc.nextLine();
                off=new Oferta(ofertaIn,correoIn,v);
                Oferta.ofertasVehiculos.add(off);
                ofertasVehiculo.add(off);
                i++;
                for(Oferta o: ofertasVehiculo){
                    o.saveArchivoOferta("Ofertas.txt");
                }
            }
            else if(resp==3){
                i--;
            }
            else if(resp==9){
                x=0;
            }
            else if(resp==2){
                if(i>=0 && i<listaSel.size()-1)
                    i++;
                else {
                    i--;
                }
            }     
        }
    }
    
    public static void eliminarOferta(Scanner sc, String nfilevendedores, String nfileVeh, String nfileO){
        System.out.println("Ingrese su correo electronico de vendedor");
        String correo = sc.nextLine();
        System.out.println("Ingrese su clave de vendedor");
        String cv = sc.nextLine();
        boolean credenciales = validarCredenciales(correo, cv, nfilevendedores);
        while(credenciales != true){
            System.out.println("Usuario o contraseña incorrecto - Ingrese de nuevo:");
            System.out.println("Ingrese su correo electronico de vendedor");
            correo = sc.nextLine();
            System.out.println("Ingrese su clave de vendedor");
            cv = sc.nextLine();
            credenciales = validarCredenciales(correo, cv, nfilevendedores);
        }
        int i=0;
        int x= 0;
        ArrayList<Oferta> ofertas = Oferta.readFileOfertas(nfileO);
        ArrayList<Oferta> ofertascomprador = Oferta.searchBycorreo(ofertas, correo);
        while(x==0){
            String placaIn = ofertascomprador.get(i).getVehiculo().getPlaca();
            ArrayList<Vehiculo> vehSel= Vehiculo.vehConPlaca(nfileVeh, placaIn);
            Vehiculo vehiculoI=vehSel.get(i);
            ArrayList<Double> precioSel=Oferta.precioVeh(placaIn);
            double precioI=precioSel.get(i);
            ArrayList<String> correoSel=Oferta.correoVeh(placaIn);
            String correoI=correoSel.get(i);
            
            System.out.println(vehiculoI.getMarca()+" "+vehiculoI.getModelo()+" Precio: "+vehiculoI.getPrecio() );
            System.out.println(" ");
            System.out.println("Oferta"+(i+1));
            System.out.println("Correo: "+correoI);
            System.out.println("Precio ofertado: "+ precioI);
            
            int resp=0;
            if(i==0){
                System.out.println("Ingrese el número de la opción que desea: \n 1.Eliminar oferta \n 2.Siguiente oferta ");
                resp=sc.nextInt();
                if (resp==1){
                    Util.eliminarInformacion("Ofertas.txt", i + 1);
                } else if (resp == 2) {
                    if (i >= 0 && i < vehSel.size() - 1) {
                        i++;
                    } else {
                        i--;
                    }
                } else if (resp == 3)
                    i--;
                else if (resp == 9)
                    x += 1;
            } 
            else if(i>0 && i<vehSel.size()-1){
                System.out.println("Ingrese el número de la opción que desea: \n 1. Eliminar oferta \n 2.Siguiente oferta \n 3. Anteior oferta");
                resp=sc.nextInt();
                if (resp==1){
                    Util.eliminarInformacion("Ofertas.txt", i + 1);
                } else if (resp == 2) {
                    if (i >= 0 && i < vehSel.size() - 1) {
                        i++;
                    } else {
                        i--;
                    }
                } else if (resp == 3)
                    i--;
                else if (resp == 9)
                    x += 1;
            }
            else if(i==vehSel.size()-1){
                System.out.println("Ingrese el número de la opción que desea: \n 1.Eliminar oferta \n 3.Anterior oferta ");
                resp=sc.nextInt();
                if (resp==1){
                    Util.eliminarInformacion("Ofertas.txt", i + 1);
                } else if (resp == 2) {
                    if (i >= 0 && i < vehSel.size() - 1) {
                        i++;
                    } else {
                        i--;
                    }
                } else if (resp == 3)
                    i--;
                else if (resp == 9)
                    x += 1;
            }
        }
    }       
}
