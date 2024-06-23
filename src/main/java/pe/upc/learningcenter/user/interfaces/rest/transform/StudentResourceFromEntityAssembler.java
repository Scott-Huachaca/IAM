package pe.upc.learningcenter.user.interfaces.rest.transform;

import pe.upc.learningcenter.user.domain.model.aggregates.Student;
import pe.upc.learningcenter.user.interfaces.rest.resources.StudentResource;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student entity) {
        return new StudentResource(entity.getId(), entity.getFullName(), entity.getEmail());
    }
}
