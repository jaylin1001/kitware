<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>KITWare </title>
     <!-- JQuery 3.3.1 CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- BootStrap 3.3.7 js CDN -->
    <script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Custom Theme JavaScript -->
	<script src="${pageContext.request.contextPath}/js/custom/sb-admin-2.js"></script>

    
    <!-- BootStrap 3.3.7 css CDN -->
    <link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
	<!-- Custom CSS -->
	<link href="${pageContext.request.contextPath}/css/custom/sb-admin-2.css" rel="stylesheet">
 
	<script>
		$(function(){
			
			var $idObj = $('input#userid');
			var $pwObj = $('input#userpw');
			var $chkObj = $('input[type=checkbox]');
			
			var itemValue = localStorage.getItem('id');
			
			if(itemValue != null){
				$chkObj.prop('checked', true);
			}else{
				$chkObj.prop('checked', false);
			}
			
			$idObj.val(itemValue);
			
			
			$('form').submit(function(){
				var idValue = $idObj.val();
				//chkObj가 체크된 경우에는
				if($chkObj.prop('checked')){
				  localStorage.setItem('id', idValue);		
				}else{//chkObj가 체크안된 경우에는
				  localStorage.removeItem('id');
				}		
				$.ajax({
					data:
					    {'id':$('input[name=userid]').val(),
						 'pwd':$('input[name=userpw]').val()
						},
					method:'POST',
					url: 'login.do',
					success: function(data){	
						var logindata = data.trim();
						if(logindata == '1'){
							alert("로그인성공");
							location.href="${pageContext.request.contextPath}/index.jsp";

						}else if(logindata == '-1'){
							window.alert('로그인 실패');
						}else{
							alert(logindata);
						}
	
					}
				});
				
				return false; //기본이벤트처리 막기
			});
		});
	</script>

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">KITWare Login</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" method="post" id="form1" name="form1">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" required="required" placeholder="ID" name="userid" id="userid"  autofocus value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" required="required" placeholder="Password" name="userpw" id="userpw" type="password" value="" onkeydown="if(event.keyCode == 13) { fn_formSubmit();}">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" >계정저장
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button class="btn btn-lg btn-info btn-block" >Login</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
