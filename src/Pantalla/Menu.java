package Pantalla;

import clasesJSON.User;
import MetodosOrdenacion.Temporalidad.OrdenarTiempo;

import java.util.Scanner;

public class Menu {

    private static void menuMetodos(){
        System.out.println("\nElija el método de ordenación que quiera utilizar:");
        System.out.println("1. Quick Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Selection Sort");
        System.out.println("4. Radix Sort");
        System.out.println("5. Atrás");
    }

    public static void interfazMenu(User[] users){
        Scanner sc = new Scanner(System.in);
        char opcion, opcionM;

        System.out.println("Este es el menu principal de InstaSalle!");

        do {
            System.out.println("\nElija por que criterio quiere ordenar los datos:");
            System.out.println("1. Por temporalidad");
            System.out.println("2. Por ubicación");
            System.out.println("3. Por combinación de prioridades");
            System.out.println("4. Salir");
            opcion = sc.next().charAt(0);

            switch (opcion){
                case '1':

                    OrdenarTiempo ot = new OrdenarTiempo(users);

                    do {
                        menuMetodos();
                        opcionM = sc.next().charAt(0);


                        switch (opcionM){
                            case '1':

                                ot.QuickSort(0, ot.longitud());
                                ot.print();
                                break;

                            case '2':
                                ot.MergeSort(0, ot.longitud());
                                ot.print();
                                break;

                            case '3':

                                break;

                            case '4':
                                ot.RadixSort(ot.longitud());
                                ot.print();
                                break;

                            case '5':
                                break;

                            default:
                                System.out.println("Error! Opción introducida no está dentro del rango del menú!");
                                break;


                        }

                    } while (opcionM > '4' || opcionM < '1');

                    break;

                case '2':

                    break;

                case '3':

                    break;

                case '4':
                    System.out.println("Gracias por utilizar nuestra aplicación");
                    break;

                default:
                    System.out.println("Error! Opción introducida no está dentro del rango del menú!");
            }

        } while (opcion != '4');

    }
}
