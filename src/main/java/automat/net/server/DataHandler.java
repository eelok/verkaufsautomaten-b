package automat.net.server;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Kuchen;
import automat.net.Command;

import java.time.LocalDateTime;
import java.util.List;

public class DataHandler {

    private Automat automat;
    private KuchenParser kuchenParser;

    public DataHandler(Automat automat, KuchenParser kuchenParser) {
        this.automat = automat;
        this.kuchenParser = kuchenParser;
    }

    public String handleData(String inputCommand, String info) {
        Command userCommand;
        try {
            userCommand = Command.valueOf(inputCommand);
        } catch (IllegalArgumentException ex) {
            return String.format("from server: {%s} wrong command", inputCommand);
        }
        switch (userCommand) {
            case Q:
                break;
            case ADD_HERSTELLER:
                try {
                    automat.addHersteller(new HerstellerImplementation(info));
                    return String.format("from server: hersteller %s was added to automat", info);
                } catch (ManufacturerExistException ex) {
                    return ex.getMessage();
                }
            case ADD_KUCHEN:
                Kuchen kuchenInfo = kuchenParser.getKuchenInfo(info);
                try {
                    automat.addKuchen(kuchenInfo, LocalDateTime.now());
                    return String.format("from server: kuchen of type %s was added to automat", kuchenInfo.getType());
                } catch (AutomatIsFullException | IllegalArgumentException e) {
                    return e.getMessage();
                }
            case LIST_HERSTELLER:
                List<String> herstellerWithNumberOfKuchen = automat.getHerstellerWithNumberOfKuchen();
                return "from server: " + herstellerWithNumberOfKuchen.toString();
            case LIST_KUCHEN:
                List<String> allKuchenWithFachNum = automat.getAllKuchenWithFachNum();
                return "from server: " + allKuchenWithFachNum.toString();
            case DELETE_HERSTELLER:
                try {
                    automat.deleteHersteller(info);
                    return String.format("from server: harsteller %s was deleted from automat", info);
                } catch (IllegalArgumentException ex) {
                    return ex.getMessage();
                }
            case DELETE_KUCHEN:
                try {
                    EinlagerungEntry einlagerungEntry = automat.removeKuchenFromAutomat(Integer.parseInt(info));
                    return String.format("from server: %s from fach %s was deleted", einlagerungEntry.getKuchen().getType(), einlagerungEntry.getFachnummer());
                } catch (IllegalArgumentException message) {
                    return message.getMessage();
                }
        }
        return null;
    }

}
