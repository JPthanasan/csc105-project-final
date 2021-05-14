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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ItemServlet", value = "/ItemServlet")
@MultipartConfig
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);
        try {
            Map<String, Object> map = new HashMap<>();
            int user_id = Middleware.checkAuthentication(request, response);
            int item_id = Integer.parseInt(request.getParameter("item_id"));
            ItemOperation itemOperation = new ItemOperation();
            Item item = itemOperation.viewItem(item_id);
            response.setStatus(200);
            map.put("item", item);
            map.put("Owner", (user_id == item.getUser_id()));
        } catch (ErrorResponse | SQLException errorResponse) {
            errorResponse.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);
        try {
            String item_name = request.getParameter("item_name");
            int item_amount = Integer.parseInt(request.getParameter("item_amount"));
            String category = request.getParameter("category");

            ItemOperation itemOperation = new ItemOperation();
            itemOperation.insertNewItem(item_name, item_amount, category);
            response.setStatus(201);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);
        try {
            int item_id = Integer.parseInt(request.getParameter("item_id"));
            String item_name = request.getParameter("item_name");
            int item_amount = Integer.parseInt(request.getParameter("item_amount"));
            String category = request.getParameter("category");

            ItemOperation itemOperation = new ItemOperation();
            itemOperation.editItem(item_id, item_name, item_amount, category);
            response.setStatus(204);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);
        try {
            int item_id = Integer.parseInt(request.getParameter("item_id"));
            ItemOperation itemOperation = new ItemOperation();
            Item item = itemOperation.deleteItem(item_id);
            response.setStatus(200);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doOption(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Middleware.setCORS(req, resp);
        super.doOptions(req, resp);
    }
}
