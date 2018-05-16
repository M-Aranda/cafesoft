package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import clasesAusar.Vivienda;


public class Data {

    private final Conexion con;
    private String query;
    private ResultSet rs;
    private List<Vivienda> listaViviendas;
    

    public Data() throws ClassNotFoundException, SQLException {
        con = new Conexion("localhost", "cafesoft", "root", "");
    }

}
