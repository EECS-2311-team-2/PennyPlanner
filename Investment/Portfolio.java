package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Portfolio extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Budget Text
        Text budgetText = new Text("Budget: $10000");
        // Investment Text
        Text investmentText = new Text("Investment: $5000");
        
        // Button to navigate to another page
        Button nextPageButton = new Button("Go to Another Page");
        nextPageButton.setOnAction(e -> {
            // Code to navigate to another page
            System.out.println("Navigating to another page...");
        });

        // Line Chart for Portfolio Gains
        NumberAxis lineXAxis = new NumberAxis();
        NumberAxis lineYAxis = new NumberAxis();
        lineXAxis.setLabel("Months");
        lineYAxis.setLabel("Gains ($)");
        LineChart<Number, Number> lineChart = new LineChart<>(lineXAxis, lineYAxis);
        lineChart.setTitle("Portfolio Gains");
        XYChart.Series<Number, Number> lineSeries = new XYChart.Series<>();
        lineSeries.setName("Gains");
        lineChart.setStyle("-fx-stroke: green;");
        // Sample data (Months vs Gains)
        
        lineSeries.getData().add(new XYChart.Data<>(0, 1000));
        lineSeries.getData().add(new XYChart.Data<>(2, 1500));
        lineSeries.getData().add(new XYChart.Data<>(4, 1200));
        lineSeries.getData().add(new XYChart.Data<>(6, 800));
        lineSeries.getData().add(new XYChart.Data<>(8, 2000));
        lineSeries.getData().add(new XYChart.Data<>(10, 4500));
        
        XYChart.Data<Number, Number> lastDataPoint = lineSeries.getData().get(lineSeries.getData().size() - 1);
        if (lastDataPoint.getYValue().doubleValue() < 0) {
            lineChart.setStyle("-fx-stroke: red;");
        } else {
            lineChart.setStyle("-fx-stroke: green;");
        }
        
        lineChart.getData().add(lineSeries);

        // Bar Chart for Holdings
        CategoryAxis barXAxis = new CategoryAxis();
        NumberAxis barYAxis = new NumberAxis();
        barXAxis.setLabel("Stocks");
        barYAxis.setLabel("Holdings");
        BarChart<String, Number> barChart = new BarChart<>(barXAxis, barYAxis);
        barChart.setTitle("Holdings");
        XYChart.Series<String, Number> barSeries = new XYChart.Series<>();
        barSeries.setName("Holdings");
        // Sample data (Stocks vs Holdings)
        barSeries.getData().add(new XYChart.Data<>("Stock A", 200));
        barSeries.getData().add(new XYChart.Data<>("Stock B", 300));
        barSeries.getData().add(new XYChart.Data<>("Stock C", 400));
        barChart.getData().add(barSeries);

        // Create a VBox layout to hold all components
        VBox root = new VBox(10, budgetText, investmentText, nextPageButton, lineChart, barChart);
        root.setPadding(new javafx.geometry.Insets(20));

        // Set the scene
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Portfolio Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
