package MetodosOrdenacion.Ubicacion;

import MetodosOrdenacion.Temporalidad.num;
import clasesJSON.Post;
import clasesJSON.User;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static MetodosOrdenacion.Ubicacion.Haversiano.calcularDistancia;

public class OrdenarUbicacion {
    private List<Post> lista_posts = new ArrayList<>();

    public OrdenarUbicacion(User[] users, double latitud, double longitud){

        for (User u : users){
            for(Post p : u.getPosts()){
                p.setDistanciaLocalizacion(calcularDistancia(latitud, longitud, p.getLocation().get(0), p.getLocation().get(1)));
                lista_posts.add(p);
            }
        }
    }

    public void InsertionSort(){
        Post aux;
        for (int i = 0; i < lista_posts.size() - 1; i++){
            for (int n = i; n > 0; n--){
                if (lista_posts.get(n).getDistanciaLocalizacion() < lista_posts.get(n - 1).getDistanciaLocalizacion()){
                    aux = lista_posts.get(n);
                    lista_posts.set(n, lista_posts.get(n - 1));
                    lista_posts.set(n - 1, aux);
                }
            }
        }
    }

    public void QuickSort(int start, int length){

        if(start < length){
            num a = Particio(start, length);
            QuickSort(start, a.getRight());
            QuickSort(a.getLeft(), length);
        }

    }

    private num Particio(int start, int length){
        int mig = (start + length)/2;
        Post pivot = lista_posts.get(mig);
        Post tmp;
        int left = start;
        int right = length;


        while (left <= right){
            while(lista_posts.get(left).getDistanciaLocalizacion() < pivot.getDistanciaLocalizacion()){
                left++;
            }
            while (lista_posts.get(right).getDistanciaLocalizacion() > pivot.getDistanciaLocalizacion()){
                right--;
            }
            if (left < right){
                tmp = lista_posts.get(left);

                lista_posts.set(left, lista_posts.get(right));
                lista_posts.set(right, tmp);
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

    public int longitud(){

        return lista_posts.size() - 1;
    }


    private Post getMax(int n) {
        Post max = lista_posts.get(0);
        for (int i = 1; i < n; i++)
            if ((lista_posts.get(i)).getDistanciaLocalizacion() > max.getDistanciaLocalizacion())
                max = lista_posts.get(i);
        return max;
    }

    private void countSort(int n, double exp) {
        List<Post> output = Arrays.asList(new Post[n]); // output array de tamaño n
        int count[] = new int [10];
        Arrays.fill(count,0);
        int i, modI;
        BigInteger aux, mod;

        // Cuenta de que números aparecen de entre el 0 al 9
        for (i = 0; i < n; i++) {
            aux = BigDecimal.valueOf(((lista_posts.get(i)).getDistanciaLocalizacion() / exp)).toBigInteger();
            mod = aux.remainder(BigInteger.valueOf(10L));
            modI = mod.intValue();
            count[modI]++;
        }


        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        //Se requiere del array original para tener en cualquier momento los valores de los posts originales para poder
        // hacer el swap

        for (i = n - 1; i >= 0; i--) {

            // Convertimos el valor de double a clase BigDecimal y a BigInteger (ya que esta clase se puede pasar a
            // BigInteger y double no
            aux = BigDecimal.valueOf(((lista_posts.get(i)).getDistanciaLocalizacion() / exp)).toBigInteger();

            // Convertimos 10L a BigInteger para poder aplicar el método remainder (funcion mod para valores long)
            mod = aux.remainder(BigInteger.valueOf(10L));
            // Convertimos mod de BigInteger a int para poder usarlo como índice
            modI = mod.intValue();

            output.set(count[modI] - 1, lista_posts.get(i));
            count[modI]--;
        }

        lista_posts = output;
    }

    // Basado en el ejemplo de geeksforgeeks.org
    public void RadixSort(int longitud) {

        // Buscamos el post con fecha mayor para saber cuantas cifras tendrán los números de la lista de posts
        Post post_mayor = getMax(longitud);
        double max = post_mayor.getDistanciaLocalizacion();

        // Usamos método de ordenación auxiliar "Counting Sort". Este método lo aplicamos a partir de las unidades,
        // decenas, ... , usando como referencia el valor "Published" y en cada iteración ordenando los posts al completo
        for (double exp = 0.000000000001; max/exp > 0.0; exp *= 10.0) {
            countSort(longitud, exp);
        }

    }

    public void print() {
        System.out.println("Orden Prioridad     ID       Distancia");
        System.out.println("---------------   ------   -------------");

        for (int i=0; i < lista_posts.size(); i++){
            System.out.print("   " + (i + 1) + ".       " + lista_posts.get(lista_posts.size() - 1 - i).getId() + "       " + lista_posts.get(lista_posts.size() - 1 - i).getDistanciaLocalizacion() + "\n");
        }
    }
}
