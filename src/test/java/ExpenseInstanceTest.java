import models.TimePeriod;
import models.instances.ExpenseInstance;
import models.money.Expense;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
public class ExpenseInstanceTest {

	ExpenseInstance expenseInstance = new ExpenseInstance();

	@Test
	public void testSetAndGetID() {
		int id = 123;
		expenseInstance.setID(id);
		assertEquals(id, expenseInstance.getID());
	}

	@Test
	public void testSetAndGetMonth() {
		TimePeriod timePeriod = new TimePeriod();
		expenseInstance.setMonth(timePeriod);
		assertEquals(timePeriod, expenseInstance.getMonth());
	}
	@Test
	public void testSetAndGetCost() {
		double cost = 50.0;
		expenseInstance.setCost(cost);
		assertEquals(cost, expenseInstance.getCost());
	}
	@Test
	public void testSetAndGetProjectedCost() {
		double projectedCost = 100.0;
		expenseInstance.setProjectedCost(projectedCost);
		assertEquals(projectedCost, expenseInstance.getProjectedCost());
	}
	@Test
	public void testDifference() {
		double projectedCost = 100.0;
		double cost = 50.0;
		expenseInstance.setProjectedCost(projectedCost);
		expenseInstance.setCost(cost);
		assertEquals(projectedCost - cost, expenseInstance.difference());
	}
	@Test
	public void testSetAndGetExpense() {
		Expense expense = new Expense();
		expenseInstance.setExpense(expense);
		assertEquals(expense, expenseInstance.getExpense());
	}
}
