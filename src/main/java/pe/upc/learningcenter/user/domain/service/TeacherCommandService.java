package pe.upc.learningcenter.user.domain.service;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.user.domain.model.aggregates.Teacher;
import pe.upc.learningcenter.user.domain.model.commands.CreateTeacherCommand;

import java.util.Optional;
@Service
public interface TeacherCommandService {
    Optional<Teacher>handle(CreateTeacherCommand command);
}
