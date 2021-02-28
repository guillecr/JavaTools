package ventanasEmergentes;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VentanaMenu extends javax.swing.JFrame {
    public VentanaMenu() {
        initComponents();
        cntPrincipal.setLayout(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cntPrincipal = new javax.swing.JPanel();
        cmdError = new javax.swing.JButton();
        cmdAdvertencia = new javax.swing.JButton();
        cmdPregunta = new javax.swing.JButton();
        cmdInformacion = new javax.swing.JButton();
        cmdComentario = new javax.swing.JButton();
        cmdSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdError.setText("Error");
        cmdError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdErrorActionPerformed(evt);
            }
        });

        cmdAdvertencia.setText("Advertencia");
        cmdAdvertencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAdvertenciaActionPerformed(evt);
            }
        });

        cmdPregunta.setText("Pregunta");
        cmdPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPreguntaActionPerformed(evt);
            }
        });

        cmdInformacion.setText("Informacion");
        cmdInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdInformacionActionPerformed(evt);
            }
        });

        cmdComentario.setText("Comentario");
        cmdComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdComentarioActionPerformed(evt);
            }
        });

        cmdSalir.setText("Salir");
        cmdSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cntPrincipalLayout = new javax.swing.GroupLayout(cntPrincipal);
        cntPrincipal.setLayout(cntPrincipalLayout);
        cntPrincipalLayout.setHorizontalGroup(
            cntPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cntPrincipalLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(cntPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cntPrincipalLayout.createSequentialGroup()
                        .addComponent(cmdError, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmdAdvertencia, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cntPrincipalLayout.createSequentialGroup()
                        .addComponent(cmdPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmdComentario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(cntPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdInformacion)
                    .addComponent(cmdSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        cntPrincipalLayout.setVerticalGroup(
            cntPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cntPrincipalLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(cntPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdError)
                    .addComponent(cmdAdvertencia)
                    .addComponent(cmdInformacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cntPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdPregunta)
                    .addComponent(cmdComentario)
                    .addComponent(cmdSalir))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cntPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cntPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // ERROR
    private void cmdErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdErrorActionPerformed
        JFrame ventana = new JFrame();
        JOptionPane.showMessageDialog(ventana,"MENSAJE","ERROR", JOptionPane.ERROR_MESSAGE);
        
    }//GEN-LAST:event_cmdErrorActionPerformed

    // SALIR
    private void cmdSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cmdSalirActionPerformed

    // ADVERTENCIA
    private void cmdAdvertenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAdvertenciaActionPerformed
        JFrame ventana = new JFrame();
        JOptionPane.showMessageDialog(ventana,"MENSAJE","ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_cmdAdvertenciaActionPerformed

    // INFORMACION
    private void cmdInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdInformacionActionPerformed
        JFrame ventana = new JFrame();
        JOptionPane.showMessageDialog(ventana,"MENSAJE","INFORMACION", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_cmdInformacionActionPerformed

    // PREGUNTA
    private void cmdPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPreguntaActionPerformed
        JFrame ventana = new JFrame();
        JOptionPane.showMessageDialog(ventana,"MENSAJE","PREGUNTA", JOptionPane.QUESTION_MESSAGE);
    }//GEN-LAST:event_cmdPreguntaActionPerformed

    // COMENTARIO
    private void cmdComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdComentarioActionPerformed
        JFrame ventana = new JFrame();
        JOptionPane.showMessageDialog(ventana,"MENSAJE","COMENTARIO", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_cmdComentarioActionPerformed

    
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
            java.util.logging.Logger.getLogger(VentanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAdvertencia;
    private javax.swing.JButton cmdComentario;
    private javax.swing.JButton cmdError;
    private javax.swing.JButton cmdInformacion;
    private javax.swing.JButton cmdPregunta;
    private javax.swing.JButton cmdSalir;
    private javax.swing.JPanel cntPrincipal;
    // End of variables declaration//GEN-END:variables
}
