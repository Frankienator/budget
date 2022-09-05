package frankie.financebudget.rest;

import frankie.financebudget.entities.dto.CompressedEntriesDto;
import frankie.financebudget.entities.dto.EntryDto;
import frankie.financebudget.entities.mapper.CompressedEntriesMapper;
import frankie.financebudget.entities.mapper.EntryMapper;
import frankie.financebudget.entities.objects.Entry;
import frankie.financebudget.exceptions.NotFoundException;
import frankie.financebudget.exceptions.ValidationException;
import frankie.financebudget.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.stream.Stream;

@RestController
@RequestMapping(EntryEndpoint.ENTRY_URL)
public class EntryEndpoint {
    static final String ENTRY_URL = "rest/api/1.0/entry";
    private final EntryService entryService;
    private final EntryMapper entryMapper;

    private final CompressedEntriesMapper compressedEntriesMapper;

    public EntryEndpoint(EntryService entryService, EntryMapper entryMapper, CompressedEntriesMapper compressedEntriesMapper) {
        this.entryService = entryService;
        this.entryMapper = entryMapper;
        this.compressedEntriesMapper = compressedEntriesMapper;
    }

    @GetMapping("")
    public Stream<EntryDto> getAllEntries() {
        return entryService.getAllEntries().stream()
                .map(entryMapper::entityToDto);
    }

    @GetMapping("/{id}")
    public EntryDto getById(@PathVariable Long id) {
        try {
            return entryMapper.entityToDto(entryService.getById(id));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/byMonth")
    public CompressedEntriesDto getByMonth(@RequestParam(name = "year") String yearValue,@RequestParam(name = "month") String monthValue) {
            LocalDate monthYear = LocalDate.of(Integer.valueOf(yearValue), Integer.valueOf(monthValue), 1);
            return compressedEntriesMapper.entityToDto(entryService.getByMonth(monthYear));
    }

    @PostMapping("/create")
    public EntryDto createEntry(@RequestBody EntryDto create) {
        try {

            Entry add = entryMapper.dtoToEntity(create);
            return entryMapper.entityToDto(entryService.createEntry(add));

        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(entryService.getEntryInputValidator().getStatus()), e.getMessage());
        }
    }


    @PutMapping("/{id}/update")
    public EntryDto updateEntry(@RequestBody EntryDto update) {
        try {

            Entry toUpdate = entryMapper.dtoToEntity(update);
            return entryMapper.entityToDto(entryService.updateEntry(toUpdate));

        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(entryService.getEntryInputValidator().getStatus()), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Long deleteEntry(@PathVariable Long id) {
        try {

            return entryService.deleteEntry(id);

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
