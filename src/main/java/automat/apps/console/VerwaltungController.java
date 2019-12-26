package automat.apps.console;

import automat.mainlib.Automat;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.hersteller.observer.AddNewHerstellerObserver;
import automat.mainlib.hersteller.observer.RemoveHarstellerObserver;
import automat.mainlib.kuchen.KuchenParser;
import automat.mainlib.kuchen.observer.AddNewKuchenObserver;
import automat.mainlib.kuchen.observer.RemoveKuchenObserver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class VerwaltungController {

    private Automat automat;
    private KuchenParser kuchenParser;
    private StringUtils stringUtils;

    public VerwaltungController(Automat automat, KuchenParser kuchenParser, StringUtils stringUtils) {
        this.automat = automat;
        this.kuchenParser = kuchenParser;
        this.stringUtils = stringUtils;
    }

    public void run() {
        automat.registerAddNewHerstellerObserver(new AddNewHerstellerObserver());
        automat.registerAddNewKuchenObserver(new AddNewKuchenObserver());
        automat.registerRemoveKuchenObserver(new RemoveKuchenObserver());
        automat.registerRemoveHarstellerObserver(new RemoveHarstellerObserver());
        mainMenu();
    }

    private void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Enter command: :a<add> :l<list> :d<delete> :q<back to main menu>");
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
                System.out.println("Enter command: manufacturer<list of manufacturer> kuchen<list of kuchen>  :q<back to main menu> ");
                System.out.print(">");
                commandListMode();
                break;
            case ":d":
                System.out.println("delete mode active");
                System.out.println("Enter command: f<fachnummer> / <name of manufacturer> / :q<back to main menu>");
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
                try {
                    automat.addHersteller(new HerstellerImplementation(userInput.toLowerCase().trim()));
                } catch (ManufacturerExistException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                addKuchen(userInput);
            }
            userInput = getUserInput(scanner);
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
                List<String> herstellerWithNumberOfKuchen = automat.getHerstellerWithNumberOfKuchen();
                if (herstellerWithNumberOfKuchen.isEmpty()) {
                    System.out.println("there is no manufacturer");
                }
                herstellerWithNumberOfKuchen.forEach(System.out::println);
                userInput = getUserInput(scanner);
            } else if (userInput.equalsIgnoreCase("kuchen")) {
                List<String> listWithKuchenWithAllocatedFachNum = automat.getAllKuchenWithFachNum();
                if (listWithKuchenWithAllocatedFachNum.isEmpty()) {
                    System.out.println("No Kuchen Available in the Automat");
                }
                listWithKuchenWithAllocatedFachNum.forEach(System.out::println);
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
                try {
                    automat.removeKuchenFromAutomat(fachNum);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    automat.deleteHersteller(userInput.toLowerCase().trim());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private String getUserInput(Scanner scanner) {
        String userInput;
        System.out.print(">");
        userInput = scanner.nextLine();
        return userInput;
    }

    private void addKuchen(String userInput) {
        try {
            automat.addKuchen(kuchenParser.getKuchenInfo(userInput), LocalDateTime.now());
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("Can not add kuchen, reason: %s", e.getMessage()));
        }
    }
}
