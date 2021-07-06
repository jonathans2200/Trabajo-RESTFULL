/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import bean.CuentaBean;
import ejb.CuentaFacade;
import ejb.FacturaFacade;
import ejb.PedidoDetalleFacade;
import ejb.PedidoFacade;
import ejb.PersonaFacade;
import java.io.IOException;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Categoria;
import model.FacturaCabecera;
import model.PedidoDetalle;

import model.Pedidos;
import model.Persona;
import model.Producto;
import model.Usuario;

/**
 *
 * @author jonat
 */
@Path("/pedido/")
public class PedidoResource {
    
    @EJB
    private PedidoFacade ejbPedido;
    @EJB
    private PedidoDetalleFacade ejbdetalle;
    
    @EJB
    private PersonaFacade ejbPersona;
    
    @EJB
    private FacturaFacade ejbFactura;
    @EJB
    private CuentaFacade ejbCuenta;
    
    @GET
    @Path("/pedidoCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedidos> getCreditos(@QueryParam("cedula") String cedula) throws Exception {
        try {
            return ejbPedido.mostrarPedidoCliente(cedula);
        } catch (Exception e) {
            throw new Exception("Se ah producido un error" + e.getMessage());
        }
    }

    /* @GET
    @Path("/pedidoCliente/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaPedidos() {

        Jsonb jsonb = JsonbBuilder.create();
        List<Pedidos> list =new ArrayList<Pedidos>();
               list= ejbPedido.findAll();

        // para evitar el error del CORS se agregan los headers
        return Response.ok(jsonb.toJson(list))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    }

   
     */
    @POST
    @Path("/solicitud")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(@FormParam("cedula") String cedula) throws IOException {
        
        try {
            Persona p = ejbPersona.obtenerPersona(cedula);
            if (p != null) {
                Categoria c = new Categoria(1, "zapatos");
                Categoria c1 = new Categoria(2, "ropa");
                Categoria c2 = new Categoria(3, "accesorios");
                
                Producto pro = new Producto(1, "zapatos addidas", 30.00, c);
                Producto pro1 = new Producto(2, "blusa polo", 14.00, c1);
                Producto pro2 = new Producto(3, "gorra addidas", 15, c2);
                Producto pro3 = new Producto(4, "gorra puma", 10, c2);
                Pedidos ped = new Pedidos("2021/07/5", "PRUEBA2", 165.00, p, "Enviado");
                ejbPedido.create(ped);
                ejbdetalle.create(new PedidoDetalle(pro, ped, 2, 60.00));
                ejbdetalle.create(new PedidoDetalle(pro1, ped, 1, 14.00));
                ejbdetalle.create(new PedidoDetalle(pro2, ped, 4, 60.00));
                ejbdetalle.create(new PedidoDetalle(pro3, ped, 1, 10.00));
                
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return Response.ok("SE A SOLICITADO EL PEDIDO CORRECTAMENTE!").build();
    }
    
    @POST
    @Path("/generarFactura")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response generarFactura(@FormParam("codigo") int codigo) throws IOException {
        try {
            Pedidos p = ejbPedido.find(codigo);
            if (p != null) {
                Persona per = ejbPersona.obtenerPersona(p.getClienteCedula().getCedula());
                ejbFactura.create(new FacturaCabecera(per, p, p.getValor(), p.getValor() * 0.12, 0.12));
                p.setEstadoPedido("Receptado");
                ejbPedido.edit(p);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        
        return Response.ok("SE A GENERADO LA FACTURA CORRECTAMENTE!").build();
    }
    
    @GET
    @Path("/modificar")
    @Produces(MediaType.TEXT_PLAIN)
    public String modificar(@QueryParam("nombres") String nombre,
            @QueryParam("apellidos") String apellido, @QueryParam("cedula") String cedula, @QueryParam("usuario") String usuario, @QueryParam("clave") String clave) throws Exception {
        Usuario u = ejbCuenta.obtenerPersona(cedula);
        Persona p = ejbPersona.obtenerPersona(cedula);
        p.setCodigo(u.getPropietario().getCodigo());
        p.setApellido(apellido);
        p.setNombre(nombre);
        p.setCedula(cedula);
        u.setPropietario(p);
        u.setUsuario(usuario);
        u.setClave(clave);
        ejbCuenta.edit(u);
        ejbPersona.edit(p);
        return "MODIFICAR CUENTA" + u.toString()  + "USUARIO "+p.toString();
    }
    
}
