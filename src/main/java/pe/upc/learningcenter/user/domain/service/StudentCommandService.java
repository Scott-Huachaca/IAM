package pe.upc.learningcenter.user.domain.service;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.user.domain.model.aggregates.Student;
import pe.upc.learningcenter.user.domain.model.commands.CreateStudentCommand;
import pe.upc.learningcenter.user.domain.model.commands.UpdateStudentCommand;

import java.util.Optional;
@Service
public interface StudentCommandService {
    Optional<Student> handle(CreateStudentCommand command);
    Optional<Student> handle(UpdateStudentCommand command);
}
