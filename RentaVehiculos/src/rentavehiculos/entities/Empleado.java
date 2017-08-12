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
import javax.persistence.CascadeType;
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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "Empleado.findByCedula", query = "SELECT e FROM Empleado e WHERE e.cedula = :cedula"),
    @NamedQuery(name = "Empleado.findByContrasena", query = "SELECT e FROM Empleado e WHERE e.contrasena = :contrasena"),
    @NamedQuery(name = "Empleado.findBySueldo", query = "SELECT e FROM Empleado e WHERE e.sueldo = :sueldo"),
    @NamedQuery(name = "Empleado.findByFechaInicio", query = "SELECT e FROM Empleado e WHERE e.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Empleado.findByPuesto", query = "SELECT e FROM Empleado e WHERE e.puesto = :puesto"),
    @NamedQuery(name = "Empleado.findByGrupoTrabajo", query = "SELECT e FROM Empleado e WHERE e.grupoTrabajo = :grupoTrabajo"),
    @NamedQuery(name = "Empleado.findByHoraEntrada", query = "SELECT e FROM Empleado e WHERE e.horaEntrada = :horaEntrada"),
    @NamedQuery(name = "Empleado.findByHoraSalida", query = "SELECT e FROM Empleado e WHERE e.horaSalida = :horaSalida"),
    @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empleado.findByDireccion", query = "SELECT e FROM Empleado e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "contrasena")
    private String contrasena;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sueldo")
    private Float sueldo;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "grupo_trabajo")
    private String grupoTrabajo;
    @Column(name = "hora_entrada")
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne
    private Departamento idDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado1")
    private Collection<Cuidado> cuidadoCollection;
    @OneToMany(mappedBy = "idSupervisor")
    private Collection<Departamento> departamentoCollection;
    @OneToMany(mappedBy = "empleado")
    private Collection<Reparacion> reparacionCollection;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Float getSueldo() {
        return sueldo;
    }

    public void setSueldo(Float sueldo) {
        this.sueldo = sueldo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getGrupoTrabajo() {
        return grupoTrabajo;
    }

    public void setGrupoTrabajo(String grupoTrabajo) {
        this.grupoTrabajo = grupoTrabajo;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @XmlTransient
    public Collection<Cuidado> getCuidadoCollection() {
        return cuidadoCollection;
    }

    public void setCuidadoCollection(Collection<Cuidado> cuidadoCollection) {
        this.cuidadoCollection = cuidadoCollection;
    }

    @XmlTransient
    public Collection<Departamento> getDepartamentoCollection() {
        return departamentoCollection;
    }

    public void setDepartamentoCollection(Collection<Departamento> departamentoCollection) {
        this.departamentoCollection = departamentoCollection;
    }

    @XmlTransient
    public Collection<Reparacion> getReparacionCollection() {
        return reparacionCollection;
    }

    public void setReparacionCollection(Collection<Reparacion> reparacionCollection) {
        this.reparacionCollection = reparacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Empleado[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
