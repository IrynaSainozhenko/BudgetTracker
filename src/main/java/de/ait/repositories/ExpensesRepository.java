package de.ait.repositories;

import de.ait.models.Expense;

import java.util.List;

public interface ExpensesRepository {
    List<Expense> getAllExpenses();
    void save(Expense expense);
}
