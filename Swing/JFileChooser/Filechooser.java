package filechooser;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Filechooser extends javax.swing.JFrame {

    public Filechooser() {
        initComponents();
        txtBrowse.setEditable(false);
    }

    public void newFilechooser(){
        JFileChooser ficBrowse = new JFileChooser();
        
        // Archivos admitidos
        FileFilter docx = new FileNameExtensionFilter("MS Word file(.docx)", "docx");
        FileFilter doc = new FileNameExtensionFilter("MS Word file(.doc)", "doc");
        FileFilter pdf = new FileNameExtensionFilter("Pdf file(.pdf)", "pdf");
        FileFilter txt = new FileNameExtensionFilter("Txt file(.txt)", "txt");
        FileFilter classs = new FileNameExtensionFilter("Class file(.class)", "class");
        
        // Añadimos los filtros a nuestro JFileChooser
        ficBrowse.addChoosableFileFilter(docx);
        ficBrowse.addChoosableFileFilter(doc);
        ficBrowse.addChoosableFileFilter(pdf);
        ficBrowse.addChoosableFileFilter(txt);
        ficBrowse.addChoosableFileFilter(classs);
        ficBrowse.setFileFilter(classs);//El que aparece por defecto entre todos los demás
        
        // Indicamos que no aceptamos cualquier tipo de archivo (.*)
        ficBrowse.setAcceptAllFileFilterUsed(false);
        
        // Inciamos que solo aceptamos Archivos
        ficBrowse.setFileSelectionMode(JFileChooser.FILES_ONLY);//Modo de la selección
        
        /*
        Este nos abrirá el buscador y nos devolverá una selección, por tanto, la 
        ejecución que bloqueará aqui hasta que nos devuelva una selección marcada por 
        el usuario. Cuando el usuario haya decidido, pasaremos al if, donde valoraremos 
        si la selección ha sido aceptada o no (se dió a cancelar)
        */
        int seleccion = ficBrowse.showOpenDialog(this);
        System.out.println("Selección: " + seleccion);
        if (seleccion == JFileChooser.APPROVE_OPTION) {//Si el usuario presiona el botonBoton aceptar
            File f = ficBrowse.getSelectedFile();//Captura el archivo seleccionado
            txtBrowse.setText(f.getAbsolutePath());//Obtengo la ruta absoluta del archivo seleccionado anteriormente
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtBrowse = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBrowseActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(txtBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(txtBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jButton1)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        newFilechooser();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBrowseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBrowseActionPerformed

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
            java.util.logging.Logger.getLogger(Filechooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Filechooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Filechooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Filechooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Filechooser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtBrowse;
    // End of variables declaration//GEN-END:variables
}
