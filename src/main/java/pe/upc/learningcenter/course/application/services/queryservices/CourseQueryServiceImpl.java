package pe.upc.learningcenter.course.application.services.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.course.domain.model.aggregates.Course;
import pe.upc.learningcenter.course.domain.model.querys.GetAllCousesQuery;
import pe.upc.learningcenter.course.domain.model.querys.GetCourseByIdQuery;
import pe.upc.learningcenter.course.domain.model.querys.GetCourseByNameQuery;
import pe.upc.learningcenter.course.domain.service.CourseQueryService;
import pe.upc.learningcenter.course.infrastructure.persistence.jpa.CourseRepository;

import java.util.List;
import java.util.Optional;
@Service
public class CourseQueryServiceImpl implements CourseQueryService {

    public CourseQueryServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    private final CourseRepository courseRepository;


    @Override
    public Optional<Course> handle(GetCourseByIdQuery query) {
        return courseRepository.findById(query.id());
    }

    @Override
    public Optional<Course> handle(GetCourseByNameQuery query) { return courseRepository.findByCourseName(query.courseName()); }

    @Override
    public List<Course> handle(GetAllCousesQuery query) { return courseRepository.findAll(); }

}
