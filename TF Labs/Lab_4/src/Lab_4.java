public class Lab_4 {//Typ samma som lab 3, behöver dock en loop för removeTail, behöver hitta pos. för den nya tail först

    private static node head;
    private static node tail;
    private static int a = 0;

    public static class node{
        int data = a++;
        node next;
    }

    private static void addHead(){
        if(head == null){
            head = new node();
            tail = head;
            head.next = tail;
        }else{
            node New = new node();
            New.next = head;
            tail.next = New;
            head = New;
        }
        //System.out.println("add head");
        print();
    }

    private static void addTail(){
        if(tail == null){
            addHead();
        }else{
            node New = new node();
            New.next = head;
            tail.next = New;
            tail = New;
        }
        //System.out.println("add tail");
        print();
    }

    private static void removeHead(){
        if(head != null && head.next != head){
            tail.next = head.next;
            head = head.next;
        }else{
            head = null;
        }
        //System.out.println("remove head");
        print();
    }

    private static void removeTail(){
        if(tail != null && tail.next != tail){
            node newTail = head;
            while(newTail.next != tail){
                newTail = newTail.next;
            }
            newTail.next = head;
            tail = newTail;
        }else{
            head = null;
        }
        //System.out.println("remove tail");
        print();
    }

    private static void print(){
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

    public static void main(String[] args) {
        addHead();
        addHead();
        addTail();
        addTail();
        removeHead();
        removeTail();
        addHead();
        removeTail();
        removeTail();
        removeTail();
        removeTail();
        removeTail();
        addTail();
        addTail();
        addHead();
        addHead();
        addTail();
        addTail();
        removeHead();
        removeHead();
        removeTail();
        removeTail();
    }
}

