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
				System.out.println("제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "title", 1);
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("제목역순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "title", 0);
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "due_date", 1);
				isList = true;
				break;
				
			case "ls_date_desc":
				System.out.println("날짜역순으로 정렬하였습니다.");
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
				
				System.out.println("-----중요하면서 급한 일입니다. 지금 당장 하시길 추천드립니다.-----");
				TodoUtil.checkimportant(l,"hurry!","high");
				System.out.println("\n---중요하지만 급한 일은 아닙니다. 기한이 다가오기전에 완료하시길 바랍니다.---");
				TodoUtil.checkimportant(l,"not yet","high");
				System.out.println("\n-중요하진 않지만 급한 일입니다. 급하고, 중요한 일이 처리되면 그 후 하시길 바랍니다.-");
				TodoUtil.checkimportant(l,"hurry!","low");
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("정해진 명령을 입력하세요 (명령을 모른다면 help를 입력하세요)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		DbConnect.closeConnection();
		sc.close();
		//TodoUtil.saveList(l, "todolist.txt");
	}
}
