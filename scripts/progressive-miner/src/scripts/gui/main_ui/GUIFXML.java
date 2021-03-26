package scripts.gui.main_ui;

public class GUIFXML {
    public static String get = """
            <?xml version="1.0" encoding="UTF-8"?>

            <?import javafx.geometry.*?>
            <?import javafx.scene.control.*?>
            <?import javafx.scene.layout.*?>

            <AnchorPane maxHeight="400.0" maxWidth="350.0" minHeight="400.0" minWidth="350.0" prefHeight="400.0" prefWidth="350.0" xmlns="http://javafx.com/javafx/11.0.1" xmlns:fx="http://javafx.com/fxml/1" fx:controller="scripts.gui.main_ui.GUIController">
                <children>
                    <TabPane maxHeight="400.0" maxWidth="350.0" minHeight="400.0" minWidth="350.0" prefHeight="400.0" prefWidth="350.0" tabClosingPolicy="UNAVAILABLE" tabMaxHeight="20.0" tabMaxWidth="50.0" tabMinHeight="20.0" tabMinWidth="50.0">
                        <tabs>
                            <Tab text="Mining">
                                <content>
                                    <AnchorPane minHeight="0.0" minWidth="0.0" prefHeight="180.0" prefWidth="200.0">
                                        <children>
                                            <VBox alignment="CENTER" maxHeight="320.0" maxWidth="350.0" minHeight="320.0" minWidth="350.0" prefHeight="320.0" prefWidth="350.0" spacing="15.0">
                                                <children>
                                                    <ListView fx:id="taskListView" maxHeight="250.0" maxWidth="320.0" minHeight="250.0" minWidth="320.0" prefHeight="250.0" prefWidth="320.0" />
                                                    <HBox alignment="CENTER" maxHeight="30.0" maxWidth="350.0" minHeight="30.0" minWidth="350.0" prefHeight="30.0" prefWidth="350.0" spacing="15.0">
                                                        <children>
                                                            <Button fx:id="addTaskButton" maxHeight="25.0" maxWidth="100.0" minHeight="25.0" minWidth="100.0" mnemonicParsing="false" onAction="#addTaskButtonPressed" prefHeight="25.0" prefWidth="100.0" text="Add Task" />
                                                            <Button fx:id="removeTaskButton" maxHeight="25.0" maxWidth="100.0" minHeight="25.0" minWidth="100.0" mnemonicParsing="false" onAction="#removeTaskButtonPressed" prefHeight="25.0" prefWidth="100.0" text="Remove Task" />
                                                        </children>
                                                    </HBox>
                                                </children>
                                            </VBox>
                                        </children>
                                    </AnchorPane>
                                </content>
                            </Tab>
                            <Tab text="Anti-Ban">
                                <content>
                                    <AnchorPane minHeight="0.0" minWidth="0.0" prefHeight="180.0" prefWidth="200.0" />
                                </content>
                            </Tab>
                            <Tab text="Profiles">
                                <content>
                                    <AnchorPane minHeight="0.0" minWidth="0.0" prefHeight="180.0" prefWidth="200.0" />
                                </content>
                            </Tab>
                        </tabs>
                    </TabPane>
                    <ToolBar layoutY="350.0" maxHeight="50.0" maxWidth="350.0" minHeight="50.0" minWidth="350.0" prefHeight="50.0" prefWidth="350.0">
                        <items>
                            <CheckBox fx:id="drawPaintBox" maxHeight="25.0" maxWidth="115.0" minHeight="25.0" minWidth="115.0" mnemonicParsing="false" prefHeight="25.0" prefWidth="115.0" text="Draw Paint">
                                <padding>
                                    <Insets left="25.0" right="25.0" />
                                </padding>
                            </CheckBox>
                            <Button fx:id="startButton" maxHeight="25.0" maxWidth="100.0" minHeight="25.0" minWidth="100.0" mnemonicParsing="false" onAction="#startButtonPressed" prefHeight="25.0" prefWidth="100.0" text="Start Mining" />
                        </items>
                    </ToolBar>
                </children>
            </AnchorPane>
            """;
}
