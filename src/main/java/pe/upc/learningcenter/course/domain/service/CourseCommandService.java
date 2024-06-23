package pe.upc.learningcenter.course.domain.service;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.course.domain.model.aggregates.Course;
import pe.upc.learningcenter.course.domain.model.commands.CreateCourseCommand;
import pe.upc.learningcenter.course.domain.model.commands.DeleteCourseCommand;
import pe.upc.learningcenter.course.domain.model.commands.UpdateCourseCommand;

import java.util.Optional;
@Service
public interface CourseCommandService {
    Optional<Course> handle(CreateCourseCommand command);
    void handle(DeleteCourseCommand command);
    Optional<Course> handle(UpdateCourseCommand command);
}
