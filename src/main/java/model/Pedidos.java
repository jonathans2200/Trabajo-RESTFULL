/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 *
 * @author jonat
 */
@Entity
public class Pedidos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String fecha;
    private String direccion;
    private double valor;
    private String estadoPedido;
    @ManyToOne
    private Persona clienteCedula;
 
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoDetalle> pedidoDetalle;
    @Transient
    private boolean editable;

    public Pedidos() {
    }

    public Pedidos(String fecha, String direccion, double valor, Persona clienteCedula,String estado) {
        this.fecha = fecha;
        this.direccion = direccion;
        this.valor = valor;
        this.clienteCedula = clienteCedula;
        this.estadoPedido=estado;
    
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Persona getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(Persona clienteCedula) {
        this.clienteCedula = clienteCedula;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<PedidoDetalle> getPedidoDetalle() {
        return pedidoDetalle;
    }

    public void setPedidoDetalle(List<PedidoDetalle> pedidoDetalle) {
        this.pedidoDetalle = pedidoDetalle;
    }

    @Override
    public String toString() {
        return "Pedidos{" + "codigo=" + codigo + ", fecha=" + fecha + ", direccion=" + direccion + ", valor=" + valor + ", estadoPedido=" + estadoPedido + ", clienteCedula=" + clienteCedula + ", pedidoDetalle=" + pedidoDetalle + ", editable=" + editable + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.codigo;
        hash = 23 * hash + Objects.hashCode(this.fecha);
        hash = 23 * hash + Objects.hashCode(this.direccion);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 23 * hash + Objects.hashCode(this.estadoPedido);
        hash = 23 * hash + Objects.hashCode(this.clienteCedula);
        hash = 23 * hash + Objects.hashCode(this.pedidoDetalle);
        hash = 23 * hash + (this.editable ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedidos other = (Pedidos) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.editable != other.editable) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.estadoPedido, other.estadoPedido)) {
            return false;
        }
        if (!Objects.equals(this.clienteCedula, other.clienteCedula)) {
            return false;
        }
        if (!Objects.equals(this.pedidoDetalle, other.pedidoDetalle)) {
            return false;
        }
        return true;
    }

}
