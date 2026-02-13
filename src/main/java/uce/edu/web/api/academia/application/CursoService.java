package uce.edu.web.api.academia.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.academia.application.representation.CursoRepresentation;
import uce.edu.web.api.academia.domain.Curso;
import uce.edu.web.api.academia.infraestructure.CursoRepository;

@ApplicationScoped
@Transactional
public class CursoService {
    @Inject
    CursoRepository cursoRepository;

    // Mapeo
    public CursoRepresentation mapCursotoRepresentation(Curso curso) {
        CursoRepresentation cr = new CursoRepresentation();
        cr.id = curso.id;
        cr.codigo = curso.codigo;
        cr.nombre = curso.nombre;
        cr.descripcion = curso.descripcion;
        cr.profesor = curso.profesor;
        return cr;
    }

    public Curso mapRepresentationToCurso(CursoRepresentation cr) {
        Curso curso = new Curso();
        curso.id = cr.id;
        curso.codigo = cr.codigo;
        curso.nombre = cr.nombre;
        curso.descripcion = cr.descripcion;
        curso.profesor = cr.profesor;
        return curso;
    }

    // CRUD
    // Crear
    public void crearCurso(CursoRepresentation cr) {
        Curso curso = this.mapRepresentationToCurso(cr);
        this.cursoRepository.persist(curso);
    }

    // Leer
    public List<CursoRepresentation> listartodos() {
        List<Curso> curso = this.cursoRepository.listAll();
        List<CursoRepresentation> cr = new ArrayList<>();
        for (Curso curs : curso) {
            CursoRepresentation estr = this.mapCursotoRepresentation(curs);
            cr.add(estr);
        }
        return cr;
    }

    public CursoRepresentation buscarID(Integer id) {
        Curso curso = this.cursoRepository.findById(id.longValue());
        return mapCursotoRepresentation(curso);
    }

    // actualizar
    public void actualizar(Integer id, CursoRepresentation cr) {
        Curso curso = this.cursoRepository.findById(id.longValue());
        curso.nombre = cr.nombre;
        curso.codigo = cr.codigo;
        curso.profesor = cr.profesor;
        curso.descripcion = cr.descripcion;
    }
    //actualizar parcial 
    public void actualizarParcial (Integer id, CursoRepresentation cr){
        Curso curso = this.cursoRepository.findById(id.longValue());
        if(curso == null){
            throw new RuntimeException ("Curso no encontrado por el id número:");
        }
        if(cr.nombre != null){
            curso.nombre = cr.nombre;
        }
        if(cr.codigo != null){
            curso.codigo = cr.codigo ;
        }
        if(cr.profesor != null){
              curso.profesor = cr.profesor;
        }
        if(cr.descripcion!=null){
             curso.descripcion = cr.descripcion;
        }
    }
    // Eliminar
    public void eliminar (Integer id){
        this.cursoRepository.deleteById(id.longValue());
    }
}
