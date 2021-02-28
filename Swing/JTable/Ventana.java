package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import librerias.DataBaseManager;
import modelo.Doctore;

/**
 * Ventana de la actividad 6 (A06)
 * 
 * Funcionalidad principal
 *  - Conectarse a la base de datos "Hospital" en MySQL (localhost). Creará la
 *  base de datos si esta no existe
 *  - Añadir todas las consultas del archivo data.datos.csv en la tabla 
 *  <code>consultas</code> en MySQL. Creará la tabla si esta no existe.
 *  - Añadirá 2 tablas (<code>consultasMujeres</code> y <code>consultasHombre</code>) en MySQL (las creará
 *  si estas no existe) cuyos datos procederá de una selección de registros de
 *  la tabla <code>consultas</code> (Insert con subconsulta)
 *  - Mostrará en diferentes tablas los registros de cada tabla creada.
 * 
 * @author Guillermo Casas Reche
 * @author g.casas.r94@gmail.com
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * ===================================================================
     * ====================== VARIABLES ==================================
     * ===================================================================
     */
    public static int tablaCreada = 0; // Controlador de tablas creadas => 0: Ninguno , 1: Todos, 2: Todos+Sexo
    
    //Variables de tablas
    private final String[] cabecera = {"ID","FECHA","NOMBRE","SEXO","TIPO"};
    private final String[][] body={};
    private final DefaultTableModel dtmT = new DefaultTableModel(body, cabecera);
    private final DefaultTableModel dtmM = new DefaultTableModel(body, cabecera);
    private final DefaultTableModel dtmH = new DefaultTableModel(body, cabecera);
    private List<DefaultTableModel> dtmS = new ArrayList<>();
    
    //Variable de fichero
    static String fichero = "src/main/java/data/datos.csv";
    
    // Variable de Base de datos
    public static String[] tablaBase = {"consultas"};
    public static String[] filtroM = {"consultasMujeres","Mujer"};
    public static String[] filtroH = {"consultasHombres","Hombre"};
    public static List<String[]> TabFil = new ArrayList<>();
    public static DataBaseManager dbm;    
    
    // Lista botones
    public static List<JButton> buttoms = new ArrayList<>();
    
    /*
     * ===================================================================
     * ===================== CONSTRUCTOR =================================
     * ===================================================================
     */    
    public Ventana() {
        initComponents();
        estiloVentana();
        cargarElementos();
        estadoCarga(false);
    }

    
    /*
     * ===================================================================
     * ================== MÉTODOS INICIALES ==============================
     * ===================================================================
     */
    /**
     * Definir las propiedades de la ventana
     */
    protected void estiloVentana(){
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    /**
     * Método que preparará todas los elementos empleada en la ventana
     */
    protected void cargarElementos(){
        // Definición de las listas usadas
        buttoms = Arrays.asList(cmdTodos,cmdSexo,cmdSalir,cmdActTodos,cmdActMujeres,cmdActHombres,cmdLimpiar);
        tblTodos.setModel(dtmT);
        tblMujeres.setModel(dtmM);
        tblHombres.setModel(dtmH);
        dtmS = Arrays.asList(dtmT,dtmM,dtmH);
        TabFil = Arrays.asList(tablaBase,filtroM,filtroH);
        
        // Gestión de la base de datos
        dbm = new DataBaseManager("Hospital");
        dbm.setTableSelect(TabFil.get(0)[0]);
        
    }
    
    
    /*
     * ===================================================================
     * ======================= MÉTODOS ===================================
     * ===================================================================
     */
    /**
     * Añadir elementos a una tabla de una lista
     * @param tabla Gestor de la tabla (<code>DefaultTableModel</code>)
     * @param lista Lista de elementos
     */
    public void addListaTabla(DefaultTableModel tabla, List<Doctore> lista){
        if(lista!=null) lista.forEach(d -> {String[] fila = {String.valueOf(d.getId()),d.getFecha(),d.getNombre(),d.getSexo(),d.getTipo()};tabla.addRow(fila);});
    }
    
    /**
     * Eliminará todas las filas de la tabla indicada.
     * El método recoge un <code>DefaultTableMode</code>.
     * @param tabla El modelo por defecto asociado a la tabal a vaciar
     */
    public void limpiarTabla(int tabla){
        int fila = dtmS.get(tabla).getRowCount()-1;
        for(int i = fila;i>=0;i--){
            dtmS.get(tabla).removeRow(i);
        }
    }
 
    /**
     * Método para refrescar el contenido de las tablas con las listas almacenadas en la base de datos
     * 0 -> Tabla mujeres
     * 1 -> Tabla hombres
     * @param tabla 
     */
    public void refrescarTabla(int tabla){
        limpiarTabla(tabla);
        dbm.setTableSelect(TabFil.get(tabla)[0]);
        addListaTabla(dtmS.get(tabla), dbm.getTodo());
    }
    
    /**
     * Método que prepara la ventana en el estado en carga o lo saca del estado en carga dependiendo del valor entregado
     * Al salir del estado en carga:
     * - Si procedemos del constructor (tablaCreada = 0) bloqueará la separación de tablas por sexo.
     * - Si procedemos del botón "Todos" (tablaCreada = 1) bloqueará el botón de "Todos"
     * - Si procedemos del botón "Por sexo" (tablaCreada = 2) bloqueará ambos botones (Todos y Por sexo)
     * @param flag Bandera que llevará o sacara el estado de carga a la ventana
     */
    public static void estadoCarga(boolean flag){
        buttoms.forEach(b -> {b.setEnabled(!flag);});
        lblCarga.setVisible(flag);
        lblNumCarga.setVisible(flag);
        if(tablaCreada!=0)buttoms.get(0).setEnabled(false);
        if(tablaCreada==2||tablaCreada==0) buttoms.get(1).setEnabled(false);
        lblNumCarga.setText("");
    }
    
    /**
     * Función principal de generador de tablas en MySQL
     * Llevará al estado de carga a la ventana, asignado previamente el valor correcto a <code>tablaCreada</code>
     * Ejecutará la función <code>run()</code> de la clase Hilo en un nuevo hilo. Esto hará que la ventana no se 
     * bloquee mientras inserta los registros en la tabla.
     * La función del Hilo tiene el procedimiento para sacar la ventana del estado de carga.
     * @param funcion Procedencia de ejecución de la función (1 -> Todos // 2 -> Por Sexo)
     */
    public void ejecutar(int funcion){
        tablaCreada = funcion;
        estadoCarga(true);
        Hilo.setEjecutador(funcion);
        Hilo h = new Hilo();
        h.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblTodos = new javax.swing.JLabel();
        lblMujeres = new javax.swing.JLabel();
        lblHombres = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTodos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMujeres = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHombres = new javax.swing.JTable();
        cmdActTodos = new javax.swing.JButton();
        cmdActMujeres = new javax.swing.JButton();
        cmdActHombres = new javax.swing.JButton();
        lblFunciones = new javax.swing.JLabel();
        cmdTodos = new javax.swing.JButton();
        cmdSexo = new javax.swing.JButton();
        cmdSalir = new javax.swing.JButton();
        lblCarga = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblNumCarga = new javax.swing.JLabel();
        cmdLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        lblTitulo.setText("Actividd 6: Operaciones CRUD");

        lblTodos.setText("Todos");

        lblMujeres.setText("Mujeres");

        lblHombres.setText("Hombres");

        tblTodos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTodos);

        tblMujeres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblMujeres);

        tblHombres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblHombres);

        cmdActTodos.setText("Actualizar");
        cmdActTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActTodosActionPerformed(evt);
            }
        });

        cmdActMujeres.setText("Actualizar");
        cmdActMujeres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActMujeresActionPerformed(evt);
            }
        });

        cmdActHombres.setText("Actualizar");
        cmdActHombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActHombresActionPerformed(evt);
            }
        });

        lblFunciones.setText("Almacenamiento en MySQL:");

        cmdTodos.setText("Todos");
        cmdTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTodosActionPerformed(evt);
            }
        });

        cmdSexo.setText("Por sexo");
        cmdSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSexoActionPerformed(evt);
            }
        });

        cmdSalir.setText("Salir");
        cmdSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSalirActionPerformed(evt);
            }
        });

        lblCarga.setForeground(new java.awt.Color(204, 0, 0));
        lblCarga.setText("Generando tabla en MySQL... Espere porfavor");

        lblNumCarga.setForeground(new java.awt.Color(204, 0, 0));
        lblNumCarga.setText(" 0/0");

        cmdLimpiar.setText("Limpiar");
        cmdLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(lblTitulo))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(62, 62, 62)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblHombres)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmdActHombres))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblMujeres)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmdActMujeres))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblTodos)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmdActTodos))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdTodos)
                    .addComponent(lblFunciones))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(cmdSexo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(cmdSalir)
                        .addGap(85, 85, 85))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCarga)
                        .addGap(18, 18, 18)
                        .addComponent(lblNumCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(430, 430, 430))))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTodos)
                    .addComponent(cmdActTodos))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMujeres)
                    .addComponent(cmdActMujeres))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHombres)
                    .addComponent(cmdActHombres))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFunciones)
                    .addComponent(lblCarga)
                    .addComponent(lblNumCarga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdTodos)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdSexo)
                        .addComponent(cmdSalir)
                        .addComponent(cmdLimpiar)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cmdSalirActionPerformed

    private void cmdTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTodosActionPerformed
        ejecutar(1);
    }//GEN-LAST:event_cmdTodosActionPerformed

    private void cmdActMujeresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActMujeresActionPerformed
        refrescarTabla(1);
    }//GEN-LAST:event_cmdActMujeresActionPerformed

    private void cmdSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSexoActionPerformed
        ejecutar(2);
    }//GEN-LAST:event_cmdSexoActionPerformed

    private void cmdActHombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActHombresActionPerformed
        refrescarTabla(2);
    }//GEN-LAST:event_cmdActHombresActionPerformed

    private void cmdActTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActTodosActionPerformed
        refrescarTabla(0);
    }//GEN-LAST:event_cmdActTodosActionPerformed

    private void cmdLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLimpiarActionPerformed
        for(int i=0;i<dtmS.size();i++)limpiarTabla(i);
    }//GEN-LAST:event_cmdLimpiarActionPerformed

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
    private javax.swing.JButton cmdActHombres;
    private javax.swing.JButton cmdActMujeres;
    private javax.swing.JButton cmdActTodos;
    private javax.swing.JButton cmdLimpiar;
    private javax.swing.JButton cmdSalir;
    private javax.swing.JButton cmdSexo;
    private javax.swing.JButton cmdTodos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JLabel lblCarga;
    private javax.swing.JLabel lblFunciones;
    private javax.swing.JLabel lblHombres;
    private javax.swing.JLabel lblMujeres;
    public static javax.swing.JLabel lblNumCarga;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTodos;
    private javax.swing.JTable tblHombres;
    private javax.swing.JTable tblMujeres;
    private javax.swing.JTable tblTodos;
    // End of variables declaration//GEN-END:variables
}
