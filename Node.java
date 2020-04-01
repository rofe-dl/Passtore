public class Node{
    Node next, prev;
    int value;

    public Node(int value, Node next){
        this.value = value;
        this.next = next;
    }

    public Node(int value, Node next, Node prev){
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}