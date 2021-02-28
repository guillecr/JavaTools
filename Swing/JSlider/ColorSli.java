package colores;

import java.awt.Color;
import javax.swing.event.ChangeEvent;

/**
 * 
 * @author Guillermo Casas
 */
public class ColorSli extends javax.swing.JFrame {
    
    public ColorSli() {
        initComponents();
        inicial();
    }
    
    
    public void inicial(){
        personalizar();
        slzG.addChangeListener(this::eventoSli);
        slzR.addChangeListener(this::eventoSli);
        slzB.addChangeListener(this::eventoSli);
        setColor();
    }
    
    
    public void personalizar(){
        setLayout(null);
        setResizable(false);
        setLocation(140, 140);
    }
    
    
    public void eventoSli(ChangeEvent ch){
        setColor();
    }

    public void setColor(){
        int[] color = getColor();
        lblIntR.setText(String.valueOf(color[0]));
        lblIntG.setText(String.valueOf(color[1]));
        lblIntB.setText(String.valueOf(color[2]));
        pnlColor.setBackground(new Color(color[0], color[1], color[2]));
    }
    public int[] getColor(){
        int[] color = {0,0,0};
        color[0] = slzR.getValue();
        color[1] = slzG.getValue();
        color[2] = slzB.getValue();
        return color;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        pnlSliz = new javax.swing.JPanel();
        slzR = new javax.swing.JSlider();
        slzG = new javax.swing.JSlider();
        slzB = new javax.swing.JSlider();
        lblR = new javax.swing.JLabel();
        lblG = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();
        lblIntR = new javax.swing.JLabel();
        lblIntG = new javax.swing.JLabel();
        lblIntB = new javax.swing.JLabel();
        pnlColor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        slzR.setMaximum(255);

        slzG.setMaximum(255);

        slzB.setMaximum(255);

        lblR.setText("R");

        lblG.setText("G");

        lblB.setText("B");

        lblIntR.setText("100");

        lblIntG.setText("100");

        lblIntB.setText("100");

        javax.swing.GroupLayout pnlSlizLayout = new javax.swing.GroupLayout(pnlSliz);
        pnlSliz.setLayout(pnlSlizLayout);
        pnlSlizLayout.setHorizontalGroup(
            pnlSlizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSlizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSlizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlSlizLayout.createSequentialGroup()
                        .addComponent(lblR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slzR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSlizLayout.createSequentialGroup()
                        .addComponent(lblB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slzB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSlizLayout.createSequentialGroup()
                        .addComponent(lblG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slzG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlSlizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIntG)
                    .addComponent(lblIntR)
                    .addComponent(lblIntB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSlizLayout.setVerticalGroup(
            pnlSlizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSlizLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlSlizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIntR)
                    .addComponent(slzR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblR))
                .addGap(18, 18, 18)
                .addGroup(pnlSlizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slzG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblG, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIntG, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pnlSlizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(slzB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblB)
                    .addComponent(lblIntB))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pnlColor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlColorLayout = new javax.swing.GroupLayout(pnlColor);
        pnlColor.setLayout(pnlColorLayout);
        pnlColorLayout.setHorizontalGroup(
            pnlColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnlColorLayout.setVerticalGroup(
            pnlColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSliz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(pnlColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlSliz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColorSli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblG;
    private javax.swing.JLabel lblIntB;
    private javax.swing.JLabel lblIntG;
    private javax.swing.JLabel lblIntR;
    private javax.swing.JLabel lblR;
    private javax.swing.JPanel pnlColor;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlSliz;
    private javax.swing.JSlider slzB;
    private javax.swing.JSlider slzG;
    private javax.swing.JSlider slzR;
    // End of variables declaration//GEN-END:variables
}
