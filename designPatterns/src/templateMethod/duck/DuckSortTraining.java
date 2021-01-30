package templateMethod.duck;

import java.util.Arrays;

public class DuckSortTraining {
    public static void main(String[] args) {
        Duck[] ducks = {new Duck("Daffy", 8), new Duck("Dewey", 2), new Duck("Howard", 7), new Duck("Louie", 2), new Duck("Donald", 10)};

        //Arrays.sort(ducks);
        mergeSort(ducks);

        //((Comparable) ducks[0]).compareTo(ducks[1]);
        //ducks[0].compareTo(ducks[1]);

        display(ducks);
    }

    private static void mergeSort(Object[] ducks) {
        for (int i = 0; i < ducks.length; i++) {
            //System.out.println(0 > 0 && ((Comparable) ducks[0 - 1]).compareTo(ducks[0]) > 0);
            for (int j = i; j > 0 && ((Comparable) ducks[j - 1]).compareTo(ducks[j]) > 0; j--) {
                swap(ducks, j, j - 1);
            }
        }
    }

    private static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    private static void display(Duck[] ducks) {
        for (Duck duck : ducks) {
            System.out.println(duck);
        }
    }
}