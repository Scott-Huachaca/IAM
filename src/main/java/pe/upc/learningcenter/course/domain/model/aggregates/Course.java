package pe.upc.learningcenter.course.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.upc.learningcenter.course.domain.model.commands.CreateCourseCommand;
import pe.upc.learningcenter.course.domain.model.valueobjects.Price;
import pe.upc.learningcenter.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;

@Entity
@Getter
@NoArgsConstructor
public class Course extends AuditableAbstractAggregateRoot<Course> {

    @NotBlank
    private String courseName;
    @NotBlank
    private String courseDescription;
    //    @Embedded
//    CourseInfo info;
    //Teacher fk
    //private String teacher;
    @Embedded
    Price price;

    public Course(CreateCourseCommand command){
        this.courseName = command.courseName();
        this.courseDescription = command.courseDescription();
        this.price = new Price(command.currency(),command.amount());
    }

    public Course updateInformation(String courseName, String courseDescription, String currency, String amount){
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.price = new Price(currency,amount);
        return this;
    }

    public String getCourseName() { return courseName; }
    public String getCourseDescription() { return courseDescription; }

    public String getPrice() {
        return price.getPrice();
    }
}
