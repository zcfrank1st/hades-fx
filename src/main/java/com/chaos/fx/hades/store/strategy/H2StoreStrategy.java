package com.chaos.fx.hades.store.strategy;

import com.chaos.fx.hades.model.InfoMeta;

import java.sql.*;

/**
 * Created by zcfrank1st on 29/12/2016.
 */
public class H2StoreStrategy implements StoreStrategy {
    private static final String JDBC_URL = "jdbc:h2:" + System.getProperty("user.dir") + "/meta";

    public H2StoreStrategy () throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, "sa", "")) {
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet rs = databaseMetaData.getTables(null, null, "META", null);

            if (!rs.next()) {
                conn.createStatement().execute("CREATE TABLE META ( user_name VARCHAR(512), private_key VARCHAR (512), project_name VARCHAR (512), project_path VARCHAR (512));");
            }
        }
    }

    @Override
    public InfoMeta readStore() throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, "sa", "")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM META limit 1;");
            if (rs.next()) {
                InfoMeta meta = new InfoMeta(rs.getString("user_name"),
                        rs.getString("private_key"),
                        rs.getString("project_name"),
                        rs.getString("project_path"));
                return meta;
            }
            return null;
        }
    }

    @Override
    public void writeStore(String userName, String privateKey, String projectName, String projectPath) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, "sa", "")) {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO META VALUES ('" +
                    userName + "','" +
                    privateKey + "','" +
                    projectName + "','" +
                    projectPath + "');"
            );
        }
    }
}
