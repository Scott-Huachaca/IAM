package pe.upc.learningcenter.course.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import pe.upc.learningcenter.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import pe.upc.learningcenter.user.domain.model.aggregates.Student;

@Entity
@Getter
public class Comment extends AuditableAbstractAggregateRoot<Comment> {

    private String comment;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Getter
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Comment(){}

    public Comment(Course course, Student student, String comment){
        this.course = course;
        this.student = student;
        this.comment = comment;
    }

}
