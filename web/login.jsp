<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
    <style>
        /* General Styles */
        body {
            font-family: 'Montserrat', sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            background-size: cover;
            background-attachment: fixed;
            color: #fff;
            margin: 0;
            padding: 0;
        }

        /* Container Styles */
        .container {
            max-width: 400px;
            margin: 100px auto;
            padding: 40px;
            background-color: rgba(255, 255, 255, 0.1);
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            backdrop-filter: blur(10px);
        }

        /* Heading Styles */
        h1 {
            text-align: center;
            margin-bottom: 30px;
            font-weight: 700;
            letter-spacing: 2px;
            text-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }

        /* Input Field Styles */
        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
            border: none;
            border-radius: 4px;
            background-color: rgba(255, 255, 255, 0.3);
            color: #fff;
            font-size: 16px;
        }

        /* Submit Button Styles */
        input[type="submit"] {
            width: 100%;
            background-color: #764ba2;
            color: #fff;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #5c3b7d;
        }

        /* Other Link Styles */
        #otherLink {
            text-align: center;
            margin-top: 20px;
        }

        #otherLink a {
            color: #fff;
            text-decoration: none;
            font-size: 14px;
            transition: color 0.3s ease;
        }

        #otherLink a:hover {
            color: #e2e2e2;
        }
    </style>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Login</h1>
        <form action="LoginServlet" method="POST">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Login">

            <div id="otherLink">
                <a href="register">Don't have an account? Create one</a>
            </div>
        </form>
    </div>
</body>
</html>