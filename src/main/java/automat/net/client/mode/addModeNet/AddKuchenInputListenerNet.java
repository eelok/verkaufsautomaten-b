package automat.net.client.mode.addModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.kuchen.Kuchen;
import automat.net.common.Command;
import automat.net.client.connection.DataSender;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddKuchenInputListenerNet implements InputEventListener {

    private KuchenParser kuchenParser;
    private DataSender dataSender;


    public AddKuchenInputListenerNet(KuchenParser kuchenParser, DataSender dataSender) {
        this.kuchenParser = kuchenParser;
        this.dataSender = dataSender;
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
        dataSender.addKuchen(kuchenInfo, LocalDateTime.now());
    }
}
