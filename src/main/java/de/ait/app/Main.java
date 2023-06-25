package de.ait.app;

import de.ait.models.Category;
import de.ait.models.Expense;
import de.ait.repositories.ExpensesRepository;
import de.ait.repositories.ExpensesRepositoryText;
import de.ait.services.ExpensesServices;
import de.ait.services.ExpensesServicesImpl;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        Scanner scanner = new Scanner(System.in);
        ExpensesRepository expensesRepository = new ExpensesRepositoryText("expenses.txt");
        ExpensesServicesImpl expensesServices = new ExpensesServicesImpl(expensesRepository);

        while (true) {
            System.out.println("1. Добавить расход");
            System.out.println("2. Изменить расход");
            System.out.println("3. Удалить расход");
            System.out.println("4. Вывести расходы");
            System.out.println("0. Выход");

            int command = 0;
            if(scanner.hasNextInt()){
                command = scanner.nextInt();
                scanner.nextLine();
                switch (command) {
                    case 1:
                        System.out.println("1. Добавить расход");
                        System.out.println("Введите название расхода:");
                        String title = scanner.nextLine();
                        System.out.println("Выберите категорию расхода:");

                        int categoryIndex = 1;
                        for (Category category : Category.values()) {
                            System.out.println(categoryIndex + ". " + category);
                            categoryIndex++;
                        }

                        int categoryChoice = scanner.nextInt();
                        Category category = Category.values()[categoryChoice - 1];

                        scanner.nextLine();

                        System.out.println("Введите сумму расхода:");
                        double sumExpenses = Double.parseDouble(scanner.nextLine());
                        System.out.println("Введите дату расхода");
                        LocalDate date = LocalDate.parse(scanner.nextLine());
                        expensesServices.addNewExpense(title, category, sumExpenses, date);
                        System.out.println("Расход успешно добавлен!");
                        break;

                    case 2:
                        System.out.println("2. Изменить расход");
                        System.out.println("Выберите поле для изменения:");
                        System.out.println("1. Название расхода");
                        System.out.println("2. Категория расхода");
                        System.out.println("3. Сумма расхода");
                        System.out.println("4. Дата расхода");
                        int command2 = 0;
                        if(scanner.hasNextInt()){
                            command2 = scanner.nextInt();
                            scanner.nextLine();
                            switch (command2) {
                                case 1:
                                    System.out.println("Введите название расхода:");
                                    String expenseTitle1 = scanner.nextLine();
                                    System.out.println("Введите новое название расхода:");
                                    String expenseNewTitle = scanner.nextLine();
                                    expensesServices.changeExpenseTitle(expenseTitle1, expenseNewTitle);
                                    break;
                                case 2:
                                    System.out.println("Введите название расхода:");
                                    String expenseTitle2 = scanner.nextLine();
                                    System.out.println("Выберите новую категорию расхода:");
                                    int categoryIndex1 = 1;
                                    for (Category categoryNew : Category.values()) {
                                        System.out.println(categoryIndex1 + ". " + categoryNew);
                                        categoryIndex1++;
                                    }
                                    int categoryChoice1 = scanner.nextInt();
                                    Category categoryNew = Category.values()[categoryChoice1 - 1];

                                    scanner.nextLine();
                                    expensesServices.changeExpenseCategory(expenseTitle2, categoryNew);
                                    break;
                                case 3:
                                    System.out.println("Введите название расхода, который хотите изменить:");
                                    String expenseTitle3 = scanner.nextLine();
                                    System.out.println("Введите новую сумму расхода:");
                                    double newSumExpense =  Double.parseDouble(scanner.nextLine());
                                    expensesServices.changeSumExpense(expenseTitle3, newSumExpense);
                                    break;
                                case 4:
                                    System.out.println("Введите название расхода, который хотите изменить:");
                                    String expenseTitle4 = scanner.nextLine();
                                    System.out.println("Введите новую дату расхода:");
                                    LocalDate newDate = LocalDate.parse(scanner.nextLine());
                                    expensesServices.changeExpenseDate(expenseTitle4, newDate);
                                    break;
                            }
                        }
                        break;

                    case 3:
                        System.out.println("3. Удалить расход");
                        System.out.println("Выберите вариант удаления:");
                        System.out.println("1. Удалить один расход");
                        System.out.println("2. Удалить всe расходы");
                        int command1 = 0;
                        if(scanner.hasNextInt()){
                            command1 = scanner.nextInt();
                            scanner.nextLine();
                            switch (command1) {
                                case 1:
                                    System.out.println("Введите название расхода:");
                                    String expenseName = scanner.nextLine();
                                    expensesServices.removeExpenses(expenseName);
                                    break;
                                case 2:
                                    expensesServices.removeAllExpenses();
                                    break;
                            }
                        }
                        break;

                    case 4:
                        System.out.println("1. Вывести расходы за весь период\n" +
                                        "2. Вывести расходы за последние 7 дней\n" +
                                        "3. Вывести расходы за текущий месяц");
                        if(scanner.hasNextInt()){
                            List<Expense> expenses = null;
                            switch (scanner.nextInt()){
                                case 1:
                                    System.out.println("Все расходы за весь период: ");
                                    expenses = expensesServices.getAll();
                                    printExpensesList(expenses);
                                    break;
                                case 2:
                                    System.out.println("Расходы за последние 7 дней: ");
                                    expenses = expensesServices.getExpensesSevenDays();
                                    printExpensesList(expenses);
                                    break;
                                case 3:
                                    System.out.println("Расходы за текущий месяц: ");
                                    expenses = expensesServices.getExpensesTheCurrentMonth();
                                    printExpensesList(expenses);
                                    break;
                            }
                        }

                        break;


                    case 0:
                        System.out.println("Выход");
                        System.exit(0);
                    default:
                        System.out.println("Команда не распознана");
                }
            } else{
                System.out.println("Введенное значение не является числом!");
                System.out.println("Перезапустите программу!");
                return;
            }
        }
    }

    public static void printExpensesList(List<Expense> expensesList){
        for(Expense expenses : expensesList){
            System.out.println("Наименование: " + expenses.getTitle()
                    + "; сумма: " + expenses.getSumExpenses()
                    + "; дата: " + expenses.getDate()
                    + "; категория: " + expenses.getCategory());
        }
    }
}