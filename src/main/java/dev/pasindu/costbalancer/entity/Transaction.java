package dev.pasindu.costbalancer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Integer id;
    private Integer userId;
    private Integer categoryId;
    private Double amount;
    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private String note;
}
