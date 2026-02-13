package uce.edu.web.api.academia.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="curso")
@SequenceGenerator(name="curso_seq", sequenceName="curso_seq", allocationSize=1)
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso_seq")
    public Integer id;
    public String codigo;
    public String nombre;
    public String descripcion;
    public String profesor;
    //le dice a la base de datos que la relacion es bidireccional y que el lado propietario es el de Curso
    @ManyToMany
    @JoinTable(name = "curso_estudiante",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    public List<Estudiante> estudiantes;
}
