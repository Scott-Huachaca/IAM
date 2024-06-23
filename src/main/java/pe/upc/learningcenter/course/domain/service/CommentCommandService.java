package pe.upc.learningcenter.course.domain.service;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.course.domain.model.aggregates.Comment;
import pe.upc.learningcenter.course.domain.model.commands.CreateCommentCommand;

import java.util.Optional;

@Service
public interface CommentCommandService {
    Optional<Comment> handle(CreateCommentCommand command);
}
