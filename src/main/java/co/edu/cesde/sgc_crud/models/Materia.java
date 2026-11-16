package co.edu.cesde.sgc_crud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    @NotEmpty
    private String nombre;

    @Column(name = "descripcion", length = 200)
    @Size(max = 200)
    private String descripcion;

    @Column(name = "creditos")
    private Integer creditos;

    @Column(name = "activo", nullable = false)
    @NotNull
    private Estado activo;

    public Materia() {
    }

    public Materia(Long id, String nombre, String descripcion, Integer creditos, Estado activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.activo = activo;
    }

    public Materia(String nombre, String descripcion, Integer creditos, Estado activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Estado getActivo() {
        return activo;
    }

    public void setActivo(Estado activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", creditos=" + creditos +
                ", activo=" + activo +
                '}';
    }
}
