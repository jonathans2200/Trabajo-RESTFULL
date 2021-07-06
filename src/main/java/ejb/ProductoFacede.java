/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Producto;

/**
 *
 * @author jonat
 */
@Stateless
public class ProductoFacede extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "trabajo02")
    private EntityManager em;

    public ProductoFacede() {
        super(Producto.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
