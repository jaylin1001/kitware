<%@page import="com.kitware.member.vo.GradeInfo"%>
<%@page import="com.kitware.member.vo.DeptInfo"%>
<%@page import="com.kitware.member.vo.Members"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<% 
   List<DeptInfo> deptlist = (List) request.getAttribute("deptlist");
   List<GradeInfo> gradelist = (List) request.getAttribute("gradelist");
   List<List<List<Members>>> memberlist = (List) request.getAttribute("memberlist");
%>

<div class="modal-header">
   <button type="button" class="close" data-dismiss="modal"
      aria-label="Close" aria-hidden="true">x</button>
   <h5 class="smaller lighter blue no-margin modal-title">결재자 선택</h5>
</div>
<div class="modal-body">
   <div class="easy-tree">
   <ul>
   <%for(int i=0;i<memberlist.size();i++){ %>
      <li class="dept">
         <label><%=deptlist.get(i).getDept_name()%><%=memberlist.size() %></label>
         <ul>
         <% for(int j=0;j<memberlist.get(i).size();j++){%>
            <li class="grade"><label><%=gradelist.get(j).getPosition_name()%></label>
               <ul>
                  <%for(int k=0;k<memberlist.get(i).get(j).size();k++){ %>
                  <li><%=memberlist.get(i).get(j).get(k).getName() %></li>
                  <%}%>
               </ul>
            </li>
         <% }%>
         </ul>
      </li>
   <%} %>
   </ul>
   </div>
</div>
<div class="modal-footer">
   <button class="btn btn-sm btn-deafult pull-right" data-dismiss="modal" id="btnClose">
      <i class="ace-icon fa fa-times"></i>닫기
   </button>
   <button class="btn btn-sm btn-default pull-right" data-dismiss="modal" id="btnChoice">
      <i class="ace-icon fa fa-check"></i>선택
   </button>
</div>



<link rel="stylesheet" href="${pageContext.request.contextPath}/css/easyTree/easyTree.css">
<script src="${pageContext.request.contextPath}/js/easyTree/easyTree.js"></script>
<script>
   $(function() {
      $('.easy-tree').EasyTree({
      });
      
      $('#btnChoice').click(function(){
         $('#grantor<%=request.getParameter("id")%>_grade').html($('.easy-tree li.li_selected').closest('ul').siblings('span').find('a').html());
         window.alert('<%=request.getParameter("id")%>')
         <%-- console.log($('.easy-tree li.li_selected').closest('ul').siblings('span').find('a').html());
         console.log($('.easy-tree li.li_selected').parents('li.grade').html());
         console.log('<%=request.getParameter("id")%>');
         console.log('<%=request.getRequestURL()%>'); --%>
         $('#grantor<%=request.getParameter("id")%>').html($('.easy-tree li.li_selected > span > a').html());
         /* console.log($('.easy-tree li.li_selected > span > a').html()); */ 
         $('#myModal').on('hidden.bs.modal', function(){
            $(this).removeData('bs.modal');
         });
      });
      
   });
</script>
<style>
.easy-tree {
   min-height: 20px;
   margin-bottom: 20px;
   background-color: #337ab7;
   color: #fff;
   border: none;
   border-top: none;
   padding-bottom: 15px;
}

.easy-tree li>span>a {
   color: #fff;
   text-decoration: none;
}

.easy-tree li>span>a:hover {
   color: #8A4500;
   text-decoration: none;
   background: #FFCB97;
}

.easy-tree li.parent_li>span:hover, .easy-tree li.parent_li>span:hover+ul li span
   {
   background: #004040;
   color: #8A4500;
}
/* 인사부,개발부 ,경영부 , 기획부  아래 다 display:none  */
.easy-tree>ul>li ul {
   display: none;
}
</style>