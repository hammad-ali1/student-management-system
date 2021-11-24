import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


public class CoursesController {
    static {
        //populating our student list with dummy values
        populateStudentsList(Main.courseList);
    }
    //method to populate observable list with dummy values
    public static void populateStudentsList(ObservableList<Course> list){
        list.add(new Course("CSC 101", "Object Oriented Programming"));
        list.add(new Course("MTH 101", "Linear Algebra"));
        list.add(new Course("MTH 302", "Statistics and Probability"));
        list.add(new Course("PHY 202", "Modern Physics"));
    }

    @FXML
    private TableColumn<Course, String> courseCodeColumn;
    @FXML
    private TextField courseCodeTextField;
    @FXML
    private TableColumn<Course, String> courseNameColumn;
    @FXML
    private TextField courseNameTextField;
    @FXML
    private TableView<Course> coursesTable;
    @FXML //event handler for adding a new course
    void addCourseButtonPressed(ActionEvent event) {
        Course newCourse = new Course(courseCodeTextField.getText(), courseNameTextField.getText());
        Main.courseList.add(newCourse);
    }
    @FXML //event handler for deleting a record of course
    void deleteRecordButtonPressed(ActionEvent event) {
        Course deleteCourse = coursesTable.getSelectionModel().getSelectedItem();
        coursesTable.getItems().remove(deleteCourse);
    }
    @FXML //event handler for switching to root Sections.fxml
    void sectionsButtonPressed(ActionEvent event) throws Exception{
        changeScene("Sections.fxml");
    }

    @FXML //event handler for switching to root Students.fxml
    void studentsButtonPressed(ActionEvent event) throws Exception{
        changeScene("Students.fxml");
    }
    //method for changing root node of a scene
    public void changeScene(String fxml) throws Exception{
        Parent newRoot = FXMLLoader.load(getClass().getResource(fxml));
        Scene currentScene = coursesTable.getScene();
        currentScene.setRoot(newRoot);
     }
     //initialize method (called at loading time)
    public void initialize(){
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
        //adding list to table
        coursesTable.setItems(Main.courseList);
    }
}
