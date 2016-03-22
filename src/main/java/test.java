/**
 * Created by Santer on 07.03.2016.
 */
public class test {
    public static void main(String[] args) {

        A a = new A();
        System.out.println(a.a);
        System.out.println(a.b);
        System.out.println(A.b);

        A b = new B();
        System.out.println();
        System.out.println(b.a);
        System.out.println(b.b);
        System.out.println(B.b);
    }


}

class A {
    static int b = 2;
    int a = 1;
}

class B extends A {
    static String b = "b";
    String a = "bla";
}