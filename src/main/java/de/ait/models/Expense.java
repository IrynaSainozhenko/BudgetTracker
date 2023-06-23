package de.ait.models;

import java.time.LocalDate;
import java.util.Date;

public class Expense {
    private String title;
    private Category category;
    private double sumExpenses;
    private LocalDate date;

    @Override
    public String toString() {
        return "Expense{" +
                "title='" + title + '\'' +
                ", category=" + category +
                ", sumExpenses=" + sumExpenses +
                ", date=" + date +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getSumExpenses() {
        return sumExpenses;
    }

    public void setSumExpenses(double sumExpenses) {
        this.sumExpenses = sumExpenses;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Expense(String title, Category category, double sumExpenses, LocalDate date) {
        this.title = title;
        this.category = category;
        this.sumExpenses = sumExpenses;
        this.date = date;
    }

    public void setAmount(double newAmount) {
    }
}

