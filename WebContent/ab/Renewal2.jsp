<%@ page contentType="text/html; charset=UTF-8"%>
<% 

String proj_num_ = request.getParameter("proj_num");
System.out.println(proj_num_);
int proj_num = Integer.parseInt(proj_num_);
response.sendRedirect("/semi/divMain.ls?proj_num="+proj_num);
%>