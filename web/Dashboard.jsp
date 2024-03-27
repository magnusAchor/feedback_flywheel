<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Flywheel</title>
    <link rel="stylesheet" href="css/styles.css">
    <%@include file="animate.jsp" %>
   
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
     
   $(document).ready(function() {
        $('#searchForm').submit(function(event) {
            event.preventDefault(); // Prevent the form from submitting normally

            // Create a new FormData object
            var formData = new FormData(this);

            // Send the AJAX request
            $.ajax({
                type: 'POST',
                url: 'DashboardServlet',
                data: formData,
                processData: false, // Tell jQuery not to process the data
                contentType: false, // Tell jQuery not to set the content type
                success: function(response) {
                    // Handle the successful response
                    console.log(response);
                    displaySuccessMessage(response);
                },
                error: function(xhr, status, error) {
                    // Handle the error
                    console.error(error);
                    displayErrorMessage(xhr.responseText);
                }
            });
        });
    });

function displaySuccessMessage(message) {
    // Create a new element to display the success message
    var successMessageElement = $('<p style="color: green;">' + message + '</p>');

    // Append the success message element to a container on the page
    $('#successMessageContainer').html(successMessageElement);
}

function displayErrorMessage(message) {
    // Create a new element to display the error message
    var errorMessageElement = $('<p style="color: red;">' + 'error' + '</p>');

    // Append the error message element to a container on the page
    $('#errorMessageContainer').html(errorMessageElement);
}
</script>
</head>
<body>
    <header style="height: 140px;" class="header">
        
        <h1>Feedback Flywheel</h1>
        <p >Share your ideas and improve the web.</p>
      <div id="logout" style="display: flex; justify-content: center; gap: 20px;"> 
          <p style="background-color: #b2a2a2; color: white; padding: 10px; margin-top: 0px; border-radius: 5px;" id="user">Welcome, <c:out value="${username}" /></p>
        <!-- Logout button -->
    <form id="logout" action="LogoutServlet" method="get">
        <button style="background-color: #b2a2a2;" type="submit" value="Logout">Logout</button>
    </form>
        </div>
    </header>

    <main class="main">
        <section class="search-section">
           
            <h2 id="sbtsug">Submit a Suggestion</h2>
            <!-- Your suggestion form goes here -->
 
            <h2>Find a Website or App</h2>
         <form action="DashboardServlet" method="post" id="searchForm" enctype="multipart/form-data">
   
        <select name="websiteUrl" id="websiteUrl">
            <option value="">Select a Website</option>
            <c:forEach items="${urls}" var="url">
                <option value="${url}">${url}</option>
            </c:forEach>
        </select>

        <a href="#website"> Can't find your website for suggestion?Add website</a>

        <br>

        <label for="website">Or enter website URL/name:</label>
        <input type="text" name="website" id="website" placeholder="Website URL or name">

        <br>

        <label for="title">Suggestion Title:</label>
        <input type="text" name="title" id="title" placeholder="Clear and concise title" required>

        <label for="description">Description:</label>
        <textarea name="description" id="description" placeholder="Explain your suggestion in detail" required></textarea>

        <label for="attachment">Attachment (optional):</label>
        <input type="file" name="attachment" id="attachment">

        <button type="submit" id="submitButton">Submit</button>
    </form>

<div id="successMessageContainer"></div>
<!--<div id="errorMessageContainer"></div> -->
<div id="errorMessageContainer"></div>
            <div class="search-results"></div>
            <c:if test="${createSuggestionResult == 'success'}">
        <p>Suggestion created successfully!</p>
    </c:if>

    <c:if test="${createSuggestionResult == 'failure'}">
        <p>Failed to create suggestion.</p>
    </c:if>
        </section>
        
        <section class="browse-section">
    <h2 id="dissug">Browse Suggestions</h2>
    <div id="scroll" class="scroll-container">
        <ul class="suggestions-list">
            <c:forEach var="suggestion" items="${suggestions}">
                <li>
                    <b>Website:</b> <a href="<c:out value='${suggestion.websiteId.url}'/>" target="_blank"><c:out value='${suggestion.websiteId.url}'/></a><br><br>
                    <b>Title:</b> <c:out value="${suggestion.title}"/><br><br>
                    <c:if test="${suggestion.description != null}">
                        Description: <c:out value="${suggestion.description}"/><br><br>
                    </c:if>
                    <c:if test="${not empty suggestion.attachment}">
    Attachment: <a href="DownloadAttachmentServlet?suggestionId=${suggestion.id}" target="_blank">Download</a><br>
</c:if>
                </li>
            </c:forEach>
        </ul>
    </div>
</section>


        
    </main>

    <script src="js/scripts.js"></script>
    <script>
        document.getElementById('searchForm').addEventListener('submit', function(event) {
            var websiteUrl = document.getElementById('websiteUrl').value;
            var websiteInput = document.getElementById('website').value;
            if (websiteUrl === '' && websiteInput === '') {
                alert('Please select a website or enter a website URL/name.');
                event.preventDefault(); // Prevent form submission
            }
        });
        
        document.addEventListener('DOMContentLoaded', function () {
        const websiteUrlSelect = document.getElementById('websiteUrl');
        const websiteInput = document.getElementById('website');
        const form = document.getElementById('searchForm');

        form.addEventListener('submit', function (event) {
            if (websiteUrlSelect.value !== '' && websiteInput.value !== '') {
                event.preventDefault(); // Prevent form submission
                alert("You cannot select and create a name at the same time.");
            }
        });
    });
   
   document.getElementById('searchForm').addEventListener('submit', function(event) {
        // Prevent the form from submitting immediately
        event.preventDefault();

        // Serialize form data
        const formData = new FormData(this);

        // Submit form data using fetch API
        fetch(this.getAttribute('action'), {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (response.ok) {
                // Reset form fields on successful submission
                this.reset();
            } else {
                // Handle errors if necessary
                console.error('Form submission failed:', response.status);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
</script>
</body>
<footer>
    <p id="copywright" style="margin-top: -2px; justify-content: center;" >&copy;Feedback Flywheel</P>
</footer>
</html>
