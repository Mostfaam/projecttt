package com.example.test_javafx;

import com.example.test_javafx.models.DataModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(DataModel.getSetion()));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("University");
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/uni.jpg"))));
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("The program will be closed");
            alert.setHeaderText("Do you want to save the entered data?");
            alert.setContentText(null);

            ButtonType save = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.FINISH);
            ButtonType do_not_save = new ButtonType("Don't Save", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(save, cancel, do_not_save);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == save) {
                DataModel model = new DataModel();
                model.save_Students();
                model.saveTeatchers();
                model.saveCourse();
                model.saveLectures();
            } else if (result.get() == cancel) {
                event.consume();
            }
        });
    }
}
