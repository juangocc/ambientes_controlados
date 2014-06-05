package com.chat.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juangocc
 */
public class clsConn {

    ResultSet res = null;
    private Connection con = null;
    Statement st = null;
    String driver = "org.postgresql.Driver";
    String url = "jdbc:postgresql://127.0.0.1:5432/db";
    String userDB = "postgres";
    String passDB = "LosAlcazarES15";

    public clsConn() {
        conectar();
    }

    public int conectar() {
        try {
            Class.forName(driver);
            System.out.println("Cargado el driver :-) ");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargal el driver :-( " + driver);
            return -1;
        }
        try {
            con = DriverManager.getConnection(url, userDB, passDB);
            System.out.println("Conectado con éxito :-) !!! ");
        } catch (SQLException e) {
            System.out.println("No se pudo realizar la conexion :-(");
            return -2;
        }
        return 1;
    }

    public String buscarS(String sql) {
        try {
            ResultSet r = null;
            st = getCon().createStatement();
            r = st.executeQuery(sql);
            r.next();
            return r.getString(1);
        } catch (SQLException e) {
            return "-1";
        }
    }

    public String buscarC(String sql, int c) {
        try {
            ResultSet r = null;
            st = getCon().createStatement();
            r = st.executeQuery(sql);
            if (r.next());
            return r.getString(c);
        } catch (SQLException e) {
            return "-1";
        }
    }

    public SQLException insertar(String sql) {
        try {
            st = getCon().createStatement();
            st.execute(sql);
            st.close();
            return null;
        } catch (SQLException err) {
            System.out.println("Error en Insertar !!!"+err);
            return err;
        }
    }

    public ResultSet consultar(String sql) {
        try {
            st = getCon().createStatement();
            res = st.executeQuery(sql);
        } catch (SQLException err) {
            System.out.println("Error en Consultar !!! "+err);
            return null;
        }
        return res;
    }

    public int verificar(String sql) {
        try {
            st = getCon().createStatement();
            res = st.executeQuery(sql);
            if (res.next()) {
                return 1;
            }
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int actualizar(String sql) {
        try {
            st = getCon().createStatement();
            st.executeUpdate(sql);
            st.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public void cerrar() {
        try {
            getCon().close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String getJSON(String sql, int columnas) {
        String jsonResult = "";
        int col = columnas;
        try {
            JSon.JQGridJSONModel json = new JSon.JQGridJSONModel();
            java.util.List<JSon.JQGridRow> rows = new java.util.ArrayList<JSon.JQGridRow>();
            JSon.JQGridRow row = new JSon.JQGridRow();
            java.util.List<String> cells = new java.util.ArrayList<String>();
            st = getCon().createStatement();
            res = st.executeQuery(sql);
            int y = 1;
            while (res.next()) {
                cells = new java.util.ArrayList<String>();
                row = new JSon.JQGridRow();
//row.setId(Integer.parseInt(res.getString(0)));
                row.setId(y);
                col = 1;
                 ResultSetMetaData rsMetaData = res.getMetaData();

    int numberOfColumns = rsMetaData.getColumnCount();
                while (col <= columnas) {
                    cells.add(res.getString(col));
                    col++;
                }
                row.setCell(cells);
                rows.add(row);
                y++;
            }
            json.setPage("1");
            json.setRecords(y);
            json.setTotal("+" + (y <= 10 ? 1 : 2) + "");
            json.setRows(rows);
            flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();
            jsonResult = serializer.exclude("*.class").deepSerialize(json);
        } catch (SQLException ex) {
            Logger.getLogger(clsConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonResult;
    }

    public String getJSONCols(String sql, String cuales) {
        String jsonResult = "";
        java.util.StringTokenizer cols;
        try {
            JSon.JQGridJSONModel json = new JSon.JQGridJSONModel();
            java.util.List<JSon.JQGridRow> rows = new java.util.ArrayList<JSon.JQGridRow>();
            JSon.JQGridRow row = new JSon.JQGridRow();
            java.util.List<String> cells = new java.util.ArrayList<String>();
            st = getCon().createStatement();
            res = st.executeQuery(sql);
            int y = 1;
            while (res.next()) {
                cells = new java.util.ArrayList<String>();
                row = new JSon.JQGridRow();
//row.setId(Integer.parseInt(res.getString(0)));
                row.setId(y);
                cols = new java.util.StringTokenizer(cuales, ";");
                while (cols.hasMoreTokens()) {
                    cells.add(res.getString(Integer.parseInt(cols.nextToken())));
                }
                row.setCell(cells);
                rows.add(row);
                y++;
            }
            json.setPage("1");
            json.setRecords(y);
            json.setTotal("+" + (y <= 10 ? 1 : 2) + "");
            json.setRows(rows);
            flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();
            jsonResult = serializer.exclude("*.class").deepSerialize(json);
        } catch (SQLException ex) {
            Logger.getLogger(clsConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonResult;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }
}
