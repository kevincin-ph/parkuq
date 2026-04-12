package vista;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import servicio.ParqueaderoService;
import servicio.UsuarioService;

public class MainApp extends Application {

    public static ParqueaderoService parqueadero = new ParqueaderoService();
    public static UsuarioService usuarioService  = new UsuarioService();

    @Override
    public void start(Stage stage) {
        mostrarLogin(stage);
    }

    public static void mostrarLogin(Stage stage) {
        Text titulo = new Text("PARKUQ");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        titulo.setFill(Color.web("#2c3e50"));

        Text subtitulo = new Text("Sistema de Gestión de Parqueadero");
        subtitulo.setFont(Font.font("Arial", 14));
        subtitulo.setFill(Color.web("#7f8c8d"));

        TextField usuarioField = new TextField();
        usuarioField.setPromptText("Usuario");
        usuarioField.setMaxWidth(300);
        usuarioField.setStyle("-fx-padding: 10; -fx-font-size: 14;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-padding: 10; -fx-font-size: 14;");

        Label mensajeError = new Label("");
        mensajeError.setStyle("-fx-text-fill: red; -fx-font-size: 12;");

        Button btnLogin = new Button("Ingresar");
        btnLogin.setStyle(
            "-fx-background-color: #2c3e50; -fx-text-fill: white;" +
            "-fx-font-size: 14; -fx-padding: 10 40; -fx-cursor: hand;" +
            "-fx-background-radius: 5;"
        );

        btnLogin.setOnAction(e -> {
            String usuario  = usuarioField.getText();
            String password = passwordField.getText();
            if (usuario.equals("operador") && password.equals("1234")) {
                new OperadorView(stage);
            } else if (usuario.equals("admin") && password.equals("admin")) {
                new AdminView(stage);
            } else {
                mensajeError.setText("Usuario o contraseña incorrectos");
            }
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-background-color: #ecf0f1;");
        layout.getChildren().addAll(
            titulo, subtitulo,
            new Separator(),
            usuarioField, passwordField,
            mensajeError, btnLogin
        );

        Scene scene = new Scene(layout, 500, 450);
        stage.setTitle("PARKUQ - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
