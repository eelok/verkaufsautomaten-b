package automat.apps.fx;

import automat.apps.fx.model.HerstellerFX;
import automat.apps.fx.model.KuchenFx;
import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.Hersteller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AutomatController implements Initializable {

    private Automat automat;

    @FXML
    private TextField herstellerInput;

    @FXML
    private Button submitHersteller;

    @FXML
    private ListView<Hersteller> herstellerListView;

    @FXML
    private TextField priceInput;

    @FXML
    private TextField naehrwertInput;

    @FXML
    private TextField inputHerstellerWithKuchen;

    @FXML
    private TextField allergensInput;

    @FXML
    private TextField haltbarkeitInput;

    @FXML
    private TableView<EinlagerungEntry> allKuchenTable;

    @FXML
    private TableColumn<EinlagerungEntry, Integer> fachCol;

    @FXML
    private TableColumn<EinlagerungEntry, String> kuchenCol;

    @FXML
    private TableColumn<EinlagerungEntry, String> haltbarkeitCol;

    @FXML
    private TableColumn<EinlagerungEntry, String> herstellerCol;

    @FXML
    private Button deleteKuchen;

    @FXML
    private Button addKuchen;

    @FXML
    private Button addObstkuchen;

    @FXML
    private Button addKremkuchen;

    @FXML
    public Button addObsttorte;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.automat = AutomatSingleton.getInstance();
        this.herstellerListView.setItems((ObservableList<Hersteller>) automat.getHerstellerList());
        this.allKuchenTable.setItems((ObservableList<EinlagerungEntry>) automat.getStorage());
        initCellValueInKuchenTable();

        herstellerInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    String userInputHerstellerName = herstellerInput.getText().trim().toLowerCase();
                    addNewHerstellerInList(userInputHerstellerName);
                }
            }
        });

        automat.addHersteller(new HerstellerFX("alex"));
        automat.addHersteller(new HerstellerFX("donna"));
        automat.addHersteller(new HerstellerFX("tom"));
    }

    @FXML
    void handleAddKuchen(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddKuchen.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        AddKuchenController controller = fxmlLoader.getController();

        Stage stage = new Stage();
        stage.setTitle("Add Kuchen");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleAddObstkuchen(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddObstkuchen.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        AddObstkuchenController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Add Obstkuchen");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleAddObsttorte(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddObsttorte.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        AddObsttorteController controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Add Obsttorte");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleAddKremkuchen(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddKremkuchen.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        AddKremkuchenController controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Add Kremkuchen");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void handleDeleteKuchen(ActionEvent event) {
        EinlagerungEntry selectedItem = this.allKuchenTable.getSelectionModel().getSelectedItem();
        automat.removeKuchenFromAutomat(selectedItem.getFachnummer());
    }

    @FXML
    void handleSubmitHersteller(ActionEvent event) {
        String userInputHerstellerName = herstellerInput.getText().trim().toLowerCase();
        addNewHerstellerInList(userInputHerstellerName);
    }

    /**
     * https://code.makery.ch/ru/library/javafx-tutorial/part2/
     * von dem Tutorial wurde die Initialisierung von Tabelle als Beispiel verwendet
     * Teil : PersonOverviewController
     *     private void initialize() {
     *         // Инициализация таблицы адресатов с двумя столбцами.
     *         firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
     *         lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
     *     }
     */
    @FXML
    private void initCellValueInKuchenTable() {
        this.herstellerCol.setCellValueFactory(cellData ->
                ((HerstellerFX) cellData.getValue().getKuchen().getHersteller())
                        .getNameProperty()
        );
        this.haltbarkeitCol.setCellValueFactory(cellData ->
                ((KuchenFx)cellData.getValue().getKuchen()).getHaltbarkeitProperty()
        );
        this.fachCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getFachnummer()));
        this.kuchenCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getKuchen().getType()));
    }


    private void addNewHerstellerInList(String userInputHerstellerName) {
        if (!userInputHerstellerName.isEmpty()) {
            try {
                Hersteller hersteller = new HerstellerFX(userInputHerstellerName);
                automat.addHersteller(hersteller);
                herstellerInput.clear();
            } catch (ManufacturerExistException e) {
                manufacturerAlert(e.getMessage());
                herstellerInput.clear();
            }
        }
    }

    private void manufacturerAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setTitle("Information:");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
