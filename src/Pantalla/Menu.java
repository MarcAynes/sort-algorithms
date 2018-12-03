package Pantalla;

import clasesJSON.User;
import MetodosOrdenacion.Temporalidad.OrdenarTiempo;

import java.util.Scanner;

public class Menu {

    public static void interfazMenu(User[] users){
        Scanner sc = new Scanner(System.in);

        System.out.println("Este es el menu principal de InstaSalle!");
        System.out.println("\nPresione ENTER para continuar");
        sc.nextLine();
        System.out.println("\nMostrando posts en orden de temporalidad...\n");

       // OrdenarTiempo ot = new OrdenarTiempo(users);

        System.out.println("\nPresione ENTER para continuar");
        sc.nextLine();
        System.out.println("\nMostrando posts en orden de ubicacion...\n");

        System.out.println("\nPresione ENTER para continuar");
        sc.nextLine();

    }
}
