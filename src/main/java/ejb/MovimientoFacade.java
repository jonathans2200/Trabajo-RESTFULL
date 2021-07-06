/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Movimiento;
import model.Persona;

/**
 *
 * @author jonat
 */
@Stateless
public class MovimientoFacade extends AbstractFacade<Movimiento> {

    @PersistenceContext(unitName = "trabajo02")
    private EntityManager em;

    public MovimientoFacade() {
        super(Movimiento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Movimiento> buscarIngresos() {
        String dato = "ingreso";
        String jpl = "select p from Movimiento p Where p.tipomovimiento =:dato";

        Query q = em.createQuery(jpl, Movimiento.class);
        q.setParameter("dato", dato);
        return (List<Movimiento>) q.getResultList();

        //return null;
    }

    public List<Movimiento> buscarSalidas() {

        String jpl = "select p from Movimiento p Where p.tipomovimiento ='" + "salida'";
        Query q = em.createQuery(jpl, Movimiento.class);

        return q.getResultList();

    }

    public List<Movimiento> buscarBodega(int id) {

        String jpl = "select p from Movimiento p Where p.bodega.codigo ="+id;

        Query q = em.createQuery(jpl, Movimiento.class);

        return q.getResultList();

    }

    public List<Movimiento> obtenerBodega(int id) {
        return em.createQuery("select c from movimiento c inner join c.bodega us where us.codigo=:usr").setParameter("usr", id).getResultList();

        //return null;
    }
}
