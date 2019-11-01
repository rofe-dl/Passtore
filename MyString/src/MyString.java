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

    public void replaceFirst(char a, char b){
        for (int i = 0; i < this.length(); i++){
            if (this.charAt(i) == a){
                this.c[i] = b;
                break;
            }
        }
    }

    public void replaceAll(char a, char b){
        for (int i = 0; i < this.length(); i++){
            if (this.charAt(i) == a){
                this.c[i] = b;
            }
        }
    }

    public void replaceLast(char a, char b){
        for (int i = this.length() - 1; i >= 0; i--){
            if (this.charAt(i) == a){
                this.c[i] = b;
                break;
            }
        }
    }

    public void toLowerCase(){
        for (int i = 0; i < this.length(); i++){
            if (this.c[i] >= 65 && this.c[i] <= 90){
                this.c[i] += 32;
            }
        }
    }

    public void toUpperCase(){
        for (int i = 0; i < this.length(); i++){
            if (this.c[i] >= 97 && this.c[i] <= 122){
                this.c[i] -= 32;
            }
        }
    }

    public boolean equals(MyString a){
        if (a.length() != this.length()) return false;

        for (int i = 0; i < this.length(); i++){
            if (this.charAt(i) != a.charAt(i)) return false;
        }

        return true;
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

    public  void trim(){
        int startIndex = 0, endIndex = this.length() - 1, emptySpaces = 0;

        for (int i = 0; i < this.length(); i++){
            if (this.c[i] != ' '){
                startIndex = i;
                break;
            }else emptySpaces++;
        }

        for (int i = this.length() - 1; i >= 0; i--){
            if (this.c[i] != ' '){
                endIndex = i;
                break;
            }
            else emptySpaces++;
        }

        char[] newC = new char[this.length() - emptySpaces];

        for (int i = 0; startIndex <= endIndex; startIndex++, i++){
            newC[i] = this.c[startIndex];
        }

        this.c = newC;
    }

    public boolean equalsIgnoreCase(MyString s){

    }
}