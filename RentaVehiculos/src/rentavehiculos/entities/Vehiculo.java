/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
    @NamedQuery(name = "Vehiculo.findByMatricula", query = "SELECT v FROM Vehiculo v WHERE v.matricula = :matricula"),
    @NamedQuery(name = "Vehiculo.findByTipo", query = "SELECT v FROM Vehiculo v WHERE v.tipo = :tipo"),
    @NamedQuery(name = "Vehiculo.findByMarca", query = "SELECT v FROM Vehiculo v WHERE v.marca = :marca"),
    @NamedQuery(name = "Vehiculo.findByA\u00f1o", query = "SELECT v FROM Vehiculo v WHERE v.a\u00f1o = :a\u00f1o"),
    @NamedQuery(name = "Vehiculo.findByNombreModelo", query = "SELECT v FROM Vehiculo v WHERE v.nombreModelo = :nombreModelo"),
    @NamedQuery(name = "Vehiculo.findByDisponibilidad", query = "SELECT v FROM Vehiculo v WHERE v.disponibilidad = :disponibilidad"),
    @NamedQuery(name = "Vehiculo.findByColor", query = "SELECT v FROM Vehiculo v WHERE v.color = :color"),
    @NamedQuery(name = "Vehiculo.findByCapacidad", query = "SELECT v FROM Vehiculo v WHERE v.capacidad = :capacidad"),
    @NamedQuery(name = "Vehiculo.findByPrecio", query = "SELECT v FROM Vehiculo v WHERE v.precio = :precio")})
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "a\u00f1o")
    private Integer año;
    @Column(name = "nombre_modelo")
    private String nombreModelo;
    @Column(name = "disponibilidad")
    private Boolean disponibilidad;
    @Column(name = "color")
    private String color;
    @Column(name = "capacidad")
    private Integer capacidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Float precio;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @OneToMany(mappedBy = "vehiculo")
    private Collection<Alquilervehiculo> alquilervehiculoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo1")
    private Collection<Cuidado> cuidadoCollection;
    @OneToMany(mappedBy = "vehiculo")
    private Collection<Reparacion> reparacionCollection;
    @JoinColumn(name = "proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne
    private Proveedor proveedor;
    
    private String estado;

    public Vehiculo() {
    }

    public Vehiculo(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @XmlTransient
    public Collection<Alquilervehiculo> getAlquilervehiculoCollection() {
        return alquilervehiculoCollection;
    }

    public void setAlquilervehiculoCollection(Collection<Alquilervehiculo> alquilervehiculoCollection) {
        this.alquilervehiculoCollection = alquilervehiculoCollection;
    }

    @XmlTransient
    public Collection<Cuidado> getCuidadoCollection() {
        return cuidadoCollection;
    }

    public void setCuidadoCollection(Collection<Cuidado> cuidadoCollection) {
        this.cuidadoCollection = cuidadoCollection;
    }

    @XmlTransient
    public Collection<Reparacion> getReparacionCollection() {
        return reparacionCollection;
    }

    public void setReparacionCollection(Collection<Reparacion> reparacionCollection) {
        this.reparacionCollection = reparacionCollection;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getPrecioS(){
        return "$"+getPrecio();
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rentavehiculos.entities.Vehiculo[ matricula=" + matricula + " ]";
    }
    
}
