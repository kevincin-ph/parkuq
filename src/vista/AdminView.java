package vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class AdminView {

    public AdminView(Stage stage) {
        Text titulo = new Text("Menu Administrador");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        String estilo =
            "-fx-background-color: #27ae60; -fx-text-fill: white;" +
            "-fx-font-size: 13; -fx-padding: 10 30; -fx-cursor: hand;" +
            "-fx-background-radius: 5; -fx-min-width: 250;";

        String estiloRojo =
            "-fx-background-color: #e74c3c; -fx-text-fill: white;" +
            "-fx-font-size: 13; -fx-padding: 10 30; -fx-cursor: hand;" +
            "-fx-background-radius: 5; -fx-min-width: 250;";

        Button btnUsuarios  = new Button("Ver Usuarios");
        Button btnRegistrar = new Button("Registrar Usuario");
        Button btnEspacios  = new Button("Gestionar Espacios");
        Button btnVolver    = new Button("Cerrar Sesion");

        btnUsuarios.setStyle(estilo);
        btnRegistrar.setStyle(estilo);
        btnEspacios.setStyle(estilo);
        btnVolver.setStyle(estiloRojo);

        btnUsuarios.setOnAction(e  -> new UsuariosView(stage));
        btnRegistrar.setOnAction(e -> new RegistrarUsuarioView(stage));
        btnEspacios.setOnAction(e  -> new EspaciosView(stage));
        btnVolver.setOnAction(e    -> MainApp.mostrarLogin(stage));

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #ecf0f1;");
        layout.getChildren().addAll(
            titulo, new Separator(),
            btnUsuarios, btnRegistrar,
            btnEspacios,
            new Separator(), btnVolver
        );

        Scene scene = new Scene(layout, 500, 400);
        stage.setTitle("PARKUQ - Administrador");
        stage.setScene(scene);
        stage.show();
    }
}
