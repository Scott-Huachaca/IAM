package pe.upc.learningcenter.course.domain.model.commands;

public record CreateCommentCommand(Long courseId, Long studentId, String comment) {
}
