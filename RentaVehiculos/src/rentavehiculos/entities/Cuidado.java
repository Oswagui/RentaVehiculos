/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Uchiha ClanPc
 */
@Entity
@Table(name = "cuidado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuidado.findAll", query = "SELECT c FROM Cuidado c"),
    @NamedQuery(name = "Cuidado.findByEmpleado", query = "SELECT c FROM Cuidado c WHERE c.cuidadoPK.empleado = :empleado"),
    @NamedQuery(name = "Cuidado.findByVehiculo", query = "SELECT c FROM Cuidado c WHERE c.cuidadoPK.vehiculo = :vehiculo"),
    @NamedQuery(name = "Cuidado.findByObservaciones", query = "SELECT c FROM Cuidado c WHERE c.observaciones = :observaciones")})
public class Cuidado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuidadoPK cuidadoPK;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "empleado", referencedColumnName = "id_empleado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado1;
    @JoinColumn(name = "vehiculo", referencedColumnName = "matricula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vehiculo vehiculo1;

    public Cuidado() {
    }

    public Cuidado(CuidadoPK cuidadoPK) {
        this.cuidadoPK = cuidadoPK;
    }

    public Cuidado(int empleado, String vehiculo) {
        this.cuidadoPK = new CuidadoPK(empleado, vehiculo);
    }

    public CuidadoPK getCuidadoPK() {
        return cuidadoPK;
    }

    public void setCuidadoPK(CuidadoPK cuidadoPK) {
        this.cuidadoPK = cuidadoPK;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Empleado getEmpleado1() {
        return empleado1;
    }

    public void setEmpleado1(Empleado empleado1) {
        this.empleado1 = empleado1;
    }

    public Vehiculo getVehiculo1() {
        return vehiculo1;
    }

    public void setVehiculo1(Vehiculo vehiculo1) {
        this.vehiculo1 = vehiculo1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuidadoPK != null ? cuidadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuidado)) {
            return false;
        }
        Cuidado other = (Cuidado) object;
        if ((this.cuidadoPK == null && other.cuidadoPK != null) || (this.cuidadoPK != null && !this.cuidadoPK.equals(other.cuidadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cuidado[ cuidadoPK=" + cuidadoPK + " ]";
    }
    
}
