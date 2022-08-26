package frankie.financebudget.rest;

import frankie.financebudget.entities.objects.Entry;
import frankie.financebudget.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(entryEndpoint.ENTRY_URL)
public class entryEndpoint {

    static final String ENTRY_URL = "/entry";
    private final EntryService entryService;

    public entryEndpoint(EntryService entryService) {
        this.entryService = entryService;
    }
    @GetMapping("/all")
    public Stream<Entry> getAllEntries() {
        return entryService.getAllEntries().stream();
    }

    @GetMapping("/all/{id}")
    public Entry getById(@PathVariable Long id) {
        try {
            return entryService.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public Entry createEntry(@RequestBody Entry create) {
        try {
            Entry add = new Entry(
                    create.getAmount(),
                    create.getDescription(),
                    create.getDateCreated(),
                    create.getType());

            return entryService.createEntry(add);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }
}
