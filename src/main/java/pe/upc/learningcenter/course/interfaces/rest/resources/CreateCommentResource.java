package pe.upc.learningcenter.course.interfaces.rest.resources;

public record CreateCommentResource(Long courseId, Long studentId, String comment) {
}
