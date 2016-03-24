import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Santer on 07.03.2016.
 */
public class test {
    public static void main(String[] args) {

 List<Integer> integerList = new ArrayList<>();
        Class<? extends List> k = integerList.getClass();

        System.out.println(k.getFields() + " " + List.class);
        System.out.println(int.class);

        int[] arr = (int[]) java.lang.reflect.Array.newInstance(int.class, 5);
        arr[1] = 2;
        System.out.println(Arrays.toString(arr));

    }




}
