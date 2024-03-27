import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import Entity.Websites;

@WebServlet("/RetrieveServlet")
public class RetrieveServlet extends HttpServlet {

    @Override

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    EntityManagerFactory emf = null;
    EntityManager em = null;

    try {
        emf = Persistence.createEntityManagerFactory("RegistrationLoginPU");
        em = emf.createEntityManager();

        TypedQuery<String> query = em.createQuery("SELECT w.url FROM Websites w", String.class);
        List<String> urls = query.getResultList();

        if (urls != null && !urls.isEmpty()) {
            request.setAttribute("urls", urls);
            request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
        } else {
            response.getWriter().println("No URLs found.");
        }
    } catch (IOException | ServletException e) {
        response.getWriter().println("An error occurred: " + e.getMessage());
        e.printStackTrace(); // Add stack trace logging
    } finally {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}}
