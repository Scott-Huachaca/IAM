package pe.upc.learningcenter.user.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.learningcenter.user.domain.model.querys.GetAllTeachersQuery;
import pe.upc.learningcenter.user.domain.service.TeacherCommandService;
import pe.upc.learningcenter.user.domain.service.TeacherQueryService;
import pe.upc.learningcenter.user.interfaces.rest.resources.CreateTeacherResource;
import pe.upc.learningcenter.user.interfaces.rest.resources.StudentResource;
import pe.upc.learningcenter.user.interfaces.rest.resources.TeacherResource;
import pe.upc.learningcenter.user.interfaces.rest.transform.CreateTeacherCommandFromResourceAssembler;
import pe.upc.learningcenter.user.interfaces.rest.transform.TeacherResourceFromEntityAssembler;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Teacher", description = "Teacher Management EndPoints")
public class TeacherController {
    private final TeacherQueryService teacherQueryService;
    private final TeacherCommandService teacherCommandService;


    public TeacherController(TeacherQueryService teacherQueryService, TeacherCommandService teacherCommandService) {
        this.teacherQueryService = teacherQueryService;
        this.teacherCommandService = teacherCommandService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherResource>> getAllTeachers() {
        var getAllTeachersQuery = new GetAllTeachersQuery();
        var teachers = teacherQueryService.handle(getAllTeachersQuery);
        var teacherResources = teachers.stream().map(TeacherResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(teacherResources);
    }

    @PostMapping
    public ResponseEntity<TeacherResource> createTeacher(@RequestBody CreateTeacherResource resource) {
        var createTeacherCommand = CreateTeacherCommandFromResourceAssembler.toCommandFromResource(resource);
        var teacher = teacherCommandService.handle(createTeacherCommand);
        if(teacher.isEmpty()) return ResponseEntity.notFound().build();
        var teacherResource = TeacherResourceFromEntityAssembler.toResourceFromEntity(teacher.get());
        return new ResponseEntity<TeacherResource>(teacherResource, HttpStatus.CREATED);
    }
}
