package automat.apps.console.mvc;

import java.util.Scanner;

public class ConsoleReader {
    private InputEventHandler handler;

    public void setHandler(InputEventHandler handler) {
        this.handler = handler;
    }

    public void start() {
        Scanner s = new Scanner(System.in);
        do {
            if (this.handler == null) {
                break;
            }
            System.out.print(">");
            String userInput = s.nextLine();
            if (userInput.equals(":q")) {
                break;
            }
            handler.handle(new InputEvent(this, userInput));
        } while (true);
    }
}
