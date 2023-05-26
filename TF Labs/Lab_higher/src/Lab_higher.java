import edu.princeton.cs.algs4.StdIn;

public class Lab_higher {
    private static node head;

    public static class node{
        char c;
        node next;
    }

    private static void add(char c) {
        if(head == null){
            head = new node();
            head.c = c;
        }else{
            node New = new node();
            node temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = New;
        }
    }


    private static void firstCheck(){
        node temp = head;
        while( temp != null) {

            if (temp.c == '(' || temp.c == '[' || temp.c == '{' && temp.next != null){
                System.out.println("check");
                temp = temp.next;
                continue;
            }

            System.out.println("check2");
            node temp2 = head;
            while(temp2.next != temp && temp2.next != null && head.next != temp){
                temp2 = temp2.next;
            }
            switch (temp2.c){
                case ')':
                    if ( temp2.c == '{' || temp2.c == '['){
                        break;
                    }else{
                        node temp3 = head;
                        while(temp3.next != temp2){
                            temp3 = temp3.next;
                        }
                        temp3.next = temp.next;
                    }

                case '}':
                    if (temp.c == '(' || temp.c == '['){
                        break;
                    }else{
                        node temp3 = head;
                        while(temp3.next != temp2){
                            temp3 = temp3.next;
                        }
                        temp3.next = temp.next;
                    }

                case ']':
                    if (temp.c == '(' || temp.c == '{'){
                        break;
                    }else{
                        node temp3 = head;
                        while(temp3.next != temp2){
                            temp3 = temp3.next;
                        }
                        temp3.next = temp.next;
                    }
            }
        }
    }

    public static void main(String[] args) {
        char c = StdIn.readChar();
        while(c != '\n'){
            add(c);
            c = StdIn.readChar();
        }
        if(head != null){
            firstCheck();
        }

        if(head == null){
            System.out.println("balanced");
        }else{
            System.out.println("not balanced");
        }

    }
}
