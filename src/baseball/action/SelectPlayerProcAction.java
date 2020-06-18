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


public class SelectPlayerProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String input = null;
		while((input = br.readLine()) != null) {
			sb.append(input);
		}
		String result = sb.toString();
		Gson gson = new Gson();
		Player p = gson.fromJson(result, Player.class);
		System.out.println("SelectplayerProcAction : p : " + p.getName() );
		BaseBallRepository baseballRepository = BaseBallRepository.getInstance();
		List<Player> playerlist= baseballRepository.selectplayer(p.getName());
		System.out.println("SelectplayerProcAction : playerlist : " + playerlist);
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