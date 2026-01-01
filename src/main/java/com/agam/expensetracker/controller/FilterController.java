package com.agam.expensetracker.controller;

import com.agam.expensetracker.dto.ExpenseDTO;
import com.agam.expensetracker.dto.FilterDTO;
import com.agam.expensetracker.dto.IncomeDTO;
import com.agam.expensetracker.service.ExpenseService;
import com.agam.expensetracker.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filter")
public class FilterController {

    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> filterTransactions(@RequestBody FilterDTO filter) {
        //preparing the data or validation
        LocalDate startDate = filter.getStartDate() != null ? filter.getStartDate() : LocalDate.MIN;
        LocalDate endDate = filter.getEndDate() != null ? filter.getEndDate() : LocalDate.now();
        String keyWord = filter.getKeyWord() != null ? filter.getKeyWord() : null;
        String sortField =  filter.getSortField() != null ? filter.getSortField() : "date";
        Sort.Direction direction = "desc".equalsIgnoreCase(filter.getSortOrder()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortField);

        if("income".equalsIgnoreCase(filter.getType())) {
            List<IncomeDTO> incomes =  incomeService.filterIncomes(startDate, endDate, keyWord, sort);
            return ResponseEntity.ok().body(incomes);
        }else if("expense".equalsIgnoreCase(filter.getType())) {
           List<ExpenseDTO> expenses =  expenseService.filterExpenses(startDate, endDate, keyWord, sort);
           return ResponseEntity.ok().body(expenses);
        }
        else {
            return ResponseEntity.badRequest().body("Invalid type! Must be income or expense");
        }
    }
}
