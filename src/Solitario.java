
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.Calendar;
import javax.xml.transform.TransformerException;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;




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
    private static int tamañoTablero;
    public static final char FUERA = '-';
    public static final char VACIO = '#';
    public static final char BOLA = 'O';
    public static final char BOLA_Y_ULTIMA_CASILLA = '@';
    public static final char VACIO_Y_ULTIMA_CASILLA = '%';   
    
    private static ListaMovimientos listaMovimientos = new ListaMovimientos();
    
    private static StopWatch tiempo = new StopWatch();
    
    
    public static void reiniciar(String nivel, String nombreTablero) {
        int contadorBolas=0;
        
        //Cuenta las bolas restantes cuando se reinicio, para mostrarlo en el CSV.
        for (int y = 0; y < tablero.length; y++) {
            for (int x = 0; x < tablero.length; x++) {
                if(tablero[y][x]==BOLA || tablero[y][x]==BOLA_Y_ULTIMA_CASILLA){
                    contadorBolas++;
                }
            }
        }
        CSV.añadirArchivoCSV(nombreTablero, contadorBolas, tiempo);
        
        //Pinta de nuevo el tablero, si se ha cargado uno alguno se pinta ese, si no pinta el tablero por defecto.
        if(nivel!=null){
            Solitario.pintarTablero(nivel);            
        }else{
            Solitario.pintarTablero();
        }
        if(tiempo.isStarted()){
            tiempo.stop();
            tiempo.reset();        
        }        
        listaMovimientos.crearXML();
        listaMovimientos.borrarTodo();
    }    
    
    public static void pintarTablero(){
        tablero = new char [7][7];
        for (int y = 0; y < tablero.length; y++) {
            for (int x = 0; x < tablero.length; x++) {
                if((y<2 || y>4) && (x<2 || x>4)){
                    tablero[y][x]=FUERA;                    
                }else{
                    if(y==3 && x==3){
                        tablero[y][x]=VACIO_Y_ULTIMA_CASILLA;
                    }else{
                        tablero[y][x]=BOLA;
                    }
                }
            }
        }        
    }
    
    public static void pintarTablero(String nombreArchivoNivel){        
        if(!comprobarFormatoArchivo(nombreArchivoNivel)){
            System.out.println("Error4: El archivo no tiene el formato correcto");
        }else{
            String nombreFichero = nombreArchivoNivel;
            BufferedReader br = null;
            try {               
                br = new BufferedReader(new FileReader(nombreFichero));               
                br.readLine();               
                tablero = new char[tamañoTablero][tamañoTablero];                    
                String texto = br.readLine();
                while(texto != null){
                    for (int y = 0; y < tablero.length; y++) {                        
                        for (int x = 0; x < tablero.length; x++) {
                            tablero[y][x]=texto.charAt(x);
                        }
                        texto = br.readLine();                        
                    }
                }
                
                //Pintar en salida
                for (int y = 0; y < tablero.length; y++) {                
                    for (int x = 0; x < tablero.length; x++) {
                        System.out.print(""+tablero[y][x]);
                    }
                    System.out.print("\n");
                }
                System.out.println("\n");
            }
            catch (FileNotFoundException e) {
                System.out.println("Error6: Fichero no encontrado");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            catch(Exception e) {
                System.out.println("Error7 de lectura del fichero");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            finally {
                try {
                    if(br != null)
                        br.close();                    
                }
                catch (Exception e) {
                    System.out.println("Error8 al cerrar el fichero");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static boolean comprobarFormatoArchivo(String nombreArchivoNivel){
        String todo = "";
        String texto ="";
        boolean correcto=false;
        int caracteresCorrectos=0;
        int huecosTablero=0;
        int ultimaCasilla=0;        
        int contadorFilas=-1;
        int contadorColumnasCorrectas=0;        
        BufferedReader br = null;
        try {            
            br = new BufferedReader(new FileReader(nombreArchivoNivel));                        
            texto=br.readLine();
            while(texto != null){  
                int numeroCaracteres=0;
                switch(contadorFilas){ 
                    case -1:
                        tamañoTablero=Integer.valueOf(texto);
                        break;
                    default:
                        for (int i = 0; i < texto.length(); i++){
                            numeroCaracteres++;
                        }
                        break;
                }
                contadorFilas++;
                todo+=texto;
                if(numeroCaracteres==tamañoTablero){
                    contadorColumnasCorrectas++;                    
                } 
                texto=br.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error1: Fichero no encontrado");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e) {
            System.out.println("Error2 de lectura del fichero");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println("Error3 al cerrar el fichero");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        for (int i = 0; i < todo.length(); i++) {
            char c = todo.charAt(i);
            switch(c){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case '\r':
                case '\n':
                case '-':
                case 'O':
                    caracteresCorrectos++;
                    break;
                case '%': 
                    ultimaCasilla++;
                    caracteresCorrectos++;
                    huecosTablero++;
                    break;
                case '#':
                    caracteresCorrectos++;
                    huecosTablero++;
                    break;
                case '@':
                    caracteresCorrectos++;
                    ultimaCasilla++;
                    break;
            }
        }
        if (caracteresCorrectos==todo.length()-1 && ultimaCasilla==1 && (tamañoTablero>=4 && tamañoTablero<=9)
                && contadorFilas==tamañoTablero && contadorColumnasCorrectas==contadorFilas && huecosTablero>=1){
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
    
    public static boolean comprobarFinJuego() throws TransformerException{
        boolean fin=false;
        int Y_Final_Juego=0;
        int X_Final_Juego=0;
        int numeroBolasTotales=0;
        for (int y = 0; y < tablero.length; y++) {
            for (int x = 0; x < tablero.length; x++) {
                if(tablero[y][x]==BOLA || tablero[y][x]==BOLA_Y_ULTIMA_CASILLA){
                    numeroBolasTotales++;
                }
                if(tablero[y][x]==BOLA_Y_ULTIMA_CASILLA || tablero[y][x]==VACIO_Y_ULTIMA_CASILLA){
                    Y_Final_Juego=y;
                    X_Final_Juego=x;
                }
            }
        }
        if(numeroBolasTotales==1 && tablero[Y_Final_Juego][X_Final_Juego]==BOLA_Y_ULTIMA_CASILLA){
            fin=true;
            listaMovimientos.crearXML();
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
            if(!tiempo.isStarted()){
                tiempo.start();                
            }
            listaMovimientos.añadirMovimiento(origenY, origenX, destinoY, destinoX);
            return true;
        }else{
            return false;
        }
    }
    
    public static void deshacerMovimiento(){
        if(!listaMovimientos.comprobarVacio()){
           int origenY=listaMovimientos.obtenerUltimoMovimiento().getOrigenY();
            int origenX=listaMovimientos.obtenerUltimoMovimiento().getOrigenX();
            int destinoY=listaMovimientos.obtenerUltimoMovimiento().getDestinoY();
            int destinoX=listaMovimientos.obtenerUltimoMovimiento().getDestinoX();
            //Sustituimos el vacio del origen por una bola, segun si es ultima casilla o no.
            if(tablero[origenY][origenX]==VACIO){
                tablero[origenY][origenX]=BOLA;
            }else{
                tablero[origenY][origenX]=BOLA_Y_ULTIMA_CASILLA;
            }

            //Sustituimos la bola de la casilla de destino por vacio, segun si es ultima casilla o no.
            if(tablero[destinoY][destinoX]==BOLA){
                tablero[destinoY][destinoX]=VACIO;
            }else{
                tablero[destinoY][destinoX]=VACIO_Y_ULTIMA_CASILLA;
            }

            //Comprobamos la bola intermedia y la sustituimos segun si es ultima casilla o no.
            //Misma coordenada Y, hacia la derecha.
            if(origenX==destinoX && destinoY>origenY){                
                if(tablero[origenY+1][origenX]==VACIO){
                    tablero[origenY+1][origenX]=BOLA;
                }else{
                    tablero[origenY+1][origenX]=BOLA_Y_ULTIMA_CASILLA;
                }
                //Misma coordenada Y, hacia la izquierda
            }else{
                if(origenX==destinoX && destinoY<origenY){                        
                    if(tablero[origenY-1][origenX]==VACIO){
                        tablero[origenY-1][origenX]=BOLA;
                    }else{
                        tablero[origenY-1][origenX]=BOLA_Y_ULTIMA_CASILLA;
                    }
                }else{
                    //Misma coordenada X, hacia abajo.
                    if(origenY==destinoY && destinoX>origenX){
                        if(tablero[origenY][origenX+1]==VACIO){
                            tablero[origenY][origenX+1]=BOLA;
                        }else{
                            tablero[origenY][origenX+1]=BOLA_Y_ULTIMA_CASILLA;
                        }
                    }else{
                        //Misma coordenada X, hacia arriba.
                        if(origenY==destinoY && destinoX<origenX){
                            if(tablero[origenY][origenX-1]==VACIO){
                                tablero[origenY][origenX-1]=BOLA;
                            }else{
                                tablero[origenY][origenX-1]=BOLA_Y_ULTIMA_CASILLA;
                            } 
                        }                        
                    }
                }
            }
            listaMovimientos.borrarUltimoMovimiento(); 
        }
        
    }       
    
    public static char[][] getTablero() {
        return tablero;
    }
}
