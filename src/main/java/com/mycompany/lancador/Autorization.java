/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lancador;

/**
 *
 * @author cristiano
 */
public class Autorization {
    String key;
    String hash;
    String msg;
    String version;
    String validation;

    public Autorization(String key, String hash, String msg, String version, String validation) {
        this.key = key;
        this.hash = hash;
        this.msg = msg;
        this.version = version;
        this.validation = validation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    @Override
    public String toString() {
        return "Autorization{" + "key=" + key + ", hash=" + hash + ", msg=" + msg + ", version=" + version + ", validation=" + validation + '}';
    }
    
}
