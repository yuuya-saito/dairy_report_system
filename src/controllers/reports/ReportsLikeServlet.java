package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Like;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsLikeServlet
 */
@WebServlet("/reports/like")
public class ReportsLikeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            EntityManager em = DBUtil.createEntityManager();

            Like l = new Like();

            Object rid = "1";

            l.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            l.setReport((Report)rid);

            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
            em.close();

            request.setAttribute("like", l);

            response.sendRedirect(request.getContextPath() + "/index");
    }

}
