package model;

import clasesAusar.VistaLog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import clasesAusar.VistaVivienda;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class Data {

    private final Conexion con;
    private String query;
    private String querySegunda;
    private ResultSet rs;
    private Connection cone = null;

    public Data() throws ClassNotFoundException, SQLException {
        con = new Conexion("localhost", "cafesoft", "root", "");
    }

    public void usarConexionAlternativa() throws SQLException {
        cone = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/cafesoft", "root", "");
    }

    public void cerrarConexionAlternativa() throws SQLException {
        cone.close();
    }
    //--------------------------------------------------------------------------OPERACIONES
    public StatsSimple calcularStats() throws SQLException {
        query = "SELECT * FROM estat_default;";
        rs = con.ejecutarSelect(query);
        
        StatsSimple s = new StatsSimple(0, 0, 0);
                
        while (rs.next()) {
            s.setCasas(rs.getInt(1));
            s.setDepartamentos(rs.getInt(2));
            s.setTodas(rs.getInt(3));
        }
        return s;
    }
    
    public StatsSimple calcularStatsFechas(String fecha1, String fecha2) throws SQLException {
        query = "CALL estat_por_fechas('"+fecha1+"','"+fecha2+"')";
        rs = con.ejecutarSelect(query);
        
        StatsSimple s = new StatsSimple(0, 0, 0);
                
        while (rs.next()) {
            s.setCasas(rs.getInt(1));
            s.setDepartamentos(rs.getInt(2));
            s.setTodas(rs.getInt(3));
        }
        return s;
    }
    //--------------------------------------------------------------------------OPERACIONES

    //--------------------------------------------------------------------------BUSCAR-SEARCH
    public int existeUsuario(String run) throws SQLException {
        query = "select count(*) from usuario where run = '" + run + "';";
        rs = con.ejecutarSelect(query);
        int res = 0;
        while (rs.next()) {
            res = rs.getInt(1);
        }
        return res;
    }

    public Usuario buscarUsuario(String run) throws SQLException {
        query = "select * from usuario where run = '" + run + "';";
        rs = con.ejecutarSelect(query);
        Usuario u = null;
        while (rs.next()) {
            u = new Usuario();
            u.setRun(rs.getString(1));
            u.setNombre(rs.getString(2));
            u.setTipo(rs.getInt(3));
        }
        return u;
    }

    //--------------------------------------------------------------------------BUSCAR-SEARCH
    // -------------------------------------------------------------------------CREAR-CREATE
    public void crearTipoUsuario(TipoUsuario t) throws SQLException {
        query = "INSERT INTO tipo_usuario VALUES (NULL,'"
                + t.getNombre() + "')";

        con.ejecutar(query);
    }

    public void crearCliente(Cliente c) throws SQLException {
        query = "INSERT INTO cliente VALUES ('" + c.getRun() + "','"
                + c.getNombre() + "','"
                + c.getSueldo() + "')";

        con.ejecutar(query);
    }

    public int usarFuncionCrear_cliente(Cliente c, String usuarioActual) throws SQLException {

        CallableStatement llamarFuncionCrearCliente = (CallableStatement) cone.prepareCall("SELECT crear_cliente(?,?,?,?)");
        llamarFuncionCrearCliente.setString(1, "" + c.getRun() + "");
        llamarFuncionCrearCliente.setString(2, "" + c.getNombre() + "");
        llamarFuncionCrearCliente.setInt(3, c.getSueldo());
        llamarFuncionCrearCliente.setString(4, usuarioActual);
        System.out.println(llamarFuncionCrearCliente);
        llamarFuncionCrearCliente.execute();
        int coincidenciasDeRut = 2;

        ResultSet res = llamarFuncionCrearCliente.getResultSet();

        while (res.next()) {
            coincidenciasDeRut = res.getInt(1);
        }
        res.close();


        return coincidenciasDeRut;

    }

    public void crearLog(Log l) throws SQLException { // VER SI SE PUEDE HACER CON TRIGGERS
        query = "CALL nuevo_log('" + l.getDescripcion() + "','" + l.getUsuario() + "')";
        con.ejecutar(query);
    }

    public void crearTipoVivienda(TipoVivienda t) throws SQLException {
        query = "INSERT INTO tipo_vivienda VALUES (NULL,'"
                + t.getNombre() + "')";

        con.ejecutar(query);
    }

    public void crearUsuario(Usuario u, String user) throws SQLException {
        query = "SELECT crear_vendedor('" + u.getRun() + "','" + u.getNombre() + "','" + user + "');";

        con.ejecutarSelect(query);
        con.close();
    }

    public void crearVenta(Venta v) throws SQLException {
        query = "INSERT INTO tipo_usuario VALUES (NULL,'"
                + v.getCliente() + "','"
                + v.getUsuario() + "','"
                + v.getnRol() + "','"
                + v.getFecha() + "')";

        con.ejecutar(query);
    }

    public void crearVivienda(Vivienda v, String user) throws SQLException {
        query = "SELECT crear_vivienda (" + v.getnRol() + ","
                + v.getTipo() + ","
                + v.getDispVivienda() + ","
                + v.getPrecioArriendo() + ","
                + v.getPrecioVenta() + ","
                + v.getCantBaños() + ","
                + v.getCantPiezas() + ",'"
                + v.getDireccion() + "',"
                + v.isUsada() + ",'"
                + user + "');";
        con.ejecutarSelect(query);
        con.close();
    }

    // -------------------------------------------------------------------------CREAR-CREATE
    // -------------------------------------------------------------------------LEER-READ
    public List<Vivienda> leerViviendas() throws SQLException {
        query = "SELECT * FROM vivienda;";

        List<Vivienda> viviendas = new ArrayList<>();

        rs = con.ejecutarSelect(query);

        while (rs.next()) {
            Vivienda v = new Vivienda();

            v.setnRol(rs.getInt(1));
            v.setTipo(rs.getInt(2));
            v.setDispVivienda(rs.getInt(3));
            v.setPrecioArriendo(rs.getInt(4));
            v.setPrecioVenta(rs.getInt(5));
            v.setCantBaños(rs.getInt(6));
            v.setCantPiezas(rs.getInt(7));
            v.setDireccion(rs.getString(8));
            v.setUsada(rs.getBoolean(9));

            viviendas.add(v);
        }

        con.close();

        return viviendas;
    }

    public Vivienda buscarVivienda(int nRol) throws SQLException {
        query = "SELECT * FROM vivienda WHERE n_rol = " + String.valueOf(nRol) + ";";
        rs = con.ejecutarSelect(query);
        Vivienda v = null;

        while (rs.next()) {
            v = new Vivienda();
            v.setnRol(rs.getInt(1));
            v.setTipo(rs.getInt(2));
            v.setDispVivienda(rs.getInt(3));
            v.setPrecioArriendo(rs.getInt(4));
            v.setPrecioVenta(rs.getInt(5));
            v.setCantBaños(rs.getInt(6));
            v.setCantPiezas(rs.getInt(7));
            v.setDireccion(rs.getString(8));
            v.setUsada(rs.getBoolean(9));
        }

        return v;
    }

    public List<VistaVivienda> leerTodasLasViviendasDisponibles() throws SQLException {
        query = "SELECT * FROM vista_viviendas_disponibles";

        List<VistaVivienda> viviendas = new ArrayList<>();

        rs = con.ejecutarSelect(query);

        while (rs.next()) {
            VistaVivienda v = new VistaVivienda();

            v.setnDeRol(rs.getInt(1));
            v.setTipo(rs.getString(2));
            v.setDisponibilidad(rs.getString(3));
            v.setPrecioDeArriendo(rs.getInt(4));
            v.setPrecioDeVenta(rs.getInt(5));
            v.setCantBanios(rs.getInt(6));
            v.setCantPiezas(rs.getInt(7));
            v.setDireccion(rs.getString(8));
            v.setCondicion(rs.getString(9));

            viviendas.add(v);
        }

        con.close();

        return viviendas;
    }

    public List<VistaLog> leerVistaLogs() throws SQLException {
        query = "SELECT * FROM vista_logs ORDER BY vista_logs.fecha DESC";

        List<VistaLog> logs = new ArrayList<>();

        rs = con.ejecutarSelect(query);

        while (rs.next()) {
            VistaLog v = new VistaLog();

            v.setFecha(rs.getString(1));
            v.setDescripcion(rs.getString(2));
            v.setRun(rs.getString(3));
            v.setNombre(rs.getString(4));

            logs.add(v);
        }

        con.close();

        return logs;
    }

    public List<Venta> leerVentas() throws SQLException {
        query = "SELECT * FROM venta";

        List<Venta> ventas = new ArrayList<>();

        rs = con.ejecutarSelect(query);

        while (rs.next()) {
            Venta v = new Venta();

            v.setnRol(rs.getInt(1));
            v.setCliente(rs.getString(2));
            v.setUsuario(rs.getString(3));
            v.setnRol(rs.getInt(4));
            v.setFecha(rs.getString(5));

            ventas.add(v);
        }

        con.close();

        return ventas;
    }

    public List<Log> leerLog() throws SQLException {
        query = "SELECT * FROM log";

        List<Log> logs = new ArrayList<>();

        rs = con.ejecutarSelect(query);

        while (rs.next()) {
            Log l = new Log();

            l.setId(rs.getInt(1));
            l.setDescripcion(rs.getString(2));
            l.setFecha(rs.getString(3));
            l.setUsuario(rs.getString(4));

            logs.add(l);
        }

        con.close();

        return logs;
    }

    public List<Usuario> leerUsuario() throws SQLException {
        query = "SELECT * FROM usuario";

        List<Usuario> usuarios = new ArrayList<>();

        rs = con.ejecutarSelect(query);

        while (rs.next()) {
            Usuario u = new Usuario();

            u.setRun(rs.getString(1));
            u.setNombre(rs.getString(2));
            u.setTipo(rs.getInt(3));

            usuarios.add(u);
        }

        con.close();

        return usuarios;
    }

    public List<Cliente> leerClientes() throws SQLException {
        query = "SELECT * FROM cliente";

        List<Cliente> clientes = new ArrayList<>();

        rs = con.ejecutarSelect(query);

        while (rs.next()) {
            Cliente c = new Cliente();

            c.setRun(rs.getString(1));
            c.setNombre(rs.getString(2));
            c.setSueldo(rs.getInt(3));

            clientes.add(c);
        }

        con.close();

        return clientes;
    }

    public List<TipoUsuario> leerTipoUsuarios() throws SQLException {
        query = "SELECT * FROM tipo_usuario";

        List<TipoUsuario> tipos = new ArrayList<>();

        rs = con.ejecutarSelect(query);

        while (rs.next()) {
            TipoUsuario t = new TipoUsuario();

            t.setId(rs.getInt(1));
            t.setNombre(rs.getString(2));

            tipos.add(t);
        }

        con.close();

        return tipos;
    }

    public List<TipoVivienda> leerTipoVivienda() throws SQLException {
        query = "SELECT * FROM tipo_vivienda";

        List<TipoVivienda> tipos = new ArrayList<>();

        rs = con.ejecutarSelect(query);

        while (rs.next()) {
            TipoVivienda t = new TipoVivienda();

            t.setId(rs.getInt(1));
            t.setNombre(rs.getString(2));

            tipos.add(t);
        }

        con.close();

        return tipos;
    }

    // -------------------------------------------------------------------------LEER-READ
    // -------------------------------------------------------------------------ACTUALIZAR-UPDATE
    public void actualizarTipoUsuario(TipoUsuario t) throws SQLException {
        query = "UPDATE tipo_usuario SET nombre ='" + t.getNombre()
                + "' WHERE id ='" + t.getId() + "'";

        con.ejecutar(query);
    }

    public void actualizarCliente(Cliente c) throws SQLException {
        query = "UPDATE cliente SET nombre ='" + c.getNombre() + "','"
                + "sueldo ='" + c.getSueldo()
                + "' WHERE run ='" + c.getRun() + "'";

        con.ejecutar(query);
    }

    public void actualizarLog(Log l) throws SQLException {
        query = "UPDATE log SET descripcion ='" + l.getDescripcion() + "',"
                + "fecha ='" + l.getFecha() + "',"
                + "id_usuario ='" + l.getUsuario()
                + "' WHERE id ='" + l.getId() + "'";

        con.ejecutar(query);
    }

    public void actualizarTipoVivienda(TipoVivienda t) throws SQLException {
        query = "UPDATE cliente SET nombre ='" + t.getNombre()
                + "' WHERE id ='" + t.getId() + "'";

        con.ejecutar(query);
    }

    public void actualizarUsuario(Usuario u) throws SQLException {
        query = "UPDATE cliente SET nombre ='" + u.getNombre()
                + "',tipo = '" + u.getTipo()
                + "' WHERE run ='" + u.getRun() + "'";

        con.ejecutar(query);
    }

    public void actualizarVenta(Venta v) throws SQLException {
        query = "UPDATE cliente SET cliente_fk ='" + v.getCliente()
                + "',usuario_fk ='" + v.getUsuario()
                + "',vivienda_fk='" + v.getnRol()
                + "',fecha ='" + v.getFecha()
                + "' WHERE id ='" + v.getId() + "'";

        con.ejecutar(query);
    }

    public void actualizarVivienda(Vivienda v) throws SQLException {
        query = "UPDATE vivienda SET n_rol ='" + v.getnRol()
                + "',tipo ='" + v.getTipo()
                + "',dis_vivienda ='" + v.getDispVivienda()
                + "',precio_arriendo ='" + v.getPrecioArriendo()
                + "',precio_venta ='" + v.getPrecioVenta()
                + "',cant_banios ='" + v.getCantBaños()
                + "',cant_piezas ='" + v.getCantPiezas()
                + "',direccion = '" + v.getDireccion()
                + "',usada ='" + v.isUsada()
                + "' WHERE n_rol ='" + v.getnRol() + "'";
        con.ejecutar(query);
    }

    // -------------------------------------------------------------------------ACTUALIZAR-UPDATE
    // -------------------------------------------------------------------------BORRAR-DELETE
    public void borrarCliente(Cliente c) throws SQLException {
        query = "DELETE cliente WHERE run ='" + c.getRun() + "'";

        con.ejecutar(query);
    }

    public void borrarLog(Log l) throws SQLException {
        query = "DELETE log WHERE id ='" + l.getId() + "'";

        con.ejecutar(query);
    }

    public void borrarTipoUsuario(TipoUsuario t) throws SQLException {
        query = "DELETE tipo_usuario WHERE id ='" + t.getId() + "'";

        con.ejecutar(query);
    }

    public void borrarTipoVivienda(TipoVivienda t) throws SQLException {
        query = "DELETE tipo_vivienda WHERE id ='" + t.getId() + "'";

        con.ejecutar(query);
    }

    public void borrarUsuario(Usuario u) throws SQLException {
        query = "CALL borrarUsuario('"+u.getRun()+"');";

        con.ejecutar(query);
        con.close();
    }

    public void borrarVenta(Venta v) throws SQLException {
        query = "DELETE cliente WHERE id ='" + v.getId() + "'";

        con.ejecutar(query);
    }

    public void borrarVivienda(Vivienda v) throws SQLException {
        query = "DELETE FROM vivienda WHERE n_rol ='" + v.getnRol() + "'";

        con.ejecutar(query);
    }
    // -------------------------------------------------------------------------BORRAR-DELETE

    //--------------------------------------------------------------------------Consultas filtradas
    //estaba pesando en hacer un puro metodo que tomara valores de lo seleccionado en el filtrado
    //pero tambien capaz de seleccionar el tipo de query en cierto casos, como cuando necesita
    //ver ambos tipos de vivienda/condicion. Tome de referencia los metodos que ya estaban, y se me ocurre
    //algo con la direccion del siguiente metodo
    public List<VistaVivienda> leerTodasLasViviendasSegunSeleccion(String tipo1, String tipo2, String condicion1, String condicion2, String tipoPrecio, String orden, boolean consultaCompleja) throws SQLException {
        if (!consultaCompleja) {
            query = " SELECT * FROM vista_viviendas_disponibles  WHERE (tipo='" + tipo1 + "' OR tipo='" + tipo2 + "') AND (condicion='" + condicion1 + "' OR condicion='" + condicion2 + "')ORDER BY " + tipoPrecio + " " + orden + "";
        } else if (consultaCompleja) {
            query = condicion1;
            querySegunda = condicion2;
            System.out.println(querySegunda);
        }

        List<VistaVivienda> viviendas = new ArrayList<>();

        rs = con.ejecutarSelect(query);

        while (rs.next()) {
            VistaVivienda v = new VistaVivienda();

            v.setnDeRol(rs.getInt(1));
            v.setTipo(rs.getString(2));
            v.setDisponibilidad(rs.getString(3));
            v.setPrecioDeArriendo(rs.getInt(4));
            v.setPrecioDeVenta(rs.getInt(5));
            v.setCantBanios(rs.getInt(6));
            v.setCantPiezas(rs.getInt(7));
            v.setDireccion(rs.getString(8));
            v.setCondicion(rs.getString(9));

            viviendas.add(v);
        }
        

        if (consultaCompleja) {
            rs = con.ejecutarSelect(querySegunda);

            while (rs.next()) {
                VistaVivienda v = new VistaVivienda();

                v.setnDeRol(rs.getInt(1));
                v.setTipo(rs.getString(2));
                v.setDisponibilidad(rs.getString(3));
                v.setPrecioDeArriendo(rs.getInt(4));
                v.setPrecioDeVenta(rs.getInt(5));
                v.setCantBanios(rs.getInt(6));
                v.setCantPiezas(rs.getInt(7));
                v.setDireccion(rs.getString(8));
                v.setCondicion(rs.getString(9));

                viviendas.add(v);
                
            }
            
            
            
            con.close();

        }

        con.close();
  

        return viviendas;
    }

}
