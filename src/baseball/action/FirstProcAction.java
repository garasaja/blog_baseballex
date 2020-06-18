package baseball.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import baseball.model.Player;
import baseball.repository.BaseBallRepository;


public class FirstProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BaseBallRepository baseballRepository = BaseBallRepository.getInstance();
		List<Player> playerlist= baseballRepository.firstPage();
		request.setAttribute("playerlist", playerlist);
		//System.out.println("FirstAction : kindslist.size() : " + kindslist.size());
		
		RequestDispatcher dis = request.getRequestDispatcher("/home.jsp");
		dis.forward(request, response);
	}
	
}