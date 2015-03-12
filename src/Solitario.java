
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JOptionPane;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kaña
 */
public class Solitario {
    private static char[][] tablero;
    public static final char FUERA = '-';
    public static final char VACIO = '#';
    public static final char BOLA = 'O';    
           
            
    public static void pintarTablero(){
        tablero = new char [7][7];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if((i<2 || i>4) && (j<2 || j>4)){
                    tablero[i][j]=FUERA;                    
                }else{
                    if(i==3 && j==3){
                        tablero[i][j]=VACIO;
                    }else{
                        tablero[i][j]=BOLA;
                    }
                }
            }
        }
    }
    
    public static void pintarTablero(String nombreArchivoNivel){        
        if(!comprobarFormatoArchivo(nombreArchivoNivel)){
            System.out.println("Error: El archivo no tiene el formato correcto");
        }else{
            String nombreFichero = nombreArchivoNivel;
            BufferedReader br = null;
            try {               
               br = new BufferedReader(new FileReader(nombreFichero));               
               int matrizX = Integer.valueOf(br.readLine());
               int matrizY = Integer.valueOf(br.readLine());
               tablero = new char[matrizX][matrizY];
               String texto = br.readLine();
//               int y=0;
               while(texto != null){
                    for (int y = 0; y < 10; y++) {
                       for (int x = 0; x < texto.length(); x++) {
                        tablero[y][x]=texto.charAt(x);
                        }
                        texto = br.readLine();
                     }
                   
                   
                   
//                   y++;
               }
            }
            catch (FileNotFoundException e) {
                System.out.println("Error: Fichero no encontrado");
                System.out.println(e.getMessage());
            }
            catch(Exception e) {
                System.out.println("Error de lectura del fichero");
                System.out.println(e.getMessage());
            }
            finally {
                try {
                    if(br != null)
                        br.close();
                }
                catch (Exception e) {
                    System.out.println("Error al cerrar el fichero");
                    System.out.println(e.getMessage());
                }
            }
            
            for (int i = 0; i < tablero.length; i++) {
                System.out.print("\n");
                for (int j = 0; j < tablero.length; j++) {
                    System.out.print(""+tablero[i][j]);
                }
            }
            
            
            
            
            
            
            
            
            
        }
    }
    
    private static boolean comprobarFormatoArchivo(String nombreArchivo){
        String todo="";
        boolean correcto=true;
        String nombreFichero = nombreArchivo;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(nombreFichero));
            String texto = br.readLine();
            todo = texto;
            while(texto != null){
                texto=br.readLine();
                if(texto!=null){
                    todo+=texto;
                }
            } 
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
//        for (int y = 0; y < todo.length(); y++) {
//            char c = todo.charAt(y);
//            if(!(c=='\r'&&c=='\n'&&c=='-'&&c=='#'&&c=='O'&&(c>=0 && c<=9))){
//                correcto=false;
//            }
//        }
        
        return correcto;
    }
    
    public static boolean comprobarMovimiento(int origenX, int origenY, int destinoX, int destinoY){
        boolean correcto=true;
        
        //Comprobamos que el movimiento sea Horizontal o Vertical.
        if(origenY!=destinoY && origenX!=destinoX){
            correcto=false;
        }
        
        //Comprobamos que en el origen haya una bola y el destino este vacio.
        if(tablero[origenX][origenY]!=BOLA || tablero[destinoX][destinoY]!=VACIO){
            correcto=false;
        }
        
        //Comprobamos que es un movimiento Horizontal.
        if(origenY==destinoY){
            //Comprobamos que solo hay un hueco entre medio.
            if(Math.abs(origenX-destinoX)!=2){
               correcto=false; 
            }
            //Comprobamos que haya una bola en medio si se mueve a la derecha o a la izquierda.
            if(origenX-destinoX<0 && tablero[origenX+1][origenY]!=BOLA){
                correcto=false;
            }
            if(origenX-destinoX>0 && tablero[origenX-1][origenY]!=BOLA){
                correcto=false;
            }
        }
        
        //Comprobamos que es un movimiento Vertical.
        if(origenX==destinoX){
            //Comprobamos que solo hay un hueco entre medio.
            if(Math.abs(origenY-destinoY)!=2){
               correcto=false; 
            }
            //Comprobamos que haya una bola en medio si se mueve hacia arriba o hacia abajo.
            if(origenY-destinoY<0 && tablero[origenX][origenY+1]!=BOLA){
                correcto=false;
            }
            if(origenY-destinoY>0 && tablero[origenX][origenY-1]!=BOLA){
                correcto=false;
            }
        }
        return correcto;
    }
    
    public static boolean comprobarFinJuego(){
        boolean fin=false;
        int contador=0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if((i!=3 || j!=3) && tablero[i][j]==VACIO){
                    contador++;                    
                }
            }
        }
        if(contador==32 && tablero[3][3]==BOLA){
            fin=true;
        }
        return fin;
    }
    
    public static void mover(int origenX, int origenY, int destinoX, int destinoY){
        if(comprobarMovimiento(origenX, origenY, destinoX, destinoY)){
            tablero[origenX][origenY]=VACIO;
            tablero[destinoX][destinoY]=BOLA;
            if(origenY==destinoY && destinoX>origenX){
                tablero[origenX+1][origenY]=VACIO;
            }else{
                if(origenY==destinoY && destinoX<origenX){
                    tablero[origenX-1][origenY]=VACIO;                    
                }else{
                    if(origenX==destinoX && destinoY>origenY){
                        tablero[origenX][origenY+1]=VACIO;
                    }else{
                        tablero[origenX][origenY-1]=VACIO;
                    }
                }
            }
        }
    }
    
    public static char[][] getTablero() {
        return tablero;
    }
}
