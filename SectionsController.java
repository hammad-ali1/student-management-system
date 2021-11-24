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

public class SectionsController {
    static{
        populateSectionsList(Main.sectionList);
    }
    //method to populate observable list with dummy values
    public static void populateSectionsList(ObservableList<Section> list){
        list.add(new Section("FA20-BCS-A", "CSC 101", "MTH 101", "MTH 302", "PHY 202"));
        list.add(new Section("FA20-BCS-B", "CSC 101", "MTH 101", "MTH 302", "PHY 202"));
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
        Section newSection = new Section(sectionTextField.getText(), course1TextField.getText(), 
                course2TextField.getText(), course3TextField.getText(), course4TextField.getText());
        Main.sectionList.add(newSection);
    }
    @FXML //event handler for deleting a record of section
    void deleteRecordButtonPressed(ActionEvent event) {
        Section deleteSection = sectionsTable.getSelectionModel().getSelectedItem();
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
