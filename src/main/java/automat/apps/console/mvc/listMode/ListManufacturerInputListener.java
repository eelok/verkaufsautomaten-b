package automat.apps.console.mvc.listMode;

import automat.mainlib.Verwaltung;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.hersteller.Hersteller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListManufacturerInputListener implements InputEventListener {
    private Verwaltung verwaltung;

    public ListManufacturerInputListener(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText().equals("manufacturer")) {
            Set<Hersteller> herstellerList = verwaltung.getHerstellerList();
            List<String> herstellerKuchenCount = getHerstellerKuchenCount(herstellerList);
            herstellerKuchenCount.forEach(System.out::println);
        }
    }

    private List<String> getHerstellerKuchenCount(Set<Hersteller> herstellerList) {
        return herstellerList.stream()
                .map(hersteller -> {
                    long anzahlKuchenZuHersteller = verwaltung.getAnzahlKuchenZuHersteller(hersteller.getName());
                    return hersteller.getName() + " : " + anzahlKuchenZuHersteller;
                })
                .collect(Collectors.toList());
    }

}
