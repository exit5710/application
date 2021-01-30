package adapter.collectionAdapter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class CollectionAdapterDrive {
    public static void main(String[] args) {
        System.out.println("-------------- Iterator");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("apple");
        arrayList.add("ball");
        arrayList.add("cap");
        arrayList.add("disk");

        /*
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        */
        Enumeration<?> enumeration = new IteratorEnumeration(arrayList.iterator());
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        System.out.println("\n-------------- Vector");
        Vector<String> vector = new Vector<>();
        vector.add("apple");
        vector.add("ball");
        vector.add("cap");
        vector.add("disk");

        /*
        Enumeration<String> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
        */
        Iterator<?> iterator = new EnumerationIterator(vector.elements());
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}