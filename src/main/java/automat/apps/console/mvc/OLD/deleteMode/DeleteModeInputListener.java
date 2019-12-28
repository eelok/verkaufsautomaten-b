//package automat.apps.console.mvc.OLD.deleteMode;
//
//import automat.apps.console.service.StringUtils;
//import automat.mainlib.Automat;
//import automat.apps.console.mvc.ConsoleReader;
//import automat.apps.console.mvc.InputEvent;
//import automat.apps.console.mvc.InputEventHandler;
//import automat.apps.console.mvc.InputEventListener;
//
//public class DeleteModeInputListener implements InputEventListener {
//
//    private Automat automat;
//    private StringUtils stringUtils;
//
//    public DeleteModeInputListener(Automat automat, StringUtils stringUtils) {
//        this.automat = automat;
//        this.stringUtils = stringUtils;
//    }
//
//    @Override
//    public void onInputEvent(InputEvent event) {
//        if (event.getText() != null && event.getText().equals(":d")) {
//            System.out.println("delete mode active");
//            ConsoleReader consoleReader = new ConsoleReader();
//            InputEventHandler eventHandler = new InputEventHandler();
//            eventHandler.add(new DeleteHerstellerInputListener(automat));
//            eventHandler.add(new DeleteKuchenInputListener(automat, stringUtils));
//            consoleReader.setHandler(eventHandler);
//            consoleReader.start();
//        }
//    }
//}
