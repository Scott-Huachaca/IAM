package pe.upc.learningcenter.user.interfaces.rest.transform;

import pe.upc.learningcenter.user.domain.model.commands.UpdateStudentCommand;
import pe.upc.learningcenter.user.interfaces.rest.resources.StudentResource;

public class UpdateStudentCommandFromResourceAssembler {
    public static UpdateStudentCommand toCommandFromResource(Long studentId, UpdateStudentCommand resource) {
        return new UpdateStudentCommand(
                studentId,
                resource.firstName(),
                resource.lastName(),
                resource.email()
        );
    }
}
