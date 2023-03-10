package com.laioffer.onlineorder;

import org.json.JSONObject;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.apache.commons.io.IOUtils;

import com.laioffer.onlineorder.entity.Customer;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(name = "helloServlet", value = "/hello-servlet")

//RESTFUL APT
//1. resource path(hello-servlet) --> match 后端servlet
//2. http method --> backend doGet/doSet/doRead/doDelete
//3. request 之间相对独立， 不需要等待其它请求

public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!!!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setContentType("text/html");
        response.setContentType("application/json");

        //
        ObjectMapper mapper = new ObjectMapper();
        Customer customer= new Customer();
        customer.setEmail("sun@laioffer.com");
        customer.setPassword("123456");
        customer.setFirstName("rick");
        customer.setLastName("sun");
        customer.setEnabled(true);

        response.getWriter().print(mapper.writeValueAsString(customer));




/*
//request body
        JSONObject obj = new JSONObject();
        obj.put("name", "Sun");
        obj.put("age", 50);
        obj.put("gender", "male");
        obj.put("address", "CA");

        PrintWriter out = response.getWriter();
        out.print(obj);*/

        /*
        String customer = request.getParameter( "customer");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + customer + "</h1>");
        out.println("</body></html>");
        */
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Read customer information from request body
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String email = jsonRequest.getString("email");
        String firstName = jsonRequest.getString("first_name");
        String lastName = jsonRequest.getString("last_name");
        int age = jsonRequest.getInt("age");
        // Print customer information to IDE console
        System.out.println("Email1 is: " + email);
        System.out.println("First1 name is: " + firstName);
        System.out.println("Last1 name is: " + lastName);
        System.out.println("Age1 is: " + age);
        // Return status = ok as response body to the client
        response.setContentType("application/json");

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status!!", "ok");
        response.getWriter().print(jsonResponse);
    }

    public void destroy() {
    }
}