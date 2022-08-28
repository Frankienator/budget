package frankie.financebudget.entities.entities.mapper;

import frankie.financebudget.entities.entities.dto.MonthYearDto;

import java.time.LocalDate;

public class MonthYearMapper {

    public LocalDate mapDtoToDate(MonthYearDto monthYearDto) {
        return LocalDate.of(monthYearDto.year(), monthYearDto.month(), 1);
    }
}
