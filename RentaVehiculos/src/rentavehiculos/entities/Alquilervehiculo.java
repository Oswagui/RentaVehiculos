/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Uchiha ClanPc
 */
@Entity
@Table(name = "alquilervehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alquilervehiculo.findAll", query = "SELECT a FROM Alquilervehiculo a"),
    @NamedQuery(name = "Alquilervehiculo.findByIdAlquilerVehiculo", query = "SELECT a FROM Alquilervehiculo a WHERE a.idAlquilerVehiculo = :idAlquilerVehiculo"),
    @NamedQuery(name = "Alquilervehiculo.findByNumeroDias", query = "SELECT a FROM Alquilervehiculo a WHERE a.numeroDias = :numeroDias"),
    @NamedQuery(name = "Alquilervehiculo.findByFechaSalida", query = "SELECT a FROM Alquilervehiculo a WHERE a.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "Alquilervehiculo.findByFechaLlegada", query = "SELECT a FROM Alquilervehiculo a WHERE a.fechaLlegada = :fechaLlegada"),
    @NamedQuery(name = "Alquilervehiculo.findByKilometrajeSalida", query = "SELECT a FROM Alquilervehiculo a WHERE a.kilometrajeSalida = :kilometrajeSalida"),
    @NamedQuery(name = "Alquilervehiculo.findByNivelGasolinaSalida", query = "SELECT a FROM Alquilervehiculo a WHERE a.nivelGasolinaSalida = :nivelGasolinaSalida")})
public class Alquilervehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idAlquilerVehiculo")
    private Integer idAlquilerVehiculo;
    @Column(name = "numero_dias")
    private Integer numeroDias;
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Column(name = "fecha_llegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLlegada;
    @Column(name = "kilometraje_salida")
    private Integer kilometrajeSalida;
    @Column(name = "nivel_gasolina_salida")
    private Integer nivelGasolinaSalida;
    @JoinColumn(name = "cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private Cliente cliente;
    @JoinColumn(name = "vehiculo", referencedColumnName = "matricula")
    @ManyToOne
    private Vehiculo vehiculo;
    @OneToMany(mappedBy = "idAlquilerVehiculo")
    private Collection<Tipopago> tipopagoCollection;

    public Alquilervehiculo() {
    }

    public Alquilervehiculo(Integer idAlquilerVehiculo) {
        this.idAlquilerVehiculo = idAlquilerVehiculo;
    }

    public Integer getIdAlquilerVehiculo() {
        return idAlquilerVehiculo;
    }

    public void setIdAlquilerVehiculo(Integer idAlquilerVehiculo) {
        this.idAlquilerVehiculo = idAlquilerVehiculo;
    }

    public Integer getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Integer getKilometrajeSalida() {
        return kilometrajeSalida;
    }

    public void setKilometrajeSalida(Integer kilometrajeSalida) {
        this.kilometrajeSalida = kilometrajeSalida;
    }

    public Integer getNivelGasolinaSalida() {
        return nivelGasolinaSalida;
    }

    public void setNivelGasolinaSalida(Integer nivelGasolinaSalida) {
        this.nivelGasolinaSalida = nivelGasolinaSalida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @XmlTransient
    public Collection<Tipopago> getTipopagoCollection() {
        return tipopagoCollection;
    }

    public void setTipopagoCollection(Collection<Tipopago> tipopagoCollection) {
        this.tipopagoCollection = tipopagoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlquilerVehiculo != null ? idAlquilerVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alquilervehiculo)) {
            return false;
        }
        Alquilervehiculo other = (Alquilervehiculo) object;
        if ((this.idAlquilerVehiculo == null && other.idAlquilerVehiculo != null) || (this.idAlquilerVehiculo != null && !this.idAlquilerVehiculo.equals(other.idAlquilerVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rentavehiculos.entities.Alquilervehiculo[ idAlquilerVehiculo=" + idAlquilerVehiculo + " ]";
    }
    
}
