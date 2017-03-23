/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lancador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mycompany.lancador.model.Client;
import java.io.IOException;
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

    public static void main(String[] args) {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        try {

            ResultSet rs = Database.executaQuery("select * from client");
            rs.next();
            String criptoKey = "";

           
            String urlCripto = "http://localhost:8089/Wslocal/rest/client/cripto/" + rs.getString("doc1") + rs.getString("doc2");
            HttpResponse<String> responseCripto = Unirest.get(urlCripto).
                    asObject(String.class);
            criptoKey = responseCripto.getBody();
            System.out.println("criptoKey"+criptoKey);

           String urlValit = "http://localhost:8089/Wslocal/rest/client/valit/" + rs.getString("key") + "/" + criptoKey;
            System.out.println("url = " + urlValit);

            HttpResponse<Client> httpResponse = Unirest.get(urlValit).
                    asObject(Client.class);
            Client autorization = httpResponse.getBody();
            System.out.println("cliente" + autorization.toString());

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
