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

public class StudentsController{
    static {
        //populating our student list with dummy values
        populateStudentsList(Main.studentList);
    }
    //method to populate observable list with dummy values
    public static void populateStudentsList(ObservableList<Student> list){
        list.add(new Student("FA20-BCS-087", "Hammad Ali", "FA20-BCS-A", "4.0"));
        list.add(new Student("FA20-BCS-001", "Hassan Abdullah", "FA20-BCS-A", "3.8"));
        list.add(new Student("FA20-BCS-004", "Hamid Ali", "FA20-BCS-A", "3.5"));
        list.add(new Student("FA20-BCS-009", "Muhammad Ahmed", "FA20-BCS-A", "2.7"));
    }
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> sectionColumn;
    @FXML
    private TableColumn<Student, String> gpaColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> regNoColumn;
    @FXML
    private TextField regNoTextField;
    @FXML
    private TextField gpaTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField sectionTextField;
    @FXML //event handler for adding a new student
    void addStudentButtonPressed(ActionEvent event) {
        Student newStudent = new Student(regNoTextField.getText(), nameTextField.getText(),
                     sectionTextField.getText(), gpaTextField.getText());
        Main.studentList.add(newStudent);
    }
    @FXML //event handler for deleting a record of student
    void deleteRecordButtonPressed(ActionEvent event) {
        Student deleteStudent = studentTable.getSelectionModel().getSelectedItem();
        studentTable.getItems().remove(deleteStudent);
    }
    @FXML
    void sectionsButtonPressed(ActionEvent event) throws Exception{
        changeScene("Sections.fxml");
    }
    @FXML
    void coursesButtonPressed(ActionEvent event) throws Exception{
        changeScene("Courses.fxml");
    }
    //method for changing root node of a scene
    public void changeScene(String fxml) throws Exception{
       Parent newRoot = FXMLLoader.load(getClass().getResource(fxml));
       Scene currentScene = studentTable.getScene();
       currentScene.setRoot(newRoot);
    }

    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
        regNoColumn.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        gpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        //adding list to table
        studentTable.setItems(Main.studentList);
        //sorting students by their registration number
        studentTable.getSortOrder().add(regNoColumn);
    }
}
