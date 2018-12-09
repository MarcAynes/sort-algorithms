package MetodosOrdenacion.Temporalidad;

import clasesJSON.Post;
import clasesJSON.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdenarTiempo {
    private List<Post> lista_posts = new ArrayList<>();

    public OrdenarTiempo(User[] users){
        for (User u : users){
            lista_posts.addAll(u.getPosts());
        }

    }
    public void InsertionSort(){
        Post aux;
        for (int i = 0; i < lista_posts.size() - 1; i++){
            for (int n = i; n > 0; n--){
                if (lista_posts.get(n).getPublished() < lista_posts.get(n - 1).getPublished()){
                    aux = lista_posts.get(n);
                    lista_posts.set(n, lista_posts.get(n - 1));
                    lista_posts.set(n - 1, aux);
                }
            }
        }
    }

    public void MergeSort(int i, int j){
        int mig;
        if (i < j){
            mig = (int) Math.floor((i + j)/2);
            System.out.println(mig);
            MergeSort(i, mig);
            MergeSort(mig + 1, j);
            merge(i, mig, j);
        }
    }

    private void merge(int i, int mig, int j){

        Post[] b = new Post[lista_posts.size()];
        int k1, k2, cursor, kr;
        k1 = 0;
        k2 = mig +1;
        cursor = 0;

        while (k1 <= mig && k2 <= j){
            if(lista_posts.get(k1).getPublished() <= lista_posts.get(k2).getPublished()){
                b[cursor] = lista_posts.get(k1);
                k1++;
                cursor++;
            } else {
                b[cursor] = lista_posts.get(k2);
                k2++;
                cursor++;
            }
        }

        while(k1 <= mig){
            b[cursor] = lista_posts.get(k1);
            k1++;
            cursor++;
        }
        while (k2 <= j){
            b[cursor] = lista_posts.get(k2);
            k2++;
            cursor++;
        }
        cursor = 0;
        kr = i;

        while (kr <= j){
            lista_posts.set(kr, b[cursor]);
            kr++;
            cursor++;
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


        while (left <= right ){
            while(lista_posts.get(left).getPublished() < pivot.getPublished()){
                left++;
            }
            while (lista_posts.get(right).getPublished() > pivot.getPublished()){
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
            if ((lista_posts.get(i)).getPublished() > max.getPublished())
                max = lista_posts.get(i);
        return max;
    }


    private void countSort(int n, int exp) {
        List<Post> output = Arrays.asList(new Post[n]); // output array de tamaño n
        int count[] = new int[10];
        Arrays.fill(count,0);
        int i;

        // Cuenta de que números aparecen de entre el 0 al 9
        for (i = 0; i < n; i++) {
            count[((lista_posts.get(i)).getPublished() / exp) % 10]++;
        }


        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        //Se requiere del array original para tener en cualquier momento los valores de los posts originales para poder
        // hacer el swap
        for (i = n - 1; i >= 0; i--) {
            output.set(count[ ((lista_posts.get(i)).getPublished()/exp)%10 ] - 1, lista_posts.get(i));
            count[((lista_posts.get(i)).getPublished()/exp)%10]--;
        }

        lista_posts = output;
    }

    // Basado en el ejemplo de geeksforgeeks.org

    public void RadixSort(int longitud) {
        // Buscamos el post con fecha mayor para saber cuantas cifras tendrán los números de la lista de posts

        Post post_mayor = getMax(longitud);
        int max = post_mayor.getPublished();

        // Usamos método de ordenación auxiliar "Counting Sort". Este método lo aplicamos a partir de las unidades,
        // decenas, ... , usando como referencia el valor "Published" y en cada iteración ordenando los posts al completo

        for (int exp = 1; max/exp > 0; exp *= 10) {
            countSort(longitud, exp);
        }

    }

    public void print() {
        System.out.println("Orden Prioridad    ID     Fecha de publicacion");
        System.out.println("---------------  ------   --------------------");

        for (int i=0; i < lista_posts.size(); i++){
            System.out.print("   " + (i + 1) + ".       " + lista_posts.get(lista_posts.size() - 1 - i).getId() + "       " + lista_posts.get(lista_posts.size() - 1 - i).getFecha() + "\n");
        }
    }

}
