import java.awt.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;

public class Descifrado {
    boolean ok = true;
    boolean Descifrararch(SecretKey clavef, String path) throws Exception {
      try{
          
       
      Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
          
      cifrado.init(Cipher.DECRYPT_MODE, clavef);
      
      byte[] buffer = new byte[1000]; // leer cada mil
         byte[] bufferPlano;
         
         
         // generar mis archivos
         
         FileInputStream in = new FileInputStream(path);
         FileOutputStream out = new FileOutputStream(path+".descifrado");
         
         //lectura
         
         int bytesleidos = in.read(buffer, 0, 1000);
         // mientras no este al final del archivo
         
         while(bytesleidos != -1){
             //pasar el texto claro leido al cifrador
             
             bufferPlano = cifrado.update(buffer, 0, bytesleidos);
             //generar la salida
             out.write(bufferPlano);
             bytesleidos = in.read(buffer, 0, 1000);
         }
         // vamos a reunir todos los bloques del cifrado
         bufferPlano = cifrado.doFinal();
         // ya puedo escribir el archivo cifrado
         
         out.write(bufferPlano);
         
         in.close();
         out.close();
         
      }catch(InvalidKeyException e){
          System.out.println("Error");
          JOptionPane.showMessageDialog(null,"Error\n Recuerda que la clave tiene que ser de 8 bytes");
           System.out.println(e);
           ok = false;
           
    }
      return ok;
    
      
}
    
   SecretKey originalKey;
    public  SecretKey convertStringToSecretKeyto(String claves) {
        try{
    byte[] decodedKey = Base64.getDecoder().decode(claves);
     originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
    
        }catch(Exception e){
         originalKey = null;
        }
        return originalKey;
}
    String url;
     public String SubiraArchivo(){
      
      JFileChooser file = new JFileChooser();
      
      file.showOpenDialog(file);
      File abrir = file.getSelectedFile();
      
      if(abrir!= null){
          url = abrir.getPath();
      }
      System.out.println(url);
     return url;
  }
                     
}
