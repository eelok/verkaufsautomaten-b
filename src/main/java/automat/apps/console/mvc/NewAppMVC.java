package automat.apps.console.mvc;

public class NewAppMVC {
    public static void main(String[] args) {

        ConsoleReader consoleReader = new ConsoleReader();
        InputEventHandler eventHandler = new InputEventHandler();
        consoleReader.setHandler(eventHandler);

        consoleReader.start();
    }
}
