
package ec.edu.espol.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.Oferta;
import modelo.Vehiculo;


public class Util {
   private Util(){}
    
    public static int nextID(String nfile){
        int id = 0;
        try(Scanner sc = new Scanner(new File(nfile))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                id = Integer.parseInt(tokens[0]);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id+1;
    }
    
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
 
        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
     
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
 
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
 
        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
    }
    
    public static void crearArchivo(String nfile){
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nfile),true));
            pw.close();
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void eliminarInformacion(String nfile,int idLine){
        String tempFile = "temp.txt";
        File oldFile = new File(nfile);
        File newFile = new File(tempFile);
        
        int line = 0;
        String currentline;
        
        try{
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            FileReader fr = new FileReader(nfile);
            BufferedReader br = new BufferedReader(fr);
            
            while((currentline = br.readLine())!= null){
                line++;
                
                if(idLine != line){
                    pw.println(currentline);
                }
            }
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();
            
            oldFile.delete();
            File dump = new File(nfile);
            newFile.renameTo(dump);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
   
       
    
    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        String correo = "isaac.criollo04@gmail.com";
        String clave = "ddsuwhakqwznslyc";
        
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", correo);
        props.put("mail.smtp.clave", correo);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        
        try {
        message.setFrom(new InternetAddress(correo));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
        message.setSubject(asunto);
        message.setText(cuerpo);
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", correo, clave);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
    public static boolean isNumeric(String str) { 
    try {  
      Float.parseFloat(str);  
      return true;
    } catch(NumberFormatException e){  
      return false;  
    }  
  }
    
}
