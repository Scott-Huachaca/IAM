package pe.upc.learningcenter.course.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.learningcenter.course.domain.model.aggregates.Course;

import java.util.Optional;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseName(String courseName);
    //boolean existsByName(String courseName);
    boolean existsByCourseNameAndIdIsNot(String courseName, Long id);
    //Optional<Course> findByName(String name);

}
