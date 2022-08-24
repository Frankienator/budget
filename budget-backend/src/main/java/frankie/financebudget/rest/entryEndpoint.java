package frankie.financebudget.rest;

import frankie.financebudget.entities.objects.Entry;
import frankie.financebudget.service.EntryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
