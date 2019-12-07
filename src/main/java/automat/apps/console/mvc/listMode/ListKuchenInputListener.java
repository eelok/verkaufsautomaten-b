package automat.apps.console.mvc.listMode;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Automat;

import java.util.List;
import java.util.stream.Collectors;

public class ListKuchenInputListener implements InputEventListener {
    private Automat automat;

    public ListKuchenInputListener(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText().equals("kuchen")) {
            List<String> kuchenAndFach = getKuchenAndFach();
            if (kuchenAndFach.isEmpty()) {
                System.out.println("No Kuchen Available in the Automat");
            }
            kuchenAndFach.forEach(System.out::println);
        }
    }

    private List<String> getKuchenAndFach() {
        return automat.getAllEingelagertenKuchen().stream()
                .map(kuchen -> {
                    int fachnummerZuBestimmtenKuchen = automat.getFachnummerZuBestimmtenKuchen(kuchen);
                    return String.format("%s : f%s", kuchen, fachnummerZuBestimmtenKuchen);
                })
                .collect(Collectors.toList());
    }
}
