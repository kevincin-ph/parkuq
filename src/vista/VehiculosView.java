package vista;

import modelo.Vehiculo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class VehiculosView {

    public VehiculosView(Stage stage) {
        Text titulo = new Text("Vehiculos en el Parqueadero");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        TextArea areaTexto = new TextArea();
        areaTexto.setEditable(false);
        areaTexto.setStyle("-fx-font-size: 13;");
        areaTexto.setPrefHeight(300);

        StringBuilder sb = new StringBuilder();
        boolean hayVehiculos = false;
        for (Vehiculo v : MainApp.parqueadero.getVehiculos()) {
            if (v.getEstado().equals("dentro")) {
                sb.append(v.toString()).append("\n\n");
                hayVehiculos = true;
            }
        }
        if (!hayVehiculos) sb.append("No hay vehiculos en este momento.");
        areaTexto.setText(sb.toString());

        Button btnVolver = new Button("Volver");
        btnVolver.setStyle(
            "-fx-background-color: #e74c3c; -fx-text-fill: white;" +
            "-fx-font-size: 13; -fx-padding: 10 30; -fx-cursor: hand;" +
            "-fx-background-radius: 5; -fx-min-width: 200;"
        );
        btnVolver.setOnAction(e -> new OperadorView(stage));

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #ecf0f1;");
        layout.getChildren().addAll(
            titulo, new Separator(), areaTexto, btnVolver);

        Scene scene = new Scene(layout, 550, 450);
        stage.setTitle("PARKUQ - Vehiculos");
        stage.setScene(scene);
        stage.show();
    }
}