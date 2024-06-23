package pe.upc.learningcenter.user.application.services.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.user.domain.model.aggregates.Student;
import pe.upc.learningcenter.user.domain.model.commands.CreateStudentCommand;
import pe.upc.learningcenter.user.domain.model.commands.UpdateStudentCommand;
import pe.upc.learningcenter.user.domain.service.StudentCommandService;
import pe.upc.learningcenter.user.infrastructure.persistence.jpa.StudentRepository;
import pe.upc.learningcenter.user.interfaces.rest.resources.CreateStudentResource;

import java.util.Optional;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;
    public StudentCommandServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> handle(CreateStudentCommand command) {
        var student = new Student(command);
        studentRepository.save(student);
        return Optional.of(student);
    }

    @Override
    public Optional<Student> handle(UpdateStudentCommand command) {
        //if(courseRepository.existsByCourseNameAndIdIsNot(command.courseName(), command.id()))
        //            throw new IllegalArgumentException("Course with name " + command.courseName() + " already exists");
        //Esta condicion es opcional ya que puede darse el caso de que halla mas de un alumno con el mismo nombre y apellido
        var result = studentRepository.findById(command.id());
        if(result.isEmpty()) throw new IllegalArgumentException("Student with id " + command.id() + " does not exist");
        var studentToUpdate = result.get();
        try{
            var updateStudent = studentRepository.save(studentToUpdate.updateInformation(command.firstName(), command.lastName(), command.email()));
            return Optional.of(updateStudent);
        }catch(Exception e){
            return Optional.empty();
        }
    }
}
