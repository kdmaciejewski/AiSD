import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree {
    String str;
    Node root;

    public String convertONP(String exp) {
        if (exp == null)
            return null;
        String wyr = "";  // wyrażenie onp
        int len = exp.length();
        Stack<Character> operator = new Stack<Character>();
        Stack<String> reversePolish = new Stack<String>();

        operator.push('#');  // zabezpieczenie by nie sprawdzać pustego
        for (int i = 0; i < len; ) {
            //gdy spacja
            while (i < len && exp.charAt(i) == ' ')
                i++;
            if (i == len)  //koniec
                break;

            if (isNum(exp.charAt(i))) {  // jeśli to liczba
                String num = "";
                while (i < len && isNum(exp.charAt(i)))
                    num += exp.charAt(i++);
                reversePolish.push(num);

            } else if (isOperator(exp.charAt(i))) {   //jeśli to znak
                char op = exp.charAt(i);
                switch (op) {
                    case '(':
                        operator.push(op);  // na stos dla operatorów
                        break;
                    case ')':
                        while (operator.peek() != '(')    // odpinamy operatory bo był nawias zamykający
                            reversePolish.push(Character.toString(operator.pop()));
                        operator.pop();
                        break;
                    case '+':
                    case '-':
                        if (operator.peek() == '(')  // jeśli otwierający to odkładamy operator na stos
                            operator.push(op);
                        else {
                            while (operator.peek() != '#' && operator.peek() != '(') //przenosimy operatory na 2 stos
                                reversePolish.push(Character.toString(operator.pop()));
                            operator.push(op);
                        }
                        break;
                    case '%':
                    case '*':
                    case '/':
                        if (operator.peek() == '(')
                            operator.push(op);         // jeśli jest otwierający to dodajemy na stos
                        else {
                            while (operator.peek() != '#' && operator.peek() != '+' &&
                                    operator.peek() != '-' && operator.peek() != '(')
                                reversePolish.push(Character.toString(operator.pop()));
                            operator.push(op);
                        }
                        break;
                }
                i++;  // przechodzimy na kolejny znak
            }
        }
        while (operator.peek() != '#')  //dochodzimy do początku stosu operatorów
            reversePolish.push(Character.toString(operator.pop()));  //dodaejmy na stos pozostałe operatory

        while (!reversePolish.isEmpty()) //wyjmujemy wyrażenie ze stosu
            wyr = wyr.length() == 0 ? reversePolish.pop() + wyr : reversePolish.pop() + " " + wyr;
        str = wyr;
        return wyr;
    }

    public void buduj() {     // poprawne drzewo przy pomocy stosu
        char tab[] = new char[str.length()];
        tab = str.toCharArray();

        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < tab.length; i++) {
            char c = tab[i];
            if (czyLiczba(c)) {
                Node n = new Node(c);
                stack.push(n);
            } else if (isOperator(c)) {
                Node n = new Node(c);
                n.setRight(stack.pop());
                n.setLeft(stack.pop());
                stack.push(n);
            }
        }
        root = stack.peek();
        System.out.println("Postać z nawiasami (inorder): "+stack.peek()); //wyświetlam prz pomocy toString
    }

    public void wyswietl() {
        wyswietlDrzewo(root);
    }

    public void wyswietlDrzewo(Node r) {
        Node n = r;
        if (n.getLeft() == null) {
            String.valueOf(n.getValue());
        } else {
            wyswietlDrzewo(n.getLeft());
            System.out.println(n.getValue());
            wyswietlDrzewo(n.getRight());
        }
    }


    public void kalkulator(){
        System.out.println("Wynik: " + calculate(root));
    }
    public int calculate(Node n) {
        char x = n.getValue();
        boolean isRightChildANumber = isNum(n.getRight().getValue());
        boolean isLeftChildANumber = isNum(n.getLeft().getValue());
        Node rightChild = n.getRight();
        Node leftChild = n.getLeft();

        switch(x) {
            case '+': {
                if(isRightChildANumber && isLeftChildANumber)
                    return Integer.valueOf(n.getLeft().getValue()) + Integer.valueOf(n.getRight().getValue());
                if(isLeftChildANumber)
                    return Integer.valueOf(n.getLeft().getValue()) + calculate(rightChild);
                if(isRightChildANumber)
                    return calculate(leftChild) + Integer.valueOf(n.getRight().getValue());
                else
                    return calculate(leftChild) + calculate(rightChild);
            }
            case '-': {
                if(isRightChildANumber && isLeftChildANumber)
                    return Integer.valueOf(n.getLeft().getValue()) - Integer.valueOf(n.getRight().getValue());
                if(isLeftChildANumber)
                    return Integer.valueOf(n.getLeft().getValue()) - calculate(rightChild);
                if(isRightChildANumber)
                    return calculate(leftChild) - Integer.valueOf(n.getRight().getValue());
                else
                    return calculate(leftChild) - calculate(rightChild);
            }
            case '*': {
                if(isRightChildANumber && isLeftChildANumber)
                    return Integer.valueOf(n.getLeft().getValue()) * Integer.valueOf(n.getRight().getValue());
                if(isLeftChildANumber)
                    return Integer.valueOf(n.getLeft().getValue()) * calculate(rightChild);
                if(isRightChildANumber)
                    return calculate(leftChild) * Integer.valueOf(n.getRight().getValue());
                else
                    return calculate(leftChild) * calculate(rightChild);
            }
            case '/': {
                if(isRightChildANumber && isLeftChildANumber)
                    return Integer.valueOf(n.getLeft().getValue()) / Integer.valueOf(n.getRight().getValue());
                if(isLeftChildANumber)
                    return Integer.valueOf(n.getLeft().getValue()) / calculate(rightChild);
                if(isRightChildANumber)
                    return calculate(leftChild) / Integer.valueOf(n.getRight().getValue());
                else
                    return calculate(leftChild) / calculate(rightChild);
            }
            case '%': {
                if(isRightChildANumber && isLeftChildANumber)
                    return Integer.valueOf(n.getLeft().getValue()) % Integer.valueOf(n.getRight().getValue());
                if(isLeftChildANumber)
                    return Integer.valueOf(n.getLeft().getValue()) % calculate(rightChild);
                if(isRightChildANumber)
                    return calculate(leftChild) % Integer.valueOf(n.getRight().getValue());
                else
                    return calculate(leftChild) % calculate(rightChild);
            }

        }
        return 101010;
    }


    public String ileLisci()
    {
        return "Liczba liści drzewa: " + getLeafCount(root);
    }

    public int getLeafCount(Node node)
    {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null) // jeśli oboje dzieci jest jest null to +1
            return 1;
        else
            return getLeafCount(node.left) + getLeafCount(node.right);
    }
    public String ileWezlow()
    {
        return "Liczba węzłów drzewa: "+ getNodeCount(root);
    }

    public int getNodeCount(Node node) // zliczamy każdy węzeł
    {
        if (node == null)
            return 0;

        return 1 + getNodeCount(node.left) + getNodeCount(node.right);
    }

    public String jakaWysokosc(){
        return "Wysokość: " + (getHeightCount(root)-1);
    }
    public int getHeightCount(Node node){
        if (node == null)
            return 0;
        else
        {   // szukamy maksymalnej wysokości drzew
            int l = getHeightCount(node.left);
            int r = getHeightCount(node.right);

            if (l > r)
                return (l + 1);
            else
                return (r + 1);
        }
    }

    public void goAcross(){
        goAcross(root);
    }
    public void goAcross(Node n){
        Queue<Node> queue = new LinkedList<>();
        queue.add(n);

        Node node = null;
        while(!queue.isEmpty()) {
            node = queue.remove();
            System.out.print(node.wyswietlanie() + " ");

            if(node.getLeft() != null)      // dodaejmy do kolejki dzieci odnóg
                queue.add(node.getLeft());  //wykonujemy dopóki zabraknie węzłów
            if(node.getRight() != null)
                queue.add(node.getRight());
        }
    }

    public boolean isOperator ( char c){ // sprawdzenie czy to działanie lub nawias
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '(' || c == ')';
    }

    public boolean isNum ( char c){   // czy liczba
        return c - '0' >= 0 && c - '0' <= 9;
    }
    public boolean czyLiczba ( char c){
        if ("1234567890".indexOf(c) != -1) {
            return true;
        }
        return false;
    }


}
