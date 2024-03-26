package GUI;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ConvertCurrency implements Initializable {

	@FXML
	private ComboBox<Currency> outputCurrencyComboBox;

	@FXML
	private ComboBox<Currency> inputCurrencyComboBox;

	@FXML
	private TextField outputAmount;

	@FXML
	private TextField inputAmount;

	@FXML
	private CheckBox autoCheckBoxButton;

	@FXML
	private Button convertButton;

	private final static DoubleStringConverter DOUBLE_STRING_CONVERTER = new DoubleStringConverter();

	private final static DecimalFormat CURRENCY_FORMAT = new DecimalFormat("#0.000");

	private final ObservableList<Currency> currencies = FXCollections.observableArrayList();

	private final ChangeListener<String> inputAmountChangeListener = (observable, oldValue, newValue) -> convertAction(null);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		currencies.addAll(Currency.values());
		inputCurrencyComboBox.setItems(currencies);
		outputCurrencyComboBox.setItems(currencies);
		inputCurrencyComboBox.getSelectionModel().selectFirst();
		outputCurrencyComboBox.getSelectionModel().selectLast();
		autoCheckBoxButton.setSelected(false);

		clearAction(null);

		inputCurrencyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			convertAction(null);
		});
		outputCurrencyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			convertAction(null);
		});

	}

	@FXML
	private void convertAction(ActionEvent actionEvent) {
		if (actionEvent != null || (actionEvent == null && autoCheckBoxButton.isSelected())) {
			Currency inputCurrency = inputCurrencyComboBox.getValue();
			Currency outputCurrency = outputCurrencyComboBox.getValue();
			double inputValue = 0;
			boolean validInput = true;

			if (!inputAmount.getText().isEmpty() && isNumeric(inputAmount.getText())) {
				inputValue = DOUBLE_STRING_CONVERTER.fromString(inputAmount.getText());
				if (inputValue < 0) {
					validInput = false;
					showAlert("Invalid Input", "Please enter a positive number.");
					return;
				}
			} else if (!inputAmount.getText().isEmpty()) { // Only show alert if the field is not empty
				validInput = false;
				showAlert("Invalid Input", "Please enter a numeric value.");
				return;
			}

			if (validInput) {
				double inputValueInRupees = inputValue * inputCurrency.getRupeeConversionRate();
				double outputValue = inputValueInRupees / outputCurrency.getRupeeConversionRate();
				outputAmount.setText(CURRENCY_FORMAT.format(outputValue));
			}
		}
	}
	private void showAlert(String title, String content) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(content);
			alert.showAndWait();
		}

	@FXML
	private void clearAction(ActionEvent actionEvent) {
		inputAmount.setText("");
		outputAmount.setText("");
	}

	@FXML
	private void switchAutomaticConversion(ActionEvent actionEvent) {
		if (autoCheckBoxButton.isSelected()) {
			convertButton.setDisable(true);
			inputAmount.textProperty().addListener(inputAmountChangeListener);
			convertAction(null);
		} else {
			convertButton.setDisable(false);
			inputAmount.textProperty().removeListener(inputAmountChangeListener);
		}
	}

	private static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
public void Database() {
	String dbName = "convert";
	String dbUser = "nabeelaansari";
	String userPassword = "postgres";
	String url = "jdbc:postgresql://localhost:5432/convert";
}
	public void insertConversionRecord(String sourceCurrency, String targetCurrency, double sourceAmount, double convertedAmount) {
		String sql = "INSERT INTO currency_conversions (source_currency, target_currency, source_amount, converted_amount, conversion_date) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/convert", "nabeelaansari", "postgres");
				 PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, sourceCurrency);
			pstmt.setString(2, targetCurrency);
			pstmt.setDouble(3, sourceAmount);
			pstmt.setDouble(4, convertedAmount);
			pstmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));

			int affectedRows = pstmt.executeUpdate();
			Statement statement =connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM conversion_history LIMIT 10");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}