package com.kitware.authorization.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocManipulService;
import com.kitware.member.vo.DeptInfo;

public class DocGianInfoController implements Controller {
   // Calendar cal = Calendar.getInstance();
   DocManipulService service = DocManipulService.getInstance();

   public DocGianInfoController() {
      super();
   }

   public DocGianInfoController(DocManipulService service) {
      super();
      this.service = service;
   }

   public DocManipulService getService() {
      return service;
   }

   public void setService(DocManipulService service) {
      this.service = service;
   }

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      try {
         String doc_num = service.getDocNum();
         SimpleDateFormat dformat = new SimpleDateFormat("yyMM");
         String year = dformat.format(new Date());
         List<DeptInfo> list;
         int sub = doc_num.indexOf("-");
         list = service.getDeptList();
         if (doc_num == null || !doc_num.substring(0, sub).equals(year)) {
            doc_num = year + "-0001";
         } else {
            doc_num = doc_num.substring(0, sub) + "-"
                  + String.format("%04d%n", (Integer.parseInt(doc_num.substring(sub + 1)) + 1));
         }
         request.setAttribute("doc_num", doc_num);
         request.setAttribute("list", list);
      } catch (Exception e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      String forwardURL = "authorization/docwrite.jsp";
      return forwardURL;
   }
}