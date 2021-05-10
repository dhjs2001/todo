package kr.or.connect.connectdb.dao;

import java.util.ArrayList;
import java.util.List;
import kr.or.connect.connectdb.dto.TodoDto;
import java.sql.*;

public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbUser = "connectuser1";
	private static String dbpasswd = "connect123!@#";
	
	public TodoDto getEmptyTodo() {
		
		long id = 0;
		String name = null;
		String regDate = null;
		int sequence = 0;
		String title = null;
		String type = null;
		
		TodoDto dto = new TodoDto(id,name,regDate,sequence,title,type);
		return dto;
	}
	public List<TodoDto> getTodos(){
//	public TodoDto getTodos() {
		
		List<TodoDto> list = new ArrayList<TodoDto>();
		
		
		
		long id = 0;
		String name = null;
		String regDate = null;
		int sequence = 0;
		String title = null;
		String type = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "select id, name, regDate, sequence, title, type from todo";
		try(
				Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				Statement stmt = conn.createStatement()) {
			try(ResultSet rs = stmt.executeQuery(sql)){
				while(rs.next()) {
					id = rs.getInt("id");
					name = rs.getString("name");
					regDate = rs.getString("regDate");
					sequence = rs.getInt("sequence");
					title = rs.getString("title");
					type = rs.getString("type");
					
					TodoDto dto = new TodoDto(id,name,regDate,sequence,title,type);
				
					list.add(dto);
					
					
//					System.out.println(dto);
					
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			if(list.isEmpty()) {
				TodoDto dto = new TodoDto(id, name, regDate, sequence, title, type);
				list.add(dto);
				return list;
				
			}else {
				return list;	
			}
			
//			
	}
	
	
	
	
	
	public int addTodo(TodoDto todoDto){
		int addCount = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		String sql = "insert into todo(title, name, sequence) values(?, ?, ?)";
								
		try(Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql);){
			
			ps.setString(1, todoDto.getTitle());
			ps.setString(2, todoDto.getName());
			ps.setInt(3, todoDto.getSequence());
			
			addCount = ps.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return addCount;
	}
	
	
	
	
	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		String sql = "update todo set type = ? where id = ? ";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)){
			
				ps.setString(1, todoDto.getType());
				ps.setInt(2, (int)todoDto.getId());
				
				updateCount = ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return updateCount;
	}
	
	
}
