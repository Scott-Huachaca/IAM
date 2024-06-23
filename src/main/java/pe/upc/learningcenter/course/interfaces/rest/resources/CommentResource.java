package pe.upc.learningcenter.course.interfaces.rest.resources;

public record CommentResource(Long commentId, Long courseId, Long studentId, String comment) {
}
