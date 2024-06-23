package pe.upc.learningcenter.course.interfaces.rest.transform;

import pe.upc.learningcenter.course.domain.model.commands.UpdateCourseCommand;
import pe.upc.learningcenter.course.interfaces.rest.resources.UpdateCourseResource;

public class UpdateCourseCommandFromResourceAssembler {
    public static UpdateCourseCommand toCommandFromResource(Long courseId, UpdateCourseResource resource) {
        return new UpdateCourseCommand(
                courseId,
                resource.courseName(),
                resource.courseDescription(),
                resource.amount(),
                resource.currency());
    }
}
