package automat.mainlib;

import automat.apps.console.Observable;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.observer.AddNewHerstellerMessage;
import automat.mainlib.hersteller.observer.RemoveHerstellerMessage;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.observer.AddNewKuchenMessage;
import automat.mainlib.kuchen.observer.RemoveKuchenMessage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Automat extends Observable {

    private EinlagerungEntry[] storage;
    private HashSet<Hersteller> allHersteller = new HashSet<>();
    private String automatName;

    public Automat(int platzImAutomat) {
        storage = new EinlagerungEntry[platzImAutomat];
    }

    public void setName(String automatName) {
        this.automatName = automatName;
    }

    public String getName() {
        return this.automatName;
    }

    public boolean isFull() {
        for (EinlagerungEntry entry : storage) {
            if (entry == null) {
                return false;
            }
        }
        return true;
    }

    public Set<Hersteller> getHerstellerList() {
        return allHersteller;
    }

    public boolean addHersteller(Hersteller hersteller) {
        if (allHersteller.add(hersteller)) {
            notifyAddNewHerstellerObservers(new AddNewHerstellerMessage(hersteller.getName()));
            return true;
        }

        throw new IllegalArgumentException(String.format("Manufacturer %s already exists", hersteller.getName()));
    }

    public void deleteHersteller(String name) {
        if (!herstellerExists(name)) {
            throw new IllegalArgumentException(String.format("Hersteller %s does not exist", name));
        }
        Hersteller hersteller = findHersteller(name);
        allHersteller.remove(hersteller);
        notifyRemoveHerstellerObserver(new RemoveHerstellerMessage(name));
    }


    public EinlagerungEntry addKuchen(Kuchen newKuchen, LocalDateTime date) {
        if (!herstellerExists(newKuchen.getHersteller().getName())) {
            throw new IllegalArgumentException(
                    String.format(
                            "No such manufacturer %s. Please add manufacturer %s.",
                            newKuchen.getHersteller().getName(), newKuchen.getHersteller().getName()
                    )
            );
        }
        int cell = findEmptyCell();
        EinlagerungEntry einlagerungEntry = new EinlagerungEntry(date, newKuchen, cell);
        storage[cell] = einlagerungEntry;
        int storageCapacity = getEinlagerungList().length;
        int numberOfKuchenInStorage = getAllEingelagertenKuchen().size();
        notifyAddNewKuchenObservers(new AddNewKuchenMessage(einlagerungEntry, storageCapacity, numberOfKuchenInStorage));

        return storage[cell];
    }


    public List<Kuchen> getAllEingelagertenKuchen() {
        return Arrays.stream(storage)
                .filter(einlagerungEntry -> einlagerungEntry != null)
                .map(einlagerungEntry -> einlagerungEntry.getKuchen())
                .collect(Collectors.toList());
    }

    public List<Kuchen> getKuchenOfType(Class<? extends Kuchen> typeOfKuchen) {
        return getAllEingelagertenKuchen().stream()
                .filter(kuchen -> typeOfKuchen.isInstance(kuchen))
                .collect(Collectors.toList());
    }

    public long getAnzahlKuchenZuHersteller(String nameOfHersteller) {
        return getAllEingelagertenKuchen().stream()
                .filter(kuchen -> kuchen.getHersteller().getName().equals(nameOfHersteller))
                .count();
    }

    public int getFachnummerZuBestimmtenKuchen(Kuchen theKuchen) {
        return storage[findIndexOfEinlagerungsEntry(theKuchen)].getFachnummer();
    }

    public Duration getRestHaltbarkeitZuBestimmtenKuchen(Kuchen theKuchen, LocalDateTime now) {
        int indexOfEinlagerungsEntry = findIndexOfEinlagerungsEntry(theKuchen);

        LocalDateTime datumVonEinlagerung = storage[indexOfEinlagerungsEntry].getEinlagerungsDatum();
        LocalDateTime deadLine = datumVonEinlagerung.plus(theKuchen.getHaltbarkeit());

        return Duration.ofDays(ChronoUnit.DAYS.between(now, deadLine));
    }


    public void removeKuchenFromAutomat(Kuchen kuchen) {
        int indexOfEinlagerungsEntry = findIndexOfEinlagerungsEntry(kuchen);
        storage[indexOfEinlagerungsEntry] = null;
        notifyRemoveKuchenObservers(new RemoveKuchenMessage(kuchen));
    }

    public List<Allergen> getAllergenenInAutomat() {
        return getAllEingelagertenKuchen().stream()
                .flatMap(kuchen -> kuchen.getAllergenes().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Allergen> getAllergenenNotInAutomat() {
        Collection<Allergen> allergenenInAutomat = getAllergenenInAutomat();

        return Stream.of(Allergen.values())
                .filter(allergen -> !allergenenInAutomat.contains(allergen))
                .collect(Collectors.toList());
    }

    public Kuchen findKuchenWithSmallestHaltbarkeit() {
        List<Kuchen> allEingelagertenKuchen = getAllEingelagertenKuchen();

        return allEingelagertenKuchen.stream()
                .min(Comparator.comparing(kuchen -> kuchen.getHaltbarkeit()))
                .orElse(null);
    }

    public EinlagerungEntry[] getEinlagerungList() {
        return storage;
    }

    private boolean herstellerExists(String name) {
        return findHersteller(name) != null;
    }

    private Hersteller findHersteller(String name) {
        return allHersteller.stream()
                .filter(hersteller -> hersteller.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private int findEmptyCell() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return i;
            }
        }

        throw new IllegalArgumentException("Der Automat ist voll");
    }

    private int findIndexOfEinlagerungsEntry(Kuchen kuchen) {
        for (int i = 0; i < storage.length; i++) {
            if (containsKuchen(kuchen, storage[i])) {
                return i;
            }
        }

        throw new IllegalArgumentException("Der Kuchen gibt es nicht");
    }

    private boolean containsKuchen(Kuchen kuchen, EinlagerungEntry einlagerungEntry) {
        if (einlagerungEntry == null) {
            return false;
        }

        return einlagerungEntry.getKuchen().equals(kuchen);
    }

}
