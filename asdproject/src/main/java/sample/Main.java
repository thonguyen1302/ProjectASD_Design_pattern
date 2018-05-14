package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mum.asd.Service.RoomService;
import mum.asd.SupportFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("mum.asd")
@SpringBootApplication
public class Main extends Application {

    @Autowired
    RoomService roomService;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
//        RoomService roomService = new RoomService();
//        roomService.addProjectSample(203);
    }


    public static void main(String[] args) {
        SpringApplication.run(SupportFile.class, args);
        launch(args);
    }
}
