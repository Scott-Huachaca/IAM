package pe.upc.learningcenter.course.domain.service;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.course.domain.model.aggregates.Course;
import pe.upc.learningcenter.course.domain.model.querys.GetAllCousesQuery;
import pe.upc.learningcenter.course.domain.model.querys.GetCourseByIdQuery;
import pe.upc.learningcenter.course.domain.model.querys.GetCourseByNameQuery;

import java.util.List;
import java.util.Optional;
@Service
public interface CourseQueryService {
    Optional<Course> handle(GetCourseByIdQuery query);
    Optional<Course> handle(GetCourseByNameQuery query);
    List<Course> handle(GetAllCousesQuery query);
}
