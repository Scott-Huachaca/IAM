package pe.upc.learningcenter.course.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
@Embeddable
public record Price(
        @NotBlank String currency,
        @NotBlank String amount
) {
    public Price(String currency, String amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getPrice(){
        return String.format("%s /%s", currency, amount);
    }
}
