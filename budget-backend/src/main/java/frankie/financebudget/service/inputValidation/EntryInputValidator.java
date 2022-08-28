package frankie.financebudget.service.inputValidation;

import frankie.financebudget.entities.enumerations.EntryType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;


@Component
public class EntryInputValidator {

    private int descriptionLength;
    private double maxAmount;
    private double minAmount;
    private LocalDate today;
    private int status;
    private String message;

    public EntryInputValidator() {
        this.descriptionLength = 256;
        this.maxAmount = 10000000.0;
        this.minAmount = -10000000.0;
        this.today = LocalDate.now();
        this.status = -1;
        this.message = "";
    }

    public Optional<String> descriptionValidator(String toValidate) {
        return null;
    }

    public Optional<String> amountValidator(double toValidate) {
        return null;
    }

    public Optional<String> dateValidator(LocalDate today) {
        return null;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
