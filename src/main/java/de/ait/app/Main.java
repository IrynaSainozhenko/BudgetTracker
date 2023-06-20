package de.ait.app;

import de.ait.models.Category;
import de.ait.repositories.ExpensesRepository;
import de.ait.repositories.ExpensesRepositoryText;
import de.ait.services.ExpensesServices;
import de.ait.services.ExpensesServicesImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        ExpensesRepository expensesRepository = new ExpensesRepositoryText("expenses.txt");
        ExpensesServicesImpl expensesServices = new ExpensesServicesImpl(expensesRepository);

        while (true) {
            System.out.println("1. Добавить расход");
            System.out.println("2. Изменить расход");
            System.out.println("3. ");
            System.out.println("4. ");
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
                        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(scanner.nextLine());                                ;
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
}