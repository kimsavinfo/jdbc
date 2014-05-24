package fr.epsi.individu;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/individu")
public class IndividuServlet extends HttpServlet {

	private static final long serialVersionUID = -5651824170686700849L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IndividuRepository individuRepository = new IndividuRepository();
		req.setAttribute("individus", individuRepository.getAll());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/individu.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		switch (Objects.toString(req.getParameter("action"), "")) {
		case "delete":
			doDelete(req, resp);
			break;

		default:
		case "create":
			doPut(req, resp);
			break;
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			IndividuRepository individuRepository = new IndividuRepository();
			long id = Long.valueOf(req.getParameter("individuId"));
			individuRepository.delete(id);
			resp.sendRedirect("individu");
		}
		catch (NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètre individuId doit être un nombre !");
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Individu individu = new Individu();
		individu.setNom(req.getParameter("nom"));
		individu.setPrenom(req.getParameter("prenom"));
		individu.setAge(convertParameterToInteger(req, "age"));
		
		try {
			IndividuRepository individuRepository = new IndividuRepository();
			individuRepository.create(individu);
			resp.sendRedirect("individu");
		} catch (ValidationException e) {
			req.setAttribute("errors", e.getErrorMessages());
			doGet(req, resp);
		}
	}

	private Integer convertParameterToInteger(HttpServletRequest req, String parameterName) {
		try {
			return Integer.valueOf(req.getParameter(parameterName));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}
