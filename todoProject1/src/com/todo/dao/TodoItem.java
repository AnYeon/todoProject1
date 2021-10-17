package com.todo.dao;

import java.util.*;
import java.text.SimpleDateFormat;
import com.todo.dao.TodoList;


public class TodoItem {
	private String category;
	private String title;
    private String desc;
    private String current_date;
    private String due_date;
    private int id;
    private String importancy; //(low, middle, high �� 3�ܰ�� �߿䵵 ����)
    private String priority;// ���� ��¥�� �������� ���� �ϼ��� ���, ���� �������� ǥ�� 
    private int  is_completed;
    

	@Override
	public String toString() {
		return  id+""+ "["+category+ "]"+"-" + title+"-" +  desc+"-" +  due_date + "-" + current_date + "-" +importancy + "-" + priority;
	}
	public String toString2() {
		return "[V]" +id+""+ "["+category+ "]"+"-" + title+"-" +  desc+"-" +  due_date + "-" + current_date + "-" +importancy + "-" + priority;
	}

	public TodoItem(String category ,String title, String desc, String due_date, String importancy, int is_completed){
    	this.category=category;
        this.title=title;
        this.desc=desc;
        this.due_date=due_date;
        
        SimpleDateFormat f= new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date=f.format(new Date());
        this.importancy=importancy;
        
        if(TodoList.checkPriority(due_date, current_date)==true)
        	this.priority="hurry!";
        else
        	this.priority="Not yet";
        
        setIs_completed(is_completed);
    }//������ ������ ���
	
	public TodoItem(String category ,String title, String desc, String due_date, String importancy, String priority, int is_completed){
    	this.category=category;
        this.title=title;
        this.desc=desc;
        this.due_date=due_date;
        SimpleDateFormat f= new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date=f.format(new Date());
        this.importancy=importancy;
        this.priority=priority;
        setIs_completed(is_completed);
            }//������ ����, ������ priority�� ���� ���� �״�� ����ϸ� �ȴ�.
  
    /*public TodoItem(String category ,String title, String desc, String due_date, String current_date, String importancy){
    	this.category=category;
        this.title=title;
        this.desc=desc;
        this.due_date=due_date;
        this.current_date=current_date;
        this.importancy=importancy;
    }*/
    
    public String toSaveString() {
    	return category+"##" +title+"##" + desc + "##"+ due_date +"##"+ current_date + "\n";
    }
    
    public String getCategory() {
		return category;
	}
	
	public String getDue_date() {
		return due_date;
	}
	
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getCurrent_date() {
        return current_date;
    }
    
    public void setCurrent_date(String current_date) {
        this.current_date=current_date;
    }

    
    public String getImportancy() {
		return importancy;
	}
    
    public String getpriority() {
		return priority;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
}
