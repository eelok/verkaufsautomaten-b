package automat;

import hersteller.Hersteller;
import hersteller.HerstellerImplementation;
import hersteller.observer.AddNewHerstellerObserver;
import hersteller.observer.RemoveHarstellerObserver;
import kuchen.KuchenParser;
import kuchen.observer.AddNewKuchenObserver;
import kuchen.observer.RemoveKuchenObserver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class VerwaltungController {

    private Verwaltung verwaltung;
    private KuchenParser kuchenParser;
    private StringUtils stringUtils;

    public VerwaltungController(Verwaltung verwaltung, KuchenParser kuchenParser, StringUtils stringUtils) {
        this.verwaltung = verwaltung;
        this.kuchenParser = kuchenParser;
        this.stringUtils = stringUtils;
    }

    public void run() {
        verwaltung.registerAddNewHerstellerObserver(new AddNewHerstellerObserver());
        verwaltung.registerAddNewKuchenObserver(new AddNewKuchenObserver());
        verwaltung.registerRemoveKuchenObserver(new RemoveKuchenObserver());
        verwaltung.registerRemoveHarstellerObserver(new RemoveHarstellerObserver());
        mainMenu();
    }

    private void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.print(">");
            String userInput = scanner.nextLine();
            if (userInput.equals(":q")) {
                run = false;
            }
            commandMode(userInput);
        }
    }

    private void commandMode(String commandName) {
        switch (commandName) {
            case ":a":
                System.out.println("add mode active");
                System.out.print(">");
                commandAddMode();
                break;
            case ":l":
                System.out.println("list mode active");
                System.out.print(">");
                commandListMode();
                break;
            case ":d":
                System.out.println("delete mode active");
                commandRemoveMode();
                break;
            default:
                System.out.println(String.format("No such command %s", commandName));
        }
    }

    private void commandAddMode() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        boolean run = true;
        while (run) {
            if (userInput.equals(":q")) {
                System.out.println("back to main menu");
                run = false;
                continue;
            }
            if (stringUtils.isOneWord(userInput)) {
                addManufacturer(userInput);
                userInput = getUserInput(scanner);
            } else {
                addKuchen(userInput);
                userInput = getUserInput(scanner);
            }
        }
    }

    private void commandListMode() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        boolean run = true;
        while (run) {
            if (userInput.equals(":q")) {
                System.out.println("back to main menu");
                run = false;
                continue;
            }
            if (userInput.equalsIgnoreCase("manufacturer")) {
                Set<Hersteller> herstellerList = verwaltung.getHerstellerList();
                List<String> herstellerKuchenCount = getHerstellerKuchenCount(herstellerList);
                herstellerKuchenCount.forEach(System.out::println);
                userInput = getUserInput(scanner);
            } else if (userInput.equalsIgnoreCase("kuchen")) {
                List<String> kuchenAndFach = getKuchenAndFach();
                if (kuchenAndFach.isEmpty()) {
                    System.out.println("No Kuchen Available in the Automat");
                }
                kuchenAndFach.forEach(System.out::println);
                userInput = getUserInput(scanner);
            } else {
                System.out.println("List Mode input: expected format: manufacturer / kuchen");
                userInput = getUserInput(scanner);
            }
        }
    }

    private void commandRemoveMode() {
        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        while (run) {
            String userInput = getUserInput(scanner);
            if (userInput.equals(":q")) {
                System.out.println("back to main menu");
                run = false;
                continue;
            }
            if (userInput.matches("^f.[0-9]*$")) {
                int fachNum = stringUtils.extractFachNumberFromString(userInput);
                EinlagerungEntry[] einlagerungList = verwaltung.getEinlagerungList();
                if (fachNum > einlagerungList.length - 1) {
                    System.out.println(String.format("Fach %s doesn't exist", fachNum));
                    continue;
                }
                if (einlagerungList[fachNum] == null) {
                    System.out.println(String.format("Fach %s is empty", fachNum));
                    continue;
                }
                verwaltung.removeKuchenFromAutomat(einlagerungList[fachNum].getKuchen());
            } else {
                try {
                    verwaltung.deleteHersteller(userInput.trim());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
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


    private List<String> getHerstellerKuchenCount(Set<Hersteller> herstellerList) {
        return herstellerList.stream()
                .map(hersteller -> {
                    long anzahlKuchenZuHersteller = verwaltung.getAnzahlKuchenZuHersteller(hersteller.getName());
                    return hersteller.getName() + " : " + anzahlKuchenZuHersteller;
                })
                .collect(Collectors.toList());
    }

    private String getUserInput(Scanner scanner) {
        String userInput;
        System.out.print(">");
        userInput = scanner.nextLine();
        return userInput;
    }

    private void addKuchen(String userInput) {
        try {
            verwaltung.addKuchen(kuchenParser.getKuchenInfo(userInput), LocalDateTime.now());
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("Can not add kuchen, reason: %s", e.getMessage()));
        }
    }

    private void addManufacturer(String userInput) {
        Hersteller hersteller = new HerstellerImplementation(userInput);
        verwaltung.addHersteller(hersteller);
    }
}
