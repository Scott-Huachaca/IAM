package pe.upc.learningcenter.user.interfaces.rest.resources;

public record CreateStudentResource(String firstName,
                                    String lastName,
                                    String email) {
}
