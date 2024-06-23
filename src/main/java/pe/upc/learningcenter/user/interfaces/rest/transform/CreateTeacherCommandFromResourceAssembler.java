package pe.upc.learningcenter.user.interfaces.rest.transform;

import pe.upc.learningcenter.user.domain.model.commands.CreateTeacherCommand;
import pe.upc.learningcenter.user.interfaces.rest.resources.CreateTeacherResource;

public class CreateTeacherCommandFromResourceAssembler {
    public static CreateTeacherCommand toCommandFromResource(CreateTeacherResource resource) {
        return new CreateTeacherCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email()
        );
    }
}
