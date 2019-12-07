package automat.apps.console.mvc.listMode;

import automat.mainlib.Automat;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.hersteller.Hersteller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListManufacturerInputListener implements InputEventListener {
    private Automat automat;

    public ListManufacturerInputListener(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText().equals("manufacturer")) {
            Set<Hersteller> herstellerList = automat.getHerstellerList();
            List<String> herstellerKuchenCount = getHerstellerKuchenCount(herstellerList);
            herstellerKuchenCount.forEach(System.out::println);
        }
    }

    private List<String> getHerstellerKuchenCount(Set<Hersteller> herstellerList) {
        return herstellerList.stream()
                .map(hersteller -> {
                    long anzahlKuchenZuHersteller = automat.getAnzahlKuchenZuHersteller(hersteller.getName());
                    return hersteller.getName() + " : " + anzahlKuchenZuHersteller;
                })
                .collect(Collectors.toList());
    }

}
