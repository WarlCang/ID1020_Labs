public class Lab_5 { //typ samma som lab 3, nu lägger jag till så att alla noder har en obestämd index, och innan jag skriver ut dem fixar jag index för dem, så att
			//head får 1 o vidare

    private static node head;
    private static node tail;
    private static int a = 0;

    public static class node{
        int data = a++;
        int index;
        node next;
    }

    private static void addHead(){
        if(head == null){
            head = new node();
            head.next = head;
            tail = head;
        }else{
            node New = new node();
            New.next = head;
            tail.next = New;
            head = New;
        }
        print();
    }

    private static void removeKthElement(int k){
        node temp = head;
        if(head != null && k != 1){//när man tar bort en nod som inte e head
            if(head.index < k && tail.index >= k){
                while(temp.next.index != k){
                    temp = temp.next;
                }
                temp.next = temp.next.next;
                if(k == tail.index){ //när man tar bort tail så ska en ny tail defineras
                    tail = temp;
                }
            }else{
                System.out.println("Node out of range!");
            }
        }else{
            if(head != null && head.next != head){//när man tar bort head
                tail.next = head.next;
                head = head.next;
            }else{
                head = null;
            }
        }
        print();
    }

    private static void print(){
        if(head != null){
            fixIndex();
            System.out.print("{ ");
            node print = head;
            System.out.print("[" + print.data + "][print.index], ");
            print = print.next;
            while (print != head){
                System.out.print("[" + print.data + "][print.index], ");
                print = print.next;
            }
        }else{
            System.out.println(" Empty!");
        }
        System.out.print("} \n");
    }

    private static void fixIndex(){
        node temp = head;
        int index = 1;
        head.index = index++;
        temp = temp.next;
        while(temp != head) {
            temp.index = index++;
            temp = temp.next;
        }

    }

    public static void main(String[] args) {//tester
        addHead();
        addHead();
        addHead();
        addHead();
        removeKthElement(2);
        addHead();
        addHead();
        removeKthElement(5);
        removeKthElement(1);
        removeKthElement(8);
        removeKthElement(1);
        removeKthElement(1);
        removeKthElement(2);
        removeKthElement(3);
    }
}

