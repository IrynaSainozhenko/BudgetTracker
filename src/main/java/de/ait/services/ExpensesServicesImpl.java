package de.ait.services;

import de.ait.models.Category;
import de.ait.models.Expense;
import de.ait.repositories.ExpensesRepository;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class ExpensesServicesImpl implements ExpensesServices{
    private final ExpensesRepository expensesRepository;

    public ExpensesServicesImpl(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    public List<Expense> getAll() throws IOException, ParseException {
        return expensesRepository.getAllExpenses();
    }
   public List<Expense> getExpensesSevenDays() throws IOException, ParseException {
       List<Expense> newExpense = new ArrayList<>();
       for(int i = 0; i < expensesRepository.getAllExpenses().size(); i++){
           LocalDate date = LocalDate.now().minusDays(7);
           /*int tmpDate = date.compareTo(expensesRepository.getAllExpenses().get(i).getDate());
           if(tmpDate <= 7 && tmpDate >= 0){
               System.out.println(tmpDate);
               newExpense.add(expensesRepository.getAllExpenses().get(i));
           }*/

           if(expensesRepository.getAllExpenses().get(i).getDate().isAfter(date)
           || expensesRepository.getAllExpenses().get(i).getDate().equals(date)){
               newExpense.add(expensesRepository.getAllExpenses().get(i));
           }
       }
       return newExpense;
   }

    public List<Expense> getExpensesTheCurrentMonth() throws IOException, ParseException {
        List<Expense> newExpense = new ArrayList<>();
        for(int i = 0; i < expensesRepository.getAllExpenses().size(); i++){

            if(expensesRepository.getAllExpenses().get(i).getDate().getMonthValue() == LocalDate.now().getMonthValue()){
                newExpense.add(expensesRepository.getAllExpenses().get(i));
            }
        }
        return newExpense;
    }


    @Override
    public void addNewExpense(String title, Category category, double sumExpenses, LocalDate date) {
        Expense newExpense = new Expense(title, category, sumExpenses, date);
        expensesRepository.save(newExpense);
    }
    @Override
    public void changeExpenseTitle(String oldTitle, String newTitle) {
        Expense expense = expensesRepository.findByTitle(oldTitle);

        if (expense == null) {
            throw new IllegalArgumentException("Расход с таким именем не найден");
        }

        expense.setTitle(newTitle);
        expensesRepository.changeExpense(expense);
    }

    @Override
    public void changeExpenseCategory(String title, Category category) {
        Expense expense = expensesRepository.findByTitle(title);

        if (expense == null) {
            throw new IllegalArgumentException("Расход с таким именем не найден");
        }

        expense.setCategory(category);
        expensesRepository.changeExpense(expense);
    }

    @Override
    public void changeSumExpense(String title, double newSumExpense) {
        Expense expense = expensesRepository.findByTitle(title);

        if (expense == null) {
            throw new IllegalArgumentException("Расход с таким именем не найден");
        }

        expense.setSumExpenses(newSumExpense);
        expensesRepository.changeExpense(expense);
    }

    @Override
    public void changeExpenseDate(String title, LocalDate date) {
        Expense expense = expensesRepository.findByTitle(title);

        if (expense == null) {
            throw new IllegalArgumentException("Расход с таким именем не найден");
        }

        expense.setDate(date);
        expensesRepository.changeExpense(expense);
    }

//    public void updateExpense(String expenseTitle, double newAmount){
//        expensesRepository.changeExpense(expenseTitle, newAmount);
//    }

    public void removeExpenses(String expenseToRemove){
        expensesRepository.removeExpense(expenseToRemove);
    }

    @Override
    public void removeAllExpenses() {
        expensesRepository.removeAllExpenses();
    }
}
