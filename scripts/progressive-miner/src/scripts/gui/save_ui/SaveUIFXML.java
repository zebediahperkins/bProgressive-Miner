package scripts.gui.save_ui;

public class SaveUIFXML {
    public static String get = """
            <?xml version="1.0" encoding="UTF-8"?>

            <?import javafx.scene.control.*?>
            <?import javafx.scene.layout.*?>
            <?import javafx.scene.text.*?>

            <AnchorPane maxHeight="150.0" maxWidth="250.0" minHeight="150.0" minWidth="250.0" prefHeight="150.0" prefWidth="250.0"
                        xmlns="http://javafx.com/javafx/11.0.1" xmlns:fx="http://javafx.com/fxml/1"
                        fx:controller="scripts.gui.save_ui.SaveUIController">
                <children>
                    <VBox alignment="CENTER" maxHeight="150.0" maxWidth="250.0" minHeight="150.0" minWidth="250.0"
                          prefHeight="150.0" prefWidth="250.0" spacing="5.0">
                        <children>
                            <Label text="Save As">
                                <font>
                                    <Font name="System Bold" size="12.0"/>
                                </font>
                            </Label>
                            <TextField fx:id="profileNameTextField" maxHeight="30.0" maxWidth="150.0" minHeight="30.0"
                                       minWidth="150.0" prefHeight="30.0" prefWidth="150.0" promptText="Profile Name"/>
                            <Button maxHeight="30.0" maxWidth="50.0" minHeight="30.0" minWidth="50.0" mnemonicParsing="false"
                                    onAction="#saveButtonClicked" prefHeight="30.0" prefWidth="50.0" text="Save"/>
                        </children>
                    </VBox>
                </children>
            </AnchorPane>
            """;
}
