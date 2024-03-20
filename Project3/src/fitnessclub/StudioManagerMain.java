package fitnessclub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/*
You MUST use:
- TextField
- Button
- RedioButton
- TextArea
- TableView
- TabPane
- GridPane
You MUST set a proper title for the window (stage)

To prevent the user from resizing the stage,
invoke stage.setResizable(false).

LABEL Useful Methods:
void setText(String value) //Sets the value of the property text
String getText() //Gets the value of the property text

BUTTON Useful Methods:
void setOnAction(EventHandler<ActionEvent> value)           //Sets the value of the property onAction
void setDisable(boolean value)                              //Sets the value of the property disable

CHECKBOX Useful Methods:
boolean isSelected()                                        //Gets the value of the property selected
void setSelected(boolean value)                             //Sets the value of the property selected
void setDisable(boolean value)                              //Sets the value of the property disable

RadioButton and RadioButtonGroup Useful Methods:
boolean isSelected()                                        //Gets the value of the property selected
void setSelected(boolean value)                             //Sets the value of the property selected
void setDisable(boolean value)                              //Sets the value of the property disable
*/

public class StudioManagerMain extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(StudioManagerMain.class.getResource("/studioManagerView.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        stage.setTitle("RU Fitness Club - Studio Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}

