package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		
		//DB 가져오는일
		System.out.println("접수성공용");
		
		PhonebookDao phonebookDao = new PhonebookDao();
		List<PersonVo> personList = phonebookDao.getPersonList();
		System.out.println(personList);
		
		// 화면 그리기 ( ==>포워드 (forword) )
		
		//request에 리스트 주소 넣기	
		
		request.setAttribute("personList", personList);
		
		//포워드 하는법
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
		rd.forward(request,response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
