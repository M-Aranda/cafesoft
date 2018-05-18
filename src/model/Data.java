package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import clasesAusar.VistaVivienda;

public class Data {

    private final Conexion con;
    private String query;
    private ResultSet rs;
    private List<VistaVivienda> listaViviendas;

    public Data() throws ClassNotFoundException, SQLException {
        con = new Conexion("localhost", "cafesoft", "root", "");
    }

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

    public void crearLog(Log l) throws SQLException { // VER SI SE PUEDE HACER CON TRIGGERS
        query = "INSERT INTO log VALUES (NULL,'"
                + l.getDescripcion() + "',"
                + "'" + l.getFecha() + "',"
                + "'" + l.getUsuario() + "')";

        con.ejecutar(query);
    }

    public void crearTipoVivienda(TipoVivienda t) throws SQLException {
        query = "INSERT INTO tipo_vivienda VALUES (NULL,'"
                + t.getNombre() + "')";

        con.ejecutar(query);
    }

    public void crearUsuario(Usuario u) throws SQLException {
        query = "INSERT INTO usuario VALUES ('" + u.getRun()
                + "','" + u.getNombre()
                + "','" + u.getTipo() + "')";

        con.ejecutar(query);
    }

    public void crearVenta(Venta v) throws SQLException {
        query = "INSERT INTO tipo_usuario VALUES (NULL,'"
                + v.getCliente() + "','"
                + v.getUsuario() + "','"
                + v.getnRol() + "','"
                + v.getFecha() + "')";

        con.ejecutar(query);
    }

    public void crearVivienda(Vivienda v) throws SQLException {
        query = "INSERT INTO tipo_usuario VALUES ('" + v.getnRol() + ",'"
                + v.getNombre() + "','"
                + v.getTipo() + "','"
                + v.getPrecioArriendo() + "','"
                + v.getPrecioVenta() + "','"
                + v.getCantBaños() + "','"
                + v.getCantPiezas() + "','"
                + v.getDireccion() + "','"
                + v.isUsada() + "')";
        con.ejecutar(query);
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
            v.setNombre(rs.getString(2));
            v.setTipo(rs.getInt(3));
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

    public int existeUsuario(String run) throws SQLException{
        query = "select count(*) from usuario where run = '"+run+"';";
        rs = con.ejecutarSelect(query);
        int res = 0;
        while (rs.next()) {
            res = rs.getInt(1);
        }
        return res;
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
        query = "UPDATE cliente SET nombre ='" + v.getNombre()
                + "',tipo ='" + v.getTipo()
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
        query = "DELETE cliente WHERE run ='" + u.getRun() + "'";

        con.ejecutar(query);
    }

    public void borrarVenta(Venta v) throws SQLException {
        query = "DELETE cliente WHERE id ='" + v.getId() + "'";

        con.ejecutar(query);
    }

    public void borrarVivienda(Vivienda v) throws SQLException {
        query = "DELETE cliente WHERE n_rol ='" + v.getnRol() + "'";

        con.ejecutar(query);
    }
    // -------------------------------------------------------------------------BORRAR-DELETE
}
