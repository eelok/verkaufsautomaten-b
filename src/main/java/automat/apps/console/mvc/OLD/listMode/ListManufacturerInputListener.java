//package automat.apps.console.mvc.OLD.listMode;
//
//import automat.apps.console.mvc.InputEvent;
//import automat.apps.console.mvc.InputEventListener;
//import automat.mainlib.Automat;
//
//import java.util.List;
//
//public class ListManufacturerInputListener implements InputEventListener {
//    private Automat automat;
//
//    public ListManufacturerInputListener(Automat automat) {
//        this.automat = automat;
//    }
//
//    @Override
//    public void onInputEvent(InputEvent event) {
//        if (event.getText().equals("manufacturer")) {
//            List<String> herstellerWithNumberOfKuchen = automat.getHerstellerWithNumberOfKuchen();
//            if (herstellerWithNumberOfKuchen.isEmpty()) {
//                System.out.println("there is no manufacturer");
//            } else {
//                herstellerWithNumberOfKuchen.forEach(System.out::println);
//            }
//        }
//    }
//}
