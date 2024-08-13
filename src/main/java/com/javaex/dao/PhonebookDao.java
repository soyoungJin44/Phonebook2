package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhonebookDao {

	// 필드

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/phoneBook_DB";
	private String id = "phonebook";
	private String pw = "phonebook";

	// 생성자
	// < 디폴트 생성자 사용할거임 >

	// 메서드 gs

	// 메서드 일반

	// 1.DB연결 메서드

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 2.자원정리 메서드
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// ********* 리스트 가져오기 *************

	public List<PersonVo> getPersonList() {
		
		List<PersonVo> personList = new ArrayList<PersonVo>();

		// *********************db연결 꼭 해주기
		this.getConnection();
		
		
		
		// 3. SQL문 준비 / 바인딩 실행
		try {

			String query = "";

			query += " select  person_id ";
			query += "        ,name ";
			query += "		  ,hp ";
			query += "		  ,company ";
			query += " from person ";

			// 바인딩

			pstmt = conn.prepareStatement(query);

			// 실행

			rs = pstmt.executeQuery();

			// 4. 결과처리

			while (rs.next()) {
				int id = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo personVo = new PersonVo(id,name,hp,company); 
				System.out.println(personVo);
				
				personList.add(personVo);
				
			}
			
			

		} catch (SQLException e) {

			System.out.println("error" + e);

		}
		
		this.close();
		
		System.out.println(personList);
		return personList;
	}

}
