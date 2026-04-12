#!/bin/bash
javac --module-path /Users/macbookpro15pulgadas/javafx-sdk-26/lib --add-modules javafx.controls,javafx.fxml -cp src src/vista/*.java src/modelo/*.java src/servicio/*.java src/enums/*.java src/excepciones/*.java
java --module-path /Users/macbookpro15pulgadas/javafx-sdk-26/lib --add-modules javafx.controls,javafx.fxml -cp src vista.MainApp
