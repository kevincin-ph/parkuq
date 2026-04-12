package vista;

import enums.TipoVehiculo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class IngresoView {

    public IngresoView(Stage stage) {
        Text titulo = new Text("Registrar Ingreso");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        TextField placaField  = new TextField();
        placaField.setPromptText("Placa del vehiculo");

        ComboBox<String> tipoBox = new ComboBox<>();
        tipoBox.getItems().addAll("CARRO", "MOTO", "BICICLETA");
        tipoBox.setPromptText("Tipo de vehiculo");
        tipoBox.setMaxWidth(Double.MAX_VALUE);

        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre del conductor");

        TextField idField = new TextField();
        idField.setPromptText("Identificacion");

        TextField horaField = new TextField();
        horaField.setPromptText("Hora de ingreso (HH:MM)");

        ComboBox<String> ampmBox = new ComboBox<>();
        ampmBox.getItems().addAll("AM", "PM");
        ampmBox.setPromptText("AM / PM");
        ampmBox.setMaxWidth(Double.MAX_VALUE);

        Label mensajeLabel = new Label("");
        mensajeLabel.setWrapText(true);

        Button btnRegistrar = new Button("Registrar Ingreso");
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
                String placa  = placaField.getText().toUpperCase();
                String tipo   = tipoBox.getValue();
                String nombre = nombreField.getText();
                String id     = idField.getText();
                String hora   = horaField.getText();
                String ampm   = ampmBox.getValue();

                if (placa.isEmpty() || tipo == null || nombre.isEmpty()
                        || id.isEmpty() || hora.isEmpty() || ampm == null) {
                    mensajeLabel.setStyle("-fx-text-fill: red;");
                    mensajeLabel.setText("Complete todos los campos.");
                    return;
                }

                String horaConvertida = convertirA24Horas(hora, ampm);
                TipoVehiculo tipoVehiculo = TipoVehiculo.valueOf(tipo);
                String resultado = MainApp.parqueadero.registrarIngreso(
                    placa, tipoVehiculo, nombre, id, horaConvertida);
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
            placaField, tipoBox,
            nombreField, idField,
            horaField, ampmBox,
            btnRegistrar, mensajeLabel,
            new Separator(), btnVolver
        );

        Scene scene = new Scene(layout, 500, 560);
        stage.setTitle("PARKUQ - Registrar Ingreso");
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