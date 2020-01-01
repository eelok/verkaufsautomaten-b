package automat.apps.simulation.service;

import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;

import java.time.LocalDateTime;

public class UmlagerungService {

    private Automat freshKuchenAutomat;
    private CreateAutomatService automatFactory;

    public UmlagerungService(Automat freshKuchenAutomat, CreateAutomatService automatFactory) {
        this.freshKuchenAutomat = freshKuchenAutomat;
        this.automatFactory = automatFactory;
    }

    public void umlagernKuchen() {
        this.freshKuchenAutomat.setName("freshKuchenAutomat");
        int fachnummer = freshKuchenAutomat.findKuchenWithSmallestHaltbarkeit().getFachnummer();
        EinlagerungEntry kuchenForUmlagerung = freshKuchenAutomat.removeKuchenFromAutomat(fachnummer);

        System.out.println(String.format("%s {haltbarkeit in days: %s} aus %s wird in anderer Automat umgelagert",
                kuchenForUmlagerung.getKuchen().getType(),
                kuchenForUmlagerung.getKuchen().getHaltbarkeit().toDays(),
                freshKuchenAutomat.getName()
        ));
        System.out.println(String.format("%s Removing >> %s", freshKuchenAutomat.getName(), kuchenForUmlagerung.getKuchen().getType()));

        Automat oldKuchenAutomat = automatFactory.createAutomat(10);
        oldKuchenAutomat.setName("OldKuchenAutomat");
        oldKuchenAutomat.addKuchen(kuchenForUmlagerung.getKuchen(), LocalDateTime.now());

        System.out.println(String.format("%s -> Adding >> %s {haltbarkeit in days: %s}", oldKuchenAutomat.getName(), kuchenForUmlagerung.getKuchen().getType(), kuchenForUmlagerung.getKuchen().getHaltbarkeit().toDays()));
    }

}
