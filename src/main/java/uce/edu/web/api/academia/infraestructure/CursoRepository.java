package uce.edu.web.api.academia.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.academia.domain.Curso;

@ApplicationScoped
public class CursoRepository implements PanacheRepository<Curso> {

}
