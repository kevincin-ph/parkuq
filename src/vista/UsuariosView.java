package vista;

import modelo.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class UsuariosView {

    public UsuariosView(Stage stage) {
        Text titulo = new Text("Usuarios Registrados");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        TextArea areaTexto = new TextArea();
        areaTexto.setEditable(false);
        areaTexto.setStyle("-fx-font-size: 13;");
        areaTexto.setPrefHeight(300);

        StringBuilder sb = new StringBuilder();
        for (Usuario u : MainApp.usuarioService.getUsuarios()) {
            sb.append(u.toString()).append("\n\n");
        }
        if (sb.length() == 0) sb.append("No hay usuarios registrados.");
        areaTexto.setText(sb.toString());

        Button btnVolver = new Button("Volver");
        btnVolver.setStyle(
            "-fx-background-color: #e74c3c; -fx-text-fill: white;" +
            "-fx-font-size: 13; -fx-padding: 10 30; -fx-cursor: hand;" +
            "-fx-background-radius: 5; -fx-min-width: 200;"
        );
        btnVolver.setOnAction(e -> new AdminView(stage));

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #ecf0f1;");
        layout.getChildren().addAll(
            titulo, new Separator(), areaTexto, btnVolver);

        Scene scene = new Scene(layout, 500, 430);
        stage.setTitle("PARKUQ - Usuarios");
        stage.setScene(scene);
        stage.show();
    }
}