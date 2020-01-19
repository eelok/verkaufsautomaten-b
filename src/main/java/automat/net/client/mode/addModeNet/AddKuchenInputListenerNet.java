package automat.net.client.mode.addModeNet;

import automat.net.client.mode.inputEvent.InputEvent;
import automat.net.client.mode.inputEvent.InputEventListener;
import automat.net.common.KuchenParser;
import automat.mainlib.kuchen.Kuchen;
import automat.net.common.Command;
import automat.net.client.connection.DataSender;

import java.io.IOException;

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
        try {
            dataSender.sendDataToServer(event.getText(), Command.ADD_KUCHEN);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
