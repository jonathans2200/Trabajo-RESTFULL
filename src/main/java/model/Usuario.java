/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author jonat
 */
@Entity
public class Usuario implements Serializable {

    @Id
    private String usuario;
    private String clave;
    private String rol;
    @OneToOne
    private Persona propietario;
    private String estadoCuenta;
    @Transient
    private boolean editable;

    public Usuario() {
    }

    public Usuario(String usuario, String clave, Persona propietario, String rol, String estadoCuenta) {
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
        this.estadoCuenta = estadoCuenta;
        this.propietario = propietario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", clave=" + clave + ", rol=" + rol + ", propietario=" + propietario + ", estadoCuenta=" + estadoCuenta + ", editable=" + editable + '}';
    }

}
