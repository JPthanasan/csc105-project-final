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

@WebServlet(name = "SigninServlet", value = "/SigninServlet")
public class SigninServlet extends HttpServlet {
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
            System.out.println(username);
            System.out.println(password);

            if(username == null || password == null) {
                ErrorResponse errorResponse = new ErrorResponse("Username and password are required", 400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
                return;
            }

            UserOperation userQueryModel = new UserOperation();
            User user = userQueryModel.signInUser(username, password);
            if (user == null) {
                ErrorResponse errorResponse = new ErrorResponse("Username and/or password are not correct", 400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
                return;
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("user_id", user.getId());
            response.setStatus(200);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Middleware.setCORS(req, resp);
        super.doOptions(req, resp);
    }

}

