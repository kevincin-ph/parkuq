package vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class OperadorView {

    public OperadorView(Stage stage) {
        Text titulo = new Text("Menu Operador");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        String estilo =
            "-fx-background-color: #2c3e50; -fx-text-fill: white;" +
            "-fx-font-size: 13; -fx-padding: 10 30; -fx-cursor: hand;" +
            "-fx-background-radius: 5; -fx-min-width: 250;";

        String estiloRojo =
            "-fx-background-color: #e74c3c; -fx-text-fill: white;" +
            "-fx-font-size: 13; -fx-padding: 10 30; -fx-cursor: hand;" +
            "-fx-background-radius: 5; -fx-min-width: 250;";

        Button btnIngreso   = new Button("Registrar Ingreso");
        Button btnSalida    = new Button("Registrar Salida");
        Button btnVehiculos = new Button("Ver Vehiculos Adentro");
        Button btnEspacios  = new Button("Ver Espacios Disponibles");
        Button btnVolver    = new Button("Cerrar Sesion");

        btnIngreso.setStyle(estilo);
        btnSalida.setStyle(estilo);
        btnVehiculos.setStyle(estilo);
        btnEspacios.setStyle(estilo);
        btnVolver.setStyle(estiloRojo);

        btnIngreso.setOnAction(e   -> new IngresoView(stage));
        btnSalida.setOnAction(e    -> new SalidaView(stage));
        btnVehiculos.setOnAction(e -> new VehiculosView(stage));
        btnEspacios.setOnAction(e  -> new EspaciosView(stage));
        btnVolver.setOnAction(e    -> MainApp.mostrarLogin(stage));

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #ecf0f1;");
        layout.getChildren().addAll(
            titulo, new Separator(),
            btnIngreso, btnSalida,
            btnVehiculos, btnEspacios,
            new Separator(), btnVolver
        );

        Scene scene = new Scene(layout, 500, 450);
        stage.setTitle("PARKUQ - Operador");
        stage.setScene(scene);
        stage.show();
    }
}