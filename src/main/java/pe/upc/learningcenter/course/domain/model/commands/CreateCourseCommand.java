package pe.upc.learningcenter.course.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateCourseCommand(
        @NotBlank String courseName,
        @NotBlank String courseDescription,
        @NotBlank String amount,
        @NotBlank String currency) {
}
