package GUI;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTestUtils {
	public static void insertTestData(Connection connection) throws SQLException {
		// Removed the line that establishes a new connection inside this method.
		String sql = "INSERT INTO expenses (expense, amount) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			// Inserting data for a specific expense category
			stmt.setString(1, "Groceries");
			stmt.setDouble(2, 1000.0); // Expense amount
			stmt.executeUpdate();

			// Inserting data for another expense category
			stmt.setString(1, "Utilities");
			stmt.setDouble(2, 1500.0); // Expense amount
			stmt.executeUpdate();
		}
	}

	public static void deleteTestData(Connection connection) throws SQLException {
		String sql = "DELETE FROM expenses";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.executeUpdate();
		}

	}

	public static void deleteTestData2(Connection connection) throws SQLException {
		String sql = "DELETE FROM expenses";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.executeUpdate();
		}
	}
}

