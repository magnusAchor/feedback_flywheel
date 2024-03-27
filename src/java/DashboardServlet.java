import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Entity.Suggestions;
import Entity.Websites;

@WebServlet(name = "DashboardServlet", urlPatterns = { "/Dashboard" })
@MultipartConfig
public class DashboardServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String websiteUrl = request.getParameter("website");
    String websiteInput = request.getParameter("websiteUrl");
    String suggestionTitle = request.getParameter("title");
    String suggestionDescription = request.getParameter("description");

    // Get the uploaded file
    Part filePart = request.getPart("attachment");
    byte[] fileData = null;
    if (filePart != null && filePart.getSize() > 0) {
      InputStream fileContent = filePart.getInputStream();
      fileData = fileContent.readAllBytes();
    }

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RegistrationLoginPU");
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Websites website;
      if (websiteInput == null || websiteInput.isEmpty()) {
        website = getOrCreateWebsite(em, websiteUrl);
      } else {
        website = getOrCreateWebsite(em, websiteInput);
      }

      Suggestions suggestion = new Suggestions();
      suggestion.setWebsite(website);
      suggestion.setTitle(suggestionTitle);
      suggestion.setDescription(suggestionDescription);
      suggestion.setAttachment(fileData);
      suggestion.setCreatedAt(new Date());

      // Handle potential duplicate website entry exception
      try {
        em.persist(suggestion);
      } catch (Exception e) {
        if (e.getMessage().contains("Duplicate entry")) {
          // User-friendly message for duplicate website
          response.setContentType("text/plain");
          response.getWriter().print("Suggestion created, but website URL already exists!");
          em.getTransaction().rollback(); // Rollback to avoid partial data insertion
          return; // Exit after handling exception
        } else {
          // Re-throw other exceptions for further handling
          throw e;
        }
      }

      em.getTransaction().commit();

      // Success response if no exception occurs
      response.setContentType("text/plain");
      response.getWriter().print("Suggestion created successfully!");
    } catch (IOException e) {
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
      // Send an error response for other exceptions
      response.setContentType("text/plain");
      response.getWriter().print("Error: " + e.getMessage());
    } finally {
      em.close();
      emf.close();
    }
  }

  private Websites getOrCreateWebsite(EntityManager em, String url) {
    List<Websites> websiteList = em.createQuery("SELECT w FROM Websites w WHERE w.url = :url", Websites.class)
      .setParameter("url", url)
      .getResultList();

    Websites website;
    if (!websiteList.isEmpty()) {
      website = websiteList.get(0);
    } else {
      website = new Websites();
      website.setUrl(url);
      website.setName("Default Name");
      em.persist(website);
    }
    return website;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
