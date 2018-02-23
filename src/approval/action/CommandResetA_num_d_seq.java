package approval.action;

import java.sql.Date;

import approval.db.ApprovalDAO;

public class CommandResetA_num_d_seq {
	 public void deleteA_num_d() {
	      
	      String date = new Date(System.currentTimeMillis()).toString();
	      String date2 = new Date(System.currentTimeMillis()-86400000).toString();
	      
	      if(!date.substring(0,4).equals(date2.substring(0, 4))) {
	         ApprovalDAO dao = ApprovalDAO.getInstance();
	         dao.resetDocumentNum();
	      }else {
	      }
	   }
}
