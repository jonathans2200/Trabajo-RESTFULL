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
import model.PedidoDetalle;

/**
 *
 * @author jonat
 */
@Stateless
public class PedidoDetalleFacade extends AbstractFacade<PedidoDetalle> {

    @PersistenceContext(unitName = "trabajo02")
    private EntityManager em;

    public PedidoDetalleFacade() {
        super(PedidoDetalle.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
 public List<PedidoDetalle> mostrarDetalle(int dato) {
        return em.createQuery("select c from PedidoDetalle c  where c.pedido.codigo=:usr").setParameter("usr", dato).getResultList();
    }
}
