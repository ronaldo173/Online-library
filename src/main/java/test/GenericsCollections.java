package test;

import java.util.*;

/**
 * Created by Developer on 18.03.2016.
 */
public class GenericsCollections {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        int s = 0;

        for (Integer integer : list) {
            s += integer;
        }
        System.out.println(s);

        List<String> words = new ArrayList<>();
        words.add("first ");
        words.add("second");

        String word = words.get(0).concat(words.get(1));
        System.out.println(word);
        assert word.equals("first second");

        List<Integer> integerList = Arrays.asList(1, 1, 3);
        assert integerList.get(0) == integerList.get(1);
        assert new Integer(28).intValue() == new Integer(28);


        List<? super Number> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2.22);

        System.out.println(ints);

        List<Object> objects = Arrays.<Object>asList(2, 3.1, "hwllo");
        System.out.println(objects);
        List<Integer> integerList1 = Arrays.asList(5, 6);
        Collections.<Integer>copy(objects, integerList);
        System.out.println(objects + "obj");
        System.out.println(objects.toString());


        List<Integer> i1 = Arrays.asList(1,2,3);
        System.out.println(sum(i1));

        List<Double> i2 = Arrays.asList(1.1, 2.2, 3.3);
        System.out.println(sum(i2));

        List<Number> i3 = Arrays.<Number>asList(1.2, 2.1);
        System.out.println(sum(i3));

        List<Object> objects1 = Arrays.<Object>asList(1, "two");
        List<? super Integer > gg = objects1;
        for (Object o : gg) {
            System.out.println(gg.toString());
        }

        List test = new ArrayList<>();
        test.add(1);
        test.add("go warning");
        Map map = new HashMap<>();
        map.put(1,2);


    }

    public static double sum(Collection<? extends Number > numbers) {
        double res = 0.0;

        for (Number number : numbers) {
            res += number.doubleValue();
        }
        return res ;
    }


}
