package uce.edu.web.api.academia.domain;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;
@Entity
@Table(name ="estudiante")
@SequenceGenerator(name="estudiante_seq", sequenceName="estudiante_seq", allocationSize=1)
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiate_seq")
    public Integer id;
    public String nombre;
    public String apellido;
    public String email;
    public String celular;
    //le dice a la base de datos que la relacion es bidireccional y que el lado propietario es el de Curso
    //mappedby es el nombre exacto del atributo en la clase Curso que hace referencia a esta relacion
    @ManyToMany(mappedBy = "estudiantes")
    public List<Curso> cursos;
    
}
