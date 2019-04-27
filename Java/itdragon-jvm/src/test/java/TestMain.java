public class TestMain {

    public static void main(String[] args) {

        for (int i = 0; i <= 50; i++) {
            String tmp = "http://pm.enargycorp.com/lifanacgup/lifanacg/20180427/6/";
            if (i < 10) {
                tmp = tmp + "f00" + i +"ko.jpg";
            } else if (i < 100) {
                tmp = tmp + "f0" + i +"ko.jpg";
            }
            System.out.println(tmp);
        }
    }
}
