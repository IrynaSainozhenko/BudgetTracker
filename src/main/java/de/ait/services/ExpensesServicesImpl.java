package de.ait.services;

import de.ait.models.Category;
import de.ait.models.Expense;
import de.ait.repositories.ExpensesRepository;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ExpensesServicesImpl implements ExpensesServices{
    private final ExpensesRepository expensesRepository;

    public ExpensesServicesImpl(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    public List<Expense> getAll() throws IOException, ParseException {

        return expensesRepository.getAllExpenses();
    }

    @Override
    public void addNewExpense(String title, Category category, double sumExpenses, LocalDate date) {
        Expense newExpense = new Expense(title, category, sumExpenses, date);
        expensesRepository.save(newExpense);
    }

    public void updateExpense(String expenseTitle, double newAmount){
        expensesRepository.changeExpense(expenseTitle, newAmount);
    }

    public void removeExpenses(String expenseToRemove){
        expensesRepository.removeExpense(expenseToRemove);
    }

    @Override
    public void removeAllExpenses() {
        expensesRepository.removeAllExpenses();
    }
}
