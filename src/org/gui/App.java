package org.gui;

import model.VistaLog;
import model.VistaStatsVend;
import model.VistaStatsViviendas;
import model.VistaVivienda;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Cliente;
import model.Data;
import model.HiloLector;
import model.Log;
import model.StatsSimple;
import model.TModelLog;
import model.TModelNoDisponibles;
import model.TModelStatVendedores;
import model.TModelViviendasDisponibles;
import model.Usuario;
import model.Vivienda;

/**
 *
 * Autores: Lalo Adasme Marcelo Aranda Franco Barrera Marcelo Gatica Javier
 * Vergara
 */
public class App extends javax.swing.JFrame {

    private Data d;
    private TModelViviendasDisponibles modelViviendasDisponibles;
    private TModelNoDisponibles modelStatViviendas;
    private TModelStatVendedores modelStatVendedores;
    private TModelLog modelLog;
    private Log log;
    private List<VistaStatsVend> vendedoresStats;
    private List<VistaVivienda> viviendasDisponibles;
    private List<VistaStatsViviendas> viviendasStats;
    private List<VistaLog> logs;
    private List<Usuario> usuarios;
    private Usuario sesion;
    private JFileChooser fcBackup;
    private FileNameExtensionFilter filtroBakcup;
    private String runUtilizadoParaIngresar;//necesario para un metodo que necesita el run de quien inngreso
    private StatsSimple statSimple;
    private SimpleDateFormat fInicio, fFinal;
    private SpinnerNumberModel nm;
    private String indiceDeViviendaAArrendar;
    private String indiceDeViviendaAVender;
    private String viviendaPasaAEstar;


    /**
     * Creates new form App
     */
    public App() throws SQLException {

        try {
            d = new Data();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "ERROR " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage());
        }
        initComponents();

        HiloDePrueba properties = new HiloDePrueba();
        properties.start();
        

        this.setTitle("Inicio de sesión");
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        
        txtAListaClientes.setEditable(false);
        cargarListaClientes();
        fcBackup = new JFileChooser();
        filtroBakcup = new FileNameExtensionFilter("Seleccione un archivo SQL Válido", "sql");
        fcBackup.setFileFilter(filtroBakcup);

        fInicio = new SimpleDateFormat("yyyy-MM-dd");
        fFinal = new SimpleDateFormat("yyyy-MM-dd");

        nm = new SpinnerNumberModel();
        nm.setMinimum(0);

        JfVendedor.setTitle("Vendedor");
        inicializarSeleccionDeFiltroVendedor();
        //  Data d= new Data();
        // se puede descomentar esto para incializar tabla de vendedor, con datos. viviendasDisponibles = d.leerTodasLasViviendasDisponibles();

        llenarCbosFrameVendedor();
        cargarTablaJFrameVendedor();
        getSimpleStats();

        cargarTblLog();

        JFrameCrearCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JfVendedor = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatosFrameVend = new javax.swing.JTable();
        rbtFiltrarAsc = new javax.swing.JRadioButton();
        rbtFiltrarDesc = new javax.swing.JRadioButton();
        cboFiltrarPorEstado = new javax.swing.JComboBox<>();
        chkFiltrarPorCasas = new javax.swing.JCheckBox();
        chkFiltrarPorDepartamentos = new javax.swing.JCheckBox();
        lblOrden = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblViviendasDisponibles = new javax.swing.JLabel();
        btnFiltrarViviendasJfVendedor = new javax.swing.JButton();
        lblTipoPrecio = new javax.swing.JLabel();
        rbtDeVenta = new javax.swing.JRadioButton();
        rbtDeRenta = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAListaClientes = new javax.swing.JTextArea();
        lblListaClientes = new javax.swing.JLabel();
        jMBarra = new javax.swing.JMenuBar();
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
        btnBuscarVivienda = new javax.swing.JButton();
        btnBorrarVivienda = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnVerViviendas = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtDepa = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtCasas = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTodos = new javax.swing.JTextField();
        jdHasta = new com.toedter.calendar.JDateChooser();
        jdDesde = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnEstDefault = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        btnVerVendedores = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLog = new javax.swing.JTable();
        btnLog = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnRespaldo = new javax.swing.JButton();
        btnRestaurar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
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
        frmStatViviendas = new javax.swing.JFrame();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbStatViviendas = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jdInicio1 = new com.toedter.calendar.JDateChooser();
        jdTermino1 = new com.toedter.calendar.JDateChooser();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        cboContrato = new javax.swing.JComboBox<>();
        cboTipoVivienda = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        frmStatVendedores = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbStatVendedores = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jdInicio2 = new com.toedter.calendar.JDateChooser();
        jdTermino2 = new com.toedter.calendar.JDateChooser();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        cboOrden = new javax.swing.JComboBox<>();
        btnGVentaORenta = new javax.swing.ButtonGroup();
        frmColores = new javax.swing.JFrame();
        clrChoosser = new javax.swing.JColorChooser();
        btnGuardarCambiosBotones = new javax.swing.JButton();
        btnCancelarColores = new javax.swing.JButton();
        rdCambiarColoresTextoBotones = new javax.swing.JRadioButton();
        rdCambiarColoresBotones = new javax.swing.JRadioButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        btnGCambioColores = new javax.swing.ButtonGroup();
        jFSeleccionarClienteContrato = new javax.swing.JFrame();
        lblRunClienteContrato = new javax.swing.JLabel();
        txtRunCliContrato = new javax.swing.JTextField();
        btnArrendarOVender = new javax.swing.JButton();
        txtRun = new javax.swing.JTextField();
        lblRUN = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        lblicono = new javax.swing.JLabel();

        JfVendedor.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        JfVendedor.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                JfVendedorWindowClosing(evt);
            }
        });

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

        cboFiltrarPorEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chkFiltrarPorCasas.setText("Filtrar por casas");

        chkFiltrarPorDepartamentos.setText("Filtrar por departamentos");

        lblOrden.setText("Orden");

        lblViviendasDisponibles.setText("            Viviendas disponibles");

        btnFiltrarViviendasJfVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_filter_64280.png"))); // NOI18N
        btnFiltrarViviendasJfVendedor.setText("Filtrar");
        btnFiltrarViviendasJfVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarViviendasJfVendedorActionPerformed(evt);
            }
        });

        lblTipoPrecio.setText("Tipo de precio");

        btnGVentaORenta.add(rbtDeVenta);
        rbtDeVenta.setText("De venta");

        btnGVentaORenta.add(rbtDeRenta);
        rbtDeRenta.setText("De renta (mensual)");

        txtAListaClientes.setColumns(20);
        txtAListaClientes.setRows(5);
        jScrollPane5.setViewportView(txtAListaClientes);

        lblListaClientes.setText("Lista de clientes");

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
        jMVCamAp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMVCamApActionPerformed(evt);
            }
        });
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

        jMBarra.add(jMArchivo);

        JfVendedor.setJMenuBar(jMBarra);

        javax.swing.GroupLayout JfVendedorLayout = new javax.swing.GroupLayout(JfVendedor.getContentPane());
        JfVendedor.getContentPane().setLayout(JfVendedorLayout);
        JfVendedorLayout.setHorizontalGroup(
            JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JfVendedorLayout.createSequentialGroup()
                .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(JfVendedorLayout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(lblViviendasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFiltrarViviendasJfVendedor)
                .addGap(149, 149, 149))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblListaClientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JfVendedorLayout.createSequentialGroup()
                        .addComponent(lblTipoPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtDeVenta)
                        .addGap(18, 18, 18)
                        .addComponent(rbtDeRenta)
                        .addContainerGap())
                    .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                            .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chkFiltrarPorDepartamentos)
                                .addComponent(chkFiltrarPorCasas))
                            .addGap(18, 18, 18)
                            .addComponent(cboFiltrarPorEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(66, 66, 66))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                            .addComponent(lblOrden)
                            .addGap(18, 18, 18)
                            .addComponent(rbtFiltrarDesc)
                            .addGap(18, 18, 18)
                            .addComponent(rbtFiltrarAsc)
                            .addGap(31, 31, 31)))))
        );
        JfVendedorLayout.setVerticalGroup(
            JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JfVendedorLayout.createSequentialGroup()
                .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(JfVendedorLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkFiltrarPorCasas)
                            .addComponent(cboFiltrarPorEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(chkFiltrarPorDepartamentos)
                        .addGap(2, 2, 2)
                        .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipoPrecio)
                            .addComponent(rbtDeVenta)
                            .addComponent(rbtDeRenta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JfVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtFiltrarDesc)
                            .addComponent(rbtFiltrarAsc)
                            .addComponent(lblOrden)))
                    .addGroup(JfVendedorLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblListaClientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnFiltrarViviendasJfVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(lblViviendasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        frmAdmin.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        frmAdmin.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                frmAdminWindowClosing(evt);
            }
        });

        jLabel1.setText("RUN:");

        jLabel2.setText("Nombre:");

        txtNombreVendedor.setEnabled(false);

        btnCrearVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_Create_132699.png"))); // NOI18N
        btnCrearVendedor.setText(" Crear");
        btnCrearVendedor.setEnabled(false);
        btnCrearVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearVendedorActionPerformed(evt);
            }
        });

        btnBuscarVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_search_107183.png"))); // NOI18N
        btnBuscarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVendedorActionPerformed(evt);
            }
        });

        btnBorrarVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_basket_1814090.png"))); // NOI18N
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
                        .addGap(0, 348, Short.MAX_VALUE)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtRunVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearVendedor)
                    .addComponent(btnBorrarVendedor))
                .addContainerGap(212, Short.MAX_VALUE))
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

        txtDireccion.setEnabled(false);

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTipo.setEnabled(false);

        cboDisp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDisp.setEnabled(false);

        cboCondicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCondicion.setEnabled(false);

        spnBanios.setEnabled(false);

        spnPiezas.setEnabled(false);

        spnArriendo.setEnabled(false);

        spnVenta.setEnabled(false);

        btnCrearVivienda.setText("Crear");
        btnCrearVivienda.setEnabled(false);
        btnCrearVivienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearViviendaActionPerformed(evt);
            }
        });

        btnBuscarVivienda.setText("...");
        btnBuscarVivienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarViviendaActionPerformed(evt);
            }
        });

        btnBorrarVivienda.setText("Borrar");
        btnBorrarVivienda.setEnabled(false);
        btnBorrarVivienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarViviendaActionPerformed(evt);
            }
        });

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
                            .addComponent(txtDireccion)
                            .addComponent(cboTipo, 0, 419, Short.MAX_VALUE)
                            .addComponent(cboDisp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboCondicion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spnBanios)
                            .addComponent(spnPiezas)
                            .addComponent(spnVenta)
                            .addComponent(spnArriendo)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNRol)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarVivienda))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBorrarVivienda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCrearVivienda)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarVivienda)))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearVivienda)
                    .addComponent(btnBorrarVivienda))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Vivienda", jPanel2);

        btnVerViviendas.setText("Ver");
        btnVerViviendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerViviendasActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Tabla de viviendas vendidas o arrendadas");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Cantidad de casas vendidas                     :");

        txtDepa.setEditable(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Cantidad de departamentos vendidos       :");

        txtCasas.setEditable(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Cantidad de contratos realizados              :");

        txtTodos.setEditable(false);

        jLabel24.setText("Desde :");

        jLabel25.setText("Hasta :");

        jButton2.setText("Por defecto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnEstDefault.setText("Filtrar por fecha");
        btnEstDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstDefaultActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Tabla de contratos por vendedores");

        btnVerVendedores.setText("Ver");
        btnVerVendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVendedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCasas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jdDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(btnEstDefault)
                            .addGap(53, 53, 53)
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jdHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(52, 52, 52))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(83, 83, 83)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVerVendedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVerViviendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtDepa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jLabel24)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEstDefault)
                        .addComponent(jLabel25)))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(btnVerViviendas))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerVendedores)
                    .addComponent(jLabel26))
                .addContainerGap(81, Short.MAX_VALUE))
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

        btnLog.setText("Actualizar Log");
        btnLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLog)
                .addGap(220, 220, 220))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addComponent(btnLog)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ver Log", jPanel4);

        btnRespaldo.setText("Respaldar");
        btnRespaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRespaldoActionPerformed(evt);
            }
        });

        btnRestaurar.setText("Restaurar");
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Crear punto de restauración");

        jLabel13.setText("Crea un punto de restauración con toda la información de la aplicacion, ");

        jLabel14.setText("fecha y hora actual para un posible uso posterior.");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Restaurar desde un punto guardado");

        jLabel16.setText("Carga todos los datos de un archivo de restauración anteriormente");

        jLabel17.setText("guardado, sobreescribiendo la infromación actual en la aplicación.");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("ADVERTENCIA :  Para evitar perdida de datos, se recomienda crear un punto de ");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("restauración antes de restaurar desde un punto guardado.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRestaurar))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                            .addComponent(btnRespaldo)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addGap(27, 27, 27)
                .addComponent(jLabel13)
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(btnRespaldo))
                .addGap(26, 26, 26)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(25, 25, 25)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(btnRestaurar))
                .addGap(30, 30, 30)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addContainerGap(68, Short.MAX_VALUE))
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

        btnCrearCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_Create_132699.png"))); // NOI18N
        btnCrearCliente.setText("Crear");
        btnCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearClienteActionPerformed(evt);
            }
        });

        lblNombreCliente.setText("Nombre:");

        lblRunCliente.setText("Run:");

        lblSueldoCliente.setText("Sueldo:");

        btnCancelarCreacionDeCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_cancel_103431.png"))); // NOI18N
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
                .addComponent(btnCancelarCreacionDeCliente)
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
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Listado de viviendas arrendadas/vendidas"));

        jScrollPane3.setViewportView(tbStatViviendas);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Filtrar"));

        jSpinner1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel27.setText("Fecha Inicio:");

        jLabel28.setText("Fecha Termino:");

        jLabel29.setText("Ctdad :");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_filter_64280.png"))); // NOI18N
        jButton1.setText("Filtrar tabla");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_restore_2460287.png"))); // NOI18N
        jButton4.setText("Restaurar tabla");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel30.setText("Tipo de contrato:");

        jLabel35.setText("Tipo de Vivienda:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(cboContrato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdTermino1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdInicio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTipoVivienda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdTermino1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTipoVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4))
        );

        javax.swing.GroupLayout frmStatViviendasLayout = new javax.swing.GroupLayout(frmStatViviendas.getContentPane());
        frmStatViviendas.getContentPane().setLayout(frmStatViviendasLayout);
        frmStatViviendasLayout.setHorizontalGroup(
            frmStatViviendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmStatViviendasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frmStatViviendasLayout.setVerticalGroup(
            frmStatViviendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frmStatViviendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frmStatViviendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Listado de estadisticas vendedores"));

        jScrollPane4.setViewportView(tbStatVendedores);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Filtrar"));

        jLabel31.setText("Fecha Inicio:");

        jLabel32.setText("Fecha Termino:");

        jLabel33.setText("Top :");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_filter_64280.png"))); // NOI18N
        jButton5.setText("Filtrar tabla");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_restore_2460287.png"))); // NOI18N
        jButton6.setText("Restaurar tabla");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel34.setText("Tipo de Orden:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton6)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdTermino2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cboOrden, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdTermino2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6))
        );

        javax.swing.GroupLayout frmStatVendedoresLayout = new javax.swing.GroupLayout(frmStatVendedores.getContentPane());
        frmStatVendedores.getContentPane().setLayout(frmStatVendedoresLayout);
        frmStatVendedoresLayout.setHorizontalGroup(
            frmStatVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmStatVendedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frmStatVendedoresLayout.setVerticalGroup(
            frmStatVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frmStatVendedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frmStatVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnGuardarCambiosBotones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_stock_save_20659.png"))); // NOI18N
        btnGuardarCambiosBotones.setText("Guardar Cambios");
        btnGuardarCambiosBotones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosBotonesActionPerformed(evt);
            }
        });

        btnCancelarColores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_cancel_103431.png"))); // NOI18N
        btnCancelarColores.setText("Cancelar");
        btnCancelarColores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarColoresActionPerformed(evt);
            }
        });

        btnGCambioColores.add(rdCambiarColoresTextoBotones);
        rdCambiarColoresTextoBotones.setText("Cambiar Color Texto Botones");

        btnGCambioColores.add(rdCambiarColoresBotones);
        rdCambiarColoresBotones.setSelected(true);
        rdCambiarColoresBotones.setText("Cambiar Color Botones");

        jToggleButton1.setText("Por Defecto");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frmColoresLayout = new javax.swing.GroupLayout(frmColores.getContentPane());
        frmColores.getContentPane().setLayout(frmColoresLayout);
        frmColoresLayout.setHorizontalGroup(
            frmColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmColoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frmColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frmColoresLayout.createSequentialGroup()
                        .addComponent(btnGuardarCambiosBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frmColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rdCambiarColoresTextoBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdCambiarColoresBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(btnCancelarColores, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(clrChoosser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        frmColoresLayout.setVerticalGroup(
            frmColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmColoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clrChoosser, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frmColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frmColoresLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(btnGuardarCambiosBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnCancelarColores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(frmColoresLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(frmColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(frmColoresLayout.createSequentialGroup()
                                .addComponent(rdCambiarColoresBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdCambiarColoresTextoBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );

        lblRunClienteContrato.setText("Run cliente");

        btnArrendarOVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_xeyes_9544.png"))); // NOI18N
        btnArrendarOVender.setText("Arrendar/Vender");
        btnArrendarOVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrendarOVenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFSeleccionarClienteContratoLayout = new javax.swing.GroupLayout(jFSeleccionarClienteContrato.getContentPane());
        jFSeleccionarClienteContrato.getContentPane().setLayout(jFSeleccionarClienteContratoLayout);
        jFSeleccionarClienteContratoLayout.setHorizontalGroup(
            jFSeleccionarClienteContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFSeleccionarClienteContratoLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(lblRunClienteContrato)
                .addGap(18, 18, 18)
                .addGroup(jFSeleccionarClienteContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnArrendarOVender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRunCliContrato))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jFSeleccionarClienteContratoLayout.setVerticalGroup(
            jFSeleccionarClienteContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFSeleccionarClienteContratoLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jFSeleccionarClienteContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRunClienteContrato)
                    .addComponent(txtRunCliContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnArrendarOVender)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblRUN.setText("R.U.N");

        btnIniciarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/if_door_in_35977.png"))); // NOI18N
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

    private void cargarListaClientes() throws SQLException{
        ArrayList<Cliente> listCli=new ArrayList();
        listCli=d.obtenerListaDeClientes();
        
        
        for (Cliente c : listCli) {
            txtAListaClientes.append(c.toString() + "\n");
            
        }
        
    }
    
    private void jMVenderVivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMVenderVivActionPerformed
        int colSelec = 0;
        int filaSelec = tblDatosFrameVend.getSelectedRow();

        viviendaPasaAEstar = "Vendida";

        try {
            indiceDeViviendaAVender = tblDatosFrameVend.getModel().getValueAt(filaSelec, colSelec).toString();

        } catch (Exception e) {
            msgErrorNoSeSeleccionoVivienda();
        }
        if (indiceDeViviendaAVender != null) {
            jFSeleccionarClienteContrato.setBounds(WIDTH, WIDTH, 400, 400);
            jFSeleccionarClienteContrato.setLocationRelativeTo(null);
            jFSeleccionarClienteContrato.setVisible(true);
        }

    }//GEN-LAST:event_jMVenderVivActionPerformed

    private void jMArrendarViviendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMArrendarViviendaActionPerformed

        int colSelec = 0;
        int filaSelec = tblDatosFrameVend.getSelectedRow();

        viviendaPasaAEstar = "Arrendada";

        try {
            indiceDeViviendaAArrendar = tblDatosFrameVend.getModel().getValueAt(filaSelec, colSelec).toString();

        } catch (Exception e) {
            msgErrorNoSeSeleccionoVivienda();
        }
        if (indiceDeViviendaAArrendar != null) {
            jFSeleccionarClienteContrato.setBounds(WIDTH, WIDTH, 400, 400);
            jFSeleccionarClienteContrato.setLocationRelativeTo(null);
            jFSeleccionarClienteContrato.setVisible(true);
        }
    }//GEN-LAST:event_jMArrendarViviendaActionPerformed

    private void itemAdminSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAdminSalirActionPerformed
        try {
            salir();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemAdminSalirActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        String run = txtRun.getText();
        runUtilizadoParaIngresar = txtRun.getText();
        txtRun.setText(null);

        try {
            Usuario u = d.buscarUsuario(run);
            if (u != null) {
                sesion = u;
                log = new Log();
                log.setDescripcion(sesion.getNombre() + " Ha iniciado sesion");
                log.setUsuario(sesion.getRun());
                d.crearLog(log);
                if (u.getTipo() == 1) {

                    this.dispose();
                    cargarCboViviendaAdmin();
                    frmAdmin.setBounds(WIDTH, WIDTH, 600, 450);
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
        try {
            //JfVendedor.dispatchEvent(new WindowEvent(JfVendedor, WindowEvent.WINDOW_CLOSING));
            salir();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMCerrarSesionActionPerformed

    private void btnCancelarCreacionDeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCreacionDeClienteActionPerformed
        JFrameCrearCliente.dispatchEvent(new WindowEvent(JFrameCrearCliente, WindowEvent.WINDOW_CLOSING));

    }//GEN-LAST:event_btnCancelarCreacionDeClienteActionPerformed

    private void btnCrearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearClienteActionPerformed

        try {
            
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
                    rutClienteExiste = d.usarFuncionCrear_cliente(c, runUtilizadoParaIngresar);
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
            cargarListaClientes();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnCrearClienteActionPerformed

    private void btnFiltrarViviendasJfVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarViviendasJfVendedorActionPerformed
        String filtro = new String();
        boolean precio = false;
        boolean existe = false;

        if (chkFiltrarPorCasas.isSelected()) {
            if (!chkFiltrarPorDepartamentos.isSelected()) {
                filtro += " tipo = 'Casa'";
                existe = true;
            }

        }

        if (chkFiltrarPorDepartamentos.isSelected()) {
            if (!chkFiltrarPorCasas.isSelected()) {
                filtro += " tipo = 'Departamento'";
                existe = true;
            }

        }
        switch (cboFiltrarPorEstado.getSelectedIndex()) {
            case 0:
                if (existe) {
                    filtro += " AND";
                }
                filtro += " condicion = 'Nueva'";
                existe = true;
                break;
            case 1:
                if (existe) {
                    filtro += " AND";

                }
                filtro += " condicion = 'Usado'";
                existe = true;
            default:
                break;
        }
        if (rbtDeRenta.isSelected()) {
            precio = true;
        }

        if (rbtFiltrarDesc.isSelected()) {
            if (precio) {
                filtro += " ORDER BY precio_arriendo DESC";
            } else {
                filtro += " ORDER BY precio_venta DESC";
            }
        } else {
            if (precio) {
                filtro += " ORDER BY precio_arriendo ASC";
            } else {
                filtro += " ORDER BY precio_venta ASC";
            }
        }
        if (existe) {
            filtro = "WHERE " + filtro;
        }

        try {
            /* SIENDO LAS 5:25 AM DEL DIA EN QUE SE ENTREGA ESTO NO FUINCIONA XD ASI QUE LO HARE A MI ESTILO
            String tipo1 = "";
            String tipo2 = "";
            String condicion1 = "";
            String condicion2 = "";
            String tipoPrecio = "";
            String orden = "";
            boolean consultaCompleja = false;
            
            //evalua si se Filtra ascendentemente or descendentemente
            if (rbtFiltrarAsc.isSelected()) {
            orden = "ASC";
            } else if (rbtFiltrarDesc.isSelected()) {
            orden = "DESC";
            }
            
            //evalua si se filtra por precio de venta o precio de arriendo
            if (rbtDeVenta.isSelected()) {
            tipoPrecio = "precio_venta";
            } else if (rbtDeRenta.isSelected()) {
            tipoPrecio = "precio_arriendo";
            }
            
            //evalua si se filtran casas o departamentos (3 if grandes, cada uno con sus respectivas posibilidades de filtrado)
            if (chkFiltrarPorCasas.isSelected() && !chkFiltrarPorDepartamentos.isSelected()) {
            tipo1 = "Casa";
            tipo2 = "Casa";

            if (cboFiltrarPorCasas.getSelectedIndex() == 0) {
            condicion1 = "Nueva";
            condicion2 = "Nueva";
            } else if (cboFiltrarPorCasas.getSelectedIndex() == 1) {
            condicion1 = "Usada";
            condicion2 = "Usada";
            } else if (cboFiltrarPorCasas.getSelectedIndex() == 2) {
            condicion1 = "Usada";
            condicion2 = "Nueva";
            }
            
            } else if (!chkFiltrarPorCasas.isSelected() && chkFiltrarPorDepartamentos.isSelected()) {
            tipo1 = "Departamento";
            tipo2 = "Departamento";

            if (cboFiltrarPorDepartamentos.getSelectedIndex() == 0) {
            condicion1 = "Nueva";
            condicion2 = "Nueva";
            } else if (cboFiltrarPorDepartamentos.getSelectedIndex() == 1) {
            condicion1 = "Usada";
            condicion2 = "Usada";
            } else if (cboFiltrarPorDepartamentos.getSelectedIndex() == 2) {
            condicion1 = "Usada";
            condicion2 = "Nueva";
            }
            } else if (chkFiltrarPorCasas.isSelected() && chkFiltrarPorDepartamentos.isSelected()) {
            tipo1 = "Casa";
            tipo2 = "Departamento";

            if (cboFiltrarPorCasas.getSelectedIndex() == 2 && cboFiltrarPorDepartamentos.getSelectedIndex() == 2) {
            condicion1 = "Usada";
            condicion2 = "Nueva";
            } else if (cboFiltrarPorCasas.getSelectedIndex() == 0 && cboFiltrarPorDepartamentos.getSelectedIndex() == 1) {
            condicion1 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo1 + "' AND condicion='Nueva' ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            condicion2 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo2 + "' AND condicion='Usada' ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            consultaCompleja = true;
            } else if (cboFiltrarPorCasas.getSelectedIndex() == 1 && cboFiltrarPorDepartamentos.getSelectedIndex() == 0) {
            condicion1 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo1 + "' AND condicion='Usada' ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            condicion2 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo2 + "' AND condicion='Nueva' ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            consultaCompleja = true;
            } else if (cboFiltrarPorCasas.getSelectedIndex() == 2 && cboFiltrarPorDepartamentos.getSelectedIndex() == 1) {
            condicion1 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo1 + "' AND (condicion='Nueva' OR condicion='Usada') ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            condicion2 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo2 + "' AND condicion='Usada' ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            consultaCompleja = true;
            } else if (cboFiltrarPorCasas.getSelectedIndex() == 2 && cboFiltrarPorDepartamentos.getSelectedIndex() == 0) {
            condicion1 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo1 + "' AND (condicion='Nueva' OR condicion='Usada') ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            condicion2 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo2 + "' AND condicion='Nueva' ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            consultaCompleja = true;
            } else if (cboFiltrarPorCasas.getSelectedIndex() == 0 && cboFiltrarPorDepartamentos.getSelectedIndex() == 2) {
            condicion1 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo1 + "' AND condicion='Nueva' ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            condicion2 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo2 + "' AND (condicion='Usada' or condicion='Nueva') ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            consultaCompleja = true;
            } else if (cboFiltrarPorCasas.getSelectedIndex() == 1 && cboFiltrarPorDepartamentos.getSelectedIndex() == 2) {
            condicion1 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo1 + "' AND condicion='Usada' ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            condicion2 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo2 + "' AND (condicion='Usada' OR condicion='Nueva') ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            consultaCompleja = true;
            } else if (cboFiltrarPorCasas.getSelectedIndex() == 1 && cboFiltrarPorDepartamentos.getSelectedIndex() == 1) {
            condicion1 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo1 + "' AND condicion='Usada' ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            condicion2 = "SELECT * FROM vista_viviendas_disponibles WHERE tipo='" + tipo2 + "' AND condicion='Usada' ORDER BY '" + tipoPrecio + "' '" + orden + "' ";
            consultaCompleja = true;
            }
            
            }
            
            try {
            viviendasDisponibles = d.leerTodasLasViviendasSegunSeleccion(tipo1, tipo2, condicion1, condicion2, tipoPrecio, orden, consultaCompleja);
            } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
             */
            filtrarTablaJFrameVendedor(filtro);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnFiltrarViviendasJfVendedorActionPerformed

    private void btnCrearVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearVendedorActionPerformed
        try {
            Usuario u = new Usuario(txtRunVendedor.getText(),
                    txtNombreVendedor.getText(),
                    2);
            d.crearUsuario(u, sesion.getRun());

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
            log = new Log();
            log.setDescripcion(sesion.getNombre() + " ha borrado al usuario de run " + u.getRun());
            log.setUsuario(sesion.getRun());
            d.crearLog(log);
        } catch (Exception e) {
            System.err.println(e);
        }

    }//GEN-LAST:event_btnBorrarVendedorActionPerformed

    private void btnBuscarViviendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarViviendaActionPerformed
        try {
            Vivienda v = d.buscarVivienda(Integer.parseInt(txtNRol.getText()));

            if (v != null) {
                txtDireccion.setText(v.getDireccion());
                cboTipo.setSelectedIndex(v.getTipo() - 1);
                cboDisp.setSelectedIndex(v.getDispVivienda() - 1);

                if (v.isUsada()) {
                    cboDisp.setSelectedItem("Usada");
                } else {
                    cboDisp.setSelectedItem("Nueva");
                }

                spnBanios.setValue(v.getCantBaños());
                spnPiezas.setValue(v.getCantPiezas());
                spnArriendo.setValue(v.getPrecioArriendo());
                spnVenta.setValue(v.getPrecioVenta());

                btnBorrarVivienda.setEnabled(true);
            } else {
                txtDireccion.setEnabled(true);
                cboTipo.setEnabled(true);
                cboDisp.setEnabled(true);
                cboCondicion.setEnabled(true);
                spnBanios.setEnabled(true);
                spnPiezas.setEnabled(true);
                spnArriendo.setEnabled(true);
                spnVenta.setEnabled(true);
                btnCrearVivienda.setEnabled(true);

                v.setnRol(Integer.parseInt(txtNRol.getText()));
                v.setTipo((cboTipo.getSelectedIndex() + 1));
                v.setDispVivienda((cboDisp.getSelectedIndex() + 1));
                v.setPrecioArriendo((Integer) spnArriendo.getValue());
                v.setPrecioVenta((Integer) spnVenta.getValue());
                v.setCantBaños((Integer) spnBanios.getValue());
                v.setCantPiezas((Integer) spnPiezas.getValue());
                v.setDireccion(txtDireccion.getText());

                if (cboCondicion.getSelectedItem().equals("Usada")) {
                    v.setUsada(true);
                } else {
                    v.setUsada(false);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_btnBuscarViviendaActionPerformed

    private void btnCrearViviendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearViviendaActionPerformed
        try {
            int nrol = Integer.parseInt(txtNRol.getText());

            int tipo = cboTipo.getSelectedIndex() + 1;

            int disp = cboDisp.getSelectedIndex() + 1;
            int arriendo = (Integer) spnArriendo.getValue();
            int venta = (Integer) spnVenta.getValue();
            int banios = (Integer) spnBanios.getValue();
            int piezas = (Integer) spnPiezas.getValue();
            String direccion = txtDireccion.getText();
            boolean cond = false;

            if (cboCondicion.getSelectedItem().equals("Nueva")) {
                cond = true;
            }

            Vivienda v = new Vivienda(nrol, disp, tipo, arriendo, venta, banios, piezas, direccion, cond);

            d.crearVivienda(v, sesion.getRun());

            txtNRol.setText(null);
            txtDireccion.setText(null);
            cboTipo.setSelectedIndex(0);
            cboDisp.setSelectedIndex(0);
            cboCondicion.setSelectedIndex(0);
            spnBanios.setValue(0);
            spnPiezas.setValue(0);
            spnArriendo.setValue(0);
            spnVenta.setValue(0);

            btnCrearVivienda.setEnabled(false);

            JOptionPane.showMessageDialog(this, "Vivienda creada.", "Creado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_btnCrearViviendaActionPerformed

    private void btnBorrarViviendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarViviendaActionPerformed
        try {
            int nrol = Integer.parseInt(txtNRol.getText());
            int tipo = cboTipo.getSelectedIndex() + 1;
            int disp = cboDisp.getSelectedIndex() + 1;
            int arriendo = (Integer) spnArriendo.getValue();
            int venta = (Integer) spnVenta.getValue();
            int banios = (Integer) spnBanios.getValue();
            int piezas = (Integer) spnPiezas.getValue();
            String direccion = txtDireccion.getText();
            boolean cond = false;

            if (cboCondicion.getSelectedItem().equals("Nueva")) {
                cond = true;
            }

            Vivienda v = new Vivienda(nrol, tipo, disp, arriendo, venta, banios, piezas, direccion, cond);

            d.borrarVivienda(v);

            txtNRol.setText(null);
            txtDireccion.setText(null);
            cboTipo.setSelectedIndex(0);
            cboDisp.setSelectedIndex(0);
            cboCondicion.setSelectedIndex(0);
            spnBanios.setValue(0);
            spnPiezas.setValue(0);
            spnArriendo.setValue(0);
            spnVenta.setValue(0);

            btnBorrarVivienda.setEnabled(false);

            JOptionPane.showMessageDialog(this, "Vivienda borrada.", "Borrado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_btnBorrarViviendaActionPerformed

    private void btnRespaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRespaldoActionPerformed
        try {
            confirmarRespaldo();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRespaldoActionPerformed

    private void btnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarActionPerformed

        try {
            confirmarRestaurar();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRestaurarActionPerformed

    private void frmAdminWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frmAdminWindowClosing
        try {
            salir();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_frmAdminWindowClosing

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void JfVendedorWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JfVendedorWindowClosing
        try {
            salir();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JfVendedorWindowClosing

    private void btnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogActionPerformed
        try {
            cargarTblLog();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLogActionPerformed

    private void btnVerViviendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerViviendasActionPerformed
        cargarViviendaStatFiltrada("");
        cargarCboViviendaStat();
        jSpinner1.setModel(nm);
        jdInicio1.setCalendar(null);
        jdTermino1.setCalendar(null);
        frmStatViviendas.setVisible(true);
        frmStatViviendas.setBounds(WIDTH, WIDTH, 880, 550);
        frmStatViviendas.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnVerViviendasActionPerformed

    private void jMVCamApActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMVCamApActionPerformed
        frmColores.setBounds(WIDTH, WIDTH, 920, 620);
        frmColores.setLocationRelativeTo(null);
        frmColores.setVisible(true);
    }//GEN-LAST:event_jMVCamApActionPerformed

    private void btnEstDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstDefaultActionPerformed
        if (jdDesde.getDate() != null && jdHasta.getDate() != null) {
            getFechaStats();
        }
    }//GEN-LAST:event_btnEstDefaultActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        getSimpleStats();
        jdDesde.setCalendar(null);
        jdHasta.setCalendar(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnVerVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVendedoresActionPerformed
        jSpinner2.setModel(nm);
        jdInicio2.setCalendar(null);
        jdTermino2.setCalendar(null);
        cargarCboVendedorStat();
        cargarVendedoresStatFiltrada("'1995-05-01','2028-05-23','0','0'");
        frmStatVendedores.setVisible(true);
        frmStatVendedores.setBounds(WIDTH, WIDTH, 880, 550);
        frmStatVendedores.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnVerVendedoresActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String filtro = new String();
        boolean existe = false;
        if (jdInicio1.getDate() != null && jdTermino1.getDate() != null) {
            filtro += " fecha BETWEEN '" + fInicio.format(jdInicio1.getDate()) + "' AND '" + fFinal.format(jdTermino1.getDate()) + "'";
            existe = true;
        }
        if (cboContrato.getSelectedIndex() > 0) {
            if (existe) {
                filtro += " AND";
            }
            if (cboContrato.getSelectedIndex() != 1) {
                filtro += " es_disponible = 'Vendida'";
                existe = true;
            } else {
                filtro += " es_disponible = 'Arrendada'";
                existe = true;
            }
        }
        if (cboTipoVivienda.getSelectedIndex() > 0) {
            if (existe) {
                filtro += " AND";
            }
            if (cboTipoVivienda.getSelectedIndex() != 1) {
                filtro += " tipo = 'Departamento'";
                existe = true;
            } else {
                filtro += " tipo = 'Casa'";
                existe = true;
            }
        }
        if (existe) {
            filtro = " WHERE" + filtro;
        }
        if ((int) (jSpinner1.getValue()) != 0) {
            filtro += " LIMIT " + ((int) (jSpinner1.getValue())) + "";
        }
        cargarViviendaStatFiltrada(filtro);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jdInicio1.setCalendar(null);
        jdTermino1.setCalendar(null);
        jSpinner1.setValue(0);
        cargarCboViviendaStat();
        cargarViviendaStatFiltrada("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jdInicio2.setCalendar(null);
        jdTermino2.setCalendar(null);
        jSpinner2.setValue(0);
        cargarCboVendedorStat();
        cargarVendedoresStatFiltrada("'1995-05-01','2028-05-23','0','0'");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String filtro = new String();

        if (jdInicio2.getDate() != null && jdTermino2.getDate() != null) {
            filtro += "'" + fInicio.format(jdInicio2.getDate()) + "','" + fFinal.format(jdTermino2.getDate()) + "',";
        } else {
            filtro += "'1995-05-01','2028-05-23',";
        }
        filtro += "'" + ((int) jSpinner2.getValue()) + "',";
        filtro += "'" + cboOrden.getSelectedIndex() + "'";
        cargarVendedoresStatFiltrada(filtro);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnArrendarOVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrendarOVenderActionPerformed
        int nRol = 0;
        String runCliCon = txtRunCliContrato.getText();
        txtRunCliContrato.setText("");
        if (viviendaPasaAEstar.equals("Vendida")) {
            nRol = Integer.parseInt(indiceDeViviendaAVender);
        } else if (viviendaPasaAEstar.equals("Arrendada")) {
            nRol = Integer.parseInt(indiceDeViviendaAArrendar);
        }

        try {
            if (d.buscarCoincidenciasDeRutCli(runCliCon) == 0) {
                msgErrorRutNoEncontrado();
            } else if (d.buscarCoincidenciasDeRutCli(runCliCon) == 1) {
                try {
                    d.llamarProcedimientocrear_contrato(nRol, viviendaPasaAEstar, runCliCon, runUtilizadoParaIngresar);
                    msgTrasaccionExitosa();
                    cargarTablaJFrameVendedor();
                } catch (SQLException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnArrendarOVenderActionPerformed

    private void btnCancelarColoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarColoresActionPerformed
        frmColores.setVisible(false);
    }//GEN-LAST:event_btnCancelarColoresActionPerformed

    private void btnGuardarCambiosBotonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosBotonesActionPerformed
        Color color = clrChoosser.getColor();

        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        Properties p = new Properties();
        File config = new File("config.properties");
        try {
            FileReader fr = new FileReader(config);
            p.load(fr);
        } catch (IOException iOException) {
        }

        String propBoton = p.getProperty("clrBotones");
        String propTextBoton = p.getProperty("clrTextBotones");

        if (rdCambiarColoresBotones.isSelected()) {
            p.setProperty("clrBotones", r + "," + g + "," + b);
            p.setProperty("clrTextBotones", propTextBoton);
        } else {
            p.put("clrBotones", propBoton);
            p.put("clrTextBotones", r + "," + g + "," + b);
        }

        try {
            File archivo = new File("config.properties");
            FileWriter fw = new FileWriter(archivo);
            p.store(fw, "configuracion Colores");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarCambiosBotonesActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        Properties prop = new Properties();

        prop.put("clrBotones", "212,208,200");
        prop.put("clrTextBotones", "0,0,0");

        try {
            File archivo = new File("config.properties");
            FileWriter fw = new FileWriter(archivo);
            prop.store(fw, "configuracion Colores");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void msgTrasaccionExitosa() {
        String titulo = "Aviso";
        String msg = "Transaccion exitosa";
        int tipoMsg = JOptionPane.INFORMATION_MESSAGE;
        JOptionPane.showMessageDialog(null, msg, titulo, tipoMsg);

    }

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

    private void msgErrorNoSeSeleccionoVivienda() {
        String titulo = "Error";
        String msg = "Debe seleccionar la fila de la vivienda que quiere vender o arrendar";
        int tipoMsg = JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(null, msg, titulo, tipoMsg);

    }

    private void realizarBackup() throws SQLException {
        File dir = new File(new File(".").getAbsolutePath(), "\\backup");
        dir.mkdirs();
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
            new HiloLector(proceso.getErrorStream()).start();

            InputStream inputStream = proceso.getInputStream();
            FileOutputStream fOutStream = new FileOutputStream("backup\\backup " + fecha + " " + hora + ".sql"); //el archivo se guarda en 
            //la raíz del proyecto carpeta backup;

            byte[] buff = new byte[1000];
            int readed = inputStream.read(buff);
            while (readed > 0) {

                fOutStream.write(buff, 0, readed);
                readed = inputStream.read(buff);
            }

            fOutStream.close();
            log = new Log();
            log.setDescripcion(sesion.getNombre() + " creo el punto de restauracion " + dir.getName());
            log.setUsuario(sesion.getRun());
            d.crearLog(log);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void restaurarBackup() throws SQLException {
        /* Abre jFileChoser en la ruta cafesoft/backup pidiendo archivos con extencion .sql */
        File dir = new File(new File(".").getAbsolutePath(), "\\backup");
        dir.mkdirs();
        fcBackup.setCurrentDirectory(dir);
        fcBackup.showOpenDialog(this);

        try {
            /* en este metodo se puede recuperar X base de datos guardada anteriormente. Sería bueno tener un registro de los nombres de las bases de datos respaldadas
            para poder recuperar solo su nombre. en ese caso, solo deberia cambiarse el nombre en 
            **FileInputStream fInStream = new FileInputStream("backup 1942018 17384.sql");**
            
            DETALLE IMPORTANTE
            El backup borra y reinserta los valores que quedaron guardados en la base de datos de respaldo,
            todo lo que esté despues de lo borrado no podrá ser recuperado.
             */
            dir = fcBackup.getSelectedFile();
            if (dir != null && dir.getName().contains(".sql")) {
                Process proceso = Runtime.getRuntime().exec("mysql -u root -p cafesoft"); //123456 contraseña por defecto, cambiar si hay otra
                OutputStream outputStream = proceso.getOutputStream();
                FileInputStream fInStream = new FileInputStream(dir);//("backup FECHA HORA.sql"); 
                //la fecha y hora son las que da el sistema EJ: backup 1942018 17384
                byte[] buff = new byte[1000];

                int readed = fInStream.read(buff);

                while (readed > 0) {

                    outputStream.write(buff, 0, readed);
                    readed = fInStream.read(buff);
                }
                outputStream.flush();
                outputStream.close();
                fInStream.close();
                log = new Log();
                log.setDescripcion(sesion.getNombre() + " ha restaurado desde " + dir.getName());
                log.setUsuario(sesion.getRun());
                d.crearLog(log);
            } else {
                JOptionPane.showMessageDialog(null, "No se selecciono un archivo de respaldo", "Error Al Restaurar", 0);
            }
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salir() throws SQLException {
        //if (0 == JOptionPane.showConfirmDialog(null, "Elija una opción", new Object[]{"opcion 1", "opcion 2", "opcion 3"}, 0)) {
        int op = JOptionPane.showOptionDialog(
                null,
                "¿Realmente desea salir de CafeSoft?",
                "Selecciona una opción",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono por defecto.
                new Object[]{"Aceptar", "Cerrar Sesión", "Cancelar"}, //
                null);
        if (op == 0) {
            log = new Log();
            log.setDescripcion(sesion.getNombre() + " ha salido de CafeSoft");
            log.setUsuario(sesion.getRun());
            d.crearLog(log);
            System.exit(0);
        } else if (op == 1) {
            log = new Log();
            log.setDescripcion(sesion.getNombre() + " ha salido de CafeSoft");
            log.setUsuario(sesion.getRun());
            d.crearLog(log);
            sesion = new Usuario();
            frmAdmin.setVisible(false);
            JFrameCrearCliente.setVisible(false);
            JfVendedor.setVisible(false);
            this.setVisible(true);
        }
    }

    public void confirmarRespaldo() throws SQLException {
        //if (0 == JOptionPane.showConfirmDialog(null, "Elija una opción", new Object[]{"opcion 1", "opcion 2", "opcion 3"}, 0)) {
        int op = JOptionPane.showOptionDialog(
                null,
                "¿Realmente desea crear un respaldo de CafeSoft?",
                "Crear respaldo de CafeSoft",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono por defecto.
                new Object[]{"Aceptar", "Cancelar"}, //
                "opcion 1");
        if (0 == op) {
            realizarBackup();
        }
    }

    public void confirmarRestaurar() throws SQLException {
        //if (0 == JOptionPane.showConfirmDialog(null, "Elija una opción", new Object[]{"opcion 1", "opcion 2", "opcion 3"}, 0)) {
        int op = JOptionPane.showOptionDialog(
                null,
                "¿Realmente desea restaurar CafeSoft?",
                "Restaurar CafeSoft",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono por defecto.
                new Object[]{"Aceptar", "Cancelar"}, //
                "opcion 1");
        if (0 == op) {
            restaurarBackup();
        }
    }

    public void cerrar() {
        //if (0 == JOptionPane.showConfirmDialog(null, "Elija una opción", new Object[]{"opcion 1", "opcion 2", "opcion 3"}, 0)) {

        if (0 == JOptionPane.showOptionDialog(
                null,
                "Seleccione opcion",
                "Cerrar CafeSoft",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono por defecto.
                new Object[]{"Si", "Cancelar"}, //
                "opcion 1")) {
            System.exit(0);
        }
    }

    private void getSimpleStats() {
        statSimple = new StatsSimple();
        try {
            statSimple = d.calcularStats();
            txtCasas.setText("" + statSimple.getCasas());
            txtDepa.setText("" + statSimple.getDepartamentos());
            txtTodos.setText("" + statSimple.getTodas());
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getFechaStats() {
        statSimple = new StatsSimple();
        try {
            statSimple = d.calcularStatsFechas(fInicio.format(jdDesde.getDate()), fFinal.format(jdHasta.getDate()));
            txtCasas.setText("" + statSimple.getCasas());
            txtDepa.setText("" + statSimple.getDepartamentos());
            txtTodos.setText("" + statSimple.getTodas());
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarViviendaStatFiltrada(String filtro) {
        viviendasStats = new ArrayList<>();
        try {
            viviendasStats = d.leerStatViviendasFiltrada(filtro);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelStatViviendas = new TModelNoDisponibles(viviendasStats);
        tbStatViviendas.setModel(modelStatViviendas);
    }

    private void cargarVendedoresStatFiltrada(String filtro) {
        vendedoresStats = new ArrayList<>();
        try {
            vendedoresStats = d.leerStatVendedores(filtro);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelStatVendedores = new TModelStatVendedores(vendedoresStats);
        tbStatVendedores.setModel(modelStatVendedores);
    }

    //clase hilo creada dentro de app, para modificar componetes
    private class HiloDePrueba extends Thread {

        @Override
        public void run() {

            while (true) {
                Properties p = new Properties();

                File config = new File("config.properties");

                try {
                    if (config.exists()) {
                        //si se encuentra, se leen los datos que contiene
                        FileReader fr = new FileReader(config);

                        p.load(fr);

                        String clrBotones, clrTextBotones;
                        clrBotones = p.getProperty("clrBotones");
                        clrTextBotones = p.getProperty("clrTextBotones");

                        String[] spltColorBoton = clrBotones.split(",");
                        String[] spltColorTextBoton = clrTextBotones.split(",");
                        fr.close();

                        int rB = Integer.parseInt(spltColorBoton[0]);
                        int gB = Integer.parseInt(spltColorBoton[1]);
                        int bB = Integer.parseInt(spltColorBoton[2]);

                        int rT = Integer.parseInt(spltColorTextBoton[0]);
                        int gT = Integer.parseInt(spltColorTextBoton[1]);
                        int bT = Integer.parseInt(spltColorTextBoton[2]);

                        Color cb = new Color(rB, gB, bB);
                        Color ctb = new Color(rT, gT, bT);

                        try {
                            //COLOR BOTONES
                            btnGuardarCambiosBotones.setBackground(cb);
                            btnCancelarColores.setBackground(cb);
                            btnFiltrarViviendasJfVendedor.setBackground(cb);
                            btnCrearCliente.setBackground(cb);
                            btnCancelarCreacionDeCliente.setBackground(cb);
                            btnArrendarOVender.setBackground(cb);

                            //COLOR TEXTO BOTONES
                            btnGuardarCambiosBotones.setForeground(ctb);
                            btnCancelarColores.setForeground(ctb);
                            btnFiltrarViviendasJfVendedor.setForeground(ctb);
                            btnCrearCliente.setForeground(ctb);
                            btnCancelarCreacionDeCliente.setForeground(ctb);
                            btnArrendarOVender.setForeground(ctb);

                        } catch (Exception e) {
                        }

                    } else {
                        //si no, se crea uno con datos por defecto
                        Properties prop = new Properties();

                        prop.put("clrBotones", "212,208,200");
                        prop.put("clrTextBotones", "0,0,0");

                        try {
                            File archivo = new File("config.properties");
                            FileWriter fw = new FileWriter(archivo);
                            prop.store(fw, "configuracion Colores");
                            fw.close();
                        } catch (IOException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                } catch (IOException e) {
                }
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void cargarTablaJFrameVendedor() throws SQLException {
        viviendasDisponibles = new ArrayList();
        viviendasDisponibles = d.leerTodasLasViviendasDisponibles("");
        modelViviendasDisponibles = new TModelViviendasDisponibles(viviendasDisponibles);
        tblDatosFrameVend.setModel(modelViviendasDisponibles);
        tblDatosFrameVend.setGridColor(Color.DARK_GRAY);

    }

    private void filtrarTablaJFrameVendedor(String filtro) throws SQLException {
        viviendasDisponibles = new ArrayList();
        viviendasDisponibles = d.leerTodasLasViviendasDisponibles(filtro);
        modelViviendasDisponibles = new TModelViviendasDisponibles(viviendasDisponibles);
        tblDatosFrameVend.setModel(modelViviendasDisponibles);
        tblDatosFrameVend.setGridColor(Color.DARK_GRAY);

    }

    private void llenarCbosFrameVendedor() {
        cboFiltrarPorEstado.removeAllItems();

        cboFiltrarPorEstado.addItem("Nuevas");
        cboFiltrarPorEstado.addItem("Usadas");
        cboFiltrarPorEstado.addItem("Ambas");
        cboFiltrarPorEstado.setSelectedIndex(0);
    }

    private void cargarCboViviendaAdmin() {
        cboTipo.removeAllItems();
        cboDisp.removeAllItems();
        cboCondicion.removeAllItems();

        cboTipo.addItem("Casa");
        cboTipo.addItem("Departamento");

        cboDisp.addItem("Arrendada");
        cboDisp.addItem("Vendida");
        cboDisp.addItem("Disponible");

        cboCondicion.addItem("Nueva");
        cboCondicion.addItem("Usada");
    }

    private void cargarCboViviendaStat() {
        cboContrato.removeAllItems();
        cboTipoVivienda.removeAllItems();
        cboContrato.addItem("Todas");
        cboContrato.addItem("Arrendada");
        cboContrato.addItem("Vendida");
        cboContrato.setSelectedIndex(0);
        cboTipoVivienda.addItem("Todas");
        cboTipoVivienda.addItem("Casa");
        cboTipoVivienda.addItem("Departamentos");
        cboTipoVivienda.setSelectedIndex(0);

    }

    private void cargarCboVendedorStat() {
        cboOrden.removeAllItems();
        cboOrden.addItem("Contratos");
        cboOrden.addItem("Log-in");
        cboOrden.setSelectedIndex(0);
    }

    private void inicializarSeleccionDeFiltroVendedor() {
        cboFiltrarPorEstado.setEnabled(true);
        rbtFiltrarDesc.setSelected(true);
        rbtDeVenta.setSelected(true);

    }

    private void cargarTblLog() throws SQLException {
        logs = d.leerVistaLogs();
        modelLog = new TModelLog(logs);
        tblLog.setModel(modelLog);
    }

    public void msgErrorRutNoEncontrado() {
        String titulo = "Error";
        String msg = "El rut ingresado no se encuentra registrado";
        int tipoMsg = JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(null, msg, titulo, tipoMsg);
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
                try {
                    new App().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame JFrameCrearCliente;
    private javax.swing.JFrame JfVendedor;
    private javax.swing.JButton btnArrendarOVender;
    private javax.swing.JButton btnBorrarVendedor;
    private javax.swing.JButton btnBorrarVivienda;
    private javax.swing.JButton btnBuscarVendedor;
    private javax.swing.JButton btnBuscarVivienda;
    private javax.swing.JButton btnCancelarColores;
    private javax.swing.JButton btnCancelarCreacionDeCliente;
    private javax.swing.JButton btnCrearCliente;
    private javax.swing.JButton btnCrearVendedor;
    private javax.swing.JButton btnCrearVivienda;
    private javax.swing.JButton btnEstDefault;
    private javax.swing.JButton btnFiltrarViviendasJfVendedor;
    private javax.swing.ButtonGroup btnGCambioColores;
    private javax.swing.ButtonGroup btnGFiltrarPrecio;
    private javax.swing.ButtonGroup btnGVentaORenta;
    private javax.swing.JButton btnGuardarCambiosBotones;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnLog;
    private javax.swing.JButton btnRespaldo;
    private javax.swing.JButton btnRestaurar;
    private javax.swing.JButton btnVerVendedores;
    private javax.swing.JButton btnVerViviendas;
    private javax.swing.JComboBox<String> cboCondicion;
    private javax.swing.JComboBox<String> cboContrato;
    private javax.swing.JComboBox<String> cboDisp;
    private javax.swing.JComboBox<String> cboFiltrarPorEstado;
    private javax.swing.JComboBox<String> cboOrden;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JComboBox<String> cboTipoVivienda;
    private javax.swing.JCheckBox chkFiltrarPorCasas;
    private javax.swing.JCheckBox chkFiltrarPorDepartamentos;
    private javax.swing.JColorChooser clrChoosser;
    private javax.swing.JFrame frmAdmin;
    private javax.swing.JFrame frmColores;
    private javax.swing.JFrame frmStatVendedores;
    private javax.swing.JFrame frmStatViviendas;
    private javax.swing.JMenuItem itemAdminSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JFrame jFSeleccionarClienteContrato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMArchivo;
    private javax.swing.JMenuItem jMArrendarVivienda;
    private javax.swing.JMenuBar jMBarra;
    private javax.swing.JMenuItem jMCerrarSesion;
    private javax.swing.JMenuItem jMCrearCli;
    private javax.swing.JMenuItem jMVCamAp;
    private javax.swing.JMenuItem jMVenderViv;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private com.toedter.calendar.JDateChooser jdDesde;
    private com.toedter.calendar.JDateChooser jdHasta;
    private com.toedter.calendar.JDateChooser jdInicio1;
    private com.toedter.calendar.JDateChooser jdInicio2;
    private com.toedter.calendar.JDateChooser jdTermino1;
    private com.toedter.calendar.JDateChooser jdTermino2;
    private javax.swing.JLabel lblListaClientes;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblOrden;
    private javax.swing.JLabel lblRUN;
    private javax.swing.JLabel lblRunCliente;
    private javax.swing.JLabel lblRunClienteContrato;
    private javax.swing.JLabel lblSueldoCliente;
    private javax.swing.JLabel lblTipoPrecio;
    private javax.swing.JLabel lblViviendasDisponibles;
    private javax.swing.JLabel lblicono;
    private javax.swing.JRadioButton rbtDeRenta;
    private javax.swing.JRadioButton rbtDeVenta;
    private javax.swing.JRadioButton rbtFiltrarAsc;
    private javax.swing.JRadioButton rbtFiltrarDesc;
    private javax.swing.JRadioButton rdCambiarColoresBotones;
    private javax.swing.JRadioButton rdCambiarColoresTextoBotones;
    private javax.swing.JSpinner spnArriendo;
    private javax.swing.JSpinner spnBanios;
    private javax.swing.JSpinner spnPiezas;
    private javax.swing.JSpinner spnVenta;
    private javax.swing.JTable tbStatVendedores;
    private javax.swing.JTable tbStatViviendas;
    private javax.swing.JTable tblDatosFrameVend;
    private javax.swing.JTable tblLog;
    private javax.swing.JTextArea txtAListaClientes;
    private javax.swing.JTextField txtCasas;
    private javax.swing.JTextField txtDepa;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNRol;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreVendedor;
    private javax.swing.JTextField txtRun;
    private javax.swing.JTextField txtRunCliContrato;
    private javax.swing.JTextField txtRunCliente;
    private javax.swing.JTextField txtRunVendedor;
    private javax.swing.JTextField txtSueldoCliente;
    private javax.swing.JTextField txtTodos;
    // End of variables declaration//GEN-END:variables
}
