/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.BodegaFacade;
import ejb.MovimientoFacade;
import ejb.ProductoFacede;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import model.Bodega;
import model.Movimiento;
import model.Producto;

/**
 *
 * @author jonat
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class MovimientoBean implements Serializable {

    private List<Movimiento> listIngreos;
    private List<Bodega> listaBodega;

    @EJB
    private MovimientoFacade ejbMovimiento;
    @EJB
    private ProductoFacede ejbProducto;
    @EJB
    private BodegaFacade ejbBodega;
    
    private String codigoParametro;
    private String descripcion;
    private String tipoMovimiento;
    private int cantidad;
    private int dato;
    private int dato2;
    private Producto producto;
    private List<Producto> listaProducto;
    private Bodega bodega;

    public MovimientoBean() {

    }

    @PostConstruct
    public void init() {
          producto = new Producto();
        bodega = new Bodega(); 
        listIngreos = ejbMovimiento.findAll();
        listaProducto = ejbProducto.findAll();
        listaBodega = ejbBodega.findAll();
      
    }

    public String add() {
  
        ejbMovimiento.create(new Movimiento("nada", "INGRESO", this.cantidad, this.producto, this.bodega));
        listIngreos = ejbMovimiento.findAll();
        return null;
    }

    public String delete(Movimiento p) {
        ejbMovimiento.remove(p);

        return null;
    }

    public String edit(Movimiento c) {
        c.setEditable(true);
        return null;
    }

    public String save(Movimiento p) {
        ejbMovimiento.edit(p);
        p.setEditable(false);
        return null;
    }

    public MovimientoFacade getEjbMovimiento() {
        return ejbMovimiento;
    }

    public void setEjbMovimiento(MovimientoFacade ejbMovimiento) {
        this.ejbMovimiento = ejbMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public List<Movimiento> getListIngreos() {
        return listIngreos;
    }

    public void setListIngreos(List<Movimiento> listIngreos) {
        this.listIngreos = listIngreos;
    }

    public String getCodigoParametro() {
        return codigoParametro;
    }

    public void setCodigoParametro(String codigoParametro) {
        this.codigoParametro = codigoParametro;

    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public int getDato2() {
        return dato2;
    }

    public void setDato2(int dato2) {
        this.dato2 = dato2;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public List<Bodega> getListaBodega() {
        return listaBodega;
    }

    public void setListaBodega(List<Bodega> listaBodega) {
        this.listaBodega = listaBodega;
    }

}
