package pe.upc.learningcenter.user.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.learningcenter.user.domain.model.aggregates.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
