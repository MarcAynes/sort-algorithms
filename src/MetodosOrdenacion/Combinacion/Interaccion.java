package MetodosOrdenacion.Combinacion;

import clasesJSON.Post;
import clasesJSON.User;

import java.util.ArrayList;
import java.util.List;

public class Interaccion {
    private User user = new User();
    private List<Post> lista_posts = new ArrayList<>();

    public Interaccion(User[] users, String name){
        //List<String> categUsuario = new ArrayList<>();
        List<RecuentoCategoria> categLiked = new ArrayList<>();
        List<User> lista_usersConocidos = new ArrayList<>();
        int likes, tiempo_aux, size1, size2;
        int i, j, k, m;
        int published_max = -1;
        String categoria_aux;

        //guardo el nombre
        user.setUsername(name);

        //supongo que el nombre es correcto osea existe dentro del json
        //añado todos sus datos al user u
        for (User u : users){
            if(user.getUsername().equals(u.getUsername())){
                user = u;
                break;
            }
        }


        //clasificacion de los usuarios en conocidos y no conocidos
        //iteracion en la lista total de usuarios a comparar con las connections
        boolean trobat;
        for (i = 0; i < user.getConnections().size(); i++){

            //iteracion en la lista connections
            for (j = 0; j < users.length; j++) {
                if (users[j].getUsername().equals(user.getConnections().get(i).getUsername())){
                    likes = user.getConnections().get(i).getLikes();

                    // Rango puntuación prioridad de likes dados a un usuario
                    if (likes <= 25){
                        users[j].setPuntuacion(0.0);
                    }
                    else {
                        if (likes <= 35){
                            users[j].setPuntuacion(0.1/3);
                        }
                        else {
                            if (likes <= 45){
                                users[j].setPuntuacion(0.2/3);
                            }
                            else {
                                users[j].setPuntuacion(0.1);
                            }
                        }
                    }

                    // Agregamos en lista de usuarios seguidos para poder manejar un rango de posts menor en vez de
                    // tener que estar recorriendo el array de users constantemente
                    lista_usersConocidos.add(users[j]);
                    break;
                }
            }

        }
/*
        // Buscamos que categorías sube el usuario para darles máxima prioridad
        for (i = 0; i < user.getPosts().size(); i++){
            trobat = false;

            if (i == 0){
                categUsuario.add((user.getPosts()).get(i).getCategory());
            }

            else {
                for (String auxCategUsuario : categUsuario) {
                    if (auxCategUsuario.equals((user.getPosts()).get(i).getCategory())) {
                        trobat = true;
                        break;
                    }
                }

                if (!trobat){
                    categUsuario.add((user.getPosts()).get(i).getCategory());
                }
            }
        }
*/

        for (i = 0; i < lista_usersConocidos.size(); i++){
            size1 = lista_usersConocidos.get(i).getPosts().size();
            for (j = 0; j < size1; j++){
                // Adjuntamos puntuacion prioridad por usuario seguido a sus posts respectivos
                lista_usersConocidos.get(i).getPosts().get(j).setPuntuacion(lista_usersConocidos.get(i).getPuntuacion());

                // Medimos cual es el tiempo más reciente en el que se ha subido un post
                tiempo_aux = lista_usersConocidos.get(i).getPosts().get(j).getPublished();
                if (published_max == -1){
                    published_max = tiempo_aux;
                }
                else {
                    if (published_max < tiempo_aux){
                        published_max = tiempo_aux;
                    }
                }

                // Calculamos los likes que el usuario ha dado like a las diversas categorías de los usuarios que sigue
                categoria_aux = lista_usersConocidos.get(i).getPosts().get(j).getCategory();
                size2 = lista_usersConocidos.get(i).getPosts().get(j).getLikedBy().size();
                for (k = 0; k < size2; k++){
                    if ((lista_usersConocidos.get(i).getPosts().get(j).getLikedBy().get(k)).equals(name)){
                        if (categLiked.isEmpty()){
                            RecuentoCategoria rc = new RecuentoCategoria(categoria_aux, 0);
                            categLiked.add(rc);
                        }

                        else {
                            trobat = false;
                            for (m = 0; m < categLiked.size(); m++){
                                if ((categLiked.get(m).getNombre()).equals(categoria_aux)){
                                    trobat = true;
                                    break;
                                }
                            }

                            if (trobat){
                                categLiked.get(m).aumentaRecuento();
                            }
                            else {
                                RecuentoCategoria rc = new RecuentoCategoria(categoria_aux, 0);
                                categLiked.add(rc);
                            }
                        }
                    }
                }


                lista_posts.add(lista_usersConocidos.get(i).getPosts().get(j));
            }
        }

        for (RecuentoCategoria rc : categLiked){
            rc.setPuntuacionTotal();
        }

        for (Post p: lista_posts){

            p.aumentarPuntuacion(( (double) p.getPublished() ) / ( (double) published_max ));

            categoria_aux = p.getCategory();

            // Buscamos la categoría del post para poder sumarle la puntuacion correspondiente
            for (i = 0; i < categLiked.size(); i++){
                if ((categLiked.get(i).getNombre()).equals(p.getCategory())){
                    p.aumentarPuntuacion(categLiked.get(i).getPuntuacionTotal());
                    break;
                }
            }

        }

    }

}
