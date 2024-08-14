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

	// 필드
	private static final long serialVersionUID = 1L;

	// 생성자

	// 메서드 gs

	// 메서드 일반

	// 접수받는 일( controller )
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// action 꺼내서 담기
		String action = request.getParameter("action");
		System.out.println(action);

		if ("list".equals(action)) { // null일수도 있을때를 방지하기 위해서 거꾸로 써주기
			System.out.println("list 확인용");

			PhonebookDao phonebookDao = new PhonebookDao();
			List<PersonVo> personList = phonebookDao.getPersonList();
			System.out.println(personList);

			// 화면 그리기 ( ==>포워드 (forword) )

			// request에 리스트 주소 넣기

			request.setAttribute("personList", personList);

			// 포워드 하는법
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);

			// ***************** 등록 폼 보내기 *******************
		} else if ("writeform".equals(action)) {

			System.out.println("등록 폼 요청(저장해줘x)");

			// forword(포워드) 해주기
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
		}

		//// *************** [등록해서 보내기] ************

		else if ("insert".equals(action)) {

			System.out.println("등록 요청 데이터3개 완료!~!");

			// 나머지 파라미터 꺼내기(name, hp, company) >> Vo로 묶어서 관리

			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);

			// <<<<Dao를 메모리에 올리고 insertPerson(personVo) 를 통해서 DB에 올리기>>>>

			// Dao 메모리에 올리기
			PhonebookDao phonebookDao = new PhonebookDao();

			// 디비에 값 넣어주기
//			phonebookDao.insertPerson(personVo);
			int count = phonebookDao.insertPerson(personVo);
			System.out.println(count);

//			// list로 출력해주기
//			List<PersonVo> personList = phonebookDao.getPersonList();
//
//			// request에 리스트 주소 넣기
//
//			request.setAttribute("personList", personList);
//			
//			//포워드
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
//			rd.forward(request, response);

			// <<<<<<<<<<<###########**********######### 리다이렉트 해주기
			// ##############**************###############/>>>>>>>>>>>>

			response.sendRedirect("http://localhost:8088/phonebook2/pbcontroller?action=list");

		} else if ("editform".equals(action)) {

			System.out.println("수정'폼' 입당~!");

			// 가져오는값들은 String형으로 넘어옴. int형으로 형변환 해줘야함
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);

			// 메모리 올리기
			PhonebookDao phonebookDao = new PhonebookDao();

			// 한명 주소값 가져오기
			PersonVo personVo = phonebookDao.getPersonOne(no);

			System.out.println("==============");
			System.out.println(no);

			// 화면 + 데이터 == 수정폼 만들기

			// 리케스트> 어트리뷰트 영역에 personVo 주소를 담는다
			request.setAttribute("personVo", personVo);

			/// **** 포워드 editForm.jsp*****
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editForm.jsp");
			rd.forward(request, response);

		} else if ("update".equals(action)) {

			System.out.println("수정 입니당");

			// **** 데이터(파라미터)꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			PersonVo personVo = new PersonVo(no, name, hp, company);
			System.out.println(personVo);

			// 데이터 올리기
			PhonebookDao phonebookDao = new PhonebookDao();

			// phonebookDao 를 통해서 수정을 시킨다(update)
			phonebookDao.updatePerson(personVo);

			// 리다이렉트 시켜주기
			response.sendRedirect("http://localhost:8088/phonebook2/pbcontroller?action=list");

		} else if ("delete".equals(action)) {

			System.out.println("삭제합니다~");
			
			//파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
//			String name = request.getParameter("name");
//			String hp = request.getParameter("hp");
//			String company = request.getParameter("company");
			
			//묶어주기 >>> 숫자 하나니까 굳이 담을필요없음
//			PersonVo personVo = new PersonVo(no);
//			System.out.println(personVo);
			
			//데이터 올리기
			PhonebookDao phonebookDao = new PhonebookDao();
			
			// 삭제해주기
			phonebookDao.deletePerson(no);
			
			//리다이렉트 해주기
			response.sendRedirect("http://localhost:8088/phonebook2/pbcontroller?action=list");
			
			
		}
		/*
		 * //DB 가져오는일 System.out.println("접수성공용");
		 * 
		 * PhonebookDao phonebookDao = new PhonebookDao(); List<PersonVo> personList =
		 * phonebookDao.getPersonList(); System.out.println(personList);
		 * 
		 * // 화면 그리기 ( ==>포워드 (forword) )
		 * 
		 * //request에 리스트 주소 넣기
		 * 
		 * request.setAttribute("personList", personList);
		 * 
		 * //포워드 하는법 RequestDispatcher rd =
		 * request.getRequestDispatcher("/WEB-INF/list.jsp");
		 * rd.forward(request,response);
		 */

		// ************* [수정] *******************

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
