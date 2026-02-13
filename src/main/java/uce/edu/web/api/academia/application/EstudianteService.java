package uce.edu.web.api.academia.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.academia.application.representation.EstudianteRepresentation;
import uce.edu.web.api.academia.domain.Estudiante;
import uce.edu.web.api.academia.domain.Curso;
import uce.edu.web.api.academia.infraestructure.CursoRepository;
import uce.edu.web.api.academia.infraestructure.EstudianteRepository;

@ApplicationScoped
@Transactional
public class EstudianteService {
    @Inject
    EstudianteRepository estudianteRepository;
    @Inject
    CursoRepository cursoRepository;
    //Inscibir a un estudiante a un curso
    public void inscribirCurso(Integer estudianteId, Integer cursoId){
        Estudiante estr =this.estudianteRepository.findById(estudianteId.longValue());
        if(estr== null){
            throw new RuntimeException("Estudiante no encontrado");
        }
        Curso cr = this.cursoRepository.findById(cursoId.longValue());
        if(cr == null){
            throw new RuntimeException("Curso no encontrado");
        }
        //verificar si el estudiante no esta inscrito al curso
        if(!cr.estudiantes.contains(estr)){
            cr.estudiantes.add(estr);
            this.cursoRepository.persist(cr);
        }   
    }
    //Cancelar inscripción de un estudiante a un curso
    public void cancelarInscripcion(Integer idEstudiante, Integer idCurso){
      Estudiante estr =this.estudianteRepository.findById(idEstudiante.longValue());
        if(estr== null){
            throw new RuntimeException("Estudiante no encontrado");
        }
        Curso cr = this.cursoRepository.findById(idCurso.longValue());
        if(cr == null){
            throw new RuntimeException("Curso no encontrado");
        }
        //verificar si el estudiante esta inscrito al curso
        if(cr.estudiantes.contains(estr)){
            cr.estudiantes.remove(estr);
            this.cursoRepository.persist(cr);
        }   
    }
    //Mapeo
     private EstudianteRepresentation MapEstudianteToRepresentation(Estudiante estudiante){
        EstudianteRepresentation er = new EstudianteRepresentation();
        er.id = estudiante.id;
        er.nombre = estudiante.nombre;
        er.apellido = estudiante.apellido;
        er.email = estudiante.email;
        er.celular=estudiante.celular;
        er.cursos = new ArrayList<>();
        if (estudiante.cursos != null) {
            for (Curso curso : estudiante.cursos) {
                Curso c = new Curso();
                c.id = curso.id;
                c.nombre = curso.nombre;
                c.codigo = curso.codigo;
                c.descripcion = curso.descripcion;
                c.profesor = curso.profesor;
                er.cursos.add(c);
            }
        }
        return er;
     }
     private Estudiante MapRepresentationtoEstudiante(EstudianteRepresentation estr){
        Estudiante es = new Estudiante();
        es.id= estr.id;
        es.nombre=estr.nombre;
        es.apellido=estr.apellido;
        es.email=estr.email;
        es.celular=estr.celular;
        return es;
     }
     //CRUD
     //crear
     public void crearEstudiante (EstudianteRepresentation estr){
        Estudiante estudiante = this.MapRepresentationtoEstudiante(estr);
        this.estudianteRepository.persist(estudiante);
     }
     //read
    public List<EstudianteRepresentation> ListarTodos(){
        List<Estudiante> estudiante = this.estudianteRepository.listAll();
        List<EstudianteRepresentation> ListaestuR = new ArrayList<>();
        for(Estudiante est: estudiante){
            EstudianteRepresentation estuR = this.MapEstudianteToRepresentation(est);
            ListaestuR.add(estuR);
        }
        return ListaestuR;
     }
     
    public EstudianteRepresentation consultarporID(Integer id){
        Estudiante est = this.estudianteRepository.findById(id.longValue());
        return this.MapEstudianteToRepresentation(est);
    }
    //actualizar
    public void actualizar(Integer id, EstudianteRepresentation estr){
        Estudiante estudiante = this.estudianteRepository.findById(id.longValue());

        estudiante.apellido = estr.apellido;
        estudiante.nombre=estr.nombre;
        estudiante.celular=estr.celular;
        estudiante.email=estr.email;
    }
    //actualizar parcial
    public void actualizarParcial(Integer id, EstudianteRepresentation estR){
        Estudiante est = this.estudianteRepository.findById(id.longValue());
        if (estR.nombre != null) {
            est.nombre= estR.nombre;
        }
        if (estR.apellido != null) {
            est.apellido=estR.apellido;
        }
        if (estR.celular != null) {
            est.celular= estR.celular;
        }
        if (est.email != null) {
            est.email=estR.email;            
        }
    }
    //eliminar
    public void eliminar (Integer id ){
       Estudiante estudiante= this.estudianteRepository.findById(id.longValue());
       if (estudiante== null) {
            throw new RuntimeException("Estudiante no encontrado");
       }
       estudiante.cursos.clear();
       this.estudianteRepository.delete(estudiante);
    }
}
