package borderLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;

public class Border extends javax.swing.JFrame {
    
    private JButton cmdNorte;
    private JButton cmdSur;
    private JButton cmdEste;
    private JButton cmdOeste;
    private JButton cmdCentro;
    private List<JButton> botoneS;
    
    public Border() {
        initComponents();
        personalizarVentana();
        this.setBoton();
        this.personalizarPanel();
        this.addComponentes();
        escucharEvento();
    }
    
    public void personalizarVentana(){
        this.setSize(new Dimension(400,400));
        this.setIconImage(Toolkit.getDefaultToolkit().createImage(Border.class.getResource("mac.jpg")));//Pone un icono al JFrame	
        this.setTitle("DESARROLLO DE INTERFACES");
        this.setLocationRelativeTo(null); 
        this.setResizable(false);
    }

    public void personalizarPanel(){
        cntPanel.setLayout(new BorderLayout());
    }
    
    public void addComponentes(){
        cntPanel.add(cmdNorte, BorderLayout.NORTH);
        cntPanel.add(cmdSur, BorderLayout.SOUTH);
        cntPanel.add(cmdEste, BorderLayout.EAST);
        cntPanel.add(cmdOeste, BorderLayout.WEST);
        cntPanel.add(cmdCentro, BorderLayout.CENTER);
        
    }
    
    public void setBoton(){
        cmdNorte = new JButton();
        cmdSur = new JButton();
        cmdEste = new JButton();
        cmdOeste = new JButton();
        cmdCentro = new JButton();
        cmdNorte.setText("Norte");
        cmdSur.setText("Sur");
        cmdEste.setText("Este");
        cmdOeste.setText("Oeste");
        cmdCentro.setText("Centro");
        botoneS = new ArrayList<>(Arrays.asList(cmdNorte,cmdSur,cmdEste,cmdOeste,cmdCentro));
    }
    
     public void escucharEvento() {
        botoneS.forEach(bt -> {
            bt.addActionListener((eve) -> {
                accionBoton(eve);
            });
        });
    }
    
    public void accionBoton(ActionEvent e) {
        if(e.getSource().getClass() == JButton.class) {
            System.out.println("Boton: "+((JButton)e.getSource()).getText());
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cntPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout cntPanelLayout = new javax.swing.GroupLayout(cntPanel);
        cntPanel.setLayout(cntPanelLayout);
        cntPanelLayout.setHorizontalGroup(
            cntPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        cntPanelLayout.setVerticalGroup(
            cntPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cntPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cntPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Border.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Border.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Border.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Border.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Border().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cntPanel;
    // End of variables declaration//GEN-END:variables
}
