package automat.mainlib;

import automat.apps.console.Observable;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.observer.AddNewHerstellerMessage;
import automat.mainlib.hersteller.observer.RemoveHerstellerMessage;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.Kuchen;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Automat extends Observable implements Serializable {

    private int platzImAutomat;
    private List<EinlagerungEntry> storage;
    private List<Hersteller> allHersteller;
    private String automatName;

    public Automat() {
    }

    public Automat(int platzImAutomat, List<EinlagerungEntry> storage, List<Hersteller> herstellersList) {
        this.platzImAutomat = platzImAutomat;
        this.storage = storage;
        this.allHersteller = herstellersList;
    }

    public Automat(int platzImAutomat) {
        this.platzImAutomat = platzImAutomat;
        this.storage = new ArrayList<>();
        this.allHersteller = new ArrayList<>();
    }

    public void setName(String automatName) {
        this.automatName = automatName;
    }

    public String getName() {
        return this.automatName;
    }

    public List<Hersteller> getHerstellerList() {
        return allHersteller;
    }

    public void setHerstellerList(List<Hersteller> allHersteller) {
        this.allHersteller = allHersteller;
    }

    public List<EinlagerungEntry> getStorage() {
        return storage;
    }

    public void setStorage(List<EinlagerungEntry> storage) {
        this.storage = storage;
    }

    public int getPlatzImAutomat() {
        return platzImAutomat;
    }

    public void setPlatzImAutomat(int platzImAutomat) {
        this.platzImAutomat = platzImAutomat;
    }

    public boolean isFull() {
        try{
            findEmptyCell();
        } catch(AutomatIsFullException ex){
            return true;
        }
        return false;
    }

    public boolean addHersteller(Hersteller hersteller) {
        if (herstellerExists(hersteller.getName())) {
            throw new IllegalArgumentException(String.format("Manufacturer %s already exists", hersteller.getName()));
        }

        notifyAddNewHerstellerObservers(new AddNewHerstellerMessage(hersteller.getName()));
        allHersteller.add(hersteller);

        return true;
    }

    public void deleteHersteller(String name) {
        Hersteller hersteller = findHersteller(name);
        if (hersteller == null) {
            throw new IllegalArgumentException(String.format("Hersteller %s does not exist", name));
        }
        allHersteller.remove(hersteller);
        notifyRemoveHerstellerObserver(new RemoveHerstellerMessage(name));
    }

    public EinlagerungEntry addKuchen(Kuchen newKuchen, LocalDateTime date) {
        if (!herstellerExists(newKuchen.getHersteller().getName())) {
            throw new IllegalArgumentException(
                    String.format(
                            "No such manufacturer: %s. Please add manufacturer: %s.",
                            newKuchen.getHersteller().getName(), newKuchen.getHersteller().getName()
                    )
            );
        }
        int cell = findEmptyCell();
        EinlagerungEntry einlagerungEntry = new EinlagerungEntry(date, newKuchen, cell);
        storage.add(einlagerungEntry);

        return einlagerungEntry;
    }


    public List<Kuchen> getAllEingelagertenKuchen() {
        return storage.stream()
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
        return storage.get(findIndexOfEinlagerungsEntry(theKuchen)).getFachnummer();
    }

    public Duration getRestHaltbarkeitZuBestimmtenKuchen(Kuchen theKuchen, LocalDateTime now) {
        int indexOfEinlagerungsEntry = findIndexOfEinlagerungsEntry(theKuchen);
        LocalDateTime datumVonEinlagerung = storage.get(indexOfEinlagerungsEntry).getEinlagerungsDatum();
        LocalDateTime deadLine = datumVonEinlagerung.plus(theKuchen.getHaltbarkeit());

        return Duration.ofDays(ChronoUnit.DAYS.between(now, deadLine));
    }


    public void removeKuchenFromAutomat(int fachNummer) {
        int indexOfEinlagerungsEntry = findIndex(fachNummer);
        if (indexOfEinlagerungsEntry == -1) {
            throw new IllegalArgumentException("fachnumber does not exist");
        }
        storage.remove(indexOfEinlagerungsEntry);
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

    public EinlagerungEntry findKuchenWithSmallestHaltbarkeit() {
        return storage.stream()
                .min(Comparator.comparing(einlagerungEntry -> einlagerungEntry.getKuchen().getHaltbarkeit()))
                .orElse(null);
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

    ///не понимаю как работает??
    private int findEmptyCell() {
        for (int i = 0; i < platzImAutomat; i++) {
            int finalI = i;
            //TODO переделать на обычный луп
            if (storage.stream().noneMatch(einlagerungEntry -> einlagerungEntry.getFachnummer() == finalI)) {
                return i;
            }
        }

        throw new AutomatIsFullException("Der Automat ist voll");
    }

    private int findIndex(int fachNummer) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getFachnummer() == fachNummer) {
                return i;
            }
        }

        return -1;
    }

    private int findIndexOfEinlagerungsEntry(Kuchen kuchen) {
        for (int i = 0; i < storage.size(); i++) {
            if (containsKuchen(kuchen, storage.get(i))) {
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

    @Override
    public String toString() {
        return "Automat{" +
                "platzImAutomat=" + platzImAutomat +
                ", storage=" + storage +
                ", allHersteller=" + allHersteller +
                ", automatName='" + automatName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automat automat = (Automat) o;
        return platzImAutomat == automat.platzImAutomat &&
                Objects.equals(storage, automat.storage) &&
                Objects.equals(allHersteller, automat.allHersteller) &&
                Objects.equals(automatName, automat.automatName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(platzImAutomat, storage, allHersteller, automatName);
    }
}
