package MetodosOrdenacion.Ubicacion;

import clasesJSON.Post;
import clasesJSON.User;

import java.util.ArrayList;
import java.util.List;

public class OrdenarUbicacion {
    private List<Post> lista_posts = new ArrayList<>();

    public OrdenarUbicacion(User[] users){


        for (User u : users){
            for(Post p : u.getPosts()){
                p.setDistanciaLocalizacion(4);
            }
        }

    }
}
