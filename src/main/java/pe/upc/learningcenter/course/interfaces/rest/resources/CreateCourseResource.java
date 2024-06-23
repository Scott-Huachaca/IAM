package pe.upc.learningcenter.course.interfaces.rest.resources;

public record CreateCourseResource(String courseName,
                                   String courseDescription,
                                   String amount,
                                   String currency) {
}
