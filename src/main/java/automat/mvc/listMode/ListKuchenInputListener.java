package automat.mvc.listMode;

import automat.Verwaltung;
import automat.mvc.InputEvent;
import automat.mvc.InputEventListener;

import java.util.List;
import java.util.stream.Collectors;

public class ListKuchenInputListener implements InputEventListener {
    private Verwaltung verwaltung;

    public ListKuchenInputListener(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
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
        return verwaltung.getAllEingelagertenKuchen().stream()
                .map(kuchen -> {
                    int fachnummerZuBestimmtenKuchen = verwaltung.getFachnummerZuBestimmtenKuchen(kuchen);
                    return String.format("%s : f%s", kuchen, fachnummerZuBestimmtenKuchen);
                })
                .collect(Collectors.toList());
    }
}
