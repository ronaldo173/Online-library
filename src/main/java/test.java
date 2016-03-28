/**
 * Created by Santer on 07.03.2016.
 */
public  class test {


}

class Delta
{
    static boolean foo(char c)
    {
        System.out.print(c);
        return true;
    }
    public static void main( String[] argv )
    {
        int i = 0;
        for (foo('A'); foo('B') && (i < 2); foo('C'))
        {
            i++;
            foo('D');
        }
    }
}