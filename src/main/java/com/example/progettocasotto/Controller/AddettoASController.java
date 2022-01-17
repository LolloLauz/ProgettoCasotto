package com.example.progettocasotto.Controller;



public class AddettoASController extends DefaultPersonaleController implements  AddettoAllaSpiaggiaControllerInterface{


    public AddettoASController(DefaultaMasterController masterController) {
        super(masterController);
    }

    public void controllaTerminale(){
        System.out.println("il ternimale e' terminale");
    }
}
