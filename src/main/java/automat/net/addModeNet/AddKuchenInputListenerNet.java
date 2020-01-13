package automat.net.addModeNet;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.kuchen.Kuchen;
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
            //todo change parameter
            dataSender.sendDataToServer(event.getText());
        } catch (AutomatIsFullException ex) {
            printer.println(String.format("Can not add kuchen, reason: %s", ex.getMessage()));
        } catch (IllegalArgumentException | IOException | ClassNotFoundException e){
            printer.println(String.format("The Kuchen could not be added, reason: %s", e.getMessage()));
        }
    }
}
