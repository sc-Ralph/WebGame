package game;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class useItem
 */
@WebServlet("/item")
public class useItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		MessageLog log = (MessageLog)session.getAttribute("log");
		User player = (User)session.getAttribute("player");
		Enemy enemy = (Enemy)session.getAttribute("enemy");
		int item = Integer.parseInt(request.getParameter("item"));

		switch(item) {
		case 0:
			log.setMessage(GameBean.itemName[item] + "を使った");
			player.setHp(player.getHp() + 30);
			break;
		default:
			break;
		}
		//エネミーターン
		log.setMessage("\n"+enemy.getName() + "の攻撃!");
		player.setHp(player.getHp() - enemy.getAttack());



		session.setAttribute("player", player);
		session.setAttribute("enemy", enemy);
		session.setAttribute("message", log);

		//jspに転送
		ServletContext context =getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Play.jsp");
		dispatcher.forward(request,response);
	}

}
