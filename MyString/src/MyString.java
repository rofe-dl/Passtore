public class MyString {
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

    public boolean endsWith(MyString prefix){
        for (int i = prefix.length() - 1, j = this.length() - 1; i >= 0; i--, j--){
            if (c[j] != prefix.charAt(i)) return false;
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

    public boolean equals(MyString a){
        if (a.length() != this.length()) return false;

        for (int i = 0; i < this.length(); i++){
            if (this.charAt(i) != a.charAt(i)) return false;
        }

        return true;
    }

    @Override
    public String toString(){
        String s = "";
        for (char i : this.c){
            s += i;
        }

        return s;
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
}