package frankie.financebudget.rest;

import frankie.financebudget.entities.entities.mapper.RecurringEntryMapper;
import frankie.financebudget.service.RecurringEntryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RecurringEntryEndpoint.RECURRING_URL)
public class RecurringEntryEndpoint {

    static final String RECURRING_URL = "rest/api/1.0/recurring";

    private final RecurringEntryService recurringEntryService;
    private final RecurringEntryMapper recurringEntryMapper;

    public RecurringEntryEndpoint(RecurringEntryService recurringEntryService, RecurringEntryMapper recurringEntryMapper) {
        this.recurringEntryService = recurringEntryService;
        this.recurringEntryMapper = recurringEntryMapper;
    }
}
