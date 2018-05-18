package org.adasme_aranda_barrera_gatica_vergara.prueba1_java_grupo5.gui;

import clasesAusar.VistaVivienda;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.Data;
import model.TModel;
import model.Usuario;
import model.Vivienda;

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

    private Data d;
    private TModel model;
    private List<Vivienda> viviendasDisponibles;
    private List<Usuario> usuarios;

    //query para los inserts
    static final String WRITE_OBJECT_SQL = "INSERT INTO ejem(nombre, valor_objeto) VALUES (?, ?)";// modificar segun sea necesario

    //query para los select
    static final String READ_OBJECT_SQL = "SELECT valor_objeto FROM ejem WHERE id = ?";//modificar segun sea necesario

    /**
     * Creates new form App
     */
    public App() {

        try {
            d = new Data();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "ERROR " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage());
        }

        initComponents();
        this.setTitle("Inicio de sesión");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        viviendasDisponibles = new ArrayList();
        try {
            //  Data d= new Data();
            viviendasDisponibles = d.leerViviendas();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        llenarCbosFrameVendedor();
        cargarTablaJFrameVendedor();

        ocultarOpcionesParaFiltrar();

    }

    private void cargarTablaJFrameVendedor() {

        model = new TModel(viviendasDisponibles);
        tblDatosFrameVend.setModel(model);
        tblDatosFrameVend.setGridColor(Color.DARK_GRAY);

    }

    private void llenarCbosFrameVendedor() {
        cboFiltrarPorCasas.removeAllItems();
        cboFiltrarPorDepartamentos.removeAllItems();

        ArrayList<String> estadosCasas = new ArrayList();
        ArrayList<String> estadosDepartamentos = new ArrayList();

        estadosCasas.add("Nuevas");
        estadosCasas.add("Usadas");
        estadosCasas.add("Ambas");

        estadosDepartamentos.add("Nuevos");
        estadosDepartamentos.add("Usados");
        estadosDepartamentos.add("Ambos");

        for (String e : estadosCasas) {
            cboFiltrarPorCasas.addItem(e);
        }

        for (String e : estadosDepartamentos) {
            cboFiltrarPorDepartamentos.addItem(e);
        }
    }

    private void ocultarOpcionesParaFiltrar() {
        cboFiltrarPorCasas.setEnabled(false);
        cboFiltrarPorDepartamentos.setEnabled(false);

        rbtFiltrarAsc.setVisible(false);
        rbtFiltrarDesc.setVisible(false);
        cboFiltrarPorCasas.setVisible(false);
        cboFiltrarPorDepartamentos.setVisible(false);
        chkFiltrarPorCasas.setVisible(false);
        chkFiltrarPorDepartamentos.setVisible(false);
        lblPrecio.setVisible(false);

    }

    private void mostrarOpcionesParaFiltrar() {
        rbtFiltrarAsc.setVisible(true);
        rbtFiltrarDesc.setVisible(true);
        cboFiltrarPorCasas.setVisible(true);
        cboFiltrarPorDepartamentos.setVisible(true);
        chkFiltrarPorCasas.setVisible(true);
        chkFiltrarPorDepartamentos.setVisible(true);
        lblPrecio.setVisible(true);
    }

    private void cargarTblLog() {
        String[] titulos = {"Fecha", "Acción"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(titulos);

        tblLog.setModel(model);
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

    public void msgErrorRutNoEncontrado() {
        String titulo = "Error";
        String msg = "El rut ingresado no se encuentra registrado";
        int tipoMsg = JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(null, msg, titulo, tipoMsg);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JfVendedor = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatosFrameVend = new javax.swing.JTable();
        rbtFiltrarAsc = new javax.swing.JRadioButton();
        rbtFiltrarDesc = new javax.swing.JRadioButton();
        cboFiltrarPorCasas = new javax.swing.JComboBox<>();
        cboFiltrarPorDepartamentos = new javax.swing.JComboBox<>();
        chkFiltrarPorCasas = new javax.swing.JCheckBox();
        chkFiltrarPorDepartamentos = new javax.swing.JCheckBox();
        lblPrecio = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMArchivo = new javax.swing.JMenu();
        jMCrearCli = new javax.swing.JMenuItem();
        jMVCamAp = new javax.swing.JMenuItem();
        jMVenderViv = new javax.swing.JMenuItem();
        jMArrendarVivienda = new javax.swing.JMenuItem();
        btnGFiltrarPrecio = new javax.swing.ButtonGroup();
        frmAdmin = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        btnFrmVendedor = new javax.swing.JButton();
        btnFrmVivienda = new javax.swing.JButton();
        btnLog = new javax.swing.JButton();
        btnStats = new javax.swing.JButton();
        btnRestore = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemAdminSalir = new javax.swing.JMenuItem();
        frmCVendedor = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRunVendedor = new javax.swing.JTextField();
        txtNomVendedor = new javax.swing.JTextField();
        btnCVendedor = new javax.swing.JButton();
        jMenuBar3 = new javax.swing.JMenuBar();
        itemCVendedorExit = new javax.swing.JMenu();
        itemSalirCVendedor = new javax.swing.JMenuItem();
        frmCVivienda = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        btnCVivienda = new javax.swing.JButton();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        itemCViviendaExit = new javax.swing.JMenuItem();
        frmLog = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLog = new javax.swing.JTable();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        itemLogExit = new javax.swing.JMenuItem();
        frmRestaurar = new javax.swing.JFrame();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        itemRestoreExit = new javax.swing.JMenuItem();
        txtRun = new javax.swing.JTextField();
        lblRUN = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        lblicono = new javax.swing.JLabel();

        tblDatosFrameVend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDatosFrameVend);

        btnGFiltrarPrecio.add(rbtFiltrarAsc);
        rbtFiltrarAsc.setText("Filtrar ascendente");

        btnGFiltrarPrecio.add(rbtFiltrarDesc);
        rbtFiltrarDesc.setText("Filtrar descendente");

        cboFiltrarPorCasas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboFiltrarPorDepartamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chkFiltrarPorCasas.setText("Filtrar por casas");
        chkFiltrarPorCasas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFiltrarPorCasasActionPerformed(evt);
            }
        });

        chkFiltrarPorDepartamentos.setText("Filtrar por departamentos");
        chkFiltrarPorDepartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFiltrarPorDepartamentosActionPerformed(evt);
            }
        });

        lblPrecio.setText("Precio:");

        jMArchivo.setText("Archivo");

        jMCrearCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMCrearCli.setText("Crear cliente");
        jMCrearCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMCrearCliActionPerformed(evt);
            }
        });
        jMArchivo.add(jMCrearCli);

        jMVCamAp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMVCamAp.setText("Cambiar apariencia");
        jMArchivo.add(jMVCamAp);

        jMVenderViv.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMVenderViv.setText("Vender vivienda");
        jMVenderViv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMVenderVivActionPerformed(evt);
            }
        });
        jMArchivo.add(jMVenderViv);

        jMArrendarVivienda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMArrendarVivienda.setText("Arrendar vivienda");
        jMArrendarVivienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMArrendarViviendaActionPerformed(evt);
            }
        });
        jMArchivo.add(jMArrendarVivienda);

        jMenuBar1.add(jMArchivo);

        JfVendedor.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout JfVendedorLayout = new javax.swing.GroupLayout(JfVendedor.getContentPane());
        JfVendedor.getContentPane().setLayout(JfVendedorLayout);
        JfVendedorLayout.setHorizontalGroup(
            JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                        .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkFiltrarPorDepartamentos)
                            .addComponent(chkFiltrarPorCasas))
                        .addGap(18, 18, 18)
                        .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboFiltrarPorCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboFiltrarPorDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                        .addComponent(lblPrecio)
                        .addGap(18, 18, 18)
                        .addComponent(rbtFiltrarDesc)
                        .addGap(18, 18, 18)
                        .addComponent(rbtFiltrarAsc)
                        .addGap(31, 31, 31))))
            .addGroup(JfVendedorLayout.createSequentialGroup()
                .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE))
                .addContainerGap())
        );
        JfVendedorLayout.setVerticalGroup(
            JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkFiltrarPorCasas)
                    .addComponent(cboFiltrarPorCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkFiltrarPorDepartamentos)
                    .addComponent(cboFiltrarPorDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtFiltrarDesc)
                    .addComponent(rbtFiltrarAsc)
                    .addComponent(lblPrecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Menú Administrador"));

        btnFrmVendedor.setText("Crear Vendedor");
        btnFrmVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrmVendedorActionPerformed(evt);
            }
        });

        btnFrmVivienda.setText("Crear Vivienda");
        btnFrmVivienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrmViviendaActionPerformed(evt);
            }
        });

        btnLog.setText("Ver Log");
        btnLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogActionPerformed(evt);
            }
        });

        btnStats.setText("Ver estadísticas");

        btnRestore.setText("Restaurar");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookAndFeels/admin_icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnFrmVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFrmVivienda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLog, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStats, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRestore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnFrmVendedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFrmVivienda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStats))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRestore)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jMenu1.setText("Archivo");

        itemAdminSalir.setText("Salir");
        itemAdminSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAdminSalirActionPerformed(evt);
            }
        });
        jMenu1.add(itemAdminSalir);

        jMenuBar2.add(jMenu1);

        frmAdmin.setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout frmAdminLayout = new javax.swing.GroupLayout(frmAdmin.getContentPane());
        frmAdmin.getContentPane().setLayout(frmAdminLayout);
        frmAdminLayout.setHorizontalGroup(
            frmAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frmAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frmAdminLayout.setVerticalGroup(
            frmAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmAdminLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("RUN:");

        jLabel2.setText("Nombre:");

        btnCVendedor.setText("Crear");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRunVendedor)
                            .addComponent(txtNomVendedor)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 283, Short.MAX_VALUE)
                        .addComponent(btnCVendedor)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRunVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCVendedor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        itemCVendedorExit.setText("Archivo");

        itemSalirCVendedor.setText("Salir");
        itemSalirCVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirCVendedorActionPerformed(evt);
            }
        });
        itemCVendedorExit.add(itemSalirCVendedor);

        jMenuBar3.add(itemCVendedorExit);

        frmCVendedor.setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout frmCVendedorLayout = new javax.swing.GroupLayout(frmCVendedor.getContentPane());
        frmCVendedor.getContentPane().setLayout(frmCVendedorLayout);
        frmCVendedorLayout.setHorizontalGroup(
            frmCVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmCVendedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frmCVendedorLayout.setVerticalGroup(
            frmCVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmCVendedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("N° Rol:");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Tipo:");

        jLabel6.setText("Precio Arriendo:");

        jLabel7.setText("Precio Venta:");

        jLabel8.setText("Baños:");

        jLabel9.setText("Piezas:");

        jLabel10.setText("Dirección:");

        jLabel11.setText("Condición:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCVivienda.setText("Crear");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jComboBox1, 0, 250, Short.MAX_VALUE)
                            .addComponent(jSpinner1)
                            .addComponent(jSpinner2)
                            .addComponent(jSpinner3)
                            .addComponent(jSpinner4)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCVivienda)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCVivienda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu2.setText("Archivo");

        itemCViviendaExit.setText("Salir");
        itemCViviendaExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCViviendaExitActionPerformed(evt);
            }
        });
        jMenu2.add(itemCViviendaExit);

        jMenuBar4.add(jMenu2);

        frmCVivienda.setJMenuBar(jMenuBar4);

        javax.swing.GroupLayout frmCViviendaLayout = new javax.swing.GroupLayout(frmCVivienda.getContentPane());
        frmCVivienda.getContentPane().setLayout(frmCViviendaLayout);
        frmCViviendaLayout.setHorizontalGroup(
            frmCViviendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmCViviendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frmCViviendaLayout.setVerticalGroup(
            frmCViviendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmCViviendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblLog);

        jMenu3.setText("Archivo");

        itemLogExit.setText("Salir");
        itemLogExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLogExitActionPerformed(evt);
            }
        });
        jMenu3.add(itemLogExit);

        jMenuBar5.add(jMenu3);

        frmLog.setJMenuBar(jMenuBar5);

        javax.swing.GroupLayout frmLogLayout = new javax.swing.GroupLayout(frmLog.getContentPane());
        frmLog.getContentPane().setLayout(frmLogLayout);
        frmLogLayout.setHorizontalGroup(
            frmLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frmLogLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
        frmLogLayout.setVerticalGroup(
            frmLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu4.setText("Archivo");

        itemRestoreExit.setText("Salir");
        jMenu4.add(itemRestoreExit);

        jMenuBar6.add(jMenu4);

        frmRestaurar.setJMenuBar(jMenuBar6);

        javax.swing.GroupLayout frmRestaurarLayout = new javax.swing.GroupLayout(frmRestaurar.getContentPane());
        frmRestaurar.getContentPane().setLayout(frmRestaurarLayout);
        frmRestaurarLayout.setHorizontalGroup(
            frmRestaurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        frmRestaurarLayout.setVerticalGroup(
            frmRestaurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblRUN.setText("R.U.N");

        btnIniciarSesion.setText("Iniciar sesión");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        lblicono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookAndFeels/logoCafé.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblicono, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRUN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRun, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRUN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIniciarSesion))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblicono, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMCrearCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMCrearCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMCrearCliActionPerformed

    private void jMVenderVivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMVenderVivActionPerformed
        if (chkFiltrarPorCasas.isVisible()) {
            ocultarOpcionesParaFiltrar();
        } else if (!chkFiltrarPorCasas.isVisible()) {
            mostrarOpcionesParaFiltrar();
        }


    }//GEN-LAST:event_jMVenderVivActionPerformed

    private void jMArrendarViviendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMArrendarViviendaActionPerformed
        if (chkFiltrarPorCasas.isVisible()) {
            ocultarOpcionesParaFiltrar();
        } else if (!chkFiltrarPorCasas.isVisible()) {
            mostrarOpcionesParaFiltrar();
        }

    }//GEN-LAST:event_jMArrendarViviendaActionPerformed

    private void chkFiltrarPorCasasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFiltrarPorCasasActionPerformed

        if (chkFiltrarPorCasas.isSelected()) {
            cboFiltrarPorCasas.setEnabled(true);
        } else if (!chkFiltrarPorCasas.isSelected()) {
            cboFiltrarPorCasas.setEnabled(false);
        }

    }//GEN-LAST:event_chkFiltrarPorCasasActionPerformed

    private void chkFiltrarPorDepartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFiltrarPorDepartamentosActionPerformed

        if (chkFiltrarPorDepartamentos.isSelected()) {
            cboFiltrarPorDepartamentos.setEnabled(true);
        } else if (!chkFiltrarPorDepartamentos.isSelected()) {
            cboFiltrarPorDepartamentos.setEnabled(false);
        }


    }//GEN-LAST:event_chkFiltrarPorDepartamentosActionPerformed

    private void itemAdminSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAdminSalirActionPerformed
        frmAdmin.dispatchEvent(new WindowEvent(frmAdmin, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_itemAdminSalirActionPerformed

    private void btnFrmVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrmVendedorActionPerformed
        frmCVendedor.setBounds(WIDTH, WIDTH, 400, 180);
        frmCVendedor.setLocationRelativeTo(null);
        frmCVendedor.setVisible(true);
    }//GEN-LAST:event_btnFrmVendedorActionPerformed

    private void itemSalirCVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirCVendedorActionPerformed
        frmCVendedor.dispatchEvent(new WindowEvent(frmCVendedor, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_itemSalirCVendedorActionPerformed

    private void btnFrmViviendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrmViviendaActionPerformed
        frmCVivienda.setBounds(WIDTH, WIDTH, 400, 385);
        frmCVivienda.setLocationRelativeTo(null);
        frmCVivienda.setVisible(true);
    }//GEN-LAST:event_btnFrmViviendaActionPerformed

    private void itemCViviendaExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCViviendaExitActionPerformed
        frmCVivienda.dispatchEvent(new WindowEvent(frmCVivienda, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_itemCViviendaExitActionPerformed

    private void btnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogActionPerformed
        frmLog.setBounds(WIDTH, WIDTH, 300, 300);
        frmLog.setLocationRelativeTo(null);
        frmLog.setVisible(true);
        cargarTblLog();
    }//GEN-LAST:event_btnLogActionPerformed

    private void itemLogExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLogExitActionPerformed
        frmLog.dispatchEvent(new WindowEvent(frmLog, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_itemLogExitActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        String run = txtRun.getText();
        usuarios = new ArrayList<>();
        try {
            usuarios = d.leerUsuario();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (d.existeUsuario(run) == 1) {
                for (Usuario u : usuarios) {

                    if (run.equals(u.getRun())) {
                        if (u.getTipo() == 1) {
                            this.dispose();
                            frmAdmin.setBounds(WIDTH, WIDTH, 389, 256);
                            frmAdmin.setLocationRelativeTo(null);
                            frmAdmin.setVisible(true);
                            JOptionPane.showMessageDialog(null, "          Bienvenido \n Administrador: " + u.getNombre());

                        }
                        if (u.getTipo() == 2) {
                            this.dispose();
                            JfVendedor.setBounds(WIDTH, WIDTH, 1000, 400);
                            JfVendedor.setLocationRelativeTo(null);
                            JfVendedor.setVisible(true);
                            JOptionPane.showMessageDialog(null, "          Bienvenido \n Vendedor: " + u.getNombre());
                        }
                    }

                }
            }else{
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                txtRun.setText("");
                txtRun.requestFocus();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnIniciarSesionActionPerformed

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
    private javax.swing.JButton btnCVendedor;
    private javax.swing.JButton btnCVivienda;
    private javax.swing.JButton btnFrmVendedor;
    private javax.swing.JButton btnFrmVivienda;
    private javax.swing.ButtonGroup btnGFiltrarPrecio;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnLog;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnStats;
    private javax.swing.JComboBox<String> cboFiltrarPorCasas;
    private javax.swing.JComboBox<String> cboFiltrarPorDepartamentos;
    private javax.swing.JCheckBox chkFiltrarPorCasas;
    private javax.swing.JCheckBox chkFiltrarPorDepartamentos;
    private javax.swing.JFrame frmAdmin;
    private javax.swing.JFrame frmCVendedor;
    private javax.swing.JFrame frmCVivienda;
    private javax.swing.JFrame frmLog;
    private javax.swing.JFrame frmRestaurar;
    private javax.swing.JMenuItem itemAdminSalir;
    private javax.swing.JMenu itemCVendedorExit;
    private javax.swing.JMenuItem itemCViviendaExit;
    private javax.swing.JMenuItem itemLogExit;
    private javax.swing.JMenuItem itemRestoreExit;
    private javax.swing.JMenuItem itemSalirCVendedor;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMArchivo;
    private javax.swing.JMenuItem jMArrendarVivienda;
    private javax.swing.JMenuItem jMCrearCli;
    private javax.swing.JMenuItem jMVCamAp;
    private javax.swing.JMenuItem jMVenderViv;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblRUN;
    private javax.swing.JLabel lblicono;
    private javax.swing.JRadioButton rbtFiltrarAsc;
    private javax.swing.JRadioButton rbtFiltrarDesc;
    private javax.swing.JTable tblDatosFrameVend;
    private javax.swing.JTable tblLog;
    private javax.swing.JTextField txtNomVendedor;
    private javax.swing.JTextField txtRun;
    private javax.swing.JTextField txtRunVendedor;
    // End of variables declaration//GEN-END:variables
}
