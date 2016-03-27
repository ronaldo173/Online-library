/**
 * Created by Santer on 07.03.2016.
 */
public class test {
    private  int first;
    private  int second;
    private  int third;
    private  int forth;


    public static class Builder{
        private  int first;
        private  int second;
        private  int third;
        private  int forth;


        public Builder(int first){
            this.first = first;
        }

        public Builder second(int second){
            this.second = second;
            return this;
        }

        public Builder third(int third){
            this.third = third;
            return this;
        }

        public Builder forth(int forth){
            this.forth = forth;
            return this;
        }

        public test build(){
            return new test(this);
        }

    }

    private test(Builder builder) {
        this.first = builder.first;
        this.second = builder.second;
        this.third = builder.third;
        this.forth = builder.forth;
    }

    public static void main(String[] args) {

        char i = 'а';
        for (int j = 0; j < 32; j++) {
            System.out.println(i);
            i++;
        }

    }




    @Override
    public String toString() {
        return "test{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                ", forth=" + forth +
                '}';
    }
}

