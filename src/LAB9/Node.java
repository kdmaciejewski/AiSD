public class Node {
    char value;
    Node left, right;

    public Node(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    public String wyswietlanie(){
        return  String.valueOf(value);
    }

    @Override
    public String toString() {
        return left == null ? String.valueOf(value) :
                ("(" + left +")"+ " " + value + " " +"(" + right + ")");
    }
    public String wyswietlanieONP(){
        return left == null ? String.valueOf(value) :
                ("(" + left +")"+ " " + value + " " +"(" + right + ")");
    }
//    @Override
//    public String toString() {
//        return left == null ? String.valueOf(value) :
//                ("(" + left + " " + value + " " + right + ")");
//    }
}