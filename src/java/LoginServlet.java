import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import Entity.Suggestions;
import Entity.Users;
import Entity.Websites;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // User authentication logic
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Users user = authenticateUser(username, password);

        if (user != null) {
             // Login successful, store username in session
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
            // Login successful, retrieve data
            List<Suggestions> suggestions = retrieveTop10Suggestions();
            List<String> urls = retrieveWebsiteUrls();

            // Set attributes for JSP
            request.setAttribute("urls", urls);
            request.setAttribute("suggestions", suggestions);

            // Forward to dashboard
            forwardToDashboard(request, response);
        } else {
            // Login failed, forward to login page with error message
            forwardToLoginWithErrorMessage(request, response);
        }
    }

    private Users authenticateUser(String username, String password) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RegistrationLoginPU");
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            return (Users) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }

    private List<Suggestions> retrieveTop10Suggestions() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RegistrationLoginPU");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Suggestions> suggestionQuery = em.createQuery(
                    "SELECT s FROM Suggestions s ORDER BY s.createdAt DESC", Suggestions.class);
            suggestionQuery.setMaxResults(20);
            return suggestionQuery.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    private List<String> retrieveWebsiteUrls() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RegistrationLoginPU");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<String> urlQuery = em.createQuery("SELECT w.url FROM Websites w", String.class);
            return urlQuery.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    private void forwardToDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Dashboard.jsp");
        rd.forward(request, response);
    }

    private void forwardToLoginWithErrorMessage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("loginResult", "failure");
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }
}
