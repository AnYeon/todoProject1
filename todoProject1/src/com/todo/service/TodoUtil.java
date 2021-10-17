package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;


public class TodoUtil {

	/*public static void saveList(TodoList l, String filename) {
		try {
			FileWriter fw = new FileWriter(filename, false);
			for (TodoItem item : l.getList()) {
				
				fw.write(item.toSaveString());
					
			}
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}*/
	public static void createItem(TodoList l) {
		
		String title, desc;
		String category;
		String due_date;
		String importancy;
		String priority;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== ��Ͽ� �߰�\n"
				+ "ī�װ��� �Է��ϼ���");
		
		category= sc.next();
		
		System.out.println("������ �Է��ϼ���");
		
		title = sc.next();
		if (l.isDuplicate(title)) {
			System.out.printf("������ �ߺ��� �� �����ϴ�.");
			return;
		}
		desc = sc.nextLine();
		
		System.out.println("������ �Է��ϼ���");
		desc = sc.nextLine();
		
		System.out.println("�������ڸ� �Է��ϼ���");
		due_date= sc.next();
		
		System.out.println("�߿䵵�� �Է��ϼ���");
		importancy= sc.next();
		
		TodoItem t = new TodoItem(category, title, desc, due_date, importancy, 0);
		if(l.addItem(t)>0) {
			System.out.println("�߰��Ǿ����ϴ�.");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n"
				+ "========== ��� ���� �Ϻ� ����\n"
				+ "������ �������� ��ȣ�� �Է��ϼ���");
		String line = sc.nextLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		int sum=0;
		
		while(st.hasMoreTokens()) {
			int num= Integer.parseInt(st.nextToken());
			sum=sum+l.deleteItem(num);
		}
		if(sum>0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== �κ��� ����\n"
				+ "�ֽ�ȭ�� �������� ��ȣ�� �Է��ϼ���");
		int number= sc.nextInt();
		if (number<=0) {
			System.out.println("�ش� ��ȣ�� �ش��ϴ� �������� ��Ͽ� �������� �ʽ��ϴ�.");
			return;
		}
		System.out.println("�ٲ� ���ο� ī�װ��� �Է��ϼ���.");
		String new_category = sc.next().trim();
		
		System.out.println("�ٲ� ���ο� ������ �Է��ϼ���.");
		String new_title = sc.next().trim();
		/*
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��� �� �����ϴ�.");
			return;
		}*/
		
		String  new_description = sc.nextLine();
		System.out.println("���ο� ������ �Է��ϼ���");
		new_description = sc.nextLine().trim();
		
		System.out.println("�������ڸ� �Է��ϼ���");
		String new_due_date = sc.next().trim();
		
		System.out.println("�߿䵵�� �Է��ϼ���");
		String importancy= sc.next().trim();
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date, importancy, 0);
		t.setId(number);
		
		if(l.updateItem(t)>0) {
			System.out.println("�ֽ�ȭ �Ǿ����ϴ�.");}
	}
	public static void completeItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("�Ϸ��� ���� ��ȣ�� �Է��ϼ���");
		
		String line = sc.nextLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		int sum=0;
		
		while(st.hasMoreTokens()) {
			String a= st.nextToken();
			int num= Integer.parseInt(a);
			sum=sum+l.completeItem(num);
		}
		if(sum>0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
		
		
	}
	public static void listComp(TodoList l) {
		
		for(TodoItem item : l.getList()) {
			if(item.getIs_completed()==1)
				System.out.println(item.toString2());
		}
		
		
	}
	public static void listAll(TodoList l) {
		System.out.println("��ü ���, �� "+ l.getCount()+"��");
		for (TodoItem item : l.getList()) {
			if(item.getIs_completed()==0)
				System.out.println(item.toString());
			else
				System.out.println(item.toString2());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("��ü ���, �� "+ l.getCount()+"��");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			{	if(item.getIs_completed()==0)
					System.out.println(item.toString());
				else
					System.out.println(item.toString2());
			}
		}
	}
	
	public static void listCate(TodoList l) {
		int count=0;
		for(String item: l.getCate()) {
			System.out.println(item+" ");
			count++;
		}
		System.out.printf("\n �� %d���� ī�װ��� ��ϵǾ����ϴ�.\n", count);
	}
	
	public static void findList(TodoList l, String keyword) {
		int count=0;
		for (TodoItem item : l.getList(keyword)) {
			if(item.getIs_completed()==0)
				System.out.println(item.toString());
			else
				System.out.println(item.toString2());
			count++;
		}
		System.out.println("�� "+count +" ���� �׸��� ã�ҽ��ϴ�.");
		
	}
	
	public static void find_cate(TodoList l, String keyword) {
		int count=0;
		
		for (TodoItem item : l.getListCate(keyword)) {
			if(item.getIs_completed()==0)
				System.out.println(item.toString());
			else
				System.out.println(item.toString2());
			count++;
		}
		System.out.println("�� "+count +" ���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void checkimportant(TodoList l, String priority, String important) {
		int count=0;
		for (TodoItem item : l.getimplist(priority, important)) {
			if(item.getIs_completed()==0)
				System.out.println(item.toString());
			else
				System.out.println(item.toString2());
			count++;
		
		}
		System.out.println("�� "+count +" ���� �׸��� ã�ҽ��ϴ�.");
	}
		
}
