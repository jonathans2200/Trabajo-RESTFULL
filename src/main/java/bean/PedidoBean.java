/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.PedidoDetalleFacade;
import ejb.PedidoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import model.Movimiento;
import model.PedidoDetalle;
import model.Pedidos;
import model.Persona;

/**
 *
 * @author jonat
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class PedidoBean implements Serializable {

    @EJB
    private PedidoFacade ejbPedido;
    @EJB
    private PedidoDetalleFacade ejbdetalle;
    private String parametro;
    private String tipoPedido;
    private List<Pedidos> list;
    private List<Pedidos> listaActual;
    private String fecha;
    private String direccion;
    private String estado;
    private double valor;
    private Persona clienteCedula;
    List<PedidoDetalle> pedidoDetalle;

    public PedidoBean() {

    }

    @PostConstruct
    public void init() {
        tipoPedido = "Enviado";
    }

    public String add() {

        ejbPedido.create(new Pedidos(fecha, direccion, valor, clienteCedula,estado));
        list = ejbPedido.findAll();
        return null;
    }

    public String delete(Pedidos p) {
        ejbPedido.remove(p);
        list = ejbPedido.findAll();
        return null;
    }

    public String edit(Pedidos c) {
        c.setEditable(true);
        return null;
    }

    public String save(Pedidos p) {
        ejbPedido.edit(p);
        p.setEditable(false);
        list = ejbPedido.findAll();
        return null;
    }

    public PedidoFacade getEjbPedido() {
        return ejbPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEjbPedido(PedidoFacade ejbPedido) {
        this.ejbPedido = ejbPedido;
    }

    public PedidoDetalleFacade getEjbdetalle() {
        return ejbdetalle;
    }

    public void setEjbdetalle(PedidoDetalleFacade ejbdetalle) {
        this.ejbdetalle = ejbdetalle;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public List<Pedidos> getList() {
        return list;
    }

    public void setList(List<Pedidos> list) {
        this.list = list;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Persona getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(Persona clienteCedula) {
        this.clienteCedula = clienteCedula;
    }

    public List<PedidoDetalle> getPedidoDetalle() {
        return pedidoDetalle;
    }

    public void setPedidoDetalle(List<PedidoDetalle> pedidoDetalle) {
        this.pedidoDetalle = pedidoDetalle;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
        if (this.parametro != null) {
            try {
                pedidoDetalle = ejbdetalle.mostrarDetalle(Integer.valueOf(this.parametro));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public List<Pedidos> getListaActual() {
        return listaActual;
    }

    public void setListaActual(List<Pedidos> listaActual) {
        this.listaActual = listaActual;
    }

    
    public void seleccionarPedido() {
        list = ejbPedido.findAll();

        if (tipoPedido != null) {
            listaActual = list;
            if (tipoPedido.equals("Enviado")) {
                listaActual = new ArrayList<Pedidos>();
                String dato = this.tipoPedido;
                System.out.println("*********** " + dato);
                for (Pedidos producto : list) {
                    if (producto.getEstadoPedido().equals(dato)) {
                        listaActual.add(producto);
                    }

                }
            } else if (tipoPedido.equals("Receptado")) {
                listaActual = new ArrayList<Pedidos>();
                String dato = this.tipoPedido;
                System.out.println("*********** " + dato);
                for (Pedidos producto : list) {
                    if (producto.getEstadoPedido().equals(dato)) {
                        listaActual.add(producto);
                    }

                }
                //  list=ejbBodega.mostrarCategoria(Integer.valueOf(dato));
            } else if (tipoPedido.equals("EnProceso")) {
                listaActual = new ArrayList<Pedidos>();
                String dato = this.tipoPedido;
                System.out.println("*********** " + dato);
                for (Pedidos producto : list) {
                    if (producto.getEstadoPedido().equals(dato)) {
                        listaActual.add(producto);
                    }

                }
            } else if (tipoPedido.equals("EnCamino")) {
                listaActual = new ArrayList<Pedidos>();
                String dato = this.tipoPedido;
                System.out.println("*********** " + dato);
                for (Pedidos producto : list) {
                    if (producto.getEstadoPedido().equals(dato)) {
                        listaActual.add(producto);
                    }

                }

            } else if (tipoPedido.equals("Finalizado")) {
                listaActual = new ArrayList<Pedidos>();
                String dato = this.tipoPedido;
                System.out.println("*********** " + dato);
                for (Pedidos producto : list) {
                    if (producto.getEstadoPedido().equals(dato)) {
                        listaActual.add(producto);
                    }

                }
            }
        }
    }
}
