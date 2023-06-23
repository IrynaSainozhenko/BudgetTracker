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
            System.out.println("4. Вывести все расходы");
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
                        System.out.println("Введите идентификатор расхода:");
                        String expenseId = scanner.nextLine();
                        System.out.println("Введите новую сумму расхода:");
                        double newAmount = Double.parseDouble(scanner.nextLine());
                        expensesServices.changeExpenseAmount(expenseId, newAmount);
                        break;

                    case 3:
                        System.out.println("3. Удалить расход");
                        System.out.println("Введите название расхода:");
                        String expenseName = scanner.nextLine();
                        expensesRepository.removeExpense(expenseName);

                        break;

                    case 4:
                        System.out.println("Все расходы за весь период: ");
                        List<Expense> expenses = expensesServices.getAll();
                        printExpensesList(expenses);
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