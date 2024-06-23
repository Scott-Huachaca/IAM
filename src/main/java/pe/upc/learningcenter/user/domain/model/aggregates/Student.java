package pe.upc.learningcenter.user.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.upc.learningcenter.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import pe.upc.learningcenter.user.domain.model.commands.CreateStudentCommand;
import pe.upc.learningcenter.user.domain.model.valueobjects.FullName;
@Entity
@Getter
@NoArgsConstructor
public class Student extends AuditableAbstractAggregateRoot<Student> {
    @Embedded
    FullName fullName;

    @NotBlank
    private String email;

    public String getFullName() { return fullName.getFullName(); }
    public String getEmail() { return email; }

    public Student(CreateStudentCommand command){
        this.fullName = new FullName(command.firstName(), command.lastName());
        this.email = command.email();
    }

    public Student updateInformation(String firstName, String lastName, String email) {
        this.fullName = new FullName(firstName, lastName);
        this.email = email;
        return this;
    }
}
