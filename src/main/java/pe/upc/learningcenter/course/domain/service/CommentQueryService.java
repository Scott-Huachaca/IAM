package pe.upc.learningcenter.course.domain.service;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.course.domain.model.aggregates.Comment;
import pe.upc.learningcenter.course.domain.model.querys.GetAllCommentsByCourseIdQuery;

import java.util.List;
@Service
public interface CommentQueryService {
    List<Comment> handle(GetAllCommentsByCourseIdQuery query);
}
