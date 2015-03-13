
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;




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
    public static final char BOLA_Y_ULTIMA_CASILLA = '@';
    public static final char VACIO_Y_ULTIMA_CASILLA = '%';
            
    public static void pintarTablero(){
        tablero = new char [7][7];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if((i<2 || i>4) && (j<2 || j>4)){
                    tablero[i][j]=FUERA;                    
                }else{
                    if(i==3 && j==3){
                        tablero[i][j]=VACIO_Y_ULTIMA_CASILLA;
                    }else{
                        tablero[i][j]=BOLA;
                    }
                }
            }
        }
    }
    
    public static boolean pintarTablero(String nombreArchivoNivel){        
        if(!comprobarFormatoArchivo(nombreArchivoNivel)){
            System.out.println("Error: El archivo no tiene el formato correcto");
            return false;
        }else{
            String nombreFichero = nombreArchivoNivel;
            BufferedReader br = null;
            try {               
                br = new BufferedReader(new FileReader(nombreFichero));               
                int matrizX = Integer.valueOf(br.readLine());
                int matrizY = Integer.valueOf(br.readLine());
                if((matrizX>=4 && matrizX<=9) && (matrizY>=4 && matrizY<=9)){
                    tablero = new char[matrizX][matrizY];
                    String texto = br.readLine();

                    while(texto != null){
                        for (int y = 0; y < tablero.length; y++) {
                            for (int x = 0; x < tablero.length; x++) {
                                tablero[y][x]=texto.charAt(x);
                            }
                            texto = br.readLine();                        
                        }
                    }
                }else{
                    System.out.println("Error de lectura del fichero");
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
            
            for (int y = 0; y < tablero.length; y++) {
                System.out.print("\n");
                for (int x = 0; x < tablero.length; x++) {
                    System.out.print(""+tablero[y][x]);
                }
            }
            return true;
        }
    }
    
    private static boolean comprobarFormatoArchivo(String nombreArchivoNivel){
        String todo="";
        boolean correcto=false;
        int caracteresCorrectos=0;
        
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(nombreArchivoNivel));
            String texto = br.readLine();            
            while(texto != null){
                todo+=texto;
                texto=br.readLine();                
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
        for (int i = 0; i < todo.length(); i++) {
            char c = todo.charAt(i);
            if(c=='\r' || c=='\n' || c=='-' || c=='#' || c=='O' || c=='%' || c=='@'|| (c>='0' && c<='9')){
                ++caracteresCorrectos;
            }
            
        }
        if (caracteresCorrectos==todo.length()){
            correcto=true;
        }
        return correcto;
    }
    
    public static boolean comprobarMovimiento(int origenY, int origenX, int destinoY, int destinoX){
        boolean correcto=true;
        
        //Comprobamos que el movimiento sea Horizontal o Vertical.
        if(origenX!=destinoX && origenY!=destinoY){
            correcto=false;
        }
        
        //Comprobamos que en el origen haya una bola y el destino este vacio.
        if((tablero[origenY][origenX]!=BOLA && tablero[origenY][origenX]!=BOLA_Y_ULTIMA_CASILLA) || (tablero[destinoY][destinoX]!=VACIO && tablero[destinoY][destinoX]!=VACIO_Y_ULTIMA_CASILLA )){
            correcto=false;
        }
        
        //Comprobamos que es un movimiento Horizontal.
        if(origenX==destinoX){
            //Comprobamos que solo hay un hueco entre medio.
            if(Math.abs(origenY-destinoY)!=2){
               correcto=false;
            }
            //Comprobamos que haya una bola en medio si se mueve a la derecha o a la izquierda.
            if(origenY-destinoY<0 && (tablero[origenY+1][origenX]!=BOLA && tablero[origenY+1][origenX]!=BOLA_Y_ULTIMA_CASILLA)){
                correcto=false;
            }
            if(origenY-destinoY>0 && (tablero[origenY-1][origenX]!=BOLA && tablero[origenY-1][origenX]!=BOLA_Y_ULTIMA_CASILLA)){
                correcto=false;
            }
        }
        
        //Comprobamos que es un movimiento Vertical.
        if(origenY==destinoY){
            //Comprobamos que solo hay un hueco entre medio.
            if(Math.abs(origenX-destinoX)!=2){
               correcto=false; 
            }
            //Comprobamos que haya una bola en medio si se mueve hacia arriba o hacia abajo.
            if(origenX-destinoX<0 && (tablero[origenY][origenX+1]!=BOLA && tablero[origenY][origenX+1]!=BOLA_Y_ULTIMA_CASILLA)){
                correcto=false;
            }
            if(origenX-destinoX>0 && (tablero[origenY][origenX-1]!=BOLA && tablero[origenY][origenX-1]!=BOLA_Y_ULTIMA_CASILLA)){
                correcto=false;
            }
        }
        return correcto;
    }
    
    public static boolean comprobarFinJuego(){
        boolean fin=false;
        int contador=0;
        int Y_Final_Juego=0;
        int X_Final_Juego=0;
        int numeroBolasTotales=0;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                switch(tablero[y][x]){
                    case(Solitario.VACIO):
                        ++contador;
                        break;
                    case(Solitario.BOLA):
                        ++numeroBolasTotales;
                        break;
                    case(Solitario.BOLA_Y_ULTIMA_CASILLA):
                        ++numeroBolasTotales;
                        Y_Final_Juego=y;
                        X_Final_Juego=x;
                        break;
                    case(Solitario.VACIO_Y_ULTIMA_CASILLA):
                        Y_Final_Juego=y;
                        X_Final_Juego=x;
                        break;
                }  
            }
        }
        if(contador==numeroBolasTotales-1 && tablero[Y_Final_Juego][X_Final_Juego]==BOLA){
            fin=true;
        }
        return fin;
    }
    
    public static boolean mover(int origenY, int origenX, int destinoY, int destinoX){
        if(comprobarMovimiento(origenY, origenX, destinoY, destinoX)){
            //Comprobamos la bola que hay en el origen y la sustituimos por vacio.
            if(tablero[origenY][origenX]==BOLA){
                tablero[origenY][origenX]=VACIO;
            }else{
                if(tablero[origenY][origenX]==BOLA_Y_ULTIMA_CASILLA){
                    tablero[origenY][origenX]=VACIO_Y_ULTIMA_CASILLA;
                }
            }
            //Comprobamos el vacio que hay en el destino y lo sustituimos por bola.
            if(tablero[destinoY][destinoX]==VACIO){
                tablero[destinoY][destinoX]=BOLA;
            }else{
                if(tablero[destinoY][destinoX]==VACIO_Y_ULTIMA_CASILLA){
                    tablero[destinoY][destinoX]=BOLA_Y_ULTIMA_CASILLA;
                }
            }            
            //Comprobamos la bola intermedia y la sustituimos segun si es ultima casilla o no.
            //Misma coordenada Y, hacia la derecha.
            if(origenX==destinoX && destinoY>origenY){                
                if(tablero[origenY+1][origenX]==BOLA){
                    tablero[origenY+1][origenX]=VACIO;
                }else{
                    tablero[origenY+1][origenX]=VACIO_Y_ULTIMA_CASILLA;
                }
            //Misma coordenada Y, hacia la izquierda
            }else{
                if(origenX==destinoX && destinoY<origenY){                        
                    if(tablero[origenY-1][origenX]==BOLA){
                        tablero[origenY-1][origenX]=VACIO;
                    }else{
                        tablero[origenY-1][origenX]=VACIO_Y_ULTIMA_CASILLA;
                    }
                }else{
                    //Misma coordenada X, hacia abajo.
                    if(origenY==destinoY && destinoX>origenX){
                        if(tablero[origenY][origenX+1]==BOLA){
                            tablero[origenY][origenX+1]=VACIO;
                        }else{
                            tablero[origenY][origenX+1]=VACIO_Y_ULTIMA_CASILLA;
                        }
                    }else{
                        //Misma coordenada X, hacia arriba.
                        if(origenY==destinoY && destinoX<origenX){
                           if(tablero[origenY][origenX-1]==BOLA){
                                tablero[origenY][origenX-1]=VACIO;
                            }else{
                                tablero[origenY][origenX-1]=VACIO_Y_ULTIMA_CASILLA;
                            } 
                        }                        
                    }
                }
            }
            return true;
        }else{
            return false;
        }
    }
    
    public static char[][] getTablero() {
        return tablero;
    }
}
