package frankie.financebudget.service.inputValidation;

import frankie.financebudget.entities.objects.Entry;
import frankie.financebudget.entities.objects.RecurringEntry;
import frankie.financebudget.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RecurringEntryInputValidator {

    private int status;
    private StringBuilder message;
    EntryInputValidator entryInputValidator;

    public RecurringEntryInputValidator(EntryInputValidator entryInputValidator) {
        this.entryInputValidator = entryInputValidator;
    }


    public boolean createValidation(RecurringEntry toValidate) {
        this.status = 200;
        this.message = new StringBuilder("Message: ");


        //Check rId
        if (toValidate.getrId() != null) {
            setStatus(400);
            message.append("Id not must be defined! ");
        }


        //Check Recurring Interval
        if (toValidate.getRecurringInterval().getIntervalRange() < 1) {
            setStatus(409);
            message.append("Interval Range of Recurring Interval must be greater 0! ");
        }
        if (toValidate.getRecurringInterval().getTimeSetter() == null) {
            setStatus(409);
            message.append("Time Setter must be defined! ");
        }

        if (toValidate.getStartingDate() == null) {
            setStatus(400);
            message.append("Starting Date must be defined! ");
        }


        //Check underlying Entry
        Entry fromRecurring = new Entry(
                toValidate.getAmount(),
                toValidate.getDescription(),
                LocalDate.now(),
                toValidate.getType());

        try {
            entryInputValidator.createValidation(fromRecurring);
        } catch (ValidationException e) {
            setStatus(entryInputValidator.getStatus());
            message.append("Entry-Message:" + entryInputValidator.getMessage());
        }

        //check status
        if (toValidate.getStatusEnum() == null) {
            setStatus(400);
            message.append("Status must be defined! ");
        }

        if (status != 200) {
            throw new ValidationException();
        }

        return true;
    }

    public boolean updateValidation(RecurringEntry toValidate) {
        this.status = 200;
        this.message = new StringBuilder("Message: ");

        //check id
        if (toValidate.getrId() == null) {
            setStatus(400);
            message.append("Id must be defined! ");
        }
        else if (toValidate.getrId() <= 0) {
            setStatus(409);
            message.append("Id must be greater than 0 ");
        }

        //Check Recurring Interval
        if (toValidate.getRecurringInterval().getIntervalRange() < 1) {
            setStatus(409);
            message.append("Interval Range of Recurring Interval must be greater 0! ");
        }
        if (toValidate.getRecurringInterval().getTimeSetter() == null) {
            setStatus(409);
            message.append("Time Setter must be defined! ");
        }

        if (toValidate.getStartingDate() == null) {
            setStatus(400);
            message.append("Starting Date must be defined! ");
        }

        //Check underlying Entry
        Entry fromRecurring = new Entry(
                null,
                toValidate.getAmount(),
                toValidate.getDescription(),
                LocalDate.now(), //Date doesn't matter here, separate checking
                toValidate.getType());

        try {
            entryInputValidator.createValidation(fromRecurring);
        } catch (ValidationException e) {
            setStatus(entryInputValidator.getStatus());
            message.append("Entry-Message:" + entryInputValidator.getMessage());
        }

        //check EntryType
        if (toValidate.getStatusEnum() == null) {
            setStatus(400);
            message.append("Status must be defined!\n");
        }

        if (status != 200) {
            throw new ValidationException();
        }

        return true;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message.toString();
    }

    private void setStatus(int status) {
        if (this.status == 400 || this.status == 409) {
            return;
        }
        this.status = status;
    }
}
