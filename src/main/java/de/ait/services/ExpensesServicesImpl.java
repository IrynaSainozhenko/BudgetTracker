package de.ait.services;

import de.ait.models.Category;
import de.ait.models.Expense;
import de.ait.repositories.ExpensesRepository;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
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
