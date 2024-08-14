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

	// ********************정보 등록 ****************

	public int insertPerson(PersonVo personVo) {

		System.out.println("Dao 저장");
		System.out.println(personVo);

		int count = -1;

		// *********************db연결 꼭 해주기

		this.getConnection();

		// 3. SQL문 준비 / 바인딩 실행
		try {

			String query = "";

			query += " insert into person ";
			query += " values(null, ?, ?, ?) ";

			// 바인딩

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			// 실행

			count = pstmt.executeUpdate();

			// 4. 결과처리

		} catch (SQLException e) {

			System.out.println("error" + e);

		}

		this.close();

//		System.out.println(count);
		return count;

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

				PersonVo personVo = new PersonVo(id, name, hp, company);
				System.out.println(personVo);

				personList.add(personVo);

			}

		} catch (SQLException e) {

			System.out.println("error" + e);

		}

		this.close();

		return personList;
	}

	// ******************** 리스트 수정 폼 >> 사람 1명 가져오기 *****************
	public PersonVo getPersonOne(int no) {

		// *********************db연결 꼭 해주기
		this.getConnection();
		int count = -1;
		PersonVo personVo = null;

		// 3. SQL문 준비 / 바인딩 실행
		try {

			String query = "";

			query += " select  person_id ";
			query += " 		 ,name ";
			query += " 		 ,hp ";
			query += " 		 ,company ";
			query += " from person ";
			query += " where person_id = ? ";

			// 바인딩

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			// 실행

			rs = pstmt.executeQuery();

			// 4. 결과처리

			rs.next();
			int personId = rs.getInt("person_id");
			String name = rs.getString("name");
			String hp = rs.getString("hp");
			String company = rs.getString("company");

			personVo = new PersonVo(personId, name, hp, company);

			System.out.println(personVo);

		} catch (

		SQLException e) {

			System.out.println("error" + e);

		}

		this.close();
		return personVo;

	}

	// 사람 정보 수정하기 (1명)
	public int updatePerson(PersonVo personVo) {

		System.out.println("=========================");
		System.out.println("수정");

		this.getConnection();
		int count = 0;

		// 3. SQL문 준비 / 바인딩 실행
		try {

			String query = "";

			query += " update person ";
			query += " set name = ? ";
			query += " 	  ,hp = ? ";
			query += " 	  ,company = ? ";
			query += " where person_id = ? ";

			// 바인딩

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			// 실행

			count = pstmt.executeUpdate();

			// 4. 결과처리

			System.out.println("dao결과" + count);

		} catch (SQLException e) {

			System.out.println("error" + e);

		}

		this.close();
		return count;
	}

	public int deletePerson(int no) {

		System.out.println(">>>>>>>>>>>>>>");
		System.out.println("삭제 Dao 임다");

		this.getConnection();
		int count = 0;

		try {

			String query = "";

			query += " delete from person ";
			query += " where person_id = ? ";

			// 바인딩

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,no);
			// 실행

			count = pstmt.executeUpdate();

			// 4. 결과처리

			System.out.println("dao결과" + count);

		} catch (SQLException e) {

			System.out.println("error" + e);

		}

		this.close();
		return count;

	}

}
