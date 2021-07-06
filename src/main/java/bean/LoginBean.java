/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.sun.javafx.logging.PulseLogger;
import ejb.CuentaFacade;
import ejb.PersonaFacade;
import java.io.Serializable;
import java.util.List;
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
public class LoginBean implements Serializable {

    @EJB
    private PersonaFacade ejbPersona;
    @EJB
    private CuentaFacade ejbCuenta;

    private List<Usuario> listaCliente;
    private List<Persona> list;
    private int codigo;
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String direccion;
    private String estadoCuenta;
    private String usuario;
    private String contrasena;
    private String rol;

    public LoginBean() {
    }

    @PostConstruct
    public void init() {
        listaCliente = ejbCuenta.listarCuentasClientes();
        list = ejbPersona.findAll();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
        // return null;

    }

    public String validar() throws Exception {
        Usuario p = ejbPersona.obtenerUsuario(this.usuario, this.contrasena);
        System.out.println("******* USUARIO ***" + p.getUsuario());
        if (p != null && p.getRol().equalsIgnoreCase("Admin")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrador", p);

            FacesContext contex = FacesContext.getCurrentInstance();

            contex.getExternalContext().redirect("PaginaAdministrador.xhtml?faces-redirect=true&cedula=" + p.getPropietario().getCedula());
        } else if (p != null && p.getRol().equalsIgnoreCase("Empleado")) {

            FacesContext contex = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", p);
            contex.getExternalContext().redirect("PaginaEmpleado.xhtml?faces-redirect=true&cedula=" + p.getPropietario().getCedula());
        } else if (p != null && p.getRol().equalsIgnoreCase("Cliente")) {

            FacesContext contex = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", p);
            contex.getExternalContext().redirect("PaginaCliente.xhtml?faces-redirect=true&cedula=" + p.getPropietario().getCedula());
        }

        return null;
    }

    public String add() {
        ejbPersona.create(new Persona(cedula, nombre, apellido));
        list = ejbPersona.findAll();
        return null;
    }

    public String delete(Persona p) {
        ejbPersona.remove(p);
        list = ejbPersona.findAll();
        listaCliente = ejbCuenta.listarCuentasClientes();
        return null;
    }

    public String save(Persona p) {
        ejbPersona.edit(p);
        p.setEditable(false);
        listaCliente = ejbCuenta.listarCuentasClientes();
        return null;
    }

    public String edit(Persona p) {
        ejbPersona.edit(p);
        p.setEditable(true);
        listaCliente = ejbCuenta.listarCuentasClientes();
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public CuentaFacade getEjbCuenta() {
        return ejbCuenta;
    }

    public void setEjbCuenta(CuentaFacade ejbCuenta) {
        this.ejbCuenta = ejbCuenta;
    }

    public List<Usuario> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Usuario> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public PersonaFacade getEjbPersona() {
        return ejbPersona;
    }

    public void setEjbPersona(PersonaFacade ejbPersona) {
        this.ejbPersona = ejbPersona;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public Persona[] getList() {
        return list.toArray(new Persona[0]);
    }

    public void setList(List<Persona> list) {
        this.list = list;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
