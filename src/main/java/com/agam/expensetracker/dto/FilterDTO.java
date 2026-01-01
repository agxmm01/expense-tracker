package com.agam.expensetracker.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class FilterDTO {
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String keyWord;
    private String sortField; // adding the fields i.e. name,date,amount
    private String sortOrder; // asc or desc
}
