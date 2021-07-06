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
import model.Pedidos;

/**
 *
 * @author jonat
 */
@Stateless
public class PedidoFacade extends AbstractFacade<Pedidos>{
 @PersistenceContext(unitName = "trabajo02")
    private EntityManager em;

    public PedidoFacade() {
        super(Pedidos.class);
    }
 
 
     @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

 public List<Pedidos> mostrarPedidos(String dato) {
        return em.createQuery("select c from Pedidos c  where c.estadoPedido=:usr").setParameter("usr", dato).getResultList();
    }
public List<Pedidos> mostrarPedidoCliente(String dato) {
        return em.createQuery("select c from Pedidos c   WHERE c.clienteCedula.cedula =:usr").setParameter("usr", dato).getResultList();
    }
}
