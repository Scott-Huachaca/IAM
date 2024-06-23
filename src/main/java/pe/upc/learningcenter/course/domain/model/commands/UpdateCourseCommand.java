package pe.upc.learningcenter.course.domain.model.commands;

public record UpdateCourseCommand(Long id, String courseName, String courseDescription, String amount, String currency) {
}
