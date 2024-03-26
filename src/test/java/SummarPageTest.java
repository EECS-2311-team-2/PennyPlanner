import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javafx.scene.chart.LineChart;

import GUI.DatabaseTestUtils;

import java.sql.Statement;



import static org.testfx.api.FxAssert.verifyThat;


import static org.junit.jupiter.api.Assertions.assertFalse;




import static org.testfx.assertions.api.Assertions.assertThat;



public class SummarPageTest extends ApplicationTest {

	private Connection connection;

	@Override

	public void start(Stage stage) throws Exception {
		// Assuming 'App' is your main JavaFX application class that sets up the JavaFX scene
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/SummaryPage.fxml"));


		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}


	@Test
	public void testLineChartIsVisible() {
		// Wait for the LineChart to be visible using its fx:id
		// Verify the LineChart is visible
		assertThat(lookup("#LineGraph").queryAs(LineChart.class)).isVisible();
	}

	@Test
	public void testPieChartIsVisible() {
		// Wait for the PieChart to be visible using its fx:id
		// Verify the PieChart is visible
		assertThat(lookup("#PieChart").queryAs(PieChart.class)).isVisible();
	}

	@BeforeEach
	public void setUp() throws Exception {
		// Establish connection to your test database
		connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
		// Set up the database schema to reflect the changes
		try (Statement stmt = connection.createStatement()) {
			stmt.execute("CREATE TABLE IF NOT EXISTS expenses (" +
				"id INT AUTO_INCREMENT PRIMARY KEY," +
				"expense VARCHAR(255)," + // Changed from 'month' to 'expense'
				"amount DOUBLE)");
		}
		// Insert test data with updated categories


		DatabaseTestUtils.insertTestData(connection);

	}

	@Test
	public void testChartDataPopulation() {
		verifyThat("#LineGraph", isNotNull());

		// Access the LineChart from the scene
		LineChart<String, Number> lineChart = lookup("#LineGraph").query();

		// Safety check: Ensure the chart has at least one series
		assertTrue(lineChart.getData().size() > 0, "Chart should have at least one series.");

	}

	@Test
	public void testIfDataIsRetrievable() throws SQLException {
		String sql = "SELECT expense, amount FROM expenses WHERE expense = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, "Groceries");
			try (ResultSet rs = stmt.executeQuery()) {
				// Verify that the result set is not empty
				assertTrue(rs.next(), "No data found for Groceries.");

				// Verify the data
				String expense = rs.getString("expense");
				double amount = rs.getDouble("amount");

				assertEquals("Groceries", expense, "The expense category does not match.");
				assertEquals(1000.0, amount, "The amount does not match.");

				// Optionally, verify that there are no more rows (data points) for Groceries
				assertTrue(!rs.next(), "More than one entry found for Groceries.");
			}

		}
	}

	@Test
	public void verifyGroceryDataPointIsDeleted() throws SQLException {
		// Delete the "Groceries" data point
		DatabaseTestUtils.deleteTestData(connection);

		// Verify it's no longer there
		String sql = "SELECT * FROM expenses WHERE expense = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, "Groceries");
			try (ResultSet rs = stmt.executeQuery()) {
				// If rs.next() is true, it means data was found, which is not what we expect after deletion
				assertFalse(rs.next(), "Data for 'Groceries' was found, but it should have been deleted.");
			}
		}
	}


			@Test
			public void ensureLineChartHasNoData() throws SQLException {
				DatabaseTestUtils.deleteTestData2(connection); // Adjust if using deleteSpecificTestData()

				verifyThat("#LineGraph", isNotNull());

				// Access the LineChart from the scene
				LineChart<String, Number> lineChart = lookup("#LineGraph").queryAs(LineChart.class);

				// Verify the LineChart has no data series, and hence no data points
				assertTrue(lineChart.getData().isEmpty(), "LineChart should have no data series, and hence no X or Y data.");
			}
		}





