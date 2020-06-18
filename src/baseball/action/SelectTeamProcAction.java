package baseball.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import baseball.model.Player;
import baseball.repository.BaseBallRepository;


public class SelectTeamProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String team = request.getParameter("team");
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String input = null;
		while((input = br.readLine()) != null) {
			sb.append(input);
		}
		String result = sb.toString();
		Gson gson = new Gson();
		Player p = gson.fromJson(result, Player.class);
		System.out.println("SelectTeamProcAction : p : " +p.getTeam() + p.getName() );
		BaseBallRepository baseballRepository = BaseBallRepository.getInstance();
		List<Player> playerlist= baseballRepository.selectteam(p.getTeam());
		System.out.println("SelectTeamProcAction : playerlist : " + playerlist);
		// request.setAttribute("playerlist", playerlist);
		//System.out.println("FirstAction : kindslist.size() : " + kindslist.size());
		
		// RequestDispatcher dis = request.getRequestDispatcher("/home.jsp");
		// dis.forward(request, response);
		String playerlistJson = gson.toJson(playerlist);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(playerlistJson);
	}
	
}