package interaciones;

import MetodosOrdenacion.Temporalidad.num;
import clasesJSON.Post;
import clasesJSON.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class interaccion {
    User user;

    private List<User> lista_users = new ArrayList<>();
    private List<User> lista_usersConocidos = new ArrayList<>();
    private List<User> lista_usersDesconocidos = new ArrayList<>();

    public interaccion(User[] users, String name){
        for(int i = 0; i < users.length - 1; i++) {
            lista_users.add(users[i]);
        }
        //guardo el nomre
        user.setUsername(name);
        //ordeno los usuarios con un quicksort para futuros usos
        ordenaUsers(0, lista_users.size() - 1);

        //supongo que el nombre es correcto osea existe dentro del json
        //añado todos sus datos al user u
        for (User u : lista_users){
            if(user.getUsername().equals(u.getUsername())){
                user = u;
                break;
            }
        }
        //clasificacion de los usuarios en conocidos y no conocidos
        //iteracion en la lista total de usuarios a comparar con las connections
        for (int i = 0; i <= user.getConnections().size() - 1; i++){
            //iteracion en la lista connections
            for (int j = 0; j <= user.getConnections().size() - 1; j++) {
                if (lista_users.get(i).getUsername().equals(user.getConnections().get(j).getUsername())){
                    lista_usersConocidos.add(lista_users.get(i));
                }
                else{
                    //si el usuario es el mismo no estara en sus connections pero tampoco debemos añadirlo a los no conocidos
                    if(!lista_users.get(i).getUsername().equals(user.getUsername())){
                        lista_usersDesconocidos.add(lista_users.get(i));
                    }
                }
            }
        }

    }

    private void ordenaUsers(int start, int length){

        if(start < length){
            num a = Particio(start, length);
            ordenaUsers(start, a.getRight());
            ordenaUsers(a.getLeft(), length);
        }
    }

    private num Particio(int start, int length){
        int mig = (start + length)/2;
        User pivot = lista_users.get(mig);
        User tmp;
        int left = start;
        int right = length;


        while (left <= right ){
            while(0 > lista_users.get(left).getUsername().compareTo(pivot.getUsername())){
                left++;
            }
            while (0 < lista_users.get(right).getUsername().compareTo(pivot.getUsername())){
                right--;
            }
            if (left < right){
                tmp = lista_users.get(left);

                lista_users.set(left, lista_users.get(right));
                lista_users.set(right, tmp);
                left++;
                right--;
            }else{
                if(left == right){
                    left++;
                    right--;
                }

            }
        }
        num a = new num();
        a.setLeft(left);
        a.setRight(right);

        return a;

    }
}
