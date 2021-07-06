/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.BodegaFacade;
import ejb.CuentaFacade;
import ejb.MovimientoFacade;

import ejb.PersonaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import model.Bodega;
import model.Movimiento;

import model.Persona;
import model.Producto;
import model.Usuario;

/**
 *
 * @author jonat
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class BodegaBean implements Serializable {

    @EJB
    private BodegaFacade ejbBodega;

    @EJB
    private PersonaFacade ejbPersona;
    @EJB
    private MovimientoFacade ejbmovimiento;

    @EJB 
    private CuentaFacade ejbCuenta;
    private String tipoCategoria;

    private String codigoBodega;
    private String codigoParametro;
    private String descripcion;
    private String direccion;
    private Persona buscarPersona;
    private Usuario buscarUsuario;
    private List<Bodega> list;
    private List<Movimiento> listaActual;
    private List<Movimiento> listaBodega;

    public BodegaBean() {

    }

    @PostConstruct
    public void init() {
        list = ejbBodega.findAll();
        tipoCategoria = "TODOS";
    }

    public String add() {

        ejbBodega.create(new Bodega(this.descripcion, this.direccion));
        list = ejbBodega.findAll();
        return null;
    }

    public String delete(Bodega p) {
        ejbBodega.remove(p);
        list = ejbBodega.findAll();
        return null;
    }

    public String edit(Bodega c) {
        c.setEditable(true);
        return null;
    }

    public String save(Bodega p) {
        ejbBodega.edit(p);
        p.setEditable(false);
        return null;
    }

    public Bodega[] getList() {
        return list.toArray(new Bodega[0]);
    }

    public void setList(List<Bodega> list) {
        this.list = list;
    }

    public BodegaFacade getEjbBodega() {
        return ejbBodega;
    }

    public void setEjbBodega(BodegaFacade ejbBodega) {
        this.ejbBodega = ejbBodega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public PersonaFacade getEjbPersona() {
        return ejbPersona;
    }

    public void setEjbPersona(PersonaFacade ejbPersona) {
        this.ejbPersona = ejbPersona;
    }

    public Persona getBuscarPersona() {
        return buscarPersona;
    }

    public String getCodigoBodega() {
        return codigoBodega;
    }

    public void setCodigoBodega(String codigoBodega) {
        this.codigoBodega = codigoBodega;
        if (codigoBodega != null) {
            listaBodega = ejbmovimiento.buscarBodega(Integer.valueOf(codigoBodega));
        }
    }

    public void setBuscarPersona(Persona buscarPersona) {
        this.buscarPersona = buscarPersona;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoParametro() {
        return codigoParametro;
    }

    public String consultarBodega(int codigo) {
        System.out.println(codigo);
        return "ConsultaInventario?codigo=" + codigo;
    }

    public List<Movimiento> getListaBodega() {
        return listaBodega;
    }

    public void setListaBodega(List<Movimiento> listaBodega) {
        this.listaBodega = listaBodega;
    }

    public void setCodigoParametro(String codigoParametro) {
        this.codigoParametro = codigoParametro;
        if (codigoParametro != null) {
            try {
                buscarPersona = ejbPersona.obtenerPersona(codigoParametro);
                buscarUsuario=ejbCuenta.obtenerPersona(codigoParametro);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public MovimientoFacade getEjbmovimiento() {
        return ejbmovimiento;
    }

    public void setEjbmovimiento(MovimientoFacade ejbmovimiento) {
        this.ejbmovimiento = ejbmovimiento;
    }

    public String getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(String tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public List<Movimiento> getListaActual() {
        return listaActual;
    }

    public void setListaActual(List<Movimiento> listaActual) {
        this.listaActual = listaActual;
    }

    public CuentaFacade getEjbCuenta() {
        return ejbCuenta;
    }

    public void setEjbCuenta(CuentaFacade ejbCuenta) {
        this.ejbCuenta = ejbCuenta;
    }

    public Usuario getBuscarUsuario() {
        return buscarUsuario;
    }

    public void setBuscarUsuario(Usuario buscarUsuario) {
        this.buscarUsuario = buscarUsuario;
    }

    public void seleccionarCategoria() {
        listaBodega = ejbmovimiento.buscarBodega(Integer.valueOf(codigoBodega));
        if (tipoCategoria != null) {

            if (tipoCategoria.equals("TODOS")) {
                listaActual = listaBodega;
            } else {
                listaActual = new ArrayList<Movimiento>();
                String dato = this.tipoCategoria;
                System.out.println("*********** " + dato);
                for (Movimiento producto : listaBodega) {
                    if (producto.getProducto().getCodigo() == Integer.valueOf(dato)) {
                        listaActual.add(producto);
                    }

                }
                //  list=ejbBodega.mostrarCategoria(Integer.valueOf(dato));
            }
        }
    }
}
