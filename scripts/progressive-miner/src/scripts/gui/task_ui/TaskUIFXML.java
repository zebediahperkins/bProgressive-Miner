package scripts.gui.task_ui;

public class TaskUIFXML {
    public static String get = """
            <?xml version="1.0" encoding="UTF-8"?>

            <?import javafx.scene.control.*?>
            <?import javafx.scene.layout.*?>

            <AnchorPane maxHeight="400.0" maxWidth="250.0" minHeight="400.0" minWidth="250.0" prefHeight="400.0" prefWidth="250.0"
                        xmlns="http://javafx.com/javafx/11.0.1" xmlns:fx="http://javafx.com/fxml/1"
                        fx:controller="scripts.gui.task_ui.TaskUIController">
                <children>
                    <VBox alignment="CENTER" maxHeight="350.0" maxWidth="250.0" minHeight="350.0" minWidth="250.0"
                          prefHeight="350.0" prefWidth="250.0" spacing="5.0">
                        <children>
                            <Label text="Rock Locations (RSTile)"/>
                            <ListView fx:id="rockListView" maxHeight="100.0" maxWidth="200.0" minHeight="100.0" minWidth="200.0"
                                      prefHeight="100.0" prefWidth="200.0"/>
                            <HBox alignment="CENTER" maxHeight="30.0" maxWidth="250.0" minHeight="30.0" minWidth="250.0"
                                  prefHeight="30.0" prefWidth="250.0" spacing="10.0">
                                <children>
                                    <Button fx:id="addRockButton" maxHeight="25.0" maxWidth="95.0" minHeight="25.0" minWidth="95.0"
                                            mnemonicParsing="false" onAction="#addRockButtonPressed" prefHeight="25.0"
                                            prefWidth="95.0" text="Add Rock"/>
                                    <Button fx:id="removeRockButton" maxHeight="25.0" maxWidth="95.0" minHeight="25.0"
                                            minWidth="95.0" mnemonicParsing="false" onAction="#removeRockButtonPressed"
                                            prefHeight="25.0" prefWidth="95.0" text="Remove Rock"/>
                                </children>
                            </HBox>
                            <HBox alignment="CENTER" maxHeight="50.0" maxWidth="250.0" minHeight="50.0" minWidth="250.0"
                                  prefHeight="50.0" prefWidth="250.0" spacing="10.0">
                                <children>
                                    <VBox alignment="CENTER" maxHeight="50.0" maxWidth="95.0" minHeight="50.0" minWidth="95.0"
                                          prefHeight="50.0" prefWidth="95.0" spacing="2.0">
                                        <children>
                                            <Label text="Start At Level:"/>
                                            <Spinner fx:id="lowLevelSpinner" maxHeight="25.0" maxWidth="95.0" minHeight="25.0"
                                                     minWidth="95.0" prefHeight="25.0" prefWidth="95.0"/>
                                        </children>
                                    </VBox>
                                    <VBox alignment="CENTER" maxHeight="50.0" maxWidth="95.0" minHeight="50.0" minWidth="95.0"
                                          prefHeight="50.0" prefWidth="95.0" spacing="2.0">
                                        <children>
                                            <Label text="End At Level:"/>
                                            <Spinner fx:id="highLevelSpinner" maxHeight="25.0" maxWidth="95.0" minHeight="25.0"
                                                     minWidth="95.0" prefHeight="25.0" prefWidth="95.0"/>
                                        </children>
                                    </VBox>
                                </children>
                            </HBox>
                            <HBox alignment="CENTER" maxHeight="50.0" maxWidth="250.0" minHeight="50.0" minWidth="250.0"
                                  prefHeight="50.0" prefWidth="250.0" spacing="10.0">
                                <children>
                                    <VBox alignment="CENTER" maxHeight="50.0" maxWidth="95.0" minHeight="50.0" minWidth="95.0"
                                          prefHeight="50.0" prefWidth="95.0" spacing="2.0">
                                        <children>
                                            <Label text="Full Inv Action:"/>
                                            <ChoiceBox fx:id="fullInvBox" maxHeight="25.0" maxWidth="95.0" minHeight="25.0"
                                                       minWidth="95.0" prefHeight="25.0" prefWidth="95.0"/>
                                        </children>
                                    </VBox>
                                    <VBox alignment="CENTER" maxHeight="50.0" maxWidth="95.0" minHeight="50.0" minWidth="95.0"
                                          prefHeight="50.0" prefWidth="95.0" spacing="2.0">
                                        <children>
                                            <Label text="Preferred Pick:"/>
                                            <ChoiceBox fx:id="prefPickBox" maxHeight="25.0" maxWidth="95.0" minHeight="25.0"
                                                       minWidth="95.0" prefHeight="25.0" prefWidth="95.0"/>
                                        </children>
                                    </VBox>
                                </children>
                            </HBox>
                            <HBox alignment="CENTER" maxHeight="25.0" maxWidth="250.0" minHeight="25.0" minWidth="250.0"
                                  prefHeight="25.0" prefWidth="250.0">
                                <children>
                                    <CheckBox fx:id="hopWorldsCheck" maxHeight="25.0" maxWidth="95.0" minHeight="25.0"
                                              minWidth="95.0" mnemonicParsing="false" prefHeight="25.0" prefWidth="95.0"
                                              text="Hop Worlds"/>
                                    <CheckBox fx:id="membersCheck" maxHeight="25.0" maxWidth="95.0" minHeight="25.0" minWidth="95.0"
                                              mnemonicParsing="false" prefHeight="25.0" prefWidth="95.0" text="Members"/>
                                </children>
                            </HBox>
                            <HBox alignment="CENTER" maxHeight="25.0" maxWidth="250.0" minHeight="25.0" minWidth="250.0"
                                  prefHeight="25.0" prefWidth="250.0">
                                <children>
                                    <CheckBox fx:id="pvpCheck" maxHeight="25.0" maxWidth="95.0" minHeight="25.0" minWidth="95.0"
                                              mnemonicParsing="false" prefHeight="25.0" prefWidth="95.0" text="Pvp Worlds"/>
                                    <CheckBox fx:id="antiPkCheck" maxHeight="25.0" maxWidth="95.0" minHeight="25.0" minWidth="95.0"
                                              mnemonicParsing="false" prefHeight="25.0" prefWidth="95.0" text="Anti-Pk"/>
                                </children>
                            </HBox>
                        </children>
                    </VBox>
                    <HBox alignment="CENTER" layoutY="350.0" maxHeight="50.0" maxWidth="250.0" minHeight="50.0" minWidth="250.0"
                          prefHeight="50.0" prefWidth="250.0">
                        <children>
                            <Button fx:id="addButton" alignment="CENTER" contentDisplay="CENTER" maxHeight="25.0" maxWidth="100.0"
                                    minHeight="25.0" minWidth="100.0" mnemonicParsing="false" onAction="#addButtonPressed"
                                    prefHeight="25.0" prefWidth="100.0" text="Add"/>
                        </children>
                    </HBox>
                </children>
            </AnchorPane>
            """;
}
