package com.example;
import java.util.Scanner;

class Queue {
	class Node{
		int data;
		Node next;
		
		Node(int val){
			data = val;
			next = null;
		}
	}
	
	Node front,rear; 
	
	Queue(){
		front = null;
		rear = null;
	}
	public void enqueue(int val) { 
		Node newNode = new Node(val);
		if(front==null)
			front = newNode;
		else
			rear.next = newNode	;
		rear = newNode;
		
	}
	
	public int dequeue() {
		if(front==null)
			throw new IndexOutOfBoundsException("Queue is Empty");
		int temp = front.data;
		front = front.next;
		if(front==null) 
			rear=null;
		return temp;
		
	}
}
public class QueueList {
	public static void main(String args[]) {
		Queue li=new Queue();
		Scanner sc=new Scanner(System.in);
		while(true) {
		System.out.println("1.Enqueue");
		System.out.println("2.Deque");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:System.out.print("Enter your data = ");
		   int val=sc.nextInt();
		   li.enqueue(val);
		   break;
		case 2:System.out.println(li.dequeue() + " dequeue from Queue");
		       break;
		default:
			System.out.println("You entered the Wrong Choice!Try again");
		}
		}
}
}
