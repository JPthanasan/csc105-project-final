package com.example.project_backend.Controller;

import com.example.project_backend.Model.ErrorResponse;
import com.example.project_backend.Model.Item;
import com.example.project_backend.Model.ItemOperation;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShowAllItemServlet", value = "/ShowAllItemServlet")
public class ShowAllItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);
        try {
            ItemOperation itemOperation = new ItemOperation();
            List<Item> list = itemOperation.viewAllItem();
            response.setStatus(201);
            out.print(gson.toJson(list));
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
