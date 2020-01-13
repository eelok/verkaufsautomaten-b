package automat.net.addModeNet;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.kuchen.Kuchen;
import automat.net.Command;
import automat.net.DataSender;

import java.io.IOException;

public class AddKuchenInputListenerNet implements InputEventListener {

    private KuchenParser kuchenParser;
    private Printer printer;
    private DataSender dataSender;


    public AddKuchenInputListenerNet(KuchenParser kuchenParser, Printer printer, DataSender dataSender) {
        this.kuchenParser = kuchenParser;
        this.printer = printer;
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
            dataSender.sendDataToServer(event.getText(), Command.addK);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
