package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;


@WebServlet("/pbcontroller")
public class phonebookController extends HttpServlet {
	
	//필드
	private static final long serialVersionUID = 1L;
       
	//생성자
   
    
    //메서드 gs
    
    
    //메서드 일반
    
	//접수받는 일( controller )
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("접수성공용");
		
		PhonebookDao phonebookDao = new PhonebookDao();
		List<PersonVo> personList = phonebookDao.getPersonList();
		System.out.println(personList);
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
