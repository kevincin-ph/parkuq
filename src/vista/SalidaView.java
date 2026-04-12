package vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class SalidaView {

    public SalidaView(Stage stage) {
        Text titulo = new Text("Registrar Salida");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        TextField placaField = new TextField();
        placaField.setPromptText("Placa del vehiculo");

        TextField horaField = new TextField();
        horaField.setPromptText("Hora de salida (HH:MM)");

        ComboBox<String> ampmBox = new ComboBox<>();
        ampmBox.getItems().addAll("AM", "PM");
        ampmBox.setPromptText("AM / PM");
        ampmBox.setMaxWidth(Double.MAX_VALUE);

        Label mensajeLabel = new Label("");
        mensajeLabel.setWrapText(true);
        mensajeLabel.setStyle("-fx-font-size: 13;");

        Button btnRegistrar = new Button("Registrar Salida");
        btnRegistrar.setStyle(
            "-fx-background-color: #2c3e50; -fx-text-fill: white;" +
            "-fx-font-size: 13; -fx-padding: 10 30; -fx-cursor: hand;" +
            "-fx-background-radius: 5; -fx-min-width: 200;"
        );

        Button btnVolver = new Button("Volver");
        btnVolver.setStyle(
            "-fx-background-color: #e74c3c; -fx-text-fill: white;" +
            "-fx-font-size: 13; -fx-padding: 10 30; -fx-cursor: hand;" +
            "-fx-background-radius: 5; -fx-min-width: 200;"
        );

        btnRegistrar.setOnAction(e -> {
            try {
                String placa = placaField.getText().toUpperCase();
                String hora  = horaField.getText();
                String ampm  = ampmBox.getValue();

                if (placa.isEmpty() || hora.isEmpty() || ampm == null) {
                    mensajeLabel.setStyle("-fx-text-fill: red;");
                    mensajeLabel.setText("Complete todos los campos.");
                    return;
                }

                String horaConvertida = convertirA24Horas(hora, ampm);
                String resultado = MainApp.parqueadero.registrarSalida(placa, horaConvertida);
                mensajeLabel.setStyle("-fx-text-fill: green;");
                mensajeLabel.setText(resultado);

            } catch (Exception ex) {
                mensajeLabel.setStyle("-fx-text-fill: red;");
                mensajeLabel.setText("ERROR: " + ex.getMessage());
            }
        });

        btnVolver.setOnAction(e -> new OperadorView(stage));

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #ecf0f1;");
        layout.getChildren().addAll(
            titulo, new Separator(),
            placaField, horaField, ampmBox,
            btnRegistrar, mensajeLabel,
            new Separator(), btnVolver
        );

        Scene scene = new Scene(layout, 500, 400);
        stage.setTitle("PARKUQ - Registrar Salida");
        stage.setScene(scene);
        stage.show();
    }

    private String convertirA24Horas(String hora, String ampm) {
        String[] partes = hora.split(":");
        int hh = Integer.parseInt(partes[0]);
        int mm = Integer.parseInt(partes[1]);
        if (ampm.equals("AM")) {
            if (hh == 12) hh = 0;
        } else {
            if (hh != 12) hh += 12;
        }
        String hhStr = hh < 10 ? "0" + hh : "" + hh;
        String mmStr = mm < 10 ? "0" + mm : "" + mm;
        return hhStr + ":" + mmStr;
    }
}