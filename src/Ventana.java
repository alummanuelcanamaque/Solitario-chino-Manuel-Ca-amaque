

import java.awt.Dimension;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Manuel Cañamaque
 */
public class Ventana extends javax.swing.JFrame {
    static String nivel;
    static String nombreTablero;
    
    static ResourceBundle bundle = ResourceBundle.getBundle("res/Strings", Locale.getDefault());
    /**
     * Creates new form Ventana
     */
    public Ventana() {        
        initComponents();
        this.setLocationRelativeTo(null);
        Solitario.pintarTablero();
        CSV.crearArchivoCSV();
        this.setSize(Solitario.getTablero().length * newJPanel1.anchoCasilla+6, Solitario.getTablero().length * newJPanel1.anchoCasilla + 160);
        newJPanel1.setSize(Solitario.getTablero().length * newJPanel1.anchoCasilla,Solitario.getTablero().length * newJPanel1.anchoCasilla);
        newJPanel1.repaint();
        this.repaint();

//        UIManager.put("OptionPane.cancelButtonText", "Annuler");
//        UIManager.put("OptionPane.noButtonText", "Non");
//        UIManager.put("OptionPane.okButtonText", "D'accord");
//        UIManager.put("OptionPane.yesButtonText", "Oui");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        newJPanel1 = new NewJPanel();
        jPanel1 = new javax.swing.JPanel();
        boton_reiniciar = new javax.swing.JButton();
        boton_Abrir_Archivo = new javax.swing.JButton();
        boton_Deshacer = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        selector_Bola = new javax.swing.JSlider();
        selector_Casilla = new javax.swing.JSlider();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("res/Strings"); // NOI18N
        setTitle(bundle.getString("titulo")); // NOI18N
        setMinimumSize(new java.awt.Dimension(300, 0));
        setPreferredSize(new java.awt.Dimension(700, 922));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        newJPanel1.setMaximumSize(new java.awt.Dimension(1200, 1200));
        newJPanel1.setPreferredSize(new java.awt.Dimension(700, 700));

        javax.swing.GroupLayout newJPanel1Layout = new javax.swing.GroupLayout(newJPanel1);
        newJPanel1.setLayout(newJPanel1Layout);
        newJPanel1Layout.setHorizontalGroup(
            newJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        newJPanel1Layout.setVerticalGroup(
            newJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        boton_reiniciar.setText(bundle.getString("botonReiniciar")); // NOI18N
        boton_reiniciar.setActionCommand(bundle.getString("botonReiniciar")); // NOI18N
        boton_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reiniciarActionPerformed(evt);
            }
        });

        boton_Abrir_Archivo.setText(bundle.getString("botonNivel")); // NOI18N
        boton_Abrir_Archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Abrir_ArchivoActionPerformed(evt);
            }
        });

        boton_Deshacer.setText(bundle.getString("botonDeshacer")); // NOI18N
        boton_Deshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_DeshacerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_Abrir_Archivo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(boton_reiniciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boton_Deshacer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_Deshacer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton_reiniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton_Abrir_Archivo)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel1.setText(bundle.getString("labelTablero")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel2.setText(bundle.getString("labelBolas")); // NOI18N

        selector_Bola.setMaximum(75);
        selector_Bola.setMinimum(25);
        selector_Bola.setToolTipText("Tamaño Bola");
        selector_Bola.setValue(40);
        selector_Bola.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selector_Bola.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                selector_BolaStateChanged(evt);
            }
        });

        selector_Casilla.setMinimum(50);
        selector_Casilla.setToolTipText("Tamaño Tablero");
        selector_Casilla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selector_Casilla.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                selector_CasillaStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selector_Bola, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(selector_Casilla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selector_Casilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addComponent(selector_Bola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(newJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(newJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        newJPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reiniciarActionPerformed
        Solitario.reiniciar(nivel, nombreTablero);            
        repaint();        
    }//GEN-LAST:event_boton_reiniciarActionPerformed

    private void boton_Abrir_ArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Abrir_ArchivoActionPerformed
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos TXT", "txt");
        fc.setFileFilter(filter);        
        fc.setCurrentDirectory(new File("C:\\Users\\Manuel Cañamaque\\Google Drive\\Classroom\\1ºDAW\\Programacion\\Tema 5\\Solitario")); 
        int respuesta = fc.showOpenDialog(this);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File archivoElegido = fc.getSelectedFile();
            if(Solitario.comprobarFormatoArchivo(archivoElegido.getPath())){
                nivel=archivoElegido.getPath();
                nombreTablero = archivoElegido.getName();
                Solitario.reiniciar(nivel, nombreTablero);
                this.setSize(Solitario.getTablero().length * newJPanel1.anchoCasilla+6, Solitario.getTablero().length * newJPanel1.anchoCasilla + 160);
                this.repaint();
                Dimension dimension= new Dimension(Solitario.getTablero().length * newJPanel1.anchoCasilla,Solitario.getTablero().length * newJPanel1.anchoCasilla);
                newJPanel1.setSize(dimension);
                newJPanel1.repaint();
            }else{                
                JOptionPane.showMessageDialog(this, bundle.getString("mensajeError"), bundle.getString("mensajeErrorTitulo"), JOptionPane.ERROR_MESSAGE);                
            }
        }
    }//GEN-LAST:event_boton_Abrir_ArchivoActionPerformed

    private void boton_DeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_DeshacerActionPerformed
        Solitario.deshacerMovimiento();
        repaint();
    }//GEN-LAST:event_boton_DeshacerActionPerformed

    private void selector_CasillaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_selector_CasillaStateChanged
        newJPanel1.setAnchoCasilla(selector_Casilla.getValue());
        this.setSize(Solitario.getTablero().length * newJPanel1.anchoCasilla+6, Solitario.getTablero().length * newJPanel1.anchoCasilla + 160);
        this.repaint();
    }//GEN-LAST:event_selector_CasillaStateChanged

    private void selector_BolaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_selector_BolaStateChanged
        newJPanel1.setAnchoBola(selector_Bola.getValue());
        this.setSize(Solitario.getTablero().length * newJPanel1.anchoCasilla+6, Solitario.getTablero().length * newJPanel1.anchoCasilla + 160);
        this.repaint();
    }//GEN-LAST:event_selector_BolaStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Solitario.reiniciar(nivel, nombreTablero);
        CSV.cerrarArchivoCSV();
        
    }//GEN-LAST:event_formWindowClosing
       
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_Abrir_Archivo;
    private javax.swing.JButton boton_Deshacer;
    private javax.swing.JButton boton_reiniciar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private NewJPanel newJPanel1;
    private javax.swing.JSlider selector_Bola;
    private javax.swing.JSlider selector_Casilla;
    // End of variables declaration//GEN-END:variables
}
