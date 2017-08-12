/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Uchiha ClanPc
 */
@Entity
@Table(name = "departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
    @NamedQuery(name = "Departamento.findByIdDepartamento", query = "SELECT d FROM Departamento d WHERE d.idDepartamento = :idDepartamento"),
    @NamedQuery(name = "Departamento.findByNombre", query = "SELECT d FROM Departamento d WHERE d.nombre = :nombre")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_departamento")
    private Integer idDepartamento;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idDepartamento")
    private Collection<Empleado> empleadoCollection;
    @JoinColumn(name = "id_supervisor", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleado idSupervisor;

    public Departamento() {
    }

    public Departamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    public Empleado getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(Empleado idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamento != null ? idDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.idDepartamento == null && other.idDepartamento != null) || (this.idDepartamento != null && !this.idDepartamento.equals(other.idDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Departamento[ idDepartamento=" + idDepartamento + " ]";
    }
    
}
