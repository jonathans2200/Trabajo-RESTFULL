/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.CuentaFacade;
import ejb.PersonaFacade;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Persona;
import model.Usuario;

/**
 *
 * @author jonat
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class CuentaBean implements Serializable {

    @EJB
    private PersonaFacade ejbPersona;

    @EJB
    private CuentaFacade ejbCuenta;

    private Persona buscarPersona;
    private Usuario buscarUsuario;
    private String usuario;
    private String clave;
    private String cedula;
    private String codigoParametro;
    private Persona persona;

    public CuentaBean() {
    }

    @PostConstruct
    public void init() {
        persona = new Persona();

    }

    public String validar() {

        try {
            Persona p = ejbPersona.obtenerPersona(this.cedula);
            if (p != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", p);

                FacesContext contex = FacesContext.getCurrentInstance();

                contex.getExternalContext().redirect("RegistrarCuenta.xhtml?faces-redirect=true&cedula=" + p.getCedula());
            } else {

            }
        } catch (Exception ex) {
            System.out.println("NO SE ENCUENTRA REGISTRADO EN EL SISTEMA");
        }
        return null;

    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setCodigoParametro(String codigoParametro) {
        this.codigoParametro = codigoParametro;
        if (codigoParametro != null) {
            try {
                buscarPersona = ejbPersona.obtenerPersona(codigoParametro);
                buscarUsuario = ejbCuenta.obtenerPersona(codigoParametro);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getCodigoParametro() {
        return codigoParametro;
    }

    public String addCuenta() {
        ejbCuenta.create(new Usuario(this.usuario, this.clave, this.buscarPersona, "Cliente", "Activo"));
        return null;
    }

    public PersonaFacade getEjbPersona() {
        return ejbPersona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;

    }

    public void setEjbPersona(PersonaFacade ejbPersona) {
        this.ejbPersona = ejbPersona;
    }

    public Usuario getBuscarUsuario() {
        return buscarUsuario;
    }

    public void setBuscarUsuario(Usuario buscarUsuario) {
        this.buscarUsuario = buscarUsuario;
    }

    public Persona getBuscarPersona() {
        return buscarPersona;
    }

    public void setBuscarPersona(Persona buscarPersona) {
        this.buscarPersona = buscarPersona;
    }

    public String getUsuario() {
        return usuario;
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

    public CuentaFacade getEjbCuenta() {
        return ejbCuenta;
    }

    public void setEjbCuenta(CuentaFacade ejbCuenta) {
        this.ejbCuenta = ejbCuenta;
    }

}
