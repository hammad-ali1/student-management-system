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
import java.sql.*;
import javafx.scene.control.Alert.AlertType;

public class SectionsController {
    static{
        populateSectionsList(Main.sectionList);
    }
    //method to populate observable list with dummy values
    public static void populateSectionsList(ObservableList<Section> list){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt=con.createStatement();
            String query = "SELECT name, course1, course2, course3, course4 FROM section";
            ResultSet result = stmt.executeQuery(query);
            ResultSetMetaData data = result.getMetaData();
            int columns = data.getColumnCount();
            while(result.next()){
                String name = (String) result.getObject(1);
                String course1 = (String) result.getObject(2);
                String course2 = (String) result.getObject(3);
                String course3 = (String) result.getObject(4);
                String course4 = (String) result.getObject(5);
                list.add(new Section(name, course1, course2, course3, course4));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    private TableColumn<Section, String> course1Column;
    @FXML
    private TextField course1TextField;
    @FXML
    private TableColumn<Section, String> course2Column;
    @FXML
    private TextField course2TextField;
    @FXML
    private TableColumn<Section, String> course3Column;
    @FXML
    private TextField course3TextField;
    @FXML
    private TableColumn<Section, String> course4Column;
    @FXML
    private TextField course4TextField;
    @FXML
    private TableColumn<Section, String> sectionColumn;
    @FXML
    private TextField sectionTextField;
    @FXML
    private TableView<Section> sectionsTable;
    @FXML
    //event handler for adding a new section
    void addSectionButtonPressed(ActionEvent event) {
        String name = sectionTextField.getText();
        String course1 = course1TextField.getText();
        String course2 = course2TextField.getText();
        String course3 = course3TextField.getText();
        String course4 = course4TextField.getText();
        Section newSection = new Section(name, course1, course2, course3, course4);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt=con.createStatement();
            String query = String.format("INSERT INTO section (name, course1, course2, course3, course4) VALUES ('%s', '%s', '%s', '%s', '%s')", name, course1, course2, course3, course4);
            stmt.execute(query);
        }catch(SQLException e){
            e.printStackTrace();
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Cannot Add Record");
            errorAlert.setContentText("Invalid Input");
            errorAlert.showAndWait();
            return;
        }
        Main.sectionList.add(newSection);
    }
    @FXML //event handler for deleting a record of section
    void deleteRecordButtonPressed(ActionEvent event) {
        Section deleteSection = sectionsTable.getSelectionModel().getSelectedItem();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Statement stmt=con.createStatement();
            String query = String.format("DELETE FROM section WHERE name = '%s' ", deleteSection.getName());
            stmt.execute(query);
        }catch(SQLException e){
            e.printStackTrace();
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Cannot Delete Record");
            errorAlert.showAndWait();
            return;
        }
        sectionsTable.getItems().remove(deleteSection);
    }
    @FXML
    void coursesButtonPressed(ActionEvent event) throws Exception{
        changeScene("Courses.fxml");
    }

    @FXML
    void studentsButtonPressed(ActionEvent event) throws Exception{
        changeScene("Students.fxml");
    }
    //method for changing root node of a scene
    public void changeScene(String fxml) throws Exception{
        Parent newRoot = FXMLLoader.load(getClass().getResource(fxml));
        Scene currentScene = sectionsTable.getScene();
        currentScene.setRoot(newRoot);
     }
    public void initialize(){
        sectionColumn.setCellValueFactory(new PropertyValueFactory<Section, String>("name"));
        course1Column.setCellValueFactory(new PropertyValueFactory<Section, String>("course1"));
        course2Column.setCellValueFactory(new PropertyValueFactory<Section, String>("course2"));
        course3Column.setCellValueFactory(new PropertyValueFactory<Section, String>("course3"));
        course4Column.setCellValueFactory(new PropertyValueFactory<Section, String>("course4"));
        //adding list to table
        sectionsTable.setItems(Main.sectionList);
    }
}
