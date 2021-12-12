import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.sql.*;

public class CoursesController {
    static {
        //populating our student list with dummy values
        populateStudentsList(Main.courseList);
    }
    //method to populate observable list with dummy values
    public static void populateStudentsList(ObservableList<Course> list){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt=con.createStatement();
            String query = "SELECT code, name FROM courses";
            ResultSet result = stmt.executeQuery(query);
            ResultSetMetaData data = result.getMetaData();
            int columns = data.getColumnCount();
            while(result.next()){
                String code = (String) result.getObject(1);
                String name = (String) result.getObject(2);
                list.add(new Course(code, name));

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
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
        String code = courseCodeTextField.getText();
        String name = courseNameTextField.getText();
        Course newCourse = new Course(code , name);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt=con.createStatement();
            String query = String.format("INSERT INTO courses (code, name) VALUES ('%s', '%s')", code, name);
            stmt.execute(query);
        }catch(SQLException e){
            e.printStackTrace();
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Cannot Add Record");
            errorAlert.setContentText("Invalid Input");
            errorAlert.showAndWait();
            return;
        }
        Main.courseList.add(newCourse);
    }
    @FXML //event handler for deleting a record of course
    void deleteRecordButtonPressed(ActionEvent event) {
        Course deleteCourse = coursesTable.getSelectionModel().getSelectedItem();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt=con.createStatement();
            String query = String.format("DELETE FROM courses WHERE code = '%s' ", deleteCourse.getCourseCode());
            stmt.execute(query);
        }catch(SQLException e){
            e.printStackTrace();
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Cannot Delete Record");
            errorAlert.showAndWait();
            return;
        }
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
