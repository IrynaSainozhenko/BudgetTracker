package de.ait.models;

import java.time.LocalDate;
import java.util.Date;

public class Expense {
    private String title;
    private Category category;
    private double sumExpenses;
    private Date date;

    public Expense(String title, Category category, double sumExpenses, Date date) {
        this.title = title;
        this.category = category;
        this.sumExpenses = sumExpenses;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public double getSumExpenses() {
        return sumExpenses;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "title='" + title + '\'' +
                ", category=" + category +
                ", sumExpenses=" + sumExpenses +
                ", date=" + date +
                '}';
    }

    public void setAmount(double newAmount) {
    }
}

