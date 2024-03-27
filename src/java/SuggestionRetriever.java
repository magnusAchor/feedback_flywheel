import Entity.Suggestions;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SuggestionRetriever")
public class SuggestionRetriever extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("RegistrationLoginPU");
            em = emf.createEntityManager();

            List<Suggestions> suggestions = em.createQuery(
                    "SELECT s FROM Suggestions s ORDER BY s.createdAt DESC")
                    .getResultList();

            request.setAttribute("suggestions", suggestions);
            request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
// Handle exceptions appropriately, e.g., logging or displaying an error message
                        response.getWriter().println("An error occurred: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
