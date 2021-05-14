package com.example.project_backend.Controller;

import com.example.project_backend.Model.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Middleware {
    public static int checkAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ErrorResponse {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ErrorResponse("Unauthorized", 401);
        }
        String userId = session.getAttribute("user_id").toString();
        return Integer.parseInt(userId);
    }

    public static void setCORS(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length");
    }
}
