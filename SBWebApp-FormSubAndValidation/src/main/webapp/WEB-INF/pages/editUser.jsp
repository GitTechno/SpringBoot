<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
  .error{
      color:#FF0000
    }

</style>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
 
	$(function() {
		$('form[id="userRegForm"]').validate({
			rules : {
				username : 'required',
				password : 'required',
				email : {
					required : true,
					email : true
				},
		         
				phno : {
					required: true,
				    minlength: 5,
				    maxlength: 10
				    
				},
			},
			messages : {
				username : 'Please enter username',
				password : 'please enter password',
				email : 'Please enter valid email',
				phno :{
					required:'please enter phone number',
					minlength:'Enter minimum 5 digits',
					maxlength:'Enter maximum 10 digits'
				} ,
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>

<title>Insert title here</title>
</head>
<body>

     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
      
      <h2>${succMsg}</h2>
      
     <h2>Update User Here</h2>   
     <form:form action="update?userId=${user.userId}" method="post" modelAttribute="user" id="userRegForm">
       <table>
          <tr>
             <td>UserName::</td>
             <td><form:input path="username"/></td>
          </tr>
          <tr>
             <td>PassWord::</td>
             <td><form:password path="password"/></td>
          </tr>
          <tr>
             <td>Email::</td>
             <td><form:input path="email"/></td>
          </tr>
          <tr>
             <td>Phone No::</td>
             <td><form:input path="phno"/></td>
          </tr>
          <tr>
            <td>Countries::</td>
            <td><select item=${countriesList} path={countries}></select></td>
          </tr>
          
          <tr>
             <td><input type="reset" value="reset"/></td>
             <td><input type="submit" value="Update"/></td>
          </tr>
       </table>
     </form:form>
     <br><br>
     <h2><a href="viewUser">ViewUser</a></h2>
     <h2 style="color:red;text-align:center">${msg}</h2>
</body>
</html>