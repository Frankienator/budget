package frankie.financebudget.rest;

import frankie.financebudget.entities.entities.dto.RecurringEntryDto;
import frankie.financebudget.entities.entities.mapper.RecurringEntryMapper;
import frankie.financebudget.entities.entities.objects.RecurringEntry;
import frankie.financebudget.service.RecurringEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @GetMapping("")
    public List<RecurringEntry> getAll() {
        try {
            return recurringEntryService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public RecurringEntry getById(@PathVariable Long id) {
        try {
            return recurringEntryService.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }

    @GetMapping("/actives")
    public List<RecurringEntry> getAllActives() {
        try {
            return recurringEntryService.getAllActives();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }

    @PostMapping("/create")
    public RecurringEntry createRecurringEntry(@RequestBody RecurringEntryDto create) {
        try {
            return recurringEntryService.createNewEntry(recurringEntryMapper.dtoToEntity(create));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }

    @PutMapping("/{id}/update")
    public RecurringEntry updateRecurringEntry(@RequestBody RecurringEntryDto update) {
        try {
            return recurringEntryService.updateRecurringEntry(recurringEntryMapper.dtoToEntity(update));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }
}
