package pe.upc.learningcenter.user.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FullName(String firstName, String lastName) {
    public FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {return String.format("%s %s", firstName, lastName); }
}
