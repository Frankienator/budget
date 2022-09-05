package frankie.financebudget.service.inputValidation;

import frankie.financebudget.entities.entities.objects.Entry;
import frankie.financebudget.persistence.EntryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class EntryInputValidator {

    private int descriptionLength = 256;
    private double maxAmount = Double.MAX_VALUE;
    private double minAmount = -Double.MAX_VALUE;
    private LocalDate today = LocalDate.now();
    private int status;
    private StringBuilder message;

    private final EntryDAO entryDAO;

    @Autowired
    public EntryInputValidator(EntryDAO entryDAO) {
        this.entryDAO = entryDAO;
    }

    public boolean createValidation(Entry toValidate) {
        this.status = 200;
        this.message = new StringBuilder("Message: \n");

        //check id
        if (toValidate.getId() != null) {
            setStatus(400);
            message.append("Id must not be defined!\n");
        }

        //check amount
        //check null
        if (toValidate.getAmount() == null) {
            setStatus(400);
            message.append("Amount must not be null!\n");
        }
        //check if amount is a number
        else if (toValidate.getAmount().isNaN()) {
            setStatus(409);
            message.append("Amount must be a defined number!");
        }
        else if (toValidate.getAmount() < minAmount || toValidate.getAmount() > maxAmount) {
            setStatus(409);
            message.append("Amount must be within " + minAmount + " - " + maxAmount + "!\n");
        }

        //check description
        //check null
        Pattern p = Pattern.compile("[ ]*");
        Matcher m = p.matcher(toValidate.getDescription());

        if (toValidate.getDescription() == null) {
            setStatus(400);
            message.append("Description must be given!\n");
        }
        //check if only whitespace
        else if (m.matches()) {
            setStatus(409);
            message.append("Description must not only contain Whitespaces!\n");
        }
        //check maxlength
        else if (toValidate.getDescription().length() > descriptionLength) {
            setStatus(409);
            message.append("Description is too Long! Max length is " + descriptionLength + " characters!\n");
        }

        //check dateCreated
        if (toValidate.getDateCreated() == null) {
            setStatus(400);
            message.append("DateCreated must be defined!\n");
        }
        else if (toValidate.getDateCreated().isAfter(LocalDate.now())) {
            setStatus(409);
            message.append("DateCreated must not be in future!");
        }

        //check EntryType
        if (toValidate.getType() == null) {
            setStatus(400);
            message.append("Type must be defined!\n");
        }

        if (this.status == 200) {
            return true;
        }

        return false;
    }

    public boolean updateValidation(Entry toValidate) {
        this.status = 200;
        this.message = new StringBuilder("Message: \n");

        //check id
        if (toValidate.getId() == null) {
            setStatus(400);
            message.append("Id must be defined!\n");
        }
        if (toValidate.getId() <= 0) {
            setStatus(409);
            message.append("Id must be greater than 0");
        }

        //check amount
        //check null
        if (toValidate.getAmount() == null) {
            setStatus(400);
            message.append("Amount must not be null!\n");
        }
        //check if amount is a number
        else if (toValidate.getAmount().isNaN()) {
            setStatus(409);
            message.append("Amount must be a defined number!");
        }
        else if (toValidate.getAmount() < minAmount || toValidate.getAmount() > maxAmount) {
            setStatus(409);
            message.append("Amount must be within " + minAmount + " - " + maxAmount + "!\n");
        }

        //check description
        //check null
        Pattern p = Pattern.compile("[ ]*");
        Matcher m = p.matcher(toValidate.getDescription());

        if (toValidate.getDescription() == null) {
            setStatus(400);
            message.append("Description must be given!\n");
        }
        //check if only whitespace
        else if (m.matches()) {
            setStatus(409);
            message.append("Description must not only contain Whitespaces!\n");
        }
        //check maxlength
        else if (toValidate.getDescription().length() > descriptionLength) {
            setStatus(409);
            message.append("Description is too Long! Max length is " + descriptionLength + " characters!\n");
        }

        //check dateCreated
        if (toValidate.getDateCreated() == null) {
            setStatus(400);
            message.append("DateCreated must be defined!\n");
        }
        else if (toValidate.getDateCreated().isAfter(LocalDate.now())) {
            setStatus(409);
            message.append("DateCreated must not be in future!");
        }

        //check EntryType
        if (toValidate.getType() == null) {
            setStatus(400);
            message.append("Type must be defined!\n");
        }

        if (this.status == 200) {
            return true;
        }

        return false;
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
