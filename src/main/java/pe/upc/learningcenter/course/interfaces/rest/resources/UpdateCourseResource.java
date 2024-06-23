package pe.upc.learningcenter.course.interfaces.rest.resources;

public record UpdateCourseResource(String courseName, String courseDescription, String amount, String currency) {
}
