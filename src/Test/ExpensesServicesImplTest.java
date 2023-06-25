import de.ait.models.Category;
import de.ait.models.Expense;
import de.ait.repositories.ExpenseListImpl;
import de.ait.services.ExpensesServicesImpl;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpensesServicesImplTest {
    private ExpensesServicesImpl expensesServicesImpl;

    @BeforeEach
    void setUp() {
        this.expensesServicesImpl = new ExpensesServicesImpl(new ExpenseListImpl());
    }


    @org.junit.jupiter.api.Test
    void getAll() throws IOException, ParseException {

        List<Expense> actual = expensesServicesImpl.getAll();
        List<Expense> expected = List.of(
                new Expense("Кофе", Category.FOOD,6.5, LocalDate.of(2023,5,18)),
                new Expense("Чай", Category.FOOD,3, LocalDate.of(2023,3,11)),
                new Expense("Джинсы", Category.CLOTHES,35, LocalDate.of(2023,2,16)),
                new Expense("Футболка", Category.CLOTHES,15, LocalDate.of(2023,1,8)),
                new Expense("Кепка", Category.CLOTHES,15, LocalDate.of(2023,6,25))
        );
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getExpensesSevenDays() throws IOException, ParseException {
        List<Expense> actual = expensesServicesImpl.getExpensesSevenDays();
        List<Expense> expected = List.of(new Expense("Кепка", Category.CLOTHES,15, LocalDate.of(2023,6,25))
        );
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getExpensesTheCurrentMonth() {
    }
}