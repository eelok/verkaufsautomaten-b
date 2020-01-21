package automat.net.client.mode.addModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.AutomatInterface;
import automat.mainlib.kuchen.Kuchen;

import java.time.LocalDateTime;

public class AddKuchenInputListenerNet implements InputEventListener {

    private KuchenParser kuchenParser;
    private AutomatInterface automat;


    public AddKuchenInputListenerNet(KuchenParser kuchenParser, AutomatInterface automat) {
        this.kuchenParser = kuchenParser;
        this.automat = automat;
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
        automat.addKuchen(kuchenInfo, LocalDateTime.now());
    }
}
