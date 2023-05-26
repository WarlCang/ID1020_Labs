public class Lab_6 { //Baserad på lab 5

    public static int randomInteger(int min, int max){ //random integer
        int a = (int)(Math.random()*((max-min)+1))+min;
        return a;
    }

    private static node head;
    private static node tail;

    public static class node{
        int data = randomInteger(1, 100);
        node next;
        int index;
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
            sort();
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

    private static void sort(){//skapar 2 loppar, loop 1 jämför current med index(current.next) sen index++ tills den hittar den som är mindre än current, o byter plats för dem o forsätt tills index nå till head;
                                //loop 2 current++;
                                //efter som de innehåller inget annat så räcker det med att byta data av dem.
        node current = head;
        node index = null;
        int temp;

        do{
            index = current.next;
            while(index != head){
                if (current.data > index.data){
                    temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }
                index = index.next;
            }
            current = current.next;
        }while(current.next != head);
    }

    private static void print(){
        if(head != null){
            fixIndex();
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

    private static void fixIndex() {
        node temp = head;
        int index = 1;
        head.index = index++;
        temp = temp.next;
        while (temp != head) {
            temp.index = index++;
            temp = temp.next;
        }
    }

    public static void main(String[] args) {//tester, eftersom det e random numbers, så behöver inte göra nån specifik test utan bara köra programmet flera gånger.
        addHead();
        addHead();
        addHead();
        addHead();
        addHead();
        addHead();
        addHead();
        removeKthElement(5);
        removeKthElement(2);
        removeKthElement(1);
        removeKthElement(2);
        addHead();
        addHead();
        addHead();
    }
}

