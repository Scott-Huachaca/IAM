package pe.upc.learningcenter.user.domain.model.commands;

public record UpdateStudentCommand(Long id, String firstName, String lastName, String email) {
}
