package org.adasme_aranda_barrera_gatica_vergara.prueba1_java_grupo5.gui;

import com.jtattoo.plaf.luna.LunaLookAndFeel;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * Autores: Lalo Adasme Marcelo Aranda Franco Barrera Marcelo Gatica Javier
 * Vergara
 */



public class App extends javax.swing.JFrame {

    
      //clase hilo creada dentro de app, para modificar componetes
    private class HiloDePrueba extends Thread {

        @Override
        public void run() {
            
        }

    }

    
    //query para los inserts
    static final String WRITE_OBJECT_SQL = "INSERT INTO ejem(nombre, valor_objeto) VALUES (?, ?)";// modificar segun sea necesario

    //query para los select
    static final String READ_OBJECT_SQL = "SELECT valor_objeto FROM ejem WHERE id = ?";//modificar segun sea necesario

    /**
     * Creates new form App
     */
    public App() {
        initComponents();
        this.setTitle("Inicio de sesión");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
    }

  

    
    //para escribir el objeto
    public static long writeJavaObject(Connection conn, Object object) throws Exception {
        String className = object.getClass().getName();
        PreparedStatement pstmt = conn.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);

        // fijar parametros de ingreso
        pstmt.setString(1, className);
        pstmt.setObject(2, object);
        pstmt.executeUpdate();

        // obtener la clave generada para el id
        ResultSet rs = pstmt.getGeneratedKeys();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }

        rs.close();
        pstmt.close();

        return id;
    }

    //para leer el objeto
    public static Object readJavaObject(Connection conn, long id) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(READ_OBJECT_SQL);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Object object = rs.getObject(1);
        String className = object.getClass().getName();

        byte[] buf = rs.getBytes(1);
        ObjectInputStream objectIn = null;
        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }

        Object deSerializedObject = objectIn.readObject();

        rs.close();
        pstmt.close();

        return deSerializedObject;
    }
    
    
        public void msgErrorRutNoEncontrado(){
        String titulo="Error";
        String msg="El rut ingresado no se encuentra registrado";
        int tipoMsg=JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(null, msg, titulo, tipoMsg);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JfVendedor = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        txtRun = new javax.swing.JTextField();
        lblRUN = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Crear cliente");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Cambiar apariencia");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Vender vivienda");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Arrendar vivienda");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        JfVendedor.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout JfVendedorLayout = new javax.swing.GroupLayout(JfVendedor.getContentPane());
        JfVendedor.getContentPane().setLayout(JfVendedorLayout);
        JfVendedorLayout.setHorizontalGroup(
            JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JfVendedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        JfVendedorLayout.setVerticalGroup(
            JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblRUN.setText("R.U.N");

        btnIniciarSesion.setText("Iniciar sesión");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addComponent(lblRUN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIniciarSesion)
                    .addComponent(txtRun, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRUN))
                .addGap(18, 18, 18)
                .addComponent(btnIniciarSesion)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            UIManager.setLookAndFeel(new LunaLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame JfVendedor;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblRUN;
    private javax.swing.JTextField txtRun;
    // End of variables declaration//GEN-END:variables
}
