package pe.upc.learningcenter.user.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.learningcenter.user.domain.model.commands.UpdateStudentCommand;
import pe.upc.learningcenter.user.domain.model.querys.GetAllStudentsQuery;
import pe.upc.learningcenter.user.domain.model.querys.GetStudentByIdQuery;
import pe.upc.learningcenter.user.domain.service.StudentCommandService;
import pe.upc.learningcenter.user.domain.service.StudentQueryService;
import pe.upc.learningcenter.user.interfaces.rest.resources.CreateStudentResource;
import pe.upc.learningcenter.user.interfaces.rest.resources.StudentResource;
import pe.upc.learningcenter.user.interfaces.rest.transform.CreateStudentCommandFromResourceAssembler;
import pe.upc.learningcenter.user.interfaces.rest.transform.StudentResourceFromEntityAssembler;
import pe.upc.learningcenter.user.interfaces.rest.transform.UpdateStudentCommandFromResourceAssembler;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Student", description = "Student Management EndPoints")
public class StudentController {
    private final StudentQueryService studentQueryService;
    private final StudentCommandService studentCommandService;

    public StudentController(StudentQueryService studentQueryService, StudentCommandService studentCommandService) {
        this.studentQueryService = studentQueryService;
        this.studentCommandService = studentCommandService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResource>> getAllStudents() {
        var getAllStudentsQuery = new GetAllStudentsQuery();
        var students = studentQueryService.handle(getAllStudentsQuery);
        var studentResources = students.stream().map(StudentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(studentResources);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResource> getStudentById(@PathVariable Long studentId) {
        var getStudentByIdQuery = new GetStudentByIdQuery(studentId);
        var student = studentQueryService.handle(getStudentByIdQuery);
        if(student.isEmpty()) return ResponseEntity.notFound().build();
        var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
        return ResponseEntity.ok(studentResource);
    }

    @PostMapping
    public ResponseEntity<StudentResource> createStudent(@RequestBody CreateStudentResource resource) {
        var createStudentCommand = CreateStudentCommandFromResourceAssembler.toCommandFromResource(resource);
        var student = studentCommandService.handle(createStudentCommand);
        if(student.isEmpty()) return ResponseEntity.notFound().build();
        var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());
        return new ResponseEntity<StudentResource>(studentResource, HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResource> updateStudent(@PathVariable Long studentId, @RequestBody UpdateStudentCommand updateStudentResource) {
        var updateStudentCommand = UpdateStudentCommandFromResourceAssembler.toCommandFromResource(studentId, updateStudentResource);
        var updateStudent = studentCommandService.handle(updateStudentCommand);
        if(updateStudent.isEmpty()) return ResponseEntity.notFound().build();
        var correctStudent = StudentResourceFromEntityAssembler.toResourceFromEntity(updateStudent.get());
        return ResponseEntity.ok(correctStudent);
    }

}
