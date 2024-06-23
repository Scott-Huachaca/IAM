package pe.upc.learningcenter.course.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.upc.learningcenter.course.domain.model.querys.GetAllCommentsByCourseIdQuery;
import pe.upc.learningcenter.course.domain.service.CommentQueryService;
import pe.upc.learningcenter.course.interfaces.rest.resources.CommentResource;
import pe.upc.learningcenter.course.interfaces.rest.transform.CommentResourceFromEntityAssembler;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses/{courseId}/comments")
@Tag(name = "Courses")
public class CourseCommentsController {
    private final CommentQueryService commentQueryService;

    public CourseCommentsController(CommentQueryService commentQueryService) {
        this.commentQueryService = commentQueryService;
    }

    @GetMapping
    public ResponseEntity<List<CommentResource>> getAllCommentsByCourseId(Long courseId) {
        var getAllCommentsByCourseIdQuery = new GetAllCommentsByCourseIdQuery(courseId);
        var comments = commentQueryService.handle(getAllCommentsByCourseIdQuery);
        var commentResources = comments.stream().map(CommentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(commentResources);
    }
}
