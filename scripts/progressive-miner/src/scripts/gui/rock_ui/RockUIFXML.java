package scripts.gui.rock_ui;

public class RockUIFXML {
    public static String get = """
            <?xml version="1.0" encoding="UTF-8"?>

            <?import javafx.scene.control.*?>
            <?import javafx.scene.layout.*?>
            <?import javafx.scene.text.*?>

            <AnchorPane maxHeight="200.0" maxWidth="300.0" minHeight="200.0" minWidth="300.0" prefHeight="200.0" prefWidth="300.0"
                        xmlns="http://javafx.com/javafx/11.0.1" xmlns:fx="http://javafx.com/fxml/1"
                        fx:controller="scripts.gui.rock_ui.RockUIController">
                <children>
                    <VBox alignment="CENTER" maxHeight="200.0" maxWidth="300.0" minHeight="200.0" minWidth="300.0"
                          prefHeight="200.0" prefWidth="300.0" spacing="5.0">
                        <children>
                            <Label text="Add Rock">
                                <font>
                                    <Font name="System Bold" size="12.0"/>
                                </font>
                            </Label>
                            <Label text="Enter tile coordinates, or click the tile on screen"/>
                            <TextField fx:id="tileXTextField" maxHeight="30.0" maxWidth="150.0" minHeight="30.0" minWidth="150.0"
                                       prefHeight="30.0" prefWidth="150.0" promptText="RSTile &quot;X&quot; Value"/>
                            <TextField fx:id="tileYTextField" maxHeight="30.0" maxWidth="150.0" minHeight="30.0" minWidth="150.0"
                                       prefHeight="30.0" prefWidth="150.0" promptText="RSTile &quot;Y&quot; Value"/>
                            <TextField fx:id="tileZTextField" maxHeight="30.0" maxWidth="150.0" minHeight="30.0" minWidth="150.0"
                                       prefHeight="30.0" prefWidth="150.0" promptText="RSTile &quot;Z&quot; Value"/>
                            <Button maxHeight="25.0" maxWidth="50.0" minHeight="25.0" minWidth="50.0" mnemonicParsing="false"
                                    onAction="#addButtonClicked" prefHeight="25.0" prefWidth="50.0" text="Add"/>
                        </children>
                    </VBox>
                </children>
            </AnchorPane>
            """;
}
