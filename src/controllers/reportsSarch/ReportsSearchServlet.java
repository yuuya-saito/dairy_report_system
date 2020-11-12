package controllers.reportsSarch;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsSearchServlet
 */
@WebServlet("/reports/search")
public class ReportsSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        try {
            if (request.getParameter("columnName").equals("title")) {
                int page;
                try{
                    page = Integer.parseInt(request.getParameter("page"));
                } catch(Exception e) {
                    page = 1;
                }
                List<Report> reports = em.createNamedQuery("getSearchResultTitle", Report.class)
                        .setParameter("keyword", "%" + request.getParameter("result") + "%")
                        .setFirstResult(15 * (page - 1))
                        .setMaxResults(15)
                        .getResultList();
                long reports_count = (long)em.createNamedQuery("getSearchResultTitleCount", Long.class)
                        .setParameter("keyword", "%" + request.getParameter("result") + "%")
                        .getSingleResult();
                request.setAttribute("reports_count", reports_count);
                request.setAttribute("reports", reports);
                request.setAttribute("page", page);

            } else if (request.getParameter("columnName").equals("content")) {
                int page;
                try{
                    page = Integer.parseInt(request.getParameter("page"));
                } catch(Exception e) {
                    page = 1;
                }
                List<Report> reports = em.createNamedQuery("getSearchResultContent", Report.class)
                        .setParameter("keyword", "%" + request.getParameter("result") + "%")
                        .setFirstResult(15 * (page - 1))
                        .setMaxResults(15)
                        .getResultList();
                long reports_count = (long)em.createNamedQuery("getSearchResultContentCount", Long.class)
                        .setParameter("keyword", "%" + request.getParameter("result") + "%")
                        .getSingleResult();
                request.setAttribute("reports_count", reports_count);
                request.setAttribute("reports", reports);
                request.setAttribute("page", page);

            } else if (request.getParameter("columnName").equals("employee_id")) {
                try {
                    int page;
                    try{
                        page = Integer.parseInt(request.getParameter("page"));
                    } catch(Exception e) {
                        page = 1;
                    }
                    List<Report> reports = em.createNamedQuery("getSearchResultEmployee_id", Report.class)
                            .setParameter("keyword", Integer.parseInt(request.getParameter("result")))
                            .setFirstResult(15 * (page - 1))
                            .setMaxResults(15)
                            .getResultList();
                    long reports_count = (long)em.createNamedQuery("getSearchResultEmployee_idCount", Long.class)
                            .setParameter("keyword", Integer.parseInt(request.getParameter("result")))
                            .getSingleResult();
                    request.setAttribute("reports_count", reports_count);
                    request.setAttribute("reports", reports);
                    request.setAttribute("page", page);
                } catch (NumberFormatException e) {
                    Integer err = 1;
                    request.setAttribute("err", err);
                    int page;
                    try{
                        page = Integer.parseInt(request.getParameter("page"));
                    } catch(Exception e2) {
                        page = 1;
                    }
                    List<Report> reports = em.createNamedQuery("getAllReports", Report.class)
                            .setFirstResult(15 * (page - 1))
                            .setMaxResults(15)
                            .getResultList();
                    long reports_count = (long)em.createNamedQuery("getReportsCount", Long.class)
                            .getSingleResult();
                    request.setAttribute("reports_count", reports_count);
                    request.setAttribute("page", page);
                    request.setAttribute("reports", reports);
                }

            } else if (request.getParameter("columnName").equals("name")) {
                try {
                    int page;
                    try{
                        page = Integer.parseInt(request.getParameter("page"));
                    } catch(Exception e) {
                        page = 1;
                    }
                    List<Report> reports = em.createNamedQuery("getEmployeeName", Report.class)
                            .setParameter("keyword", "%" + request.getParameter("result") + "%")
                            .setFirstResult(15 * (page - 1))
                            .setMaxResults(15)
                            .getResultList();
                    long reports_count = (long)em.createNamedQuery("getEmployeeNameCount", Long.class)
                            .setParameter("keyword", "%" + request.getParameter("result") + "%")
                            .getSingleResult();
                    request.setAttribute("reports_count", reports_count);
                    request.setAttribute("page", page);
                    request.setAttribute("reports", reports);
                } catch (NullPointerException e) {
                    int page;
                    try{
                        page = Integer.parseInt(request.getParameter("page"));
                    } catch(Exception e2) {
                        page = 1;
                    }
                    List<Report> reports = em.createNamedQuery("getAllReports", Report.class)
                            .setFirstResult(15 * (page - 1))
                            .setMaxResults(15)
                            .getResultList();
                    long reports_count = (long)em.createNamedQuery("getReportsCount", Long.class)
                            .getSingleResult();
                    request.setAttribute("reports_count", reports_count);
                    request.setAttribute("page", page);
                    request.setAttribute("reports", reports);
                }

            } else {
                int page;
                try{
                    page = Integer.parseInt(request.getParameter("page"));
                } catch(Exception e) {
                    page = 1;
                }
                List<Report> reports = em.createNamedQuery("getAllReports", Report.class)
                        .setFirstResult(15 * (page - 1))
                        .setMaxResults(15)
                        .getResultList();
                long reports_count = (long)em.createNamedQuery("getReportsCount", Long.class)
                        .getSingleResult();
                request.setAttribute("reports_count", reports_count);
                request.setAttribute("page", page);
                request.setAttribute("reports", reports);
            }
        } catch (NullPointerException e) {
            Integer err = 2;
            request.setAttribute("err", err);
            int page;
            try{
                page = Integer.parseInt(request.getParameter("page"));
            } catch(Exception e2) {
                page = 1;
            }
            List<Report> reports = em.createNamedQuery("getAllReports", Report.class)
                    .setFirstResult(15 * (page - 1))
                    .setMaxResults(15)
                    .getResultList();
            long reports_count = (long)em.createNamedQuery("getReportsCount", Long.class)
                    .getSingleResult();
            request.setAttribute("reports_count", reports_count);
            request.setAttribute("page", page);
            request.setAttribute("reports", reports);
        }
        em.close();

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/search.jsp");
        rd.forward(request, response);
    }

}
