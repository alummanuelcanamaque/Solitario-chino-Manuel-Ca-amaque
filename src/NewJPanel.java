
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Manuel Cañamaque
 */
public class NewJPanel extends javax.swing.JPanel {
    int anchoCasilla=50;
    int anchoBola=40;
    int coordXInicial;
    int coordXInicialTablero;
    int coordYInicial;
    int coordYInicialTablero;
    int coordXFinal;
    int coordXFinalTablero;
    int coordYFinal;
    int coordYFinalTablero;
    
    boolean arrastrando = false;
    boolean mover = false;
    
    
    Color colorBola = new Color(1, 1, 223);
    Color colorTablero = new Color(88, 88, 88);
    
    public NewJPanel() {
        initComponents();        
        
        
        
    }  
    
        
    @Override
    public void paint(Graphics g) {
        if(Solitario.getTablero()!=null){
            super.paint(g);        
    //        int[]xPoints = {200,500,500,700,700,500,500,200,200,0,0,200,200};
    //        int[]yPoints = {0,0,200,200,500,500,700,700,500,500,200,200,0};
    //        g.drawPolygon(xPoints, yPoints, 13);
    //        g.setColor(colorTablero);
    //        g.fillPolygon(xPoints, yPoints, 13);        

            for (int y = 0; y < Solitario.getTablero().length; y++) {
                for (int x = 0; x < Solitario.getTablero().length; x++) {
                    if(Solitario.getTablero()[y][x]==Solitario.BOLA){
                        g.setColor(colorBola);
                        g.fillOval(x*anchoCasilla+anchoCasilla/4, y*anchoCasilla+anchoCasilla/4, anchoBola, anchoBola);
                    }
                    if(Solitario.getTablero()[y][x]==Solitario.VACIO){
                        g.setColor(Color.BLACK);
                        g.fillOval(x*anchoCasilla+anchoCasilla/4, y*anchoCasilla+anchoCasilla/4, anchoBola, anchoBola);
                    }
                }
            }
            if(arrastrando && Solitario.getTablero()[coordXInicialTablero][coordYInicialTablero]==Solitario.BOLA){
                System.out.println("Inicial: "+coordXInicial+" "+coordYInicial);            
                System.out.println("Final: "+coordXFinal+" "+coordYFinal);
                Solitario.getTablero()[coordXInicialTablero][coordYInicialTablero]=Solitario.VACIO;
                g.fillOval(coordXFinal-anchoBola/2, coordYFinal-anchoBola/2, anchoBola, anchoBola);
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        System.out.println("Pressed--> X: "+coordXInicial+" XT: "+coordXInicialTablero+" Y: "+coordYInicial+" YT: "+coordYInicialTablero);
        Point punto = new Point((evt.getX()/anchoCasilla)*anchoCasilla+anchoBola,(evt.getY()/anchoCasilla)*anchoCasilla+anchoBola );
        if(evt.getPoint().distance(punto)<anchoBola/2){
            coordXInicial = evt.getX();
            coordYInicial = evt.getY();
            coordXInicialTablero= coordXInicial/anchoCasilla;
            coordYInicialTablero= coordYInicial/anchoCasilla;
            mover=true;            
        }
        
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        System.out.println("Released--> X: "+coordXInicial+" XT: "+coordXInicialTablero+" Y: "+coordYInicial+" YT: "+coordYInicialTablero);
        try {
            if (mover) {
                mover = false;
                arrastrando = false;
                coordXFinal = evt.getX();
                coordYFinal = evt.getY();
                coordXFinalTablero= coordXFinal/anchoCasilla;
                coordYFinalTablero= coordYFinal/anchoCasilla;
                if (!Solitario.comprobarMovimiento(coordXInicialTablero, coordYInicialTablero, coordXFinalTablero, coordYFinalTablero)
                        && ((coordXInicialTablero > 1 && coordXInicialTablero < 5) || (coordYInicialTablero > 1 && coordYInicialTablero < 5))) {                    
                    Solitario.getTablero()[coordXInicial][coordYInicial] = Solitario.BOLA;
                }
                Solitario.mover(coordXInicialTablero, coordYInicialTablero, coordXFinalTablero, coordYFinalTablero);
                repaint();
                if (Solitario.comprobarFinJuego()) {                    
                    JOptionPane.showMessageDialog(this, "Enhorabuena has ganado");
                    Solitario.pintarTablero();
                     repaint();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            repaint();
        }
        
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        System.out.println("Dragged--> X: "+coordXInicial+" XT: "+coordXInicialTablero+" Y: "+coordYInicial+" YT: "+coordYInicialTablero);
        if(mover){
            arrastrando=true;        
            coordXFinal = evt.getX();
            coordYFinal = evt.getY();
            coordXFinalTablero= coordXFinal/anchoCasilla;
            coordYFinalTablero= coordYFinal/anchoCasilla;
            repaint();
        }
    }//GEN-LAST:event_formMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
