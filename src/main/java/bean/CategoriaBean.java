/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.CategoriaFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import model.Categoria;

/**
 *
 * @author jonat
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class CategoriaBean implements Serializable {

    @EJB
    private CategoriaFacade ejbCategoria;
   
    private List<Categoria> list;
    private String descripcion;

    public CategoriaBean() {

    }

    
    @PostConstruct
    public void init() {
        //ejbCategoryFacade.create(new Category("Hola"));
        //ejbCategoryFacade.create(new Category("1211"));
        list = ejbCategoria.findAll();
    }

    public Categoria[] getList() {
        return list.toArray(new Categoria[0]);
    }

    public void setList(List<Categoria> list) {
        this.list = list;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String add() {
      
        ejbCategoria.create(new Categoria(this.descripcion));
        list = ejbCategoria.findAll();
        return null;
    }

    public String delete(Categoria c) {
        ejbCategoria.remove(c);
        list = ejbCategoria.findAll();
        return null;
    }

    public String edit(Categoria c) {
        c.setEditable(true);
        return null;
    }

    public String save(Categoria c) {
        ejbCategoria.edit(c);
        c.setEditable(false);
        return null;
    }
}
