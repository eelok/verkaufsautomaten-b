package automat.net;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Kuchen;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

//todo invent better name
public class Transit {

    private Automat automatInServer;
    private KuchenParser kuchenParser;


    public Transit(Automat automatInServer, KuchenParser kuchenParser) {
        this.automatInServer = automatInServer;
        this.kuchenParser = kuchenParser;
    }

    //    todo invent better name
    public String existingCommand(String inputCommand, String info) {
        Command userCommand;
        try {
            userCommand = Command.valueOf(inputCommand);
        } catch (IllegalArgumentException ex) {
            return String.format("from server: {%s} wrong command", inputCommand);
        }
        switch (userCommand) {
            case q:
                break;
            case addH:
                try {
                    automatInServer.addHersteller(new HerstellerImplementation(info));
                    return String.format("from server: hersteller %s was added to automat", info);
                } catch (ManufacturerExistException ex) {
                    return ex.getMessage();
                }
            case addK:
                Kuchen kuchenInfo = kuchenParser.getKuchenInfo(info);
                try {
                    automatInServer.addKuchen(kuchenInfo, LocalDateTime.now());
                    return String.format("from server: kuchen %s was added to automat", kuchenInfo.getType());
                } catch (IllegalArgumentException e) {
                    return e.getMessage();
                }
            case listH:
                List<String> herstellerWithNumberOfKuchen = automatInServer.getHerstellerWithNumberOfKuchen();
                return "from server: " + herstellerWithNumberOfKuchen.toString();
            case listK:
                List<String> allKuchenWithFachNum = automatInServer.getAllKuchenWithFachNum();
                return "from server: " + allKuchenWithFachNum.toString();
            case delH:
                try {
                    automatInServer.deleteHersteller(info);
                    return String.format("from server: harsteller %s was deleted from automat", info);
                } catch (IllegalArgumentException ex) {
                    return ex.getMessage();
                }
            case delK:
                try {
                    EinlagerungEntry einlagerungEntry = automatInServer.removeKuchenFromAutomat(Integer.parseInt(info));
                    return String.format("from server: %s from fach %s was deleted", einlagerungEntry.getKuchen().getType(), einlagerungEntry.getFachnummer());
                } catch (IllegalArgumentException message) {
                    return message.getMessage();
                }
        }
        return null;
    }

}
