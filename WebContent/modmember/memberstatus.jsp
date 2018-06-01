<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp" %>
<script>
	var className = 'modmember';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();	
</script>    
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>   
  
    

    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="div1"></div>
<div class="container">
	<%@include file="/modmember/memberstatusresult.jsp"%>
</div>
<style>
.pagination a.active {
    background-color: #237abc;
    color: white;
}
tbody tr a {
	color: black;
}
tbody tr a:hover{
	color: gray;
	text-decoration: none;
}
tbody tr>td:not(:nth-child(2)){
	text-align: center;
	vertical-align: middle;
}
tbody tr>td:nth-child(2){
	vertical-align: middle;
}
.container {
	padding-top : 35px;
	margin:0;
	width:70%;
}
body {
	width: 100%;
	margin: 0;
	padding: 0;
}
pd {
	padding-right: 200px;
}
#writeform{
	float:right;
}
.text-center>button {
	border: none;
	color: white;
	padding: 8px 10px;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}
.pagination{
	margin: auto;
}
</style>

    
    
<%@include file="../container/footer.jsp" %>