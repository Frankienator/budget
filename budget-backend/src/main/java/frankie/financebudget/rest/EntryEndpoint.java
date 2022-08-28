package frankie.financebudget.rest;

import frankie.financebudget.entities.entities.dto.CompressedEntriesDto;
import frankie.financebudget.entities.entities.dto.EntryDto;
import frankie.financebudget.entities.entities.dto.MonthYearDto;
import frankie.financebudget.entities.entities.mapper.CompressedEntriesMapper;
import frankie.financebudget.entities.entities.mapper.EntryMapper;
import frankie.financebudget.entities.entities.mapper.MonthYearMapper;
import frankie.financebudget.entities.entities.objects.Entry;
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
    private final MonthYearMapper monthYearMapper;
    private final CompressedEntriesMapper compressedEntriesMapper;

    public EntryEndpoint(EntryService entryService, EntryMapper entryMapper, MonthYearMapper monthYearMapper, CompressedEntriesMapper compressedEntriesMapper) {
        this.entryService = entryService;
        this.entryMapper = entryMapper;
        this.monthYearMapper = monthYearMapper;
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
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byMonth")
    public CompressedEntriesDto getByMonth(@RequestParam(name = "year") String yearValue,@RequestParam(name = "month") String monthValue) {
        try {

            LocalDate monthYear = LocalDate.of(Integer.valueOf(yearValue), Integer.valueOf(monthValue), 1);
            return compressedEntriesMapper.entityToDto(entryService.getByMonth(monthYear));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }

    @PostMapping("/create")
    public EntryDto createEntry(@RequestBody EntryDto create) {
        try {

            Entry add = entryMapper.dtoToEntity(create);
            return entryMapper.entityToDto(entryService.createEntry(add));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }


    @PutMapping("/{id}/update")
    public EntryDto updateEntry(@RequestBody EntryDto update) {
        try {

            Entry toUpdate = entryMapper.dtoToEntity(update);
            return entryMapper.entityToDto(entryService.updateEntry(toUpdate));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Long deleteEntry(@PathVariable Long id) {
        try {

            return entryService.deleteEntry(id);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage());
        }
    }
}
