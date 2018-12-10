package clasesJSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Post {


    private int id;

    private int published;

    private ArrayList<Double> location = null;

    private String category;

    private ArrayList<String> liked_by;

    private ArrayList<String> commented_by;

    private Date fecha;

    private double distanciaLocalizacion;

    private double puntuacion;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getPublished() {

        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public void setFecha(int codigo){
        fecha = new Date((long)codigo*1000);
    }

    public Date getFecha(){
        return fecha;
    }

    public ArrayList<Double> getLocation() {

        return location;
    }

    public void setLocation(ArrayList<Double> location) {

        this.location = location;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public ArrayList<String> getLikedBy() {

        return liked_by;
    }

    public void setLikedBy(ArrayList<String> likedBy) {

        this.liked_by = likedBy;
    }

    public ArrayList<String> getCommentedBy() {

        return commented_by;
    }

    public void setCommentedBy(ArrayList<String> commentedBy) {

        this.commented_by = commentedBy;
    }

    public double getDistanciaLocalizacion() {
        return distanciaLocalizacion;
    }

    public void setDistanciaLocalizacion(double distanciaLocalizacion) {
        this.distanciaLocalizacion = distanciaLocalizacion;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }


    public void aumentarPuntuacion(double puntuacion) {
        this.puntuacion += puntuacion;
    }
}
