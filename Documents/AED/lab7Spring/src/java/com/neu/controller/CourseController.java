package com.neu.controller;

import Bean.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CourseController extends HttpServlet {

    ArrayList<Product> productList;

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    //Global object belongs to every users.
    //Be careful, do not put anything here that you don't want to share.
    //Later this will come from the database.
    @Override
    public void init() {

        //This method will only be call for once
        productList = new ArrayList<>();
        Product computer1 = new Product();
        computer1.setCategory("Computers");
        computer1.setName("Apple Macbook Pro with 13.3 Display");
        computer1.setPrice((float) 1299.99);

        Product computer2  = new Product();
        computer2.setCategory("Computers");
        computer2.setName("Asus Laptop with Centrino 2 Black");
        computer2.setPrice((float) 949.99);

        Product computer3 = new Product();
        computer3.setCategory("Computers");
        computer3.setName("HP Pavilion Laptop with Centrino 2 DV7");
        computer3.setPrice((float) 1199.99);

        Product computer4 = new Product();
        computer4.setCategory("Computers");
        computer4.setName("Toshiba Satellite Laptop with Centrino 2 Copper");
        computer4.setPrice((float) 899.99);

        Product computer5 = new Product();
        computer5.setCategory("Computers");
        computer5.setName("Sony VAIO Laptop with Core 2 Duo Cosmopolitan Pink");
        computer5.setPrice((float) 779.99);

        Product book1 = new Product();
        book1.setCategory("Books");
        book1.setName("Java Servlet Programming");
        book1.setPrice((float) 29.95);

        Product book2 = new Product();
        book2.setCategory("Books");
        book2.setName("Oracle Database Programming");
        book2.setPrice((float) 48.95);

        Product book3 = new Product();
        book3.setCategory("Books");
        book3.setName("Java Web Services");
        book3.setPrice((float) 27.95);

        Product book4 = new Product();
        book4.setCategory("Books");
        book4.setName("Object Oriented Programming");
        book4.setPrice((float) 29.95);

        Product book5 = new Product();
        book5.setCategory("Books");
        book5.setName("System Analyst and Design with UML");
        book5.setPrice((float) 14.95);

        Product music1 = new Product();
        music1.setCategory("Music");
        music1.setName("I am going to tell you a Secret by Madonna");
        music1.setPrice((float) 26.99);

        Product music2 = new Product();
        music2.setCategory("Music");
        music2.setName("Baby one more time by Britney Spears");
        music2.setPrice((float) 10.99);

        Product music3 = new Product();
        music3.setCategory("Music");
        music3.setName("Justified by Justin Timberlake");
        music3.setPrice((float) 9.99);

        Product music4 = new Product();
        music4.setCategory("Music");
        music4.setName("Loose by Nelly Furtado");
        music4.setPrice((float) 16.99);

        productList.add(computer1);
        productList.add(computer2);
        productList.add(computer3);
        productList.add(computer4);
        productList.add(computer5);
        productList.add(book1);
        productList.add(book2);
        productList.add(book3);
        productList.add(book4);
        productList.add(book5);
        productList.add(music1);
        productList.add(music2);
        productList.add(music3);
        productList.add(music4);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //We are using one servlet for multiple things.
        //action is the url parameter to differentiate between different requests
        String category = request.getParameter("category");
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        HashSet<Product> userProducts = (HashSet<Product>) session.getAttribute("myProducts");

        if (userProducts == null) {
            session.setAttribute("myProducts", new HashSet<Product>());
        }

        if(action.equals("Music")){
            request.setAttribute("searchResults", productList);
            RequestDispatcher rd = request.getRequestDispatcher("/music.jsp");
            rd.forward(request, response);
        }

        else if(action.equals("Books")){
             request.setAttribute("searchResults", productList);
             RequestDispatcher rd = request.getRequestDispatcher("/books.jsp");
                 rd.forward(request, response);
        }

        else if(action.equals("Computers")){
            request.setAttribute("searchResults", productList);
                RequestDispatcher rd = request.getRequestDispatcher("/computers.jsp");
                 rd.forward(request, response);
        }

        else if(action.equals("add")){
            String[] pID = request.getParameterValues("id");
                 for (String id : pID) {
                     for (Product p : productList) {
                         if (p.getId() == (Integer.parseInt(id))) {
                             userProducts.add(p);
                             break;
                         }
                     }
                 }

                 RequestDispatcher rd = request.getRequestDispatcher("/mycart.jsp");
                  rd.forward(request, response);

        }

        else if(action.equals("remove")){
                String pID1 = request.getParameter("id");
                 for (Product p : userProducts) {
                     if (p.getId() == (Integer.parseInt(pID1))) {
                         userProducts.remove(p);
                         break;
                     }
                 }
                 RequestDispatcher rd = request.getRequestDispatcher("/mycart.jsp");
                 rd.forward(request, response);
        }

        else if(action.equals("cart")){
            RequestDispatcher rd = request.getRequestDispatcher("/mycart.jsp");
                 rd.forward(request, response);
        }


        else if(action.equals("exit")){
            
                session.invalidate(); 
                response.sendRedirect("index.jsp");
        }

        else if(action.equals("checkout")){
             System.out.println("REACHED HERE");
                float total = 0;
                 for (Product p : userProducts) {
                     System.out.println("INSIDE FOR " + p.toString());
                     total += p.getPrice();
                 }
                 System.out.println("Size before clear: " + userProducts.size());
                 userProducts.clear();
                 System.out.println("Size after clear: " + userProducts.size());
                
                 session.setAttribute("myProducts", userProducts);
                 request.setAttribute("total", total);
                RequestDispatcher rd = request.getRequestDispatcher("/checkout.jsp");
                 rd.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
