package clasesJSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class User {

    private String username;

    private int followers;

    private int follows;

    private ArrayList<Connection> connections = null;

    private ArrayList<Post> posts = null;

    private ArrayList<Integer> likedPosts = null;

    private ArrayList<Integer> commentedPosts = null;

    public String getUsername() {

        return username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public int getFollowers() {

        return followers;

    }

    public void setFollowers(int followers) {

        this.followers = followers;

    }

    public int getFollows() {

        return follows;

    }

    public void setFollows(int follows) {

        this.follows = follows;

    }

    public ArrayList<Connection> getConnections() {

        return connections;

    }

    public void setConnections(ArrayList<Connection> connections) {

        this.connections = connections;

    }

    public ArrayList<Post> getPosts() {

        return posts;

    }

    public void setPosts(ArrayList<Post> posts) {

        this.posts = posts;

    }

    public ArrayList<Integer> getLikedPosts() {

        return likedPosts;

    }

    public void setLikedPosts(ArrayList<Integer> likedPosts) {

        this.likedPosts = likedPosts;

    }

    public ArrayList<Integer> getCommentedPosts() {

        return commentedPosts;

    }

    public void setCommentedPosts(ArrayList<Integer> commentedPosts) {

        this.commentedPosts = commentedPosts;

    }
    public void convertirFechas(){

        for (Post i: posts) {
            i.setFecha(i.getPublished());
        }
    }

}


