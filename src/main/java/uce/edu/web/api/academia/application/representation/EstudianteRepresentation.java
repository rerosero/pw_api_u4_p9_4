package uce.edu.web.api.academia.application.representation;

import java.util.List;


import uce.edu.web.api.academia.domain.Curso;

public class EstudianteRepresentation {
    public Integer id;
    public String nombre;
    public String apellido;
    public String email;
    public String celular;
    public List<Curso> cursos;
    public List<LinkDto> links;
}
