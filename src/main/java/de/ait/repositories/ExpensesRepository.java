package de.ait.repositories;

import de.ait.models.Expense;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface
ExpensesRepository {
    List<Expense> getAllExpenses() throws IOException, ParseException;
    void save(Expense expense);
    void changeExpense(Expense updatedExpense);
    Expense findByTitle(String title);
    void removeExpense(String expenseToRemove);
    void removeAllExpenses();
}
