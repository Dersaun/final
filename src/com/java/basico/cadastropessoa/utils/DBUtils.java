package com.java.basico.cadastropessoa.utils;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class DBUtils {

    /**
     * Connect to database
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        //Informar a classe conector
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        String stringCon = "jdbc:mysql://127.0.0.1/treinaweb_jse?useTimezone=true&serverTimezone=UTC&user=root&password=";

        //Classe conexao
        Connection conn = DriverManager.getConnection(stringCon);

        return conn;
    }

    /**
     * Get query data from database
     * @param conn
     * @param sql
     * @return
     * @throws SQLException
     */
    public static ResultSet getResultSet(Connection conn, String sql) throws SQLException {
        Statement statment = conn.createStatement();
        return statment.executeQuery(sql);
    }

    /**
     * Run and get other statments in database
     * @param conn
     * @param sql
     * @return
     * @throws SQLException
     */
    public static PreparedStatement getPreparedStatment(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
}
