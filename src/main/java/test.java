import java.io.IOException;

/**
 * Created by Santer on 07.03.2016.
 */
public class test {
    public static void main(String[] args) {
        A.print();
        B.print();
    }
}

class A{
    public static void print(){
        System.out.println(1);
    }

    public A lol() throws IOException{
        return new A();
    }
}

class B extends A{
    public static void print(){
        System.out.println(2);
    }

    @Override
    public B lol() throws IllegalArgumentException {
        return new B();
    }

}
