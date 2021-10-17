package com.todo;

import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;
import com.todo.service.DbConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TodoMain {
	
	public static void start() {
	
		
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		String filename= "todolist.txt";
		
		l.importData(filename);
		
		boolean isList = false;
		boolean quit = false;
		
		Menu.displaymenu();
		
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				TodoUtil.listCate(l);
				break;
				
			case "ls_name":
				System.out.println("��������� �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "title", 1);
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("���񿪼����� �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "title", 0);
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("��¥������ �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "due_date", 1);
				isList = true;
				break;
				
			case "ls_date_desc":
				System.out.println("��¥�������� �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "due_date", 0);
				isList = true;
				break;
				
			case "find":
				String keyword= sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
				
			case "find_cate":
				String cate= sc.nextLine().trim();
				TodoUtil.find_cate(l, cate);
				break;
				
			case "comp":
				TodoUtil.completeItem(l);
				break;
				
			case "ls_comp":
				TodoUtil.listComp(l);
				break;
				
			case "todo": 
				
				System.out.println("-----�߿��ϸ鼭 ���� ���Դϴ�. ���� ���� �Ͻñ� ��õ�帳�ϴ�.-----");
				TodoUtil.checkimportant(l,"hurry!","high");
				System.out.println("\n---�߿������� ���� ���� �ƴմϴ�. ������ �ٰ��������� �Ϸ��Ͻñ� �ٶ��ϴ�.---");
				TodoUtil.checkimportant(l,"not yet","high");
				System.out.println("\n-�߿����� ������ ���� ���Դϴ�. ���ϰ�, �߿��� ���� ó���Ǹ� �� �� �Ͻñ� �ٶ��ϴ�.-");
				TodoUtil.checkimportant(l,"hurry!","low");
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("������ ����� �Է��ϼ��� (����� �𸥴ٸ� help�� �Է��ϼ���)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		DbConnect.closeConnection();
		sc.close();
		//TodoUtil.saveList(l, "todolist.txt");
	}
}
