/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lancador;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristiano
 */
public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        try {
            ResultSet rs = Database.executaQuery("select * from client");
            rs.next();
            
            HttpResponse<Autorization> httpResponse = Unirest.get("http://localhost/autorize/" + 
                    rs.getString("key") + "/"+cripografar(rs.getString("doc1")+";"+rs.getString("doc2"), "SHA1")).
                    asObject(Autorization.class);
            Autorization autorization = httpResponse.getBody();
        } catch (UnirestException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String cripografar(String input, String tipoAlgoritmo) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance(tipoAlgoritmo);
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
