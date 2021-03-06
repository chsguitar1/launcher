/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lancador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Vinicius
 */
public class Database {

    public static Statement stmt;
    private static final String DRIVER_NAME = "org.postgresql.Driver";

    static {
        try {
            Class.forName(DRIVER_NAME).newInstance();
            System.out.println("*** Driver loaded");
        } catch (Exception e) {
            System.out.println("*** Error : " + e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
        }

    }

    private static String INSTRUCTIONS = new String();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/license", "postgres", "Pkg1522pam");
    }

    public static ResultSet executaQuery(String sql) {
        // JOptionPane.showMessageDialog(null, sql);
        ResultSet rs = null;
        
        try {
            stmt = getConnection().createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("Nao foi possível efetuar consulta no banco de dados"
                    + "\n" + sql);
        }
        return rs;
    }

    public static void executeScript(String url, String user, String password, String caminhoSqlUpdate) throws SQLException {
        String s = new String();
        StringBuffer sb = new StringBuffer();

        try {
            FileReader fr = new FileReader(new File(caminhoSqlUpdate));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character  

            BufferedReader br = new BufferedReader(fr);

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();

            // here is our splitter ! We use ";" as a delimiter for each request  
            // then we are sure to have well formed statements  
            String[] inst = sb.toString().split(";");

            Connection c = getConnection();

            Statement st = c.createStatement();

            for (int i = 0; i < inst.length; i++) {
                // we ensure that there is no spaces before or after the request string  
                // in order to not execute empty statements  
                if (!inst[i].trim().equals("")) {
                    if (!st.execute(inst[i])) {
                        //st.cancel();
                    }
                    System.out.println(">>" + inst[i]);
                }
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("*** Error : " + e.toString());
//            System.out.println("*** ");
//            System.out.println("*** Error : ");
//            e.printStackTrace();
//            System.out.println("################################################");
//            System.out.println(sb.toString());
        }
    }
}
