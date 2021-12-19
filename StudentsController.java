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
import javafx.collections.FXCollections;
import java.sql.*;
import javafx.scene.control.Alert.AlertType;

public class StudentsController{
    static {
        //populating our student list with dummy values
        populateStudentsList(Main.studentList);
    }
    //method to populate observable list with dummy values
    public static void populateStudentsList(ObservableList<Student> list){
        String query = "SELECT regNo, name, section, gpa FROM students";
        try(
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt=con.createStatement();
            ResultSet result = stmt.executeQuery(query)){
            while(result.next()){
                String regNo = (String) result.getObject(1);
                String name = (String) result.getObject(2);
                String section = (String) result.getObject(3);
                String gpa = (String) result.getObject(4);
                list.add(new Student(regNo, name, section, gpa));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
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
    private ComboBox<String> sectionComboBox;
    @FXML //event handler for adding a new student
    void addStudentButtonPressed(ActionEvent event) {
        String regNo = regNoTextField.getText();
        String name = nameTextField.getText();
        String section = sectionComboBox.getValue();
        String gpa = gpaTextField.getText();
        Student newStudent = new Student(regNo, name, section, gpa);
        try(
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt=con.createStatement()){
            String query = String.format("INSERT INTO students (regNo, name, section, gpa) VALUES ('%s', '%s', '%s', '%s')", regNo, name, section, gpa);
            stmt.execute(query);
        }catch(SQLException e){
            e.printStackTrace();
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Cannot Add Record");
            errorAlert.setContentText("Invalid Input");
            errorAlert.showAndWait();
            return;
        }
        Main.studentList.add(newStudent);
    }
    @FXML //event handler for deleting a record of student
    void deleteRecordButtonPressed(ActionEvent event) {
        Student deleteStudent = studentTable.getSelectionModel().getSelectedItem();
        try(
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt=con.createStatement()){
            String query = String.format("DELETE FROM students WHERE regNo = '%s' ", deleteStudent.getRegNo());
            stmt.execute(query);
        }catch(SQLException e){
            e.printStackTrace();
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Cannot Delete Record");
            errorAlert.showAndWait();
            return;
        }
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

    public ObservableList<String> getAvailableSections(){
        ObservableList<String> sections = FXCollections.observableArrayList();
        String query = "SELECT name FROM section";
        try(
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt = con.createStatement()){
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                String section = (String) result.getObject(1);
                sections.add(section);
            }
        }catch(SQLException e){
            e.printStackTrace();
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error in Getting Sections");
            errorAlert.showAndWait();
            return null;
        }
        return sections;
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
        sectionComboBox.setItems(getAvailableSections());
    }
}
