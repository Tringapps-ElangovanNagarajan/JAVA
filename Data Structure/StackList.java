package stack;
import java.util.*;
class Stack<T> {
	class Node{
		T data;
		Node next;
		Node(T val){
			data = val;
			next = null;
		}
	}
	
	Node top;
	Stack(){
		top = null;
	}
	
	public void push(T val) {
		Node newNode = new Node(val);
		newNode.next = top;
		top = newNode;
	}
	
	public T pop() {
		if(top==null)
			throw new IndexOutOfBoundsException("Stack is Empty");
		
		T temp = top.data;
		top = top.next;
		return temp;
		
	}
	
	public T topNode() {
		return top.data;
	}
	
}
public class StackList {
public static void main(String args[]) {
	Stack<Integer> li=new Stack<Integer>();
	Scanner sc=new Scanner(System.in);
	while(true) {
	System.out.println("1.Push");
	System.out.println("2.Pop");
	System.out.println("3.topNode");
	System.out.println("Enter your choice =");
	int choice=sc.nextInt();
	switch(choice) {
	case 1:System.out.print("Enter your data = ");
	   int val=sc.nextInt();
	   li.push(val);
	   break;
	case 2:System.out.println(li.pop() + "is popped from stack");
	       break;
	case 3:System.out.println("Top element in the stack is " + li.topNode());
			break;
	default:
		System.out.println("You entered the Wrong Choice!Try again");
	}
	}
}
}
