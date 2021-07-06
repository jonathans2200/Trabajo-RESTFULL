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
import model.Usuario;

/**
 *
 * @author jonat
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "trabajo02")
    private EntityManager em;

    public CuentaFacade() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
     public List<Usuario> listarCuentasClientes() {

        String jpl = "select p from Usuario p Where p.estadoCuenta ='Activo' and p.rol='Cliente' " ;

        Query q = em.createQuery(jpl, Usuario.class);

        return q.getResultList();

    }


public Usuario obtenerPersona(String cedula) throws Exception {
        try {
            String jpl = "select p from Usuario p Where p.propietario.cedula =:usu ";
            Query q = em.createQuery(jpl, Usuario.class);
            q.setParameter("usu", cedula);
            return (Usuario) q.getSingleResult();

        } catch (NoResultException e) {
            //System.out.println(e.getMessage());
            throw new Exception("PERSONA NO ENCONTRADA");
        }
        //return null;
    }



}
