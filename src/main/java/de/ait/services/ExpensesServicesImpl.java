package de.ait.services;

import de.ait.models.Category;
import de.ait.models.Expense;
import de.ait.repositories.ExpensesRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ExpensesServicesImpl implements ExpensesServices{
    private ExpensesRepository expensesRepository;

    public ExpensesServicesImpl(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    public List<Expense> getAll() throws IOException, ParseException {

        return expensesRepository.getAllExpenses();
    }

    @Override
    public void addNewExpense(String title, Category category, double sumExpenses, Date date) {
        Expense newExpense = new Expense(title, category, sumExpenses, date);
        expensesRepository.save(newExpense);
    }

    public void changeExpenseAmount(String title, double newAmount) {
        Expense expense = expensesRepository.getExpenseById(title);
        if (expense != null) {
            expense.setAmount(newAmount);
            expensesRepository.updateExpense(expense);
            System.out.println("Сумма расхода успешно изменена");
        } else {
            System.out.println("Расход с указанным названием не найден");
        }
    }

}
