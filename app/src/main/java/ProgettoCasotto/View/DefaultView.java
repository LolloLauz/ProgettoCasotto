package ProgettoCasotto.View;

import ProgettoCasotto.Controller.MainController;
import ProgettoCasotto.GestoreDB;
import ProgettoCasotto.PersonaleDelloChalet.Privilegio;

import java.util.Scanner;

public class DefaultView {

    String email;
    String password;
    Scanner scanner;
    GestoreDB gestoreDB;
    SuperAdminView superAdminView;
    ViewClient viewClient;
    MainController mainController=new MainController();

    public DefaultView() {

//        System.out.println("FINESTRA DI LOGIN");
//        System.out.println("INSERISCI EMAIL UTENTE");
//        Scanner scanner=new Scanner(System.in);//scanner.nextLine();
//        this.email="aaa@gmail.com";
//        System.out.println("INSERISCI PASSWORD");//scanner.nextLine();
//        this.password = "aaaa";
//        gestoreDB=new GestoreDB();
        System.out.println("LogIn eseguito con successo");
        System.out.println("Inserisci privilegio SUPERADMIN");
        scanner=new Scanner(System.in);
        String privilegio= scanner.nextLine();
        switch (Privilegio.valueOf(privilegio)){
            case SUPERADMIN :
                superAdminView=new SuperAdminView(mainController);
            case USER :
                viewClient=new ViewClient(mainController.getDefaultClienteController());
                break;
            default :
                break;
        }
        mainController.getStatoPrenotazioniSpiaggia();
    }
    public Privilegio getNomePrivilegio(){
        return gestoreDB.getEmailPassw(email,password);
    }
}
