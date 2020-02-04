package automat.net.server.handler;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Kuchen;
import automat.net.common.Command;

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
            return String.format("from server: %s wrong command", inputCommand);
        }
        switch (userCommand) {
            case Q:
                break;
            case ADD_HERSTELLER:
                try {
                    String name = "";
                    name = info.replace("manufacturer:", "").trim();
                    Hersteller hersteller = new HerstellerImplementation(name);
                    automat.addHersteller(hersteller);
                    return String.format("from server: hersteller %s was added to automat", hersteller.getName());
                } catch (ManufacturerExistException | IllegalArgumentException ex) {
                    return ex.getMessage();
                }
            case ADD_KUCHEN:
                info = info.replace("kuchen:", "");
                try {
                    Kuchen kuchenInfo = kuchenParser.getKuchenInfo(info);
                    automat.addKuchen(kuchenInfo, LocalDateTime.now());
                    return String.format("from server: kuchen of type %s was added to automat", kuchenInfo.getType());
                } catch (AutomatIsFullException | IllegalArgumentException e) {
                    return e.getMessage();
                } catch (ArrayIndexOutOfBoundsException exe) {
                    return "wrong input";
                }
            case LIST_HERSTELLER:
                List<String> herstellerWithNumberOfKuchen = automat.getHerstellerWithNumberOfKuchen();
                if (herstellerWithNumberOfKuchen.isEmpty()) {
                    return "form server: there is no manufacturer";
                }
                return "from server: " + herstellerWithNumberOfKuchen.toString();
            case LIST_KUCHEN:
                List<String> allKuchenWithFachNum = automat.getAllKuchenWithFachNum();
                if (allKuchenWithFachNum.isEmpty()) {
                    return "from server: No Kuchen Available in the Automat";
                }
                return "from server: " + allKuchenWithFachNum.toString();
            case DELETE_HERSTELLER:
                try {
                    String name = "";
                    name = info.replace("manufacturer:", "").trim();
                    automat.deleteHersteller(name);
                    return String.format("from server: hersteller %s was deleted from automat", name);
                } catch (IllegalArgumentException ex) {
                    return ex.getMessage();
                }
            case DELETE_KUCHEN:
                try {
                    String fachnummer = info.replace("kuchen:", "").trim();
                    EinlagerungEntry einlagerungEntry = automat.removeKuchenFromAutomat(Integer.parseInt(fachnummer));
                    return String.format("from server: %s from fach %s was deleted", einlagerungEntry.getKuchen().getType(), einlagerungEntry.getFachnummer());
                } catch (IllegalArgumentException message) {
                    return message.getMessage();
                }
        }
        return null;
    }

}
