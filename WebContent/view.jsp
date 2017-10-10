<%--
  Created by IntelliJ IDEA.
  User: teo
  Date: 9/21/17
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*,vn.its.dao.*,vn.its.beans.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>View</title>
    </head>
    <body>
        <%
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
                out.print("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td> <td>" + e.getSalary() + "</td></tr>");
            }
            out.print("</table>");
        %>
        <a href="view.jsp?page=1">1</a>
        <a href="view.jsp?page=2">2</a>
        <a href="view.jsp?page=3">3</a>
    </body>
</html>
