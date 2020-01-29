package automat.mainlib;

import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.Hersteller;
import automat.apps.console.observer.DeleteHerstellerObserver;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.Kuchen;
import automat.apps.console.observer.AddHerstellerObserver;
import automat.apps.console.observer.AddNewKuchenObserver;
import automat.apps.console.observer.RemoveKuchenObserver;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Automat implements Subject, Serializable {

    private List<Beobachter> beobachterList = new ArrayList<>();

    private int platzImAutomat;
    private List<EinlagerungEntry> storage;
    private List<Hersteller> allHersteller;
    private String automatName;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFull() {
        try {
            findEmptyCell();
        } catch (AutomatIsFullException ex) {
            return true;
        }
        return false;
    }

    public boolean addHersteller(Hersteller hersteller) throws ManufacturerExistException {
        if (herstellerExists(hersteller.getName())) {
            throw new ManufacturerExistException(String.format("Manufacturer %s already exists", hersteller.getName()));
        }

        allHersteller.add(hersteller);
        this.message = hersteller.getName();
        benachrichtige(AddHerstellerObserver.class);
        return true;
    }

    public void deleteHersteller(String name) {
        Hersteller hersteller = findHersteller(name);
        if (hersteller == null) {
            throw new IllegalArgumentException(String.format("Hersteller %s does not exist", name));
        }
        if (containsTheNameOfHerstellerInAllEingelagerteKuchen(name)) {
            throw new IllegalArgumentException(String.format("Hersteller %s may not be deleted\nreason: a kuchen from %s is in Automat", name, name));
        }
        allHersteller.remove(hersteller);
        this.message = name;
        benachrichtige(DeleteHerstellerObserver.class);
    }

    public EinlagerungEntry addKuchen(Kuchen newKuchen, LocalDateTime date) {
        if (!herstellerExists(newKuchen.getHersteller().getName())) {
            throw new IllegalArgumentException(
                    String.format(
                            "No such manufacturer: %s Please add manufacturer: %s",
                            newKuchen.getHersteller().getName(), newKuchen.getHersteller().getName()
                    )
            );
        }
        int cell = findEmptyCell();
        EinlagerungEntry einlagerungEntry = new EinlagerungEntry(date, newKuchen, cell);
        storage.add(einlagerungEntry);
        this.message = newKuchen.getType();
        benachrichtige(AddNewKuchenObserver.class);
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


    public EinlagerungEntry removeKuchenFromAutomat(int fachNummer) {
        int indexOfEinlagerungsEntry = findIndex(fachNummer);
        if (indexOfEinlagerungsEntry == -1) {
            throw new IllegalArgumentException("fachnummer does not exist");
        }
        EinlagerungEntry removedEinlagerungEntry = storage.remove(indexOfEinlagerungsEntry);
        this.message = removedEinlagerungEntry.getKuchen().getType();
        benachrichtige(RemoveKuchenObserver.class);
        return removedEinlagerungEntry;
    }

    public List<String> getHerstellerWithNumberOfKuchen() {
        return this.allHersteller.stream()
                .map(hersteller -> {
                    long anzahlKuchenZuHersteller = getAnzahlKuchenZuHersteller(hersteller.getName());
                    return hersteller.getName() + ": " + anzahlKuchenZuHersteller;
                })
                .collect(Collectors.toList());
    }

    public List<String> getAllKuchenWithFachNum() {
        List<String> listWithKuchenWithAllocatedFachNum = new ArrayList<>();
        for (EinlagerungEntry each : storage) {
            listWithKuchenWithAllocatedFachNum.add(each.getKuchen().getType() + ": " + each.getFachnummer());
        }
        return listWithKuchenWithAllocatedFachNum;
    }

    public List<Allergen> getAllergenenInAutomat() {
        return getAllEingelagertenKuchen().stream()
                .flatMap(kuchen -> kuchen.getAllergens().stream())
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

    private int findEmptyCell() {
        for (int i = 0; i < platzImAutomat; i++) {
            int finalI = i;
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

    private boolean containsTheNameOfHerstellerInAllEingelagerteKuchen(String name) {
        return getAllEingelagertenKuchen().stream()
                .anyMatch(kuchen -> kuchen.getHersteller().getName().equals(name));
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

    @Override
    public void meldeAn(Beobachter beobachter) {
        this.beobachterList.add(beobachter);
    }

    @Override
    public void meldeAb(Beobachter beobachter) {
        this.beobachterList.remove(beobachter);
    }

    @Override
    public void benachrichtige(Class<? extends Beobachter> beobachterType) {
        this.beobachterList.stream()
                .filter(beob -> beobachterType.isInstance(beob))
                .forEach(b -> b.aktualisiere());
    }
}
