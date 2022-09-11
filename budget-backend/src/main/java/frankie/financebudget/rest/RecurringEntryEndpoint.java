package frankie.financebudget.rest;

import frankie.financebudget.entities.dto.RecurringEntryDto;
import frankie.financebudget.entities.mapper.RecurringEntryMapper;
import frankie.financebudget.entities.objects.RecurringEntry;
import frankie.financebudget.exceptions.NotFoundException;
import frankie.financebudget.exceptions.ValidationException;
import frankie.financebudget.service.RecurringEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

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
    public Stream<RecurringEntryDto> getAll() {
        return recurringEntryService.getAll().stream()
                .map(recurringEntryMapper::entityToDto);
    }

    @GetMapping("/{id}")
    public RecurringEntry getById(@PathVariable Long id) {
        try {
            return recurringEntryService.getById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/actives")
    public Stream<RecurringEntryDto> getAllActives() {
            return recurringEntryService.getAllActives().stream()
                    .map(recurringEntryMapper::entityToDto);
    }

    /*
    Example JSON
    {
        "amount": 100.0,
        "description": "yessir",
        "startingDate": "2022-01-15",
        "type": "freizeit",
        "timeSet": "day",
        "intervalRange": 3,
        "statusEnum": "active"
}
     */
    @PostMapping("")
    public RecurringEntry createRecurringEntry(@RequestBody RecurringEntryDto create) {
        try {
            RecurringEntry add = recurringEntryMapper.dtoToEntity(create);
            return recurringEntryService.createNewEntry(add);

        } catch (ValidationException e) {
            throw new ResponseStatusException(
                    HttpStatus.valueOf(recurringEntryService.getEntryInputValidator().getStatus()),
                    recurringEntryService.getEntryInputValidator().getMessage());
        }
    }

    @PatchMapping("")
    public RecurringEntry updateRecurringEntry(@RequestBody RecurringEntryDto update) {
        try {
            RecurringEntry toUpdate = recurringEntryMapper.dtoToEntity(update);
            return recurringEntryService.updateRecurringEntry(toUpdate);
        } catch (ValidationException e) {
            throw new ResponseStatusException(
                    HttpStatus.valueOf(recurringEntryService.getEntryInputValidator().getStatus()),
                    recurringEntryService.getEntryInputValidator().getMessage());
        }
    }
}
