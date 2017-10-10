/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.its.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vn.its.beans.Emp;
import vn.its.dao.EmpDao;

/**
 *
 * @author teo
 */
@WebServlet(name = "ViewServlet", urlPatterns = {"/ViewServlet"})
public class ViewServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String spageid = request.getParameter("page");
        int pageid = Integer.parseInt(spageid);
        int maxid = 5;
        int total = 0;
        if (pageid == 1) {
            total = 5;
        } else {
            pageid = (pageid - 1) * maxid + 1;
            total = pageid + 4;
        }
        List<Emp> list = EmpDao.getRecords(pageid, total);
        out.print("<h1>Page No: " + spageid + "</h1>");
        out.print("<table border='1' cellpadding='4' width='60%'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Salary</th>");
        for (Emp e : list) {
            out.print("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getSalary() + "</td></tr>");
        }
        out.print("</table>");
        out.print("<a href='ViewServlet?page=1'>1</a> ");
        out.print("<a href='ViewServlet?page=2'>2</a> ");
        out.print("<a href='ViewServlet?page=3'>3</a> ");
        out.close();
    }
}
