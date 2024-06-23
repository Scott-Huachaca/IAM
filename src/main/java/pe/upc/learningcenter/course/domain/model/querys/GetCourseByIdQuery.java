package pe.upc.learningcenter.course.domain.model.querys;

public record GetCourseByIdQuery(Long id) {
    public GetCourseByIdQuery {
        if (id < 0) throw new IllegalArgumentException("Id cannot be negative");
        if (id == 0) throw new IllegalArgumentException("Id cannot be zero");
    }
}
