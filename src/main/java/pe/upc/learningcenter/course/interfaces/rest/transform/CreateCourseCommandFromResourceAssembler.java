package pe.upc.learningcenter.course.interfaces.rest.transform;

import pe.upc.learningcenter.course.domain.model.commands.CreateCourseCommand;
import pe.upc.learningcenter.course.interfaces.rest.resources.CreateCourseResource;

public class CreateCourseCommandFromResourceAssembler {
    public static CreateCourseCommand toCommandFromResource(CreateCourseResource resource) {
        return new CreateCourseCommand(
                resource.courseName(),
                resource.courseDescription(),
                resource.amount(),
                resource.currency()
        );
    }
}
