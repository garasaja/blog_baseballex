package baseball.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import baseball.db.DBConn;
import baseball.model.Player;



// DAO
public class BaseBallRepository {
	
	private static final String TAG = "BaseBallRepository : ";
	private static BaseBallRepository instance = new BaseBallRepository();
	private BaseBallRepository() {}
	public static BaseBallRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	
	public List<Player> firstPage() {
		final String SQL = "SELECT distinct team FROM baseball";
		Player player = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기			
			// if 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			List<Player> playerlist = new ArrayList<Player>();
								
			while(rs.next()) {
				player = new Player();
				player.setTeam(rs.getString("team"));
				
				playerlist.add(player);
			}
			System.out.println("BaseBallRepository : playerlist : " + playerlist);
			return playerlist;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"firstPage() : "+e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<Player> selectteam(String team) {
		final String SQL = "SELECT team,name FROM baseball WHERE team = ?";
		Player player = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기			
			pstmt.setString(1, team);
			// if 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
		List<Player> playerlist = new ArrayList<>();
			while(rs.next()) {
				player = new Player();
				
				player.setTeam(rs.getString("team"));
				player.setName(rs.getString("name"));
				
				playerlist.add(player);
			}
			return playerlist;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"priceDesc() : "+e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<Player> selectplayer(String name) {
		final String SQL = "SELECT num,name,position FROM baseball WHERE name = ?";
		Player player = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기		
			pstmt.setString(1, name);
			// if 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
		List<Player> playerlist = new ArrayList<>();
			while(rs.next()) {
				player = new Player();				
				player.setName(rs.getString("name"));
				player.setNum(rs.getInt("num"));
				player.setPosition(rs.getString("position"));
				
				
				playerlist.add(player);
			}
			return playerlist;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"orderpriceDesc() : "+e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
	
}
