package automat.apps.fx;

import automat.apps.fx.model.Kuchen;
import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.KuchenImplementation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddKuchenController implements Initializable {

    private Automat automat;

    @FXML
    private TextField priceUserInput;

    @FXML
    private TextField naehwerteUserInput;

    @FXML
    private TextField haltbarkeitUserInput;

    @FXML
    private CheckBox erdnussCheckBox;

    @FXML
    private CheckBox glutenCheckBox;

    @FXML
    private CheckBox haselnussCheckBox;

    @FXML
    private CheckBox sesamsamenCheckBox;

    @FXML
    private ChoiceBox<Hersteller> herstellerChoiceBox;

    @FXML
    private Button submitKuchen;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.automat = AutomatSingleton.getInstance();
        this.herstellerChoiceBox.setItems((ObservableList<Hersteller>) automat.getHerstellerList());
        this.herstellerChoiceBox.getSelectionModel().selectFirst();
        tempInput();
    }

    @FXML
    void handleSubmitKuchen(ActionEvent event) {
        String priceInputText = priceUserInput.getText();
        String naehrwertInputText = naehwerteUserInput.getText().trim();
        String haltbarkeitInputText = haltbarkeitUserInput.getText().trim();

        Hersteller selectedItem = this.herstellerChoiceBox.getSelectionModel().getSelectedItem();

        List<Allergen> allergens = Arrays.asList(erdnussCheckBox, glutenCheckBox, haselnussCheckBox, sesamsamenCheckBox).stream()
                .filter(CheckBox::isSelected)
                .map(checkBox -> Allergen.valueOf(checkBox.getText()))
                .collect(Collectors.toList());

        try {
            automat.addKuchen(
                    new Kuchen(
                            new BigDecimal(priceInputText),
                            selectedItem,
                            allergens,
                            Integer.parseInt(naehrwertInputText),
                            Duration.ofDays(Long.parseLong(haltbarkeitInputText))
                    ),
                    LocalDateTime.now()
            );
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        } catch (NullPointerException e) {
            showWarning("Please check input value");
        } catch (NumberFormatException nex) {
            showWarning("Please enter a valid number");
        } catch (IllegalArgumentException ex) {
            showWarning(ex.getMessage());
        }

    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("This is a Warning");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void tempInput() {
        this.priceUserInput.setText("15.55");
        this.haltbarkeitUserInput.setText("4");
        this.naehwerteUserInput.setText("890");
    }
}
