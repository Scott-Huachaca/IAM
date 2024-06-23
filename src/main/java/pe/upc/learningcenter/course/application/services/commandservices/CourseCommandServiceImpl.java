package pe.upc.learningcenter.course.application.services.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.course.domain.model.aggregates.Course;
import pe.upc.learningcenter.course.domain.model.commands.CreateCourseCommand;
import pe.upc.learningcenter.course.domain.model.commands.DeleteCourseCommand;
import pe.upc.learningcenter.course.domain.model.commands.UpdateCourseCommand;
import pe.upc.learningcenter.course.domain.service.CourseCommandService;
import pe.upc.learningcenter.course.infrastructure.persistence.jpa.CourseRepository;

import java.util.Optional;

@Service
public class CourseCommandServiceImpl implements CourseCommandService {
    private final CourseRepository courseRepository;
    public CourseCommandServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> handle(CreateCourseCommand command) {
        var course = new Course(command);
        courseRepository.save(course);
        return Optional.of(course);
    }

    @Override
    public Optional<Course> handle(UpdateCourseCommand command) {
        if(courseRepository.existsByCourseNameAndIdIsNot(command.courseName(), command.id()))
            throw new IllegalArgumentException("Course with name " + command.courseName() + " already exists");
        var result = courseRepository.findById(command.id());
        if(result.isEmpty()) throw new IllegalArgumentException("Course with id " + command.id() + " does not exist");
        var courseToUpdate = result.get();
        try{
            var updateCourse = courseRepository.save(courseToUpdate.updateInformation(command.courseName(), command.courseDescription(), command.amount(), command.currency()));
            return Optional.of(updateCourse);
        }catch(Exception e){
            return Optional.empty();
        }
    }

    @Override
    public void handle(DeleteCourseCommand command) {
        if(!courseRepository.existsById(command.courseId())){
            throw new IllegalArgumentException("Course does not exist");
        }
        try {
            courseRepository.deleteById(command.courseId());
        }catch (Exception e){
            throw new IllegalArgumentException("Course could not be deleted");
        }
    }

}
