
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaña
 */
public class Movimiento {
    private int origenX;
    private int origenY;
    private int destinoX;
    private int destinoY;
    
    
    public Movimiento(int origenY, int origenX, int destinoY, int destinoX){
        this.origenX=origenX;
        this.origenY=origenY;
        this.destinoX=destinoX;
        this.destinoY=destinoY;
        
    }

    public int getOrigenX() {
        return origenX;
    }

    public int getOrigenY() {
        return origenY;
    }

    public int getDestinoX() {
        return destinoX;
    }

    public int getDestinoY() {
        return destinoY;
    }
    
}
