package de.ait.repositories;

import de.ait.models.Category;
import de.ait.models.Expense;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseListImpl implements ExpensesRepository{

    List<Expense> expenses = new ArrayList<>(List.of(
            new Expense("Кофе", Category.FOOD,6.5, LocalDate.of(2023,5,18)),
            new Expense("Чай", Category.FOOD,3, LocalDate.of(2023,3,11)),
            new Expense("Джинсы", Category.CLOTHES,35, LocalDate.of(2023,2,16)),
            new Expense("Футболка", Category.CLOTHES,15, LocalDate.of(2023,1,8))
    ));

    @Override
    public List<Expense> getAllExpenses() throws IOException, ParseException {
        return expenses;
    }

    @Override
    public void save(Expense expense) {
        expenses.add(expense);

    }

    @Override
    public void removeExpense(String expenseToRemove) {
        for (int i = 0; i<expenses.size();i++){
            if (expenses.get(i).getTitle().equals(expenseToRemove)) {
                expenses.remove(i);
            }
        }
    }

    @Override
    public void changeExpense(String expenseTitle, double newAmount) {
        for (int i = 0; i<expenses.size();i++){
            if (expenses.get(i).getTitle().equals(expenseTitle)) {
                expenses.get(i).setSumExpenses(newAmount);
            }
        }
    }

    @Override
    public void removeAllExpenses() {
        expenses.removeAll(expenses);
    }
}
