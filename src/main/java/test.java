import java.util.*;

/**
 * Created by Santer on 07.03.2016.
 */
public class test {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};

        System.out.println(list);
        String string = Arrays.toString(toArray(list));
        System.out.println(string);

        Integer[] integers = toArray(list);
        System.out.println(integers);


    }

    public static  <T> T[] toArray(Collection<T> c) {
        T[] a = (T[]) new Object[(c.size())];
        int i = 0;
        for (T t : c) {
            a[i++] = t;
        }
        return a;
    }


}
