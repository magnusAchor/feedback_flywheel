// Import statements: These import necessary classes and packages required for the servlet to function
import Entity.Users;
import java.io.IOException; // For handling IO operations
import javax.persistence.EntityManager; // For managing entity objects
import javax.persistence.EntityManagerFactory; // For creating entity managers
import javax.persistence.Persistence; // For accessing persistence unit
import javax.servlet.ServletException; // For handling servlet exceptions
import javax.servlet.http.HttpServlet; // Base class for HTTP servlets
import javax.servlet.http.HttpServletRequest; // For handling HTTP requests
import javax.servlet.http.HttpServletResponse; // For handling HTTP responses

// Class definition for RegisterServlet which extends HttpServlet
public class RegisterServlet extends HttpServlet {

    // Method to process HTTP requests
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set content type of the response
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve parameters from the request
        String username = request.getParameter("username"); // Get username parameter from request
        String password = request.getParameter("password"); // Get password parameter from request
        String email = request.getParameter("email"); // Get email parameter from request

        // Create EntityManagerFactory for the persistence unit "RegistrationLoginPU"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RegistrationLoginPU");
        
        // Create EntityManager from EntityManagerFactory
        EntityManager em = emf.createEntityManager();

        // Create a new instance of Users entity class
        Users user = new Users(); // Create a new user object

        // Set user properties with retrieved parameters
        user.setUsername(username); // Set username for the user
        user.setPassword(password); // Set password for the user
        user.setEmail(email); // Set email for the user

        try {
            // Begin a transaction
            em.getTransaction().begin();
            // Persist the user entity into the database
            em.persist(user);
            // Commit the transaction
            em.getTransaction().commit();
            // Write a success message to the response
            response.getWriter().println("Registration successful!");
        } catch (IOException e) { // Catch any IOException
            // Write an error message to the response
            response.getWriter().println("Registration failed: " + e.getMessage());
        } finally { // Finally block to ensure EntityManager is closed
            // Close the EntityManager
            em.close();
            // Close the EntityManagerFactory
            emf.close();
        }
    }

    // Method to handle HTTP GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Call processRequest method to handle the request
        processRequest(request, response);
    }

    // Method to handle HTTP POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Call processRequest method to handle the request
        processRequest(request, response);
    }
}
