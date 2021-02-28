/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BuscadorV extends javax.swing.JFrame {

    public BuscadorV() {
        initComponents();
    }

    public String rutaAbsoluta(String urlS){
        String url = "file://localhost//"+urlS;
        return url;
    }
     public void vista(String url){
         edpMostrar.setEditable(false); // Para evitar que el usuario pueda modificar
        try {
            edpMostrar.setPage(rutaAbsoluta(url));
        } catch (IOException ex) {
            System.out.println("ERROR: "+ex);
        }
     }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmdBuscar = new javax.swing.JButton();
        HTML = new javax.swing.JScrollPane();
        edpMostrar = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdBuscar.setText("Buscar");
        cmdBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBuscarActionPerformed(evt);
            }
        });

        HTML.setViewportView(edpMostrar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HTML)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(cmdBuscar)
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(HTML, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(cmdBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarActionPerformed
        JFileChooser ficBrowse = new JFileChooser();
        FileFilter docx = new FileNameExtensionFilter("MS Word file(.docx)", "docx");
        FileFilter doc = new FileNameExtensionFilter("MS Word file(.doc)", "doc");
        FileFilter pdf = new FileNameExtensionFilter("Pdf file(.pdf)", "pdf");
        FileFilter txt = new FileNameExtensionFilter("Txt file(.txt)", "txt");
        FileFilter java = new FileNameExtensionFilter("Java file(.java)", "java");
        ficBrowse.addChoosableFileFilter(docx);
        ficBrowse.addChoosableFileFilter(doc);
        ficBrowse.addChoosableFileFilter(pdf);
        ficBrowse.addChoosableFileFilter(txt);
        ficBrowse.addChoosableFileFilter(java);
        ficBrowse.setFileFilter(java);//El que aparece por defecto entre todos los demás
        ficBrowse.setAcceptAllFileFilterUsed(false);
        ficBrowse.setFileSelectionMode(JFileChooser.FILES_ONLY);//Modo de la selección
        int seleccion = ficBrowse.showOpenDialog(this);//Abre el cuadro de dialogo browse
        if (seleccion == JFileChooser.APPROVE_OPTION) {//Si el usuario presiona el botonBoton aceptar
            File f = ficBrowse.getSelectedFile();//Captura el archivo seleccionado
            vista(f.getAbsolutePath());
        }

    }//GEN-LAST:event_cmdBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(BuscadorV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscadorV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscadorV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscadorV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscadorV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane HTML;
    private javax.swing.JButton cmdBuscar;
    private javax.swing.JEditorPane edpMostrar;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
