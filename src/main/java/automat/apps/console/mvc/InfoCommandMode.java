package automat.apps.console.mvc;

import automat.apps.console.Printer;

public class InfoCommandMode implements InputEventListener {

    private Printer printer;

    public InfoCommandMode(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().equalsIgnoreCase(":a") && !event.getText().equalsIgnoreCase(":l") && !event.getText().equalsIgnoreCase(":d")){
            printer.println("Expected input: :a <input mode> | :l <list mode> | :d <delete mode>");
        }

    }
}

