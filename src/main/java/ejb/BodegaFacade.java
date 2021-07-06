/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Bodega;
import model.Categoria;
import model.Movimiento;
import model.Producto;

/**
 *
 * @author jonat
 */
@Stateless
public class BodegaFacade extends AbstractFacade<Bodega> {

    @PersistenceContext(unitName = "trabajo02")
    private EntityManager em;

    public BodegaFacade() {
        super(Bodega.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Producto> mostrarCategoria(int dato) {
        return em.createQuery("select c from Producto c  where c.categoria_id.codigo=:usr").setParameter("usr", dato).getResultList();
    }
}
