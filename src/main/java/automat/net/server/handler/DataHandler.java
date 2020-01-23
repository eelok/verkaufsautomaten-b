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
import automat.net.server.Server;

import java.time.LocalDateTime;
import java.util.List;

public class DataHandler {

    private Automat automat;
    private KuchenParser kuchenParser;

    public DataHandler(Automat automat, KuchenParser kuchenParser) {
        this.automat = automat;
        this.kuchenParser = kuchenParser;
    }

    public Object handleData(String inputCommand, String info) {
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
                    Hersteller hersteller = new HerstellerImplementation(info);
                    automat.addHersteller(hersteller);
                    return String.format("from server: hersteller %s was added to automat", hersteller.getName());
                } catch (ManufacturerExistException ex) {
                    return ex.getMessage();
                }
            case ADD_KUCHEN:
                Kuchen kuchenInfo = kuchenParser.getKuchenInfo(info);
                try {
                    EinlagerungEntry einlagerungEntry = automat.addKuchen(kuchenInfo, LocalDateTime.now());
                    return new ServerResponse(einlagerungEntry);
                } catch (AutomatIsFullException | IllegalArgumentException e) {
                    ServerResponse serverResponse = new ServerResponse();
                    serverResponse.setError(e.getMessage());
                    return serverResponse;
                }
            case LIST_HERSTELLER:
                List<String> herstellerWithNumberOfKuchen = automat.getHerstellerWithNumberOfKuchen();
                if(herstellerWithNumberOfKuchen.isEmpty()){
                    return "form server: there is no manufacturer";
                }
//                return "from server: " + herstellerWithNumberOfKuchen.toString();
                return herstellerWithNumberOfKuchen;
            case LIST_KUCHEN:
                List<String> allKuchenWithFachNum = automat.getAllKuchenWithFachNum();
                if(allKuchenWithFachNum.isEmpty()){
                    return "from server: No Kuchen Available in the Automat";
                }
                return "from server: " + allKuchenWithFachNum.toString();
            case DELETE_HERSTELLER:
                try {
                    automat.deleteHersteller(info);
                    return String.format("from server: hersteller %s was deleted from automat", info);
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
