package automat.net;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.KremkuchenImplementation;
import automat.mainlib.kuchen.Kuchen;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

//todo invent better name
public class Transit {

    private Automat automatInServer;
    private KuchenParser kuchenParser;
    private Command existingCommand;


    public Transit(Automat automatInServer, KuchenParser kuchenParser) {
        this.automatInServer = automatInServer;
        this.kuchenParser = kuchenParser;
    }

    //todo invent better name
    public String existingCommand(String inputCommand, String inputData) throws IOException {
        Command userCommand;
        try {
            userCommand = Command.valueOf(inputCommand);
        } catch (IllegalArgumentException ex){
            return String.format("from server: {%s} wrong command", inputCommand);
        }
        switch (userCommand){
            case q:
                break;
            case addH:
                if(automatInServer.addHersteller(new HerstellerImplementation(inputData.toLowerCase()))){
                    return String.format("from server: hersteller %s was added to automat", inputData);
                }
            case addK:
                Kuchen kuchenInfo = kuchenParser.getKuchenInfo(inputData);
                try{
                    automatInServer.addKuchen(kuchenInfo, LocalDateTime.now());
                    return String.format("from server: kuchen %s was added to automat", kuchenInfo.getType());
                } catch (IllegalArgumentException e){
                    return e.getMessage();
                }
            case listH:
                List<String> herstellerWithNumberOfKuchen = automatInServer.getHerstellerWithNumberOfKuchen();
                return "from server: " + herstellerWithNumberOfKuchen.toString();
            case listK:
//                List<Kuchen> allEingelagertenKuchen = automatInServer.getAllEingelagertenKuchen();
                List<String> allKuchenWithFachNum = automatInServer.getAllKuchenWithFachNum();
                return "from server: " + allKuchenWithFachNum.toString();
            case delH:
                automatInServer.deleteHersteller(inputData);
                return String.format("from server: harsteller %s was deleted from automat", inputData);
            case delK:
                EinlagerungEntry einlagerungEntry = automatInServer.removeKuchenFromAutomat(Integer.valueOf(inputData));
//                String typeOfKuchenWasDel = deleteKuchen(Integer.valueOf());
                return String.format("from server: %s from fach %s was deleted", einlagerungEntry.getKuchen().getType(), einlagerungEntry.getFachnummer());
        }
        return null;
    }

//    private String deleteKuchen(String inputData){
//        String number = inputData.replace("f", "");
//        int fachNum = Integer.parseInt(number);
//        try {
//            EinlagerungEntry einlagerungEntry = automatInServer.removeKuchenFromAutomat(fachNum);
//            return einlagerungEntry.getKuchen().getType();
//        } catch (IllegalArgumentException ex){
//            return ex.getMessage();
//        }
//    }

}
