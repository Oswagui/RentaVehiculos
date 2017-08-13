/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Uchiha ClanPc
 */
@Embeddable
public class CuidadoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "empleado")
    private int empleado;
    @Basic(optional = false)
    @Column(name = "vehiculo")
    private String vehiculo;

    public CuidadoPK() {
    }

    public CuidadoPK(int empleado, String vehiculo) {
        this.empleado = empleado;
        this.vehiculo = vehiculo;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empleado;
        hash += (vehiculo != null ? vehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuidadoPK)) {
            return false;
        }
        CuidadoPK other = (CuidadoPK) object;
        if (this.empleado != other.empleado) {
            return false;
        }
        if ((this.vehiculo == null && other.vehiculo != null) || (this.vehiculo != null && !this.vehiculo.equals(other.vehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rentavehiculos.entities.CuidadoPK[ empleado=" + empleado + ", vehiculo=" + vehiculo + " ]";
    }
    
}
