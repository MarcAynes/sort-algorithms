package MetodosOrdenacion.Temporalidad;

import clasesJSON.Post;
import clasesJSON.User;

import java.util.ArrayList;

public class OrdenarTiempo {
    private ArrayList<Post> lista_posts = new ArrayList<>();

    public OrdenarTiempo(User[] users){
        for (User u : users){
            lista_posts.addAll(u.getPosts());
        }
    }

    public void muestraPosts() {
        for(Post p : lista_posts){
            System.out.println(p.getId());
        }
    }
}
