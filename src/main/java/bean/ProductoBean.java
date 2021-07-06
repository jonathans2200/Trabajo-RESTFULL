/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.BodegaFacade;
import ejb.ProductoFacede;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import model.Categoria;

import model.Producto;

/**
 *
 * @author jonat
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ProductoBean implements Serializable {

    @EJB
    private ProductoFacede ejbProducto;

    @EJB
    private BodegaFacade ejbBodega;
    // List<Producto> listaProducto;
    private List<Producto> list;
    private String descripcion;
    private double precio;
    private Categoria categoria;
    private String opcionSeleccionada;

    public ProductoBean() {

    }

    @PostConstruct
    public void init() {
        //  list = ejbProducto.findAll();
        categoria = new Categoria();
        opcionSeleccionada = "TODOS";
        //    List<Producto> listaProducto;   listaProducto = ejbProducto.findAll()
    }

    public Producto[] getList() {
        return list.toArray(new Producto[0]);
    }

    public void setList(List<Producto> list) {
        this.list = list;
    }

    public String getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    public void setOpcionSeleccionada(String opcionSeleccionada) {
        this.opcionSeleccionada = opcionSeleccionada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String add() {

        ejbProducto.create(new Producto(this.descripcion, this.precio, this.categoria));
        list = ejbProducto.findAll();
        return null;
    }

    public String delete(Producto p) {
        ejbProducto.remove(p);
        list = ejbProducto.findAll();
        return null;
    }

    public String edit(Producto c) {
        c.setEditable(true);
        return null;
    }

    public String save(Producto p) {
        ejbProducto.edit(p);
        p.setEditable(false);
        return null;
    }

   

}
