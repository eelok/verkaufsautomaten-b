package automat.apps.fx;

import automat.apps.fx.model.Obstkuchen;
import automat.mainlib.Automat;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.kuchen.Allergen;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddObstkuchenController implements Initializable {

    private Automat automat;
    @FXML
    private TextField priceUserInput;

    @FXML
    private TextField naehwerteUserInput;

    @FXML
    private TextField haltbarkeitUserInput;

    @FXML
    private TextField obstsorteUserInput;

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
    private Button submitObstkuchen;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.automat = AutomatSingleton.getInstance();
        this.herstellerChoiceBox.setItems((ObservableList<Hersteller>) automat.getHerstellerList());
        this.herstellerChoiceBox.getSelectionModel().selectFirst();
        setNameCheckbox();
        this.tempInput();
    }

    @FXML
    void handleSubmitKuchen(ActionEvent event) {
        String priceInputText = this.priceUserInput.getText();
        String naehrwertInputText = this.naehwerteUserInput.getText().trim();
        String haltbarkeitInputText = this.haltbarkeitUserInput.getText().trim();
        String obstsorteInputText = this.obstsorteUserInput.getText().trim();

        Hersteller selectedItem = this.herstellerChoiceBox.getSelectionModel().getSelectedItem();

        List<Allergen> allergens = new ArrayList<>();
        List<CheckBox> checkBoxes = Arrays.asList(erdnussCheckBox, glutenCheckBox, haselnussCheckBox, sesamsamenCheckBox);
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                Allergen allergen = Allergen.valueOf(checkBox.getText());
                allergens.add(allergen);
            }
        }

        try {
            automat.addKuchen(
                    new Obstkuchen(
                            new BigDecimal(priceInputText),
                            selectedItem,
                            allergens,
                            Integer.parseInt(naehrwertInputText),
                            Duration.ofDays(Long.parseLong(haltbarkeitInputText)),
                            obstsorteInputText
                    ),
                    LocalDateTime.now()
            );
            int platzImAutomat = automat.getPlatzImAutomat();
            int numberOfKuchenInAutomat = automat.getAllEingelagertenKuchen().size();
            if (platzImAutomat - numberOfKuchenInAutomat == 1) {
                showInfo();
            }
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        } catch (NullPointerException e) {
            showWarning("Please check input value");
        } catch (NumberFormatException nex) {
            showWarning("Please enter a valid number");
        } catch (AutomatIsFullException ex) {
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

    private void showInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setContentText("Only one place in automat left");
        alert.showAndWait();
    }

    private void tempInput() {
        this.priceUserInput.setText("23.89");
        this.haltbarkeitUserInput.setText("3");
        this.naehwerteUserInput.setText("780");
        this.obstsorteUserInput.setText("Himbeeren");
    }

    private void setNameCheckbox() {
        this.glutenCheckBox.setText(Allergen.GLUTEN.name());
        this.erdnussCheckBox.setText(Allergen.ERDNUSS.name());
        this.sesamsamenCheckBox.setText(Allergen.SESAMSAMEN.name());
        this.haselnussCheckBox.setText(Allergen.HASELNUSS.name());
    }
}
