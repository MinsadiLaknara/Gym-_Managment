package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.mongodb.client.MongoCollection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

public class GUI extends Application {
    TableView<Object> table;
    public String searchFirstName;

    public static MongoCollection<Document> collection = MyGymManager.database.getCollection("gymMember");

    public ObservableList<Object> getTableData(){
        ObservableList<Object> objectList = FXCollections.observableArrayList();
        for (Document document : collection.find()) {
            DataBase newData = new DataBase();
            newData.setMemberID((Integer) document.get("memberID"));
            newData.setFirstName(String.valueOf(document.get("firstName")));
            newData.setLastName(String.valueOf(document.get("lastName")));
            objectList.addAll(newData);
        }
        return objectList;
    }

    public ObservableList<Object> getTableSearch(){
        ObservableList<Object> objectList = FXCollections.observableArrayList();
        for (Document document : collection.find(eq("firstName", searchFirstName))) {
            DataBase newData = new DataBase();
            newData.setMemberID((Integer) document.get("memberID"));
            newData.setFirstName(String.valueOf(document.get("firstName")));
            newData.setLastName(String.valueOf(document.get("lastName")));
            objectList.addAll(newData);
        }
        return objectList;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GYM");
        Image img = new Image("file:gym.jpg");

        AnchorPane pane = new AnchorPane();

        Label lbl_Title = new Label("DETAILS");
        lbl_Title.setLayoutX(100);
        lbl_Title.setLayoutY(20);



        Button btn_search = new Button("Search");
        btn_search.setLayoutX(70);
        btn_search.setLayoutY(70);
        btn_search.setPrefSize(100, 25);
        btn_search.setOnAction(event -> searchTable());


        TextField txt_search = new TextField();
        txt_search.setPromptText("First Name");
        txt_search.setLayoutY(70);
        txt_search.setLayoutX(200);
        txt_search.setPrefSize(250, 35);
        txt_search.setOnMouseExited(event -> searchFirstName = txt_search.getText());

        TableColumn<Object, Integer> IDColumn = new TableColumn<>("Member Id");
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        IDColumn.setMinWidth(100);

        TableColumn<Object, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameColumn.setMinWidth(300);

        TableColumn<Object, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setMinWidth(300);

        table = new TableView<>();
        table.setLayoutX(60);
        table.setLayoutY(150);
        table.setItems(getTableData());
        table.getColumns().addAll(IDColumn,firstNameColumn,lastNameColumn);

        pane.getChildren().addAll(lbl_Title, txt_search, btn_search, table);

        Scene scene = new Scene(pane, 800, 700);
        primaryStage.show();
        primaryStage.setScene(scene);

    }
    public class DataBase {
        int memberID;
        String firstName;
        String lastName;

        public void setMemberID(int memberID) { this.memberID = memberID;}
        public void setFirstName(String firstName) {this.firstName = firstName;}
        public void setLastName(String lastName) {this.lastName = lastName;}

        public int getMemberID() {return memberID;}
        public String getFirstName() {return firstName;}
        public String getLastName() {return lastName;}

    }




    void searchTable(){table.setItems(getTableSearch());}

    public static void main(String[] args) {
        launch(args);
    }

}




