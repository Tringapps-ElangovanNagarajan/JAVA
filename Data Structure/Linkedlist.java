import java.util.*;
class List<T> {
	Node head;
	
	class Node{
		T data;
		Node next;	
		Node(T val){
			data = val;
			next = null;
		}
	}
	List(){
		head = null;
	}
	
	public void insertAtBeginning(T val){
		Node newNode = new Node(val);
		if(head==null)
		{
			head = newNode;
		}
		else 
		{ 
			newNode.next = head;	
			head = newNode;
		}	
		
	}
	
	public void insertAtPosition(int pos,T val) {
		if(pos==0)
		{
			insertAtBeginning(val);
			return;
		}
		
		Node newNode = new Node(val);
		Node temp = head;
		for(int i=1;i<pos;i++) { 
			temp = temp.next;
			if(temp==null) 
				throw new IndexOutOfBoundsException("Invalid Position " + pos);
		}
		newNode.next = temp.next;
		temp.next = newNode;
	}

	public void display() {
		Node temp = head;
		while(temp != null) { 
			System.out.print( temp.data +" " );
			temp = temp.next; 
		}
		
	}

    public void deleteAtPosition (int pos) {
		
		if(head==null) 
			throw new IndexOutOfBoundsException("It is a empty list! Deletion cant be performed here ");
	
		if(pos==0) {
			head = head.next;
			return;
		}
		
		Node temp = head;
		Node prev = null;
		

		for(int i=1;i<=pos;i++) {
			prev = temp;
			temp = temp.next; 
		}
		
		prev.next = temp.next; 
	}
}

public class LinkedList {
public static void main(String a[]) {
	Scanner sc=new Scanner(System.in);
	List<Integer> li =new List<Integer>();
	while(true) {
	System.out.println("1.Insert at begining");
	System.out.println("2.Insert at posiition");
	System.out.println("3.Display");
	System.out.println("4.Delete");
	System.out.println("Enter your choice = ");
	int choice=sc.nextInt();
	switch(choice) {
	case 1:System.out.print("Enter your data = ");
		   int val=sc.nextInt();
		   li.insertAtBeginning(val);
		   break;
	case 2:System.out.print("Enter your data = ");
	   	   int val2=sc.nextInt();
	   	   System.out.print("Enter your target position = ");
	   	   int pos=sc.nextInt();
           li.insertAtPosition(pos,val2);
           break;
	case 3:li.display();break;
	case 4:System.out.print("Enter your target position = ");
	       int pos1=sc.nextInt();
           li.deleteAtPosition(pos1);
           break;
    default:
    	System.out.println("You entered the wrong choice! Try again");
	
    
	}
	System.out.println(" ");
	}
}
}
