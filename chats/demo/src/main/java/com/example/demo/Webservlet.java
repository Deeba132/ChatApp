package com.example.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Webservlet", urlPatterns = "/mail")
@Component
public class Webservlet extends HttpServlet{
    @Autowired
    private JavaMailSender mailSender;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
           
        String to=req.getParameter("to");
        String subject=req.getParameter("subject");
        String body=req.getParameter("body");

        resp.setContentType("text/plain");

        if (to == null || subject == null || body == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing parameters: to, subject, body");
            return;
        }
        try{
            SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
            mailSender.send(simpleMailMessage);
            resp.getWriter().println("Email sent successfully to: "+to);
        }

        catch(Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Error caught "+e.getMessage());
        }
    }

}
