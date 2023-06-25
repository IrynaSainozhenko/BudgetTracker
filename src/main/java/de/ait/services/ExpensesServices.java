package de.ait.services;

import de.ait.models.Category;
import de.ait.models.Expense;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ExpensesServices {
    List<Expense> getAll() throws IOException, ParseException;
    public List<Expense> getExpensesSevenDays() throws IOException, ParseException;
    public List<Expense> getExpensesTheCurrentMonth() throws IOException, ParseException;
    void addNewExpense(String title, Category category, double sumExpenses, LocalDate date);
    void changeExpenseTitle(String oldTitle, String newTitle);
    void changeExpenseCategory(String title, Category category);
    void changeSumExpense(String title, double newSumExpense);
    void changeExpenseDate(String title, LocalDate date);

//    void updateExpense(String expenseTitle, double newAmount);
    void removeExpenses(String expenseToRemove);
    void removeAllExpenses();

}
