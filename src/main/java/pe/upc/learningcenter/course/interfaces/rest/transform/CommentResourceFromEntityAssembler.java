package pe.upc.learningcenter.course.interfaces.rest.transform;

import pe.upc.learningcenter.course.domain.model.aggregates.Comment;
import pe.upc.learningcenter.course.interfaces.rest.resources.CommentResource;

public class CommentResourceFromEntityAssembler {
    public static CommentResource toResourceFromEntity(Comment entity){
        return new CommentResource(entity.getId(),entity.getCourse().getId(),entity.getStudent().getId(), entity.getComment());
    }
}
