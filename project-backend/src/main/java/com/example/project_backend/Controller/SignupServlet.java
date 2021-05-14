package com.example.project_backend.Controller;

import com.example.project_backend.Model.ErrorResponse;
import com.example.project_backend.Model.User;
import com.example.project_backend.Model.UserOperation;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignupServlet", value = "/SignupServlet")
@MultipartConfig
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            if (username == null || password == null || email == null) {
                ErrorResponse errorResponse = new ErrorResponse("Username, Password and email are required", 400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
                return;
            }

            UserOperation userOperation = new UserOperation();
            User existingUser = userOperation.getUser(username);
            if (existingUser != null) {
                ErrorResponse errorResponse = new ErrorResponse("Username " + username + "is used", 400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
                return;
            }

            User user = userOperation.insertNewUser(username, password, email);
            String jsonOfUser = gson.toJson(user);
            response.setStatus(201);
            request.getSession(true);
            out.print(jsonOfUser);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }

    protected void doOption(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Middleware.setCORS(req, resp);
        super.doOptions(req, resp);
    }
}
