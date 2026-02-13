package uce.edu.web.api.academia.application.representation;

import java.util.List;

import uce.edu.web.api.academia.domain.Estudiante;

public class CursoRepresentation {
    public Integer id;
    public String codigo;
    public String nombre;
    public String descripcion;
    public String profesor;
    public List<Estudiante> estudiantes;
    public List<LinkDto> links;
}
