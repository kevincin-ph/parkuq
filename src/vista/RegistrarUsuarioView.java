package vista;

import enums.TipoUsuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class RegistrarUsuarioView {

    public RegistrarUsuarioView(Stage stage) {
        Text titulo = new Text("Registrar Usuario");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre completo");

        TextField idField = new TextField();
        idField.setPromptText("Identificacion");

        ComboBox<String> tipoBox = new ComboBox<>();
        tipoBox.getItems().addAll("ESTUDIANTE", "DOCENTE", "ADMINISTRATIVO", "VISITANTE");
        tipoBox.setPromptText("Tipo de usuario");
        tipoBox.setMaxWidth(Double.MAX_VALUE);

        Label mensajeLabel = new Label("");
        mensajeLabel.setStyle("-fx-font-size: 13;");

        Button btnRegistrar = new Button("Registrar");
        btnRegistrar.setStyle(
            "-fx-background-color: #27ae60; -fx-text-fill: white;" +
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
            String nombre = nombreField.getText();
            String id     = idField.getText();
            String tipo   = tipoBox.getValue();

            if (nombre.isEmpty() || id.isEmpty() || tipo == null) {
                mensajeLabel.setStyle("-fx-text-fill: red;");
                mensajeLabel.setText("Complete todos los campos.");
                return;
            }

            TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipo);
            String resultado = MainApp.usuarioService.registrarUsuario(
                nombre, id, tipoUsuario);
            if (resultado.startsWith("ERROR")) {
                mensajeLabel.setStyle("-fx-text-fill: red;");
            } else {
                mensajeLabel.setStyle("-fx-text-fill: green;");
            }
            mensajeLabel.setText(resultado);
        });

        btnVolver.setOnAction(e -> new AdminView(stage));

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #ecf0f1;");
        layout.getChildren().addAll(
            titulo, new Separator(),
            nombreField, idField, tipoBox,
            btnRegistrar, mensajeLabel,
            new Separator(), btnVolver
        );

        Scene scene = new Scene(layout, 500, 420);
        stage.setTitle("PARKUQ - Registrar Usuario");
        stage.setScene(scene);
        stage.show();
    }
}