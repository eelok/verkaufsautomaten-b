package automat.apps.console.mvc.addMode;

import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Verwaltung;
import automat.apps.console.mvc.InputEvent;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenParser;

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
