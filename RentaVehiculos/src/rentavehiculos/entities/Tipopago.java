/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Uchiha ClanPc
 */
@Entity
@Table(name = "tipopago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipopago.findAll", query = "SELECT t FROM Tipopago t"),
    @NamedQuery(name = "Tipopago.findByIdPago", query = "SELECT t FROM Tipopago t WHERE t.idPago = :idPago"),
    @NamedQuery(name = "Tipopago.findByFechaPago", query = "SELECT t FROM Tipopago t WHERE t.fechaPago = :fechaPago"),
    @NamedQuery(name = "Tipopago.findByValorPago", query = "SELECT t FROM Tipopago t WHERE t.valorPago = :valorPago")})
public class Tipopago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pago")
    private Integer idPago;
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_pago")
    private Float valorPago;
    @JoinColumn(name = "idAlquilerVehiculo", referencedColumnName = "idAlquilerVehiculo")
    @ManyToOne
    private Alquilervehiculo idAlquilerVehiculo;

    public Tipopago() {
    }

    public Tipopago(Integer idPago) {
        this.idPago = idPago;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Float getValorPago() {
        return valorPago;
    }

    public void setValorPago(Float valorPago) {
        this.valorPago = valorPago;
    }

    public Alquilervehiculo getIdAlquilerVehiculo() {
        return idAlquilerVehiculo;
    }

    public void setIdAlquilerVehiculo(Alquilervehiculo idAlquilerVehiculo) {
        this.idAlquilerVehiculo = idAlquilerVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPago != null ? idPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipopago)) {
            return false;
        }
        Tipopago other = (Tipopago) object;
        if ((this.idPago == null && other.idPago != null) || (this.idPago != null && !this.idPago.equals(other.idPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rentavehiculos.entities.Tipopago[ idPago=" + idPago + " ]";
    }
    
}
