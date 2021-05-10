package kr.or.connect.todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.connectdb.dao.TodoDao;
import kr.or.connect.connectdb.dto.TodoDto;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/main/*")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TodoDao dao = new TodoDao();
		List<TodoDto> list = dao.getTodos();
		
		//forward
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(list);
		
		request.setAttribute("jsonStr", jsonStr);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/main.jsp");
		rd.forward(request, response);
			
		}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String getPath = request.getPathInfo();
		String[] path = getPath.split("/");
		
		TodoDao dao = new TodoDao();
		TodoDto dto = dao.getEmptyTodo();
		
		
		if(path[1].equals("TODO")) {
			dto.setId(Long.parseLong(path[2]));
			dto.setType("DOING");
			dao.updateTodo(dto);
			this.doGet(request, response);
			
		}else if(path[1].equals("DOING")){
			dto.setId(Long.parseLong(path[2]));
			dto.setType("DONE");
			dao.updateTodo(dto);
			this.doGet(request, response);
			
		}else {
			String title = request.getParameter("title");
			String name = request.getParameter("name");
			int sequence = Integer.parseInt(request.getParameter("sequence"));
			
			dto.setTitle(title);
			dto.setName(name);
			dto.setSequence(sequence);
			
			dao.addTodo(dto);
			response.sendRedirect("/Todo/main");
		}
		

		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
