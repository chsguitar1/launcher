/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lancador.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author cristiano
 */

public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer idclient;
  
    private String name;
   
    private String doc1;
   
    private String doc2;
   
    private Boolean type;
   
    private String key;
    
    private Date datavalid;
    
    private String version;

    public Client() {
    }

    public Client(Integer idclient) {
        this.idclient = idclient;
    }

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoc1() {
        return doc1;
    }

    public void setDoc1(String doc1) {
        this.doc1 = doc1;
    }

    public String getDoc2() {
        return doc2;
    }

    public void setDoc2(String doc2) {
        this.doc2 = doc2;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getDatavalid() {
        return datavalid;
    }

    public void setDatavalid(Date datavalid) {
        this.datavalid = datavalid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.idclient);
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.doc1);
        hash = 19 * hash + Objects.hashCode(this.doc2);
        hash = 19 * hash + Objects.hashCode(this.type);
        hash = 19 * hash + Objects.hashCode(this.key);
        hash = 19 * hash + Objects.hashCode(this.datavalid);
        hash = 19 * hash + Objects.hashCode(this.version);
        return hash;
    }

   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.doc1, other.doc1)) {
            return false;
        }
        if (!Objects.equals(this.doc2, other.doc2)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.idclient, other.idclient)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.datavalid, other.datavalid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "idclient=" + idclient + ", name=" + name + ", doc1=" + doc1 + ", doc2=" + doc2 + ", type=" + type + ", key=" + key + ", datavalid=" + datavalid + ", version=" + version + '}';
    }

    
    
}
