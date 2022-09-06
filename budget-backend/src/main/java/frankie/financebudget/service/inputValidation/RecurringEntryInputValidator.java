package frankie.financebudget.service.inputValidation;

import frankie.financebudget.entities.objects.Entry;
import frankie.financebudget.entities.objects.RecurringEntry;
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
        this.message = new StringBuilder("Message: \n");


        //Check rId
        if (toValidate.getrId() == null) {
            setStatus(400);
            message.append("Id must be defined!\n");
        }


        //Check Recurring Interval
        if (toValidate.getRecurringInterval().getIntervalRange() < 1) {
            setStatus(409);
            message.append("Interval Range of Recurring Interval must be greater 0!\n");
        }
        if (toValidate.getRecurringInterval().getTimeSetter() == null) {
            setStatus(409);
            message.append("Time Setter must be defined!\n");
        }


        //Check underlying Entry
        Entry fromRecurring = new Entry(
                toValidate.getAmount(),
                toValidate.getDescription(),
                LocalDate.now(),
                toValidate.getType());

        entryInputValidator.createValidation(fromRecurring);

        if (entryInputValidator.getStatus() != 200) {
            setStatus(entryInputValidator.getStatus());
            message.append("Entry-Message:\n" + entryInputValidator.getMessage());
        }

        //check EntryType
        if (toValidate.getStatusEnum() == null) {
            setStatus(400);
            message.append("Status must be defined!\n");
        }

        if (status != 200) {
            return false;
        }

        return true;
    }

    public boolean updateValidation(RecurringEntry toValidate) {
        this.status = 200;
        this.message = new StringBuilder("Message: \n");

        //check id
        if (toValidate.getrId() == null) {
            setStatus(400);
            message.append("Id must be defined!\n");
        }
        if (toValidate.getrId() <= 0) {
            setStatus(409);
            message.append("Id must be greater than 0");
        }

        //Check Recurring Interval
        if (toValidate.getRecurringInterval().getIntervalRange() < 1) {
            setStatus(409);
            message.append("Interval Range of Recurring Interval must be greater 0!\n");
        }
        if (toValidate.getRecurringInterval().getTimeSetter() == null) {
            setStatus(409);
            message.append("Time Setter must be defined!\n");
        }

        //Check underlying Entry
        Entry fromRecurring = new Entry(
                1L,
                toValidate.getAmount(),
                toValidate.getDescription(),
                LocalDate.now(),
                toValidate.getType());

        entryInputValidator.updateValidation(fromRecurring);

        if (entryInputValidator.getStatus() != 200) {
            setStatus(entryInputValidator.getStatus());
            message.append("Entry-Message:\n" + entryInputValidator.getMessage());
        }

        //check EntryType
        if (toValidate.getStatusEnum() == null) {
            setStatus(400);
            message.append("Status must be defined!\n");
        }

        if (status != 200) {
            return false;
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
