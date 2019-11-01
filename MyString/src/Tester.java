public class Tester {
    public static void main(String[] args) {
        MyString lol = new  MyString(" Good candy  ");
        char[] check = {'G','o','o'};
        char[] check2 = {' ', 'K'};
        System.out.println(lol.length());
        lol.trim();
        System.out.println(lol.length());

        System.out.println(lol.contains(check));
        System.out.println(lol.contains(check2));
    }
}
