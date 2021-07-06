/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.CuentaFacade;
import ejb.PersonaFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Persona;
import model.Usuario;

/**
 *
 * @author jonat
 */
@Path("/cliente/")
public class ClienteResource {

    @EJB
    private PersonaFacade ejbpersona;
    @EJB
    private CuentaFacade ejbCuenta;

    @GET
    @Path("/sesion")
    @Produces(MediaType.TEXT_PLAIN)
    public Response get(@QueryParam("usuario") String usuario, @QueryParam("pass") String pass) throws Exception {

        Usuario p = ejbpersona.obtenerUsuario(usuario, pass);
        if (p != null) {
            return Response.ok("SE A INICIADO SESION CORRECTAMENTE").build();
            // return "INICIO DE SESION CORRECTO!";
        } else {
            return Response.status(202).entity("CLIENTE NO REGISTRADO").build();

        }
    }

    @GET
    @Path("/obtenerCliente")
    @Produces(MediaType.TEXT_PLAIN)
    public Response obtenerCliente(@QueryParam("cedula") String cedula) throws Exception {

        Persona p = ejbpersona.obtenerPersona(cedula);
        if (p != null) {

            return Response.ok("CLIENTE ENCONTRADO" + p.getNombre() + " " + p.getApellido()).build();
            // return "INICIO DE SESION CORRECTO!";
        } else {
            return Response.status(202).entity("CLIENTE NO REGISTRADO").build();

        }
    }

    @POST
    @Path("/crearCliente")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(@FormParam("cedula") String cedula, @FormParam("nombre") String nombre, @FormParam("apellido") String apellido) throws IOException {
        ejbpersona.create(new Persona(cedula, nombre, apellido));
        return Response.ok("Cuenta creada correctamente!").build();
    }

    @POST
    @Path("/crearCuenta")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response crearCuenta(@FormParam("cedula") String cedula, @FormParam("usuario") String usuario, @FormParam("clave") String clave) throws IOException, Exception {
        Persona p = ejbpersona.obtenerPersona(cedula);
        if (p != null) {
            ejbCuenta.create(new Usuario(usuario, clave, p, "Cliente", "Activo"));

        }
        return Response.ok("Cuenta creada correctamente!").build();
    }

    @GET
    @Path("/eliminar")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminarCliente(@QueryParam("cedula") String cedula) throws Exception {
        Usuario u = ejbCuenta.obtenerPersona(cedula);
        if (u != null) {
            u.setEstadoCuenta("Eliminado");
            ejbCuenta.edit(u);
        }
        return Response.ok("Se a eliminado " + u.toString()).build();
    }
}
