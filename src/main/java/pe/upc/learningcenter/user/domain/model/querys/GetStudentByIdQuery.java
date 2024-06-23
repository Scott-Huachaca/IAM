package pe.upc.learningcenter.user.domain.model.querys;

public record GetStudentByIdQuery(Long id) {
    public GetStudentByIdQuery {
        if (id < 0) throw new IllegalArgumentException("Id cannot be negative");
        if (id == 0) throw new IllegalArgumentException("Id cannot be zero");
    }
}
