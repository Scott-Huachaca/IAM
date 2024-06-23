package pe.upc.learningcenter.course.interfaces.rest.resources;

public record CourseResource(Long id,
                             String courseName ,
                             String courseDescription,
                             String price) {
}
