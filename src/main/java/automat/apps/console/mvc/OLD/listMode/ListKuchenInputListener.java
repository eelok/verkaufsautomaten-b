//package automat.apps.console.mvc.OLD.listMode;
//
//import automat.apps.console.mvc.InputEvent;
//import automat.apps.console.mvc.InputEventListener;
//import automat.mainlib.Automat;
//
//import java.util.List;
//
//public class ListKuchenInputListener implements InputEventListener {
//    private Automat automat;
//
//    public ListKuchenInputListener(Automat automat) {
//        this.automat = automat;
//    }
//
//    @Override
//    public void onInputEvent(InputEvent event) {
//        if (event.getText().equals("kuchen")) {
//            List<String> allKuchenWithFachNum = automat.getAllKuchenWithFachNum();
//            if (allKuchenWithFachNum.isEmpty()) {
//                System.out.println("No Kuchen Available in the Automat");
//            } else {
//                allKuchenWithFachNum.forEach(System.out::println);
//            }
//        }
//    }
//}
