public class Tester {
    public static void main(String[] args) {
        MyString lol = new  MyString(" Good candy  ");
        char[] check = {'G','o','o'};
        char[] check2 = {' ', 'K'};
        System.out.println(lol.length());
        lol = lol.trim();
        System.out.println(lol.length());

        System.out.println(lol.contains(check));
        System.out.println(lol.contains(check2));

        MyString doggo = new MyString("doggooy");
        System.out.println(doggo.substring(2,5));

        MyString xd = new MyString ("quick");
        char[] arr = {'l'};
        System.out.println(xd.contains(arr));

        MyString ok = new MyString(" ");
        System.out.println(ok.isEmpty());
    }
}
