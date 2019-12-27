package automat.apps.console.mvc.listMode;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Automat;

public class ListModeInputListener implements InputEventListener {

    private Automat automat;

    public ListModeInputListener(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":l")) {
            System.out.println("list mode active");
            System.out.println("Enter command: manufacturer / kuchen / :q<back to main menu> ");
            ConsoleReader consoleReader = new ConsoleReader();
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new ListManufacturerInputListener(automat));
            eventHandler.add(new ListKuchenInputListener(automat));

            eventHandler.add(new InputEventListener() {
                @Override
                public void onInputEvent(InputEvent event) {
                    if(!event.getText().equals("manufacturer") && !event.getText().equals("kuchen")){
                        System.out.println("List Mode input: expected format: manufacturer / kuchen");
                    }
                }
            });
            consoleReader.setHandler(eventHandler);
            consoleReader.start();


        }
    }
}
