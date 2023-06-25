package de.ait.repositories;

import de.ait.models.Category;
import de.ait.models.Expense;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpensesRepositoryText implements ExpensesRepository {
    private String fileName;

    public ExpensesRepositoryText(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line = bufferedReader.readLine();

            while (line != null) {
                Expense expenses1 = parseLine(line);
                expenses.add(expenses1);
                line = bufferedReader.readLine();
            }
        } catch (IOException | ParseException e) {
            System.err.println("Что-то пошло не так");
        }
        return expenses;
    }

    private static Expense parseLine(String line) throws ParseException {
        String[] parsed = line.split("\\|");
        String title = parsed[0];
        Category category = Category.valueOf(parsed[1]);
        double sumExpenses = Double.parseDouble(parsed[2]);
        LocalDate date = LocalDate.parse(parsed[3]);

        return new Expense(title, category, sumExpenses, date);
    }


    @Override
    public void save(Expense expense) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(fileName, true))) {

            String newExpense = expense.getTitle() + "|" +
                    expense.getCategory() + "|" +
                    expense.getSumExpenses() + "|" +
                    expense.getDate();
            bufferedWriter.write(newExpense);
            bufferedWriter.newLine();

        } catch (IOException e) {
            throw new IllegalStateException("Данные не могут быть сохранены");
        }
    }

    @Override
    public void changeExpense(Expense updatedExpense) {

            List<Expense> expenses = getAllExpenses();

            for (Expense oldExpense : expenses) {
                if (oldExpense.getTitle().equals(updatedExpense.getTitle())){
                    oldExpense.setTitle(updatedExpense.getTitle());
                    oldExpense.setCategory(updatedExpense.getCategory());
                    oldExpense.setSumExpenses(updatedExpense.getSumExpenses());
                    oldExpense.setDate(updatedExpense.getDate());
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
                for (Expense expense : expenses) {
                    writer.write(expense.getTitle() + "|" +
                            expense.getCategory() + "|" +
                            expense.getSumExpenses() + "|" + expense.getDate());
                    writer.newLine();
                }
            }catch (IOException e){
                throw new IllegalStateException("Ошибка при работе с файлом - " + e.getMessage());
            }
        }


    @Override
    public Expense findByTitle(String title) {

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                List<Expense> expenses = new ArrayList<>();

                String line = reader.readLine();
                while (line != null) {
                    Expense expense = parseLine(line);
                    if (expense.getTitle().equals(title)) {
                        return expense;
                    }
                    line = reader.readLine();
                }
            } catch (IOException | ParseException e) {
                throw new IllegalStateException("Ошибка при работе с файлом - " + e.getMessage());
            }
            return null;
        }


    @Override
    public void removeExpense(String expenseToRemove) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<Expense> expenses = new ArrayList<>();
            String line = reader.readLine();
            while ((line != null)) {
                Expense expense = parseLine(line);
                expenses.add(expense);
                line = reader.readLine();
            }
            for (int i = 0; i<expenses.size();i++){
                if (expenses.get(i).getTitle().equals(expenseToRemove)) {
                    expenses.remove(i);
                }
            }
            reader.close();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                for (Expense expense:expenses){
                    writer.write(expense.getTitle() + "|" +
                            expense.getCategory() + "|" +
                            expense.getSumExpenses() + "|" + expense.getDate());
                    writer.newLine();
                }
                writer.close();
            }catch (Exception e){
                System.err.println("Ошибка записи файла");
            }
        System.out.println("Расход успешно удален");
        } catch (Exception e) {
            System.out.println("Произошла ошибка работы с файлом");
        }
    }
//    @Override
//    public void changeExpense(String expenseTitle, double newAmount) {
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(fileName));
//            List<Expense> expenses = new ArrayList<>();
//            String line = reader.readLine();
//            while ((line != null)) {
//                Expense expense = parseLine(line);
//                expenses.add(expense);
//                line = reader.readLine();
//            }
//            for (int i = 0; i<expenses.size();i++){
//                if (expenses.get(i).getTitle().equals(expenseTitle)) {
//                    expenses.get(i).setSumExpenses(newAmount);
//                }
//            }
//            reader.close();
//            try {
//                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
//                for (Expense expense:expenses){
//                    writer.write(expense.getTitle() + "|" +
//                            expense.getCategory() + "|" +
//                            expense.getSumExpenses() + "|" + expense.getDate());
//                    writer.newLine();
//                }
//                writer.close();
//            }catch (Exception e){
//                System.err.println("Ошибка записи файла");
//            }
//            System.out.println("Cумма расхода успешно изменена");
//        } catch (Exception e) {
//            System.out.println("Произошла ошибка работы с файлом");
//        }
//    }

    @Override
    public void removeAllExpenses() {
        try{
            PrintWriter printWriter = new PrintWriter(fileName);
            printWriter.close();
            System.out.println("Все расходы успешно удалены");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
