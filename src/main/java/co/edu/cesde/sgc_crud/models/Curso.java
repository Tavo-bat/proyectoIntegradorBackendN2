package co.edu.cesde.sgc_crud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity 
@Table (name = "curso")
public class Curso {

    @Id 
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    @NotEmpty
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    @NotNull
    @Size(max = 200)
    @NotEmpty
    private String descripcion;

    @Column(name = "activo", nullable = false)
    @NotNull
    private Estado activo;

    public Curso() {
    }

    public Curso(Long id, String nombre, String descripcion, Estado activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Curso(String nombre, String descripcion, Estado activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public Estado getActivo() {
        return activo;
    }

    public void setActivo(Estado activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Curso [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + "]";
    }

}
