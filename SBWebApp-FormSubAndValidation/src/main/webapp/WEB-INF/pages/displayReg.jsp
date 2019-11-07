<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
   <h2 style="color:red;text-align:center">Register Form here</h2>
 <script type="text/javascript">
    function deleteConfirm() {
        return confirm("Are you sure to want to delete");		
	}
 </script>  
</head>
<body>
    
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
   <table border="1">
     <thead>
       <tr>
         <th>Sr.no</th>
         <th>UserName</th>
         <th>Email</th>
         <th>PhoneNo</th>
       </tr>
      </thead>
      <c:forEach items="${viewUser}" var="user" varStatus="index">
        <tr>
          <td>${index.count}</td>
          <td>${user.username}</td>
          <td>${user.email}</td>
          <td>${user.phno}</td>
          <td>${user.countries}</td>
          <td><a href="editUser?userId=${user.userId}">Edit</a> &nbsp;&nbsp;
              <a href="deleteUser?userId=${user.userId}" onclick="return deleteConfirm()">Delete</a>
          </td>
        </tr>
      </c:forEach>
   </table>
   
   <c:if test="${cp>1}">
      <a href="viewUser?pn=${cp-1}">Previous</a>
   </c:if>
   
   <c:forEach begin="1" end="${tp}" var="i">
     <c:choose>
       <c:when test="${cp==i}">
         <b>${i}</b>
       </c:when>
        <c:otherwise>
           <b><a href="viewUser?pn=${i}">${i}</a></b>
        </c:otherwise>
     </c:choose>
   </c:forEach>
   
   <c:if test="${cp<tp}">
     <a href="viewUser?pn=${cp+1}">Next</a>
   </c:if>
   
   <h2 style="color:red;text-align:center">
     <a href="registerUser">Home</a>
   </h2>
      
</body>
</html>