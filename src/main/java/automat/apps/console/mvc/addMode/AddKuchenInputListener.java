package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Automat;
import automat.apps.console.mvc.InputEvent;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.kuchen.Kuchen;
import automat.apps.console.service.KuchenParser;
import automat.net.DataSender;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddKuchenInputListener implements InputEventListener {

    private KuchenParser kuchenParser;
    private Automat automat;
    private Printer printer;
    private DataSender dataSender;


    public AddKuchenInputListener(KuchenParser kuchenParser, Automat automat, Printer printer, DataSender dataSender) {
        this.kuchenParser = kuchenParser;
        this.automat = automat;
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
//            automat.addKuchen(kuchenInfo, LocalDateTime.now());
            dataSender.sendDataToServer(event.getText());
        } catch (AutomatIsFullException ex) {
            printer.println(String.format("Can not add kuchen, reason: %s", ex.getMessage()));
        } catch (IllegalArgumentException | IOException | ClassNotFoundException e){
            printer.println(String.format("The Kuchen could not be added, reason: %s", e.getMessage()));
        }
    }
}
