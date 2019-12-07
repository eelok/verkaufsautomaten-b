package automat.mvc.addMode;

import automat.Verwaltung;
import automat.mvc.InputEvent;
import automat.mvc.InputEventListener;
import kuchen.Kuchen;
import kuchen.KuchenParser;

import java.time.LocalDateTime;

public class AddKuchenInputListener implements InputEventListener {

    private KuchenParser kuchenParser;
    private Verwaltung verwaltung;

    public AddKuchenInputListener(KuchenParser kuchenParser, Verwaltung verwaltung) {
        this.kuchenParser = kuchenParser;
        this.verwaltung = verwaltung;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        Kuchen kuchenInfo = kuchenParser.getKuchenInfo(event.getText());
        if (kuchenInfo == null) {
            return;
        }
        try {
            verwaltung.addKuchen(kuchenInfo, LocalDateTime.now());
        } catch (IllegalArgumentException ex) {
            System.out.println(String.format("The Kuchen could not be added, reason: %s", ex.getMessage()));
        }
    }
}
