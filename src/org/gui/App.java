package org.gui;

import clasesAusar.VistaLog;
import clasesAusar.VistaVivienda;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Data;
import model.TModelLog;
import model.TModelVivienda;
import model.TModelViviendasDisponibles;
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
    private TModelVivienda modelVivienda;
    private TModelViviendasDisponibles modelViviendasDisponibles;
    private TModelLog modelLog;
    private List<VistaVivienda> viviendasDisponibles;
    private List<VistaLog> logs;
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

        JfVendedor.setTitle("Vendedor");
        rbtFiltrarAsc.isSelected();
        viviendasDisponibles = new ArrayList();
        try {
            //  Data d= new Data();
            logs = new ArrayList<>();
            viviendasDisponibles = d.leerTodasLasViviendasDisponibles();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        llenarCbosFrameVendedor();
        cargarTablaJFrameVendedor();

        ocultarOpcionesParaFiltrar();

        JFrameCrearCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void cargarTablaJFrameVendedor() {

        modelViviendasDisponibles = new TModelViviendasDisponibles(viviendasDisponibles);
        tblDatosFrameVend.setModel(modelViviendasDisponibles);
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
        btnFiltrarViviendasJfVendedor.setVisible(false);

    }

    private void mostrarOpcionesParaFiltrar() {
        rbtFiltrarAsc.setVisible(true);
        rbtFiltrarDesc.setVisible(true);
        cboFiltrarPorCasas.setVisible(true);
        cboFiltrarPorDepartamentos.setVisible(true);
        chkFiltrarPorCasas.setVisible(true);
        chkFiltrarPorDepartamentos.setVisible(true);
        lblPrecio.setVisible(true);
        btnFiltrarViviendasJfVendedor.setVisible(true);
    }

    private void cargarTblLog() {
        modelLog = new TModelLog(logs);
        tblLog.setModel(modelLog);
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
        lblViviendasDisponibles = new javax.swing.JLabel();
        btnFiltrarViviendasJfVendedor = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMArchivo = new javax.swing.JMenu();
        jMCrearCli = new javax.swing.JMenuItem();
        jMVCamAp = new javax.swing.JMenuItem();
        jMVenderViv = new javax.swing.JMenuItem();
        jMArrendarVivienda = new javax.swing.JMenuItem();
        jMCerrarSesion = new javax.swing.JMenuItem();
        btnGFiltrarPrecio = new javax.swing.ButtonGroup();
        frmAdmin = new javax.swing.JFrame();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRunVendedor = new javax.swing.JTextField();
        txtNombreVendedor = new javax.swing.JTextField();
        btnCrearVendedor = new javax.swing.JButton();
        btnBuscarVendedor = new javax.swing.JButton();
        btnBorrarVendedor = new javax.swing.JButton();
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
        txtNRol = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        cboTipo = new javax.swing.JComboBox<>();
        cboDisp = new javax.swing.JComboBox<>();
        cboCondicion = new javax.swing.JComboBox<>();
        spnBanios = new javax.swing.JSpinner();
        spnPiezas = new javax.swing.JSpinner();
        spnArriendo = new javax.swing.JSpinner();
        spnVenta = new javax.swing.JSpinner();
        btnCrearVivienda = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLog = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemAdminSalir = new javax.swing.JMenuItem();
        JFrameCrearCliente = new javax.swing.JFrame();
        btnCrearCliente = new javax.swing.JButton();
        lblNombreCliente = new javax.swing.JLabel();
        lblRunCliente = new javax.swing.JLabel();
        lblSueldoCliente = new javax.swing.JLabel();
        txtRunCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtSueldoCliente = new javax.swing.JTextField();
        btnCancelarCreacionDeCliente = new javax.swing.JButton();
        txtRun = new javax.swing.JTextField();
        lblRUN = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        lblicono = new javax.swing.JLabel();

        JfVendedor.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        lblViviendasDisponibles.setText("            Viviendas disponibles");

        btnFiltrarViviendasJfVendedor.setText("Filtrar");
        btnFiltrarViviendasJfVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarViviendasJfVendedorActionPerformed(evt);
            }
        });

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

        jMCerrarSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMCerrarSesion.setText("Cerrar sesion");
        jMCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMCerrarSesionActionPerformed(evt);
            }
        });
        jMArchivo.add(jMCerrarSesion);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFiltrarViviendasJfVendedor)
                .addGap(147, 147, 147))
            .addGroup(JfVendedorLayout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(lblViviendasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFiltrarViviendasJfVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(lblViviendasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        frmAdmin.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel1.setText("RUN:");

        jLabel2.setText("Nombre:");

        txtNombreVendedor.setEnabled(false);

        btnCrearVendedor.setText("Crear");
        btnCrearVendedor.setEnabled(false);
        btnCrearVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearVendedorActionPerformed(evt);
            }
        });

        btnBuscarVendedor.setText("...");
        btnBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVendedorActionPerformed(evt);
            }
        });

        btnBorrarVendedor.setText("Borrar");
        btnBorrarVendedor.setEnabled(false);
        btnBorrarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarVendedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 387, Short.MAX_VALUE)
                        .addComponent(btnBorrarVendedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCrearVendedor))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreVendedor)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtRunVendedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarVendedor)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRunVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarVendedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearVendedor)
                    .addComponent(btnBorrarVendedor))
                .addContainerGap(175, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Vendedor", jPanel1);

        jLabel3.setText("N° Rol:");

        jLabel4.setText("Dirección:");

        jLabel5.setText("Tipo:");

        jLabel6.setText("Disponibilidad:");

        jLabel7.setText("Baños:");

        jLabel8.setText("Piezas:");

        jLabel9.setText("Condición:");

        jLabel10.setText("Precio arriendo:");

        jLabel11.setText("Precio venta:");

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboDisp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboCondicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCrearVivienda.setText("Crear");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNRol)
                            .addComponent(txtDireccion)
                            .addComponent(cboTipo, 0, 431, Short.MAX_VALUE)
                            .addComponent(cboDisp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboCondicion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spnBanios)
                            .addComponent(spnPiezas)
                            .addComponent(spnVenta)
                            .addComponent(spnArriendo)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCrearVivienda)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtNRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboDisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(spnBanios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(spnArriendo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(spnVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCrearVivienda)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Crear Vivienda", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ver Estadísticas", jPanel3);

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ver Log", jPanel4);

        jButton1.setText("Respaldar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Restaurar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(255, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jButton1)
                .addGap(56, 56, 56)
                .addComponent(jButton2)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Restaurar", jPanel5);

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
            .addGroup(frmAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        frmAdminLayout.setVerticalGroup(
            frmAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        btnCrearCliente.setText("Crear");
        btnCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearClienteActionPerformed(evt);
            }
        });

        lblNombreCliente.setText("Nombre:");

        lblRunCliente.setText("Run:");

        lblSueldoCliente.setText("Sueldo:");

        btnCancelarCreacionDeCliente.setText("Cancelar");
        btnCancelarCreacionDeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCreacionDeClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFrameCrearClienteLayout = new javax.swing.GroupLayout(JFrameCrearCliente.getContentPane());
        JFrameCrearCliente.getContentPane().setLayout(JFrameCrearClienteLayout);
        JFrameCrearClienteLayout.setHorizontalGroup(
            JFrameCrearClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFrameCrearClienteLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnCancelarCreacionDeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCrearCliente)
                .addGap(18, 18, 18))
            .addGroup(JFrameCrearClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JFrameCrearClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombreCliente)
                    .addComponent(lblSueldoCliente)
                    .addComponent(lblRunCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JFrameCrearClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFrameCrearClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addComponent(txtSueldoCliente, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txtRunCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        JFrameCrearClienteLayout.setVerticalGroup(
            JFrameCrearClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFrameCrearClienteLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(JFrameCrearClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRunCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRunCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JFrameCrearClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreCliente))
                .addGap(18, 18, 18)
                .addGroup(JFrameCrearClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSueldoCliente)
                    .addComponent(txtSueldoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(JFrameCrearClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearCliente)
                    .addComponent(btnCancelarCreacionDeCliente))
                .addContainerGap(16, Short.MAX_VALUE))
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
        JFrameCrearCliente.setBounds(WIDTH, WIDTH, 260, 225);
        JFrameCrearCliente.setLocationRelativeTo(null);
        JFrameCrearCliente.setVisible(true);
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
        this.setVisible(true);
    }//GEN-LAST:event_itemAdminSalirActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        String run = txtRun.getText();

        try {
            Usuario u = d.buscarUsuario(run);
            if (u != null) {
                if (u.getTipo() == 1) {
                    this.dispose();
                    frmAdmin.setBounds(WIDTH, WIDTH, 400, 400);
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
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                txtRun.setText("");
                txtRun.requestFocus();
            }

        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void jMCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMCerrarSesionActionPerformed
        JfVendedor.dispatchEvent(new WindowEvent(JfVendedor, WindowEvent.WINDOW_CLOSING));
        this.setVisible(true);
    }//GEN-LAST:event_jMCerrarSesionActionPerformed

    private void btnCancelarCreacionDeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCreacionDeClienteActionPerformed
        JFrameCrearCliente.dispatchEvent(new WindowEvent(JFrameCrearCliente, WindowEvent.WINDOW_CLOSING));

    }//GEN-LAST:event_btnCancelarCreacionDeClienteActionPerformed

    private void btnCrearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearClienteActionPerformed

        try {
            try {
                d.usarConexionAlternativa();
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

            String runClienteARegistrar = txtRunCliente.getText();
            String nombreClienteARegistrar = txtNombreCliente.getText();
            String sueldoClienteARegistrar = txtSueldoCliente.getText();
            Cliente c = new Cliente(runClienteARegistrar, nombreClienteARegistrar, Integer.parseInt(sueldoClienteARegistrar));

            int rutClienteExiste;
            try {
                rutClienteExiste = d.usarProcedimientoCrear_cliente(c, txtRun.getText());
                if (rutClienteExiste == 1) {
                    msgErrorRunDeClienteYaExiste();
                    txtRunCliente.setText("");
                } else if (rutClienteExiste == 0) {
                    msgClienteCreado();

                    txtNombreCliente.setText("");
                    txtRunCliente.setText("");
                    txtSueldoCliente.setText("");
                }

            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                d.cerrarConexionAlternativa();
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (NumberFormatException e) {
            msgErrorIngresoSueldo();
            txtSueldoCliente.setText("");
        }


    }//GEN-LAST:event_btnCrearClienteActionPerformed

    private void btnFiltrarViviendasJfVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarViviendasJfVendedorActionPerformed

        if ((!chkFiltrarPorDepartamentos.isSelected()) && (chkFiltrarPorCasas.isSelected()) && (cboFiltrarPorCasas.getSelectedIndex() == 2) && (rbtFiltrarAsc.isSelected())) {

//            try {
//                viviendasDisponibles = d.leerTodasLasCasasDisponiblesAmbasASC();
//                cargarTablaJFrameVendedor();
//            } catch (SQLException ex) {
//                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else if ((!chkFiltrarPorCasas.isSelected()) && (chkFiltrarPorDepartamentos.isSelected()) && (cboFiltrarPorDepartamentos.getSelectedIndex() == 2 && (rbtFiltrarAsc.isSelected()))) {
            try {
                viviendasDisponibles = d.leerTodosLosDepartamentosDisponiblesAmbosASC();
                cargarTablaJFrameVendedor();
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarTablaJFrameVendedor();
        } else if ((!chkFiltrarPorDepartamentos.isSelected()) && (chkFiltrarPorCasas.isSelected()) && (cboFiltrarPorCasas.getSelectedIndex() == 2) && (rbtFiltrarDesc.isSelected())) {
            try {
                viviendasDisponibles = d.leerTodasLasCasasDisponiblesAmbasDESC();
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarTablaJFrameVendedor();
        } else if ((!chkFiltrarPorCasas.isSelected()) && (chkFiltrarPorDepartamentos.isSelected()) && (cboFiltrarPorDepartamentos.getSelectedIndex() == 2) && (rbtFiltrarDesc.isSelected())) {
            try {
                viviendasDisponibles = d.leerTodosLosDepartamentosDisponiblesAmbosDESC();
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarTablaJFrameVendedor();
        } else if ((chkFiltrarPorDepartamentos.isSelected()) && ((chkFiltrarPorCasas.isSelected())) && (cboFiltrarPorCasas.getSelectedIndex() == 2) && (cboFiltrarPorDepartamentos.getSelectedIndex() == 2) && (rbtFiltrarAsc.isSelected())) {
            try {
                viviendasDisponibles = d.leerTodasLasViviendasDisponiblesASC();
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarTablaJFrameVendedor();
        } else if ((chkFiltrarPorDepartamentos.isSelected()) && ((chkFiltrarPorCasas.isSelected())) && (cboFiltrarPorCasas.getSelectedIndex() == 2) && (cboFiltrarPorDepartamentos.getSelectedIndex() == 2) && (rbtFiltrarDesc.isSelected())) {
            try {
                viviendasDisponibles = d.leerTodasLasViviendasDisponiblesDESC();
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarTablaJFrameVendedor();
        }


    }//GEN-LAST:event_btnFiltrarViviendasJfVendedorActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        cargarTblLog();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        realizarBackup();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        restaurarBackup();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnCrearVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearVendedorActionPerformed
        try {
            Usuario u = new Usuario(txtRunVendedor.getText(),
                    txtNombreVendedor.getText(),
                    2);
            d.crearUsuario(u);

            txtRunVendedor.setText(null);
            txtNombreVendedor.setText(null);

            btnBorrarVendedor.setEnabled(false);
            btnCrearVendedor.setEnabled(false);
            txtNombreVendedor.setEnabled(false);

            txtRunVendedor.requestFocus();
            
            JOptionPane.showMessageDialog(this, "Vendedor creado.", "Creado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_btnCrearVendedorActionPerformed

    private void btnBuscarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendedorActionPerformed
        try {
            Usuario u = d.buscarUsuario(txtRunVendedor.getText());

            if (u != null && u.getTipo() == 2) {
                txtNombreVendedor.setText(u.getNombre());
                btnBorrarVendedor.setEnabled(true);

                txtRunVendedor.requestFocus();
            } else if (u == null) {
                btnCrearVendedor.setEnabled(true);
                txtNombreVendedor.setEnabled(true);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_btnBuscarVendedorActionPerformed

    private void btnBorrarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarVendedorActionPerformed
        try {
            Usuario u = new Usuario(txtRunVendedor.getText(),
                    txtNombreVendedor.getText(),
                    2);
            d.borrarUsuario(u);

            txtRunVendedor.setText(null);
            txtNombreVendedor.setText(null);

            btnCrearVendedor.setEnabled(false);
            btnBorrarVendedor.setEnabled(false);

            txtRunVendedor.requestFocus();

            JOptionPane.showMessageDialog(this, "Vendedor borrado.", "Borrado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println(e);
        }

    }//GEN-LAST:event_btnBorrarVendedorActionPerformed

    private void msgClienteCreado() {
        String titulo = "Aviso";
        String msg = "Cliente registrado con exito";
        int tipoMsg = JOptionPane.INFORMATION_MESSAGE;
        JOptionPane.showMessageDialog(null, msg, titulo, tipoMsg);

    }

    private void msgErrorIngresoSueldo() {
        String titulo = "Error";
        String msg = "Debe ingresar un numero entero en el campo de sueldo";
        int tipoMsg = JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(null, msg, titulo, tipoMsg);
    }

    private void msgErrorRunDeClienteYaExiste() {
        String titulo = "Error";
        String msg = "El rut ingresado ya existe";
        int tipoMsg = JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(null, msg, titulo, tipoMsg);

    }

    private void realizarBackup() {
        try {
            //RECUPERA FECHA Y HORA DEL SISTEMA
            Calendar c = Calendar.getInstance();
            String fecha = c.get(c.DATE) + "" + c.get(c.MONTH) + "" + c.get(c.YEAR);
            String hora = c.get(c.HOUR_OF_DAY) + "" + c.get(c.MINUTE) + "" + c.get(c.SECOND);

            //para poder hacer un backup (en el caso de windows) se debe agregar el directorio de mysql a path de las variables de entorno.
            //pc->propiedades-> conf. Avanzada del sistema-> variables de entorno
            //en la parte de abajo, en variables de sistema buscar "path"->
            //editar->";C:\Program Files\MySQL\MySQL Server 5.7\bin"(copiar todo entre los parentesis incluido el punto y coma)
            //despues de eso se podrá acceder a mysql desde el CMD del sistema ( asi me funciono a mi )
            //ahí deberían poder ejecutar este comando.
            Process proceso = Runtime.getRuntime().exec("mysqldump -u root -p cafesoft");//123456 contraseña por defecto, cambiar si hay otra
            InputStream inputStream = proceso.getInputStream();
            FileOutputStream fOutStream = new FileOutputStream("backup " + fecha + " " + hora + ".sql");//el archivo s guarda en la raíz del proyecto

            byte[] buff = new byte[1000];
            int readed = inputStream.read(buff);
            while (readed > 0) {

                fOutStream.write(buff, 0, readed);
                readed = inputStream.read(buff);
            }

            fOutStream.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void restaurarBackup() {
        try {

            /* en este metodo se puede recuperar X base de datos guardada anteriormente. Sería bueno tener un registro de los nombres de las bases de datos respaldadas
            para poder recuperar solo su nombre. en ese caso, solo deberia cambiarse el nombre en 
            **FileInputStream fInStream = new FileInputStream("backup 1942018 17384.sql");**
            
            DETALLE IMPORTANTE
            El backup borra y reinserta los valores que quedaron guardados en la base de datos de respaldo,
            todo lo que esté despues de lo borrado no podrá ser recuperado.
             */
            Process proceso = Runtime.getRuntime().exec("mysql -u root -p cafesoft"); //123456 contraseña por defecto, cambiar si hay otra
            OutputStream outputStream = proceso.getOutputStream();
            FileInputStream fInStream = new FileInputStream("backup FECHA HORA.sql"); //la fecha y hora son las que da el sistema EJ: backup 1942018 17384
            byte[] buff = new byte[1000];

            int readed = fInStream.read(buff);

            while (readed > 0) {

                outputStream.write(buff, 0, readed);
                readed = fInStream.read(buff);
            }
            outputStream.flush();
            outputStream.close();
            fInStream.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    private javax.swing.JFrame JFrameCrearCliente;
    private javax.swing.JFrame JfVendedor;
    private javax.swing.JButton btnBorrarVendedor;
    private javax.swing.JButton btnBuscarVendedor;
    private javax.swing.JButton btnCancelarCreacionDeCliente;
    private javax.swing.JButton btnCrearCliente;
    private javax.swing.JButton btnCrearVendedor;
    private javax.swing.JButton btnCrearVivienda;
    private javax.swing.JButton btnFiltrarViviendasJfVendedor;
    private javax.swing.ButtonGroup btnGFiltrarPrecio;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JComboBox<String> cboCondicion;
    private javax.swing.JComboBox<String> cboDisp;
    private javax.swing.JComboBox<String> cboFiltrarPorCasas;
    private javax.swing.JComboBox<String> cboFiltrarPorDepartamentos;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JCheckBox chkFiltrarPorCasas;
    private javax.swing.JCheckBox chkFiltrarPorDepartamentos;
    private javax.swing.JFrame frmAdmin;
    private javax.swing.JMenuItem itemAdminSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JMenuItem jMCerrarSesion;
    private javax.swing.JMenuItem jMCrearCli;
    private javax.swing.JMenuItem jMVCamAp;
    private javax.swing.JMenuItem jMVenderViv;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblRUN;
    private javax.swing.JLabel lblRunCliente;
    private javax.swing.JLabel lblSueldoCliente;
    private javax.swing.JLabel lblViviendasDisponibles;
    private javax.swing.JLabel lblicono;
    private javax.swing.JRadioButton rbtFiltrarAsc;
    private javax.swing.JRadioButton rbtFiltrarDesc;
    private javax.swing.JSpinner spnArriendo;
    private javax.swing.JSpinner spnBanios;
    private javax.swing.JSpinner spnPiezas;
    private javax.swing.JSpinner spnVenta;
    private javax.swing.JTable tblDatosFrameVend;
    private javax.swing.JTable tblLog;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNRol;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreVendedor;
    private javax.swing.JTextField txtRun;
    private javax.swing.JTextField txtRunCliente;
    private javax.swing.JTextField txtRunVendedor;
    private javax.swing.JTextField txtSueldoCliente;
    // End of variables declaration//GEN-END:variables
}
