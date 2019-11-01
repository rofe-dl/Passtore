public class MyString implements Comparable<MyString>{
    char[] c;

    public MyString(String s){
        this.c = s.toCharArray();
    }

    public int length(){
        return this.c.length;
    }

    public char charAt(int x){
        return this.c[x];
    }

    public boolean startsWith(MyString prefix){
        for (int i = 0; i < prefix.length();i++){
            if (c[i] != prefix.charAt(i)) return false;
        }

        return true;
    }

    public boolean endsWith(MyString suffix){
        for (int i = suffix.length() - 1, j = this.length() - 1; i >= 0; i--, j--){
            if (c[j] != suffix.charAt(i)) return false;
        }

        return true;
    }

    public MyString replaceFirst(char a, char b){
        MyString newString = new MyString(this.toString());
        for (int i = 0; i < newString.length(); i++){
            if (newString.charAt(i) == a){
                newString.c[i] = b;
                break;
            }
        }

        return newString;
    }

    public MyString replaceAll(char a, char b){
        MyString newString = new MyString(this.toString());
        for (int i = 0; i < newString.length(); i++){
            if (newString.charAt(i) == a){
                newString.c[i] = b;
            }
        }

        return newString;
    }

    public MyString replaceLast(char a, char b){
        MyString newString = new MyString(this.toString());
        for (int i = newString.length() - 1; i >= 0; i--){
            if (newString.charAt(i) == a){
                newString.c[i] = b;
                break;
            }
        }

        return newString;
    }

    public MyString toLowerCase(){
        MyString newString = new MyString(this.toString());
        for (int i = 0; i < newString.length(); i++){
            if (newString.c[i] >= 65 && newString.c[i] <= 90){
                newString.c[i] += 32;
            }
        }

        return newString;
    }

    public MyString toUpperCase(){
        MyString newString = new MyString(this.toString());
        for (int i = 0; i < newString.length(); i++){
            if (newString.c[i] >= 97 && newString.c[i] <= 122){
                newString.c[i] -= 32;
            }
        }

        return newString;
    }

    @Override
    public boolean equals(Object x){
        if (!(x instanceof MyString)) return false;
        MyString a = (MyString) x;
        if (a.length() != this.length()) return false;

        for (int i = 0; i < this.length(); i++){
            if (this.charAt(i) != a.charAt(i)) return false;
        }

        return true;
    }

    @Override
    public String toString(){
        return new String(this.c);
    }

    public boolean contains(char[] a){
        for (int i = 0; i < this.length(); i++){
            if (this.charAt(i) == a[0]){
                boolean contains = true;
                for (int j = i, k = 0; k < a.length; j++, k++){
                    if (this.charAt(j) != a[k]) {
                        contains = false; break;
                    }
                }

                if (contains) return true;
            }
        }

        return false;
    }

    public  MyString trim(){
        MyString newString = new MyString(this.toString());
        int startIndex = 0, endIndex = newString.length() - 1, emptySpaces = 0;

        for (int i = 0; i < newString.length(); i++){
            if (newString.c[i] != ' '){
                startIndex = i;
                break;
            }else emptySpaces++;
        }

        for (int i = newString.length() - 1; i >= 0; i--){
            if (newString.c[i] != ' '){
                endIndex = i;
                break;
            }
            else emptySpaces++;
        }

        char[] newC = new char[newString.length() - emptySpaces];

        for (int i = 0; startIndex <= endIndex; startIndex++, i++){
            newC[i] = newString.c[startIndex];
        }

        newString.c = newC;

        return newString;
    }

    public boolean equalsIgnoreCase(MyString s){
        MyString newString = this.toLowerCase(), newString2 = s.toLowerCase();
        if (newString.equals(newString2)) return true;
        return false;
    }

    @Override
    public int compareTo(MyString s){
        return this.c[0] - s.c[0];
    }

    public int compareToIgnoreCase(MyString s){
        MyString x = this.toLowerCase(), y = s.toLowerCase();

        return x.compareTo(y);
    }

    public MyString substring(int n){
        MyString newString = new MyString(this.toString());
        char[] newC = new char[this.c.length - n];

        for (int i = 0; i < newC.length; i++, n++){
            newC[i] = newString.charAt(n);
        }

        newString.c = newC;

        return newString;
    }

    public MyString substring(int n, int m){
        MyString newString = new MyString(this.toString());
        char[] newC = new char[m - n];

        for (int i = 0; i < newC.length; i++, n++){
            newC[i] = newString.charAt(n);
        }

        newString.c = newC;
        return newString;
    }

    public int indexOf(char c){
        for (int i = 0; i < this.length(); i++){
            if (this.charAt(i) == c) return i;
        }

        return -1;
    }

    public int lastIndexOf(char c){

        for (int i = this.length() - 1; i >= 0; i--){
            if (this.charAt(i) == c) return i;
        }

        return -1;
    }

    public int indexOf(char c, int x){

        for (int i = x; i < this.length(); i++){
            if (this.charAt(i) == c) return i;
        }

        return -1;
    }

    public int lastIndexOf(char c, int x){

        for (int i = x; i >= 0; i--){
            if (this.charAt(i) == c) return i;
        }

        return -1;
    }

    public MyString concat (MyString s){
        char[] newC = new char[this.length() + s.length()];
        int i = 0;
        for (char c : this.c){
            newC[i] = c;
            i++;
        }

        for (char c : s.c){
            newC[i] = c;
            i++;
        }

        MyString newString = new MyString(" ");
        newString.c = newC;

        return newString;
    }

    public boolean isEmpty(){
        if (this.length() == 0) return true;
        return false;
    }
}