package GUI;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;

public class PortfolioController {

    @FXML
    private TextFlow textFlow1;

    @FXML
    private TextFlow textFlow2;

    @FXML
    private Label label1;

    @FXML
    private Label label2;


    @FXML
    private ButtonBar buttonBar;

    @FXML
    private Button button;

    @FXML
    private LineChart<String, Number> LineChart;

    @FXML
    private BarChart<String, Number> BarChart;

    @FXML
    private void initialize() {

        //
        label1.setText("1000");
        label2.setText("9000");


        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Gains");

        series1.getData().add(new XYChart.Data<>("1", 200));
        series1.getData().add(new XYChart.Data<>("2", 300));
        series1.getData().add(new XYChart.Data<>("3", 400));
        LineChart.getData().add(series1);

        // Bar Chart
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Holdings");
        series2.getData().add(new XYChart.Data<>("Stock A", 200));
        series2.getData().add(new XYChart.Data<>("Stock B", 300));
        series2.getData().add(new XYChart.Data<>("Stock C", 400));
        BarChart.getData().add(series2);
    }

}
