package pe.upc.learningcenter.course.interfaces.rest.transform;

import pe.upc.learningcenter.course.domain.model.aggregates.Course;
import pe.upc.learningcenter.course.interfaces.rest.resources.CourseResource;

public class CourseResourceFromEntityAssembler {
    public static CourseResource toResourceFromEntity(Course entity) {
        return new CourseResource(entity.getId(),entity.getCourseName(),entity.getCourseDescription(),entity.getPrice());
    }
}
