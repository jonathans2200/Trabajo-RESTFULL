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
import model.Persona;
import model.Usuario;

/**
 *
 * @author jonat
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "trabajo02")
    private EntityManager em;

    public PersonaFacade() {
        super(Persona.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Usuario obtenerUsuario(String usuario, String contra) throws Exception {
        try {
            String jpl = "select p from Usuario p Where p.usuario =:usu AND p.clave =:contr";
            Query q = em.createQuery(jpl, Persona.class);
            q.setParameter("usu", usuario);
            q.setParameter("contr", contra);
            return (Usuario) q.getSingleResult();

        } catch (NoResultException e) {
          
            System.out.println("Credenciaales Inocorrectas");
        }
        return null;
    }

    public Persona obtenerPersona(String id) throws Exception {
        try {
            String jpl = "select p from Persona p Where p.cedula =:usu ";
            Query q = em.createQuery(jpl, Persona.class);
            q.setParameter("usu", id);
            return (Persona) q.getSingleResult();

        } catch (NoResultException e) {
            //System.out.println(e.getMessage());
            throw new Exception("PERSONA NO ENCONTRADA");
        }
        //return null;
    }




}
