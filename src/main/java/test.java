import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Santer on 07.03.2016.
 */
public class test {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("1", "two");
        List<Integer> integerList = Arrays.asList(1, 2);
        System.out.println(list.getClass());
        System.out.println(integerList.getClass());

        List<String> strings = new ArrayList<>();
        System.out.println(strings.getClass());


        B b = new B<String >();
        System.out.println(b.getClass());
        try {
            b.get();
            b.print("test");
            b.print(1);
            b.print(new StringBuilder("2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

class  A {
    int a;
    static int b;

    Object get() throws Exception {
        return null;
    }

}

class B<T> extends A {
    @Override
    Number get() throws NullPointerException {
        System.out.println(a);
        System.out.println(b);
        return null;
    }

    public void print(T gg){
        System.out.println(gg);
    }


}

class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
