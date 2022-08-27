package frankie.financebudget.rest;

import frankie.financebudget.entities.entities.dto.EntryDto;
import frankie.financebudget.entities.entities.mapper.EntryMapper;
import frankie.financebudget.entities.entities.objects.Entry;
import frankie.financebudget.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

@RestController
@RequestMapping(EntryEndpoint.ENTRY_URL)
public class EntryEndpoint {

    static final String ENTRY_URL = "rest/api/1.0/entry";
    private final EntryService entryService;
    private final EntryMapper mapper;

    public EntryEndpoint(EntryService entryService, EntryMapper mapper) {
        this.entryService = entryService;
        this.mapper = mapper;
    }
    @GetMapping("")
    public Stream<EntryDto> getAllEntries() {
        return entryService.getAllEntries().stream()
                .map(mapper::entityToDto);
    }

    @GetMapping("/{id}")
    public EntryDto getById(@PathVariable Long id) {
        try {
            return mapper.entityToDto(entryService.getById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public EntryDto createEntry(@RequestBody EntryDto create) {
        try {

            Entry add = mapper.dtoToEntity(create);
            return mapper.entityToDto(entryService.createEntry(add));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }


    @PutMapping("/update")
    public EntryDto updateEntry(@RequestBody EntryDto update) {
        try {

            Entry toUpdate = mapper.dtoToEntity(update);
            return mapper.entityToDto(entryService.updateEntry(toUpdate));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }
}
