
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaña
 */
public class ListaMovimientos {
    private ArrayList<Movimiento> listaMovimientos =  new ArrayList();
    private Movimiento movimiento;
    
    public void añadirMovimiento(int origenY, int origenX, int destinoY, int destinoX){
        movimiento = new Movimiento(origenY, origenX, destinoY, destinoX);
        listaMovimientos.add(movimiento);
    }
    
    public  Movimiento obtenerUltimoMovimiento(){        
        return listaMovimientos.get(listaMovimientos.size()-1);        
    }
    
    public boolean comprobarVacio(){
        boolean vacio=true;
        if(!listaMovimientos.isEmpty()){
            vacio=false;
        }
        return vacio;
    }
    public void borrarTodo(){
        listaMovimientos.clear();
    }

    public void borrarUltimoMovimiento(){
        listaMovimientos.remove(listaMovimientos.size()-1);
    }
}
