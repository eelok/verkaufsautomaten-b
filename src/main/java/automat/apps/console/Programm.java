package automat.apps.console;

import automat.mainlib.Automat;

import java.util.Scanner;

public class Programm {



    public void start(){
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter command:");
            System.out.print(">");
            String usertInput = scanner.nextLine();
            InputEvent event = new InputEvent(this, usertInput);
        }while (true);
    }
}
