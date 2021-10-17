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
				+ "========== 목록에 추가\n"
				+ "카테고리을 입력하세요");
		
		category= sc.next();
		
		System.out.println("제목을 입력하세요");
		
		title = sc.next();
		if (l.isDuplicate(title)) {
			System.out.printf("제목은 중복될 수 없습니다.");
			return;
		}
		desc = sc.nextLine();
		
		System.out.println("내용을 입력하세요");
		desc = sc.nextLine();
		
		System.out.println("마감일자를 입력하세요");
		due_date= sc.next();
		
		System.out.println("중요도를 입력하세요");
		importancy= sc.next();
		
		TodoItem t = new TodoItem(category, title, desc, due_date, importancy, 0);
		if(l.addItem(t)>0) {
			System.out.println("추가되었습니다.");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n"
				+ "========== 목록 내용 일부 제거\n"
				+ "제거할 아이템의 번호를 입력하세요");
		String line = sc.nextLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		int sum=0;
		
		while(st.hasMoreTokens()) {
			int num= Integer.parseInt(st.nextToken());
			sum=sum+l.deleteItem(num);
		}
		if(sum>0) {
			System.out.println("삭제되었습니다.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 부분을 편집\n"
				+ "최신화할 아이템의 번호를 입력하세요");
		int number= sc.nextInt();
		if (number<=0) {
			System.out.println("해당 번호에 해당하는 아이템이 목록에 존재하지 않습니다.");
			return;
		}
		System.out.println("바꿀 새로운 카테고리를 입력하세요.");
		String new_category = sc.next().trim();
		
		System.out.println("바꿀 새로운 제목을 입력하세요.");
		String new_title = sc.next().trim();
		/*
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다.");
			return;
		}*/
		
		String  new_description = sc.nextLine();
		System.out.println("새로운 내용을 입력하세요");
		new_description = sc.nextLine().trim();
		
		System.out.println("마감일자를 입력하세요");
		String new_due_date = sc.next().trim();
		
		System.out.println("중요도를 입력하세요");
		String importancy= sc.next().trim();
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date, importancy, 0);
		t.setId(number);
		
		if(l.updateItem(t)>0) {
			System.out.println("최신화 되었습니다.");}
	}
	public static void completeItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("완료한 일의 번호를 입력하세요");
		
		String line = sc.nextLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		int sum=0;
		
		while(st.hasMoreTokens()) {
			String a= st.nextToken();
			int num= Integer.parseInt(a);
			sum=sum+l.completeItem(num);
		}
		if(sum>0) {
			System.out.println("수정되었습니다.");
		}
		
		
	}
	public static void listComp(TodoList l) {
		
		for(TodoItem item : l.getList()) {
			if(item.getIs_completed()==1)
				System.out.println(item.toString2());
		}
		
		
	}
	public static void listAll(TodoList l) {
		System.out.println("전체 목록, 총 "+ l.getCount()+"개");
		for (TodoItem item : l.getList()) {
			if(item.getIs_completed()==0)
				System.out.println(item.toString());
			else
				System.out.println(item.toString2());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("전체 목록, 총 "+ l.getCount()+"개");
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
		System.out.printf("\n 총 %d개의 카테고리가 등록되었습니다.\n", count);
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
		System.out.println("총 "+count +" 개의 항목을 찾았습니다.");
		
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
		System.out.println("총 "+count +" 개의 항목을 찾았습니다.");
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
		System.out.println("총 "+count +" 개의 항목을 찾았습니다.");
	}
		
}
