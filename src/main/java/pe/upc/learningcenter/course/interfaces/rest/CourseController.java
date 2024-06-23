package pe.upc.learningcenter.course.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.learningcenter.course.domain.model.commands.DeleteCourseCommand;
import pe.upc.learningcenter.course.domain.model.querys.GetAllCousesQuery;
import pe.upc.learningcenter.course.domain.model.querys.GetCourseByIdQuery;
import pe.upc.learningcenter.course.domain.service.CourseCommandService;
import pe.upc.learningcenter.course.domain.service.CourseQueryService;
import pe.upc.learningcenter.course.interfaces.rest.resources.CourseResource;
import pe.upc.learningcenter.course.interfaces.rest.resources.CreateCourseResource;
import pe.upc.learningcenter.course.interfaces.rest.resources.UpdateCourseResource;
import pe.upc.learningcenter.course.interfaces.rest.transform.CourseResourceFromEntityAssembler;
import pe.upc.learningcenter.course.interfaces.rest.transform.CreateCourseCommandFromResourceAssembler;
import pe.upc.learningcenter.course.interfaces.rest.transform.UpdateCourseCommandFromResourceAssembler;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/courses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "Course Management EndPoints")
public class CourseController {
    private final CourseQueryService courseQueryService;
    private final CourseCommandService courseCommandService;


    public CourseController(CourseQueryService courseQueryService, CourseCommandService courseCommandService) {
        this.courseQueryService = courseQueryService;
        this.courseCommandService = courseCommandService;
    }

    @GetMapping
    public ResponseEntity<List<CourseResource>> getAllCourses() {
        var getAllCoursesQuery = new GetAllCousesQuery();
        var courses = courseQueryService.handle(getAllCoursesQuery);
        var courseResources = courses.stream().map(CourseResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(courseResources);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResource> getCourseById(@PathVariable Long courseId) {
        var getCourseByIdQuery = new GetCourseByIdQuery(courseId);
        var course = courseQueryService.handle(getCourseByIdQuery);
        if (course.isEmpty()) return ResponseEntity.badRequest().build();
        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(course.get());
        return ResponseEntity.ok(courseResource);
    }

    @PostMapping
    public ResponseEntity<CourseResource> createCourse(@RequestBody CreateCourseResource resource) {
        var createCourseCommand = CreateCourseCommandFromResourceAssembler.toCommandFromResource(resource);
        var course = courseCommandService.handle(createCourseCommand);
        if (course.isEmpty()) return ResponseEntity.badRequest().build();
        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(course.get());

        return new ResponseEntity<CourseResource>(courseResource, HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResource> updateCourse(@PathVariable Long courseId, @RequestBody UpdateCourseResource updateCourseResource){
        var updateCourseCommand = UpdateCourseCommandFromResourceAssembler.toCommandFromResource(courseId, updateCourseResource);
        var updateCourse = courseCommandService.handle(updateCourseCommand);
        if(updateCourse.isEmpty()) return ResponseEntity.badRequest().build();
        var correctCourse = CourseResourceFromEntityAssembler.toResourceFromEntity(updateCourse.get());
        return ResponseEntity.ok(correctCourse);

        //Resource->comand
    }


    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        var deleteCourseCommand = new DeleteCourseCommand(courseId);
        courseCommandService.handle(deleteCourseCommand);
        return ResponseEntity.ok("Deleted course");
    }
}
