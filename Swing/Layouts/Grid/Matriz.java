package grid;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;


public class Matriz extends javax.swing.JFrame {
    
    private final int elementos = 26;
    private final List<JButton> botoneS = new ArrayList<>();
    char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    public Matriz() {
        initComponents();
        personalizarVentana();
        this.setBoton();
        this.personalizarPanel();
        this.addComponentes();
    }
    
    public final void personalizarVentana(){
        this.setSize(new Dimension(600,200));
        this.setIconImage(Toolkit.getDefaultToolkit().createImage(Matriz.class.getResource("mac.jpg")));//Pone un icono al JFrame	
        this.setTitle("DESARROLLO DE INTERFACES");
        this.setLocationRelativeTo(null); 
        this.setResizable(false);
    }

    public final void personalizarPanel(){
        /*
        La prioridad esta en el numero de filas. Sin embargo, el numero de 
        filas es 0, la rpioridad será el numero de columnas y habra tantas 
        filas como haga falta.
        */
        cntPanel.setLayout(new GridLayout(0,10));
    }
    
    public final void addComponentes(){
        for(int i =0; i<elementos;i++){
            cntPanel.add(botoneS.get(i));
        }
    }
    
    public final void setBoton(){
        for(int i = 1; i<=elementos;i++){
            JButton aux = new JButton();
            aux.setText(String.valueOf(abc[i-1]));
            aux.addActionListener(eve -> accionBoton(eve));
            botoneS.add(aux);
        }
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
            java.util.logging.Logger.getLogger(Matriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Matriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Matriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Matriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Matriz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cntPanel;
    // End of variables declaration//GEN-END:variables
}
