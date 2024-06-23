package pe.upc.learningcenter.course.interfaces.rest.transform;

import pe.upc.learningcenter.course.domain.model.commands.CreateCommentCommand;
import pe.upc.learningcenter.course.interfaces.rest.resources.CreateCommentResource;

public class CreateCommentCommandFromResourceAssembler {
    public static CreateCommentCommand toCommandFromResource(CreateCommentResource resource){
        return new CreateCommentCommand(
                resource.courseId(),
                resource.studentId(),
                resource.comment());
    }
}
