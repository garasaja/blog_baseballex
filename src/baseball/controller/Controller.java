package baseball.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import baseball.action.Action;
import baseball.action.FirstProcAction;
import baseball.action.SelectPlayerProcAction;
import baseball.action.SelectTeamProcAction;


// http://localhost:8000/blog/user
@WebServlet("/base")
public class Controller extends HttpServlet {
	private final static String TAG = "Controller : ";
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("path", request.getContextPath());
		// http://localhost:8000/blog/user?cmd=join
		String cmd = request.getParameter("cmd");
		System.out.println(TAG+"router : "+cmd);
		Action action = router(cmd);
		action.execute(request, response);
	}
	
	public Action router(String cmd) {
		if(cmd.equals("firstProc")) {
			return new FirstProcAction();			
		}else if(cmd.equals("selectplayerProc")) {
			return new SelectPlayerProcAction();			
		}else if(cmd.equals("selectteamProc")) {
			return new SelectTeamProcAction();			
		}
		
		
		return null;
	}
	

}