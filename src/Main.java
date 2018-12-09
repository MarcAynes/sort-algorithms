import clasesJSON.*;
import com.google.gson.Gson;

import Pantalla.Menu;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

import static Pantalla.Menu.interfazMenu;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader("assets\\m_dataset.json"));
            User[] users = gson.fromJson(br, User[].class);

            for (User i : users) {
                i.convertirFechas();
            }

            interfazMenu(users);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error de lectura de JSON");


        }
    }
}
