import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.collections.*;

public class Main extends Application{
    //observable list of students
    public static ObservableList<Student> studentList = FXCollections.observableArrayList();
    //observable list of courses
    public static ObservableList<Course> courseList = FXCollections.observableArrayList();
    //observable list of sections
    public static ObservableList<Section> sectionList = FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Students.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Student Management System");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
