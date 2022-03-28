<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
 
<html>
   <head>
      <title>SELECT Operation</title>
   </head>

   <body>
      <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://ee417.crxkzf89o3fh.eu-west-1.rds.amazonaws.com:3306/testdb"
         user = "ee417"  password = "ee417"/>
 
      <sql:query dataSource = "${snapshot}" var = "result">
SELECT id, surname, firstname, email, country, phone FROM testdb.customers      </sql:query>
 
      <table border = "1" width = "100%">
         <tr>
            <th>ID</th>
            <th>Surname</th>
            <th>First Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Phone</th>
         </tr>
        <c:forEach var = "row" items = "${result.rows}">
            <tr>
               <td><c:out value = "${row.id}"/></td>
               <td><c:out value = "${row.surname}"/></td>
               <td><c:out value = "${row.firstname}"/></td>
               <td><c:out value = "${row.email}"/></td>
               <td><c:out value = "${row.country}"/></td>
               <td><c:out value = "${row.phone}"/></td>
            </tr>
         </c:forEach>
      </table>
 
   </body>
</html>