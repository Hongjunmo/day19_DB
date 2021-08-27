package day19_DB;

import java.sql.*;
import java.util.ArrayList;

public class DBClass {
	/*
	 * 1.드라이브 로드(오라클 기능 사용) 2. 연결된 객체를 얻어온다 3. 연결된 객체를 이용해서 명령어(쿼리문)을 전송할 수 있는 전송 객체
	 * 얻어옴 4. 전송객체를 이용해서 데이터베이스에 전송후 결과 얻어옴. 5. 얻어온 결과는 int 또는 resultSet으로 받는다
	 */
	private String url;
	private String id;
	private String pwd;
	private Connection con;

	public DBClass() {
		// 자바에서 오라클 연결할수 있게 도와주는 라이브러리 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			id = "jun";
			pwd = "wnsah12";
			con = DriverManager.getConnection(url, id, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<StudentDTO> getUsers() {
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		String sql = "select * from newst";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setStNum(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveDate(String stNum, String name, int age) {
//		insert into newst values('111','Hong',23);
		String sql = "insert into newst values('" + stNum + "','" + name + "'," + age + ")";
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
//			ResultSet rs=ps.executeQuery();
			// 위 코드로 해도 되긴 함 근데 조회가 아닌 insert기 때문에 이렇게 할 필요 x

			// 저장 성공시 1을반환 실패시 catch이동이나 0을 반환
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveDate02(String stNum, String name, int age) {
		String sql = "insert into newst values(?,?,?)";
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
//			ResultSet rs=ps.executeQuery();
			// 위 코드로 해도 되긴 함 근데 조회가 아닌 insert기 때문에 이렇게 할 필요 x

			ps.setString(1, stNum);// 쿼리문을 ? 로 채울 시 값 넣어야함
			ps.setString(2, name);
			ps.setInt(3, age);

			// 저장 성공시 1을반환 실패시 catch이동이나 0을 반환
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public int delete(String stNum) {
		int result = 0;
//	 delete from newst where id = 'userNum';
		String sql = "delete from newst where id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, stNum);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public int modify(String stNum, String name, int age) {
		int result = 0;
		// update newst set name='홍길동',age=20 where id = 'test';
		String sql = "update newst set name=?, age=? where id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, stNum);
			result = ps.executeUpdate();
		} catch (Exception e) {
		}

		return result;

	}

}
