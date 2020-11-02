package game;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GamePlay
 */
@WebServlet("/play")
public class GamePlay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		User player = (User) session.getAttribute("player");
		Enemy enemy ;
		MessageLog log = (MessageLog) session.getAttribute("log");
		ArrayList<String> result = null;

		if(session!=null) {
			if(player==null) {//初回設定
				player = new User();
				result = new ArrayList<>();
				log = new MessageLog();
			}
			enemy = new Enemy();
			player.setName(request.getParameter("playername"));
			log.setMessage(enemy.getName() + "が現れた！" + "<br />" + "どうする？");

			session.setAttribute("player", player);
			session.setAttribute("enemy", enemy);
			session.setAttribute("log", log);
			session.setAttribute("result", result);

			//response
			//jspに転送
			ServletContext context =getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Play.jsp");
			dispatcher.forward(request,response);
		}else {
			//TOPへリダイレクト
			log.setMessage("エラー");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		MessageLog log = (MessageLog)session.getAttribute("log");
		User player = (User)session.getAttribute("player");
		Enemy enemy = (Enemy)session.getAttribute("enemy");
		int choice = Integer.parseInt(request.getParameter("choice"));
		ArrayList<String> result = (ArrayList<String>)session.getAttribute("result") ;

		ServletContext context;
		RequestDispatcher dispatcher;

		switch (choice) {
		case 0 :
			log.setMessage(enemy.getName() + "に" + player.getAttack() +"ダメージ与えた！");
			enemy.setHp(enemy.getHp() - player.getAttack());
			if(enemy.getHp() <= 0) {
				log.setMessage("<br />" + enemy.getName() + "を倒した。");
				player.setExp(player.getExp() - enemy.getExp());
				result.add(enemy.getName());
			}
			break;
		case 1:
			log.setMessage(player.getName() +"は様子を見た。" +
					"<br />" + "何も起こらなかった。");
			break;
		case 2:
			session.setAttribute("player", player);
			session.setAttribute("enemy", enemy);
			session.setAttribute("log", log);

			//jspに転送
			context =getServletContext();
			dispatcher = context.getRequestDispatcher("/Item.jsp");
			dispatcher.forward(request,response);
			break;
		case 3:
			log.setMessage(player.getName() +"は逃げた。");
			session.setAttribute("player", player);
			session.setAttribute("enemy", enemy);
			session.setAttribute("log", log);

			//jspに転送
			context =getServletContext();
			dispatcher = context.getRequestDispatcher("/Los.jsp");
			dispatcher.forward(request,response);
			session.invalidate();
			break;
		default:
			log.setMessage("エラーが発生しました");
			break;
		}
		//エネミーターン
		if( choice !=2 && choice!=3 && enemy.getHp() > 0) {
			log.setMessage("\n"+enemy.getName() + "の攻撃!");
			player.setHp(player.getHp() - enemy.getAttack());

			if(player.getHp()<=0) {
				log.setMessage(player.getName() + "は負けてしまった・・・");
				session.setAttribute("player", player);
				session.setAttribute("enemy", enemy);
				session.setAttribute("log", log);

				//jspに転送
				context =getServletContext();
				dispatcher = context.getRequestDispatcher("/Los.jsp");
				dispatcher.forward(request,response);
			}else {
			session.setAttribute("player", player);
			session.setAttribute("enemy", enemy);
			session.setAttribute("log", log);

			//jspに転送
			context =getServletContext();
			dispatcher = context.getRequestDispatcher("/Play.jsp");
			dispatcher.forward(request,response);
			}
		}else if(enemy.getHp()<=0){
			doGet(request, response);
		}
	}
}
