public class Lab_3 { 

    private static node head;
    private static node tail;
    private static int a = 0;

    public static class node{
        int data = a++;
        node next;
        node prev;
    }

    private static void addHead(){ //lägger till första nod, head, heads prev och next, tail och tails prev och next ska vara samma. 
		if(head == null){
			head = new node();
			tail = head;
			head.next = tail;
			head.prev = tail;
		}else{
			System.out.println("head already exists!");
		}
        print();
    }

    private static void add(){ lägger till en nod(inte head) efter alla noder, så heads prev, tails next(old), tail ska ändras.  
        if(head == null){
            addHead();
        } else{
            node New = new node();
            head.prev = New;
            tail.next = New;
            New.prev = tail;
            New.next = head;
            tail = New;
            print();
        }
    }

    private static void remove(){  //FIFO, så head ska bort, då head.next blir den nya head.
        if(head != null && head != tail) {
            head.next.prev = tail;
            tail.next = head.next;
            head = head.next;
        }else{
            head = null;
        }

        print();
    }

    private static void print(){ //skriver head först o en loop gör att den försätter skriva tills det når till head igen
        if(head != null){
            System.out.print("{ ");
            node print = head;
            System.out.print("[" + print.data + "], ");
            print = print.next;
            while (print != head){
                System.out.print("[" + print.data + "], ");
                print = print.next;
            }
        }else{
            System.out.println(" Empty!");
        }
        System.out.print("} \n");
    }

    public static void main(String[] args) {// tester
	remove();
        add();
        add();
        add();
        add();
	addhead();
        remove();
        add();
        remove();
        remove();
        remove();
        remove();
        remove();
	remove();
    }
}
