package pe.upc.learningcenter.course.application.services.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.course.domain.model.aggregates.Comment;
import pe.upc.learningcenter.course.domain.model.aggregates.Course;
import pe.upc.learningcenter.course.domain.model.commands.CreateCommentCommand;
import pe.upc.learningcenter.course.domain.service.CommentCommandService;
import pe.upc.learningcenter.course.infrastructure.persistence.jpa.CommentRepository;
import pe.upc.learningcenter.course.infrastructure.persistence.jpa.CourseRepository;
import pe.upc.learningcenter.user.domain.model.aggregates.Student;
import pe.upc.learningcenter.user.infrastructure.persistence.jpa.StudentRepository;

import java.util.Optional;

@Service
public class CommentCommandServiceImpl implements CommentCommandService {
    private final CommentRepository commentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CommentCommandServiceImpl(CommentRepository commentRepository, CourseRepository courseRepository, StudentRepository studentRepository){
        this.commentRepository = commentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Comment>handle(CreateCommentCommand command){
        Course course = courseRepository.getById(command.courseId());
        Student student = studentRepository.getById(command.studentId());
        var comment = new Comment(course, student, command.comment());
        commentRepository.save(comment);
        return Optional.of(comment);
    }

}
