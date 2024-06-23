package pe.upc.learningcenter.course.application.services.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.course.domain.model.aggregates.Comment;
import pe.upc.learningcenter.course.domain.model.querys.GetAllCommentsByCourseIdQuery;
import pe.upc.learningcenter.course.domain.service.CommentQueryService;
import pe.upc.learningcenter.course.infrastructure.persistence.jpa.CommentRepository;

import java.util.List;

@Service
public class CommentQueryServiceImpl implements CommentQueryService {
    private final CommentRepository commentRepository;

    public CommentQueryServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> handle(GetAllCommentsByCourseIdQuery query) {
        return commentRepository.findAllByCourseId(query.courseId());
    }
}
