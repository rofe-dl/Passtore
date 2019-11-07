import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
public class Tester {
    public static void main(String[] args) {

        MyString string01 = new  MyString("Kimetsu No Yaiba");
        System.out.println(string01.length()); //length
        System.out.println(string01.charAt(2)); //charAt

        System.out.println();

        MyString kimetsu = new MyString("Kimetsu");
        MyString wrongKimetsu = new MyString("Kimu");
        System.out.println(string01.startsWith(kimetsu));//startsWith
        System.out.println(string01.startsWith(wrongKimetsu));

        System.out.println();

        MyString yaiba = new MyString("Yaiba");
        System.out.println(string01.endsWith(yaiba));//endsWith
        System.out.println(string01.endsWith(wrongKimetsu));

        System.out.println();

        MyString string02 = string01.replaceFirst('m', 'n');
        string02 = string02.replaceLast('a', 'b');
        string02 = string02.replaceAll(' ', 'S');
        System.out.println(string02);//replaceFirst, replaceLast, replaceAll, toString

        System.out.println();

        System.out.println(kimetsu.toUpperCase());//toUpperCase
        System.out.println(kimetsu.toLowerCase());//toLowerCase

        System.out.println();

        MyString kimetsu2 = new MyString("Kimetsu");
        System.out.println(kimetsu2.equals(kimetsu));//equals
        System.out.println(kimetsu2.equals(wrongKimetsu));

        System.out.println();

        char[] charArray1 = {' ', 'Y', 'a'}, charArray2 = {'l', 'o'};
        System.out.println(string01.contains(charArray1));//contains
        System.out.println(string01.contains(charArray2));

        System.out.println();

        MyString string03 = new MyString ("  Boku No Hero   ");
        System.out.println(string03);
        string03 = string03.trim();
        System.out.println(string03);//trim

        System.out.println();

        MyString caps = new MyString("CAPS");
        MyString small = new MyString("caps");
        System.out.println(caps.equalsIgnoreCase(small));//equalsIgnoreCase

        System.out.println();

        ArrayList<MyString> list = new ArrayList<MyString>(); list.add(new MyString("Ball")); list.add(new MyString("Apple")); list.add(new MyString("apple")); list.add(new MyString("Appm"));
        for (MyString i : list) System.out.println(i);
        System.out.println();
        Collections.sort(list);//compareTo
        for (MyString i : list) System.out.println(i);
        Comparator<MyString> comp = new Comparator<MyString>() {
            @Override
            public int compare(MyString o1, MyString o2) {
                return o1.compareToIgnoreCase(o2);//compareToIgnoreCase
            }
        };
        System.out.println();
        Collections.sort(list, comp);
        for (MyString i : list) System.out.println(i);

        System.out.println();

        MyString string04 = new MyString("Good doggo");
        System.out.println(string04.substring(3));//substring int
        System.out.println(string04.substring(4,6));//substring int int
        System.out.println(string04.indexOf('o'));//indexOf char
        System.out.println(string04.lastIndexOf('o'));//lastIndexOf char
        System.out.println(string04.indexOf('o',5));//indexOf char int
        System.out.println(string04.lastIndexOf('o', 3));//lastIndexOf char int

        System.out.println();

        System.out.println(string04.concat(new MyString(" is not a bad doggo")));//concat
        System.out.println(string04.isEmpty());//isEmpty
        System.out.println(new MyString("").isEmpty());
    }
}
