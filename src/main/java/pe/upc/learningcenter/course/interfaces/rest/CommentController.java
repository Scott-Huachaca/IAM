package pe.upc.learningcenter.course.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.upc.learningcenter.course.domain.service.CommentCommandService;
import pe.upc.learningcenter.course.interfaces.rest.resources.CommentResource;
import pe.upc.learningcenter.course.interfaces.rest.resources.CreateCommentResource;
import pe.upc.learningcenter.course.interfaces.rest.transform.CommentResourceFromEntityAssembler;
import pe.upc.learningcenter.course.interfaces.rest.transform.CreateCommentCommandFromResourceAssembler;

@RestController
@RequestMapping(value = "/api/v1/comments")
@Tag(name="Comments", description = "Comments Management Endpoints")
public class CommentController {
    private final CommentCommandService commentCommandService;

    public CommentController (CommentCommandService commentCommandService){
        this.commentCommandService = commentCommandService;
    }

    @PostMapping
    public ResponseEntity<CommentResource> requestComment(@RequestBody CreateCommentResource resource){
        var CreateCommentCommand = CreateCommentCommandFromResourceAssembler.toCommandFromResource(resource);
        var comment = commentCommandService.handle(CreateCommentCommand);
        if(comment.isEmpty()) return ResponseEntity.badRequest().build();
        var commentResource = CommentResourceFromEntityAssembler.toResourceFromEntity(comment.get());
        return new ResponseEntity<CommentResource>(commentResource, HttpStatus.CREATED);
    }

}
