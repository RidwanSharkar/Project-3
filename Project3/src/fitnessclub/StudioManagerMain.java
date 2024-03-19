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


Useful Methods:
void setText(String value) //Sets the value of the property text
String getText() //Gets the value of the property text
To prevent the user from resizing the stage,
invoke stage.setResizable(false).
*/

public class StudioManagerMain extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(StudioManagerMain.class.getResource("studioManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}