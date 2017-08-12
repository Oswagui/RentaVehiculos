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
@Table(name = "reparacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reparacion.findAll", query = "SELECT r FROM Reparacion r"),
    @NamedQuery(name = "Reparacion.findByIdReparacion", query = "SELECT r FROM Reparacion r WHERE r.idReparacion = :idReparacion"),
    @NamedQuery(name = "Reparacion.findByCosto", query = "SELECT r FROM Reparacion r WHERE r.costo = :costo"),
    @NamedQuery(name = "Reparacion.findByFechaReparaci\u00f3n", query = "SELECT r FROM Reparacion r WHERE r.fechaReparaci\u00f3n = :fechaReparaci\u00f3n"),
    @NamedQuery(name = "Reparacion.findByDescripcion", query = "SELECT r FROM Reparacion r WHERE r.descripcion = :descripcion")})
public class Reparacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idReparacion")
    private Integer idReparacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo")
    private Float costo;
    @Column(name = "fecha_reparaci\u00f3n")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReparación;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleado empleado;
    @JoinColumn(name = "vehiculo", referencedColumnName = "matricula")
    @ManyToOne
    private Vehiculo vehiculo;

    public Reparacion() {
    }

    public Reparacion(Integer idReparacion) {
        this.idReparacion = idReparacion;
    }

    public Integer getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(Integer idReparacion) {
        this.idReparacion = idReparacion;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Date getFechaReparación() {
        return fechaReparación;
    }

    public void setFechaReparación(Date fechaReparación) {
        this.fechaReparación = fechaReparación;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReparacion != null ? idReparacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reparacion)) {
            return false;
        }
        Reparacion other = (Reparacion) object;
        if ((this.idReparacion == null && other.idReparacion != null) || (this.idReparacion != null && !this.idReparacion.equals(other.idReparacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Reparacion[ idReparacion=" + idReparacion + " ]";
    }
    
}
