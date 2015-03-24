
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.Calendar;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaña
 */
public  class CSV {
    private static Calendar calendar = Calendar.getInstance();
    
    private static DateFormat formatoDate = DateFormat.getDateInstance();
    private static File f=new File("jugadas.csv");
    private static BufferedWriter bw = null;
    
    
    public static void crearArchivoCSV(){
        try {
            if(!f.exists()){                
                bw = new BufferedWriter(new FileWriter(f, true));
                bw.write("Fecha; Nombre Nivel;, Bolas Restantes; Tiempo Empleado"); 
            }else{
                bw = new BufferedWriter(new FileWriter(f, true));
            }
        }
        catch(Exception e) {
           System.out.println("Error de escritura del fichero");
           System.out.println(e.getMessage());
           e.printStackTrace();
        }
    }
    public static void añadirArchivoCSV(String nombreTablero, int bolasRestantes, StopWatch tiempo){ 
        String nombreFinalTablero;
        if(nombreTablero==null){
            nombreFinalTablero="Tablero por defecto";
        }else{
            nombreFinalTablero=nombreTablero;
        }
        String fecha = formatoDate.format(calendar.getTime());
        String duracion = DurationFormatUtils.formatDuration(tiempo.getTime(), "mm:ss");        
        
        try {
            bw.newLine();
            bw.write(fecha+";"+nombreFinalTablero+";"+bolasRestantes+";"+ duracion);
        }
        catch(Exception e) {
           System.out.println("Error de escritura del fichero");
           System.out.println(e.getMessage());
           e.printStackTrace();
        }
    }
    public static void cerrarArchivoCSV(){
        try {
            if(bw != null)
                bw.close();
        }
        catch (Exception e) {
            System.out.println("Error al cerrar el fichero");
            System.out.println(e.getMessage());
        }
    }
}