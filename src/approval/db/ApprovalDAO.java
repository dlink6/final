package approval.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connection.Util;


public class ApprovalDAO {
	private static ApprovalDAO instance = new ApprovalDAO();
	private ApprovalDAO() {}
	public static ApprovalDAO getInstance() {
		return instance;
	}
	private static Connection getConnection() throws Exception {
		String driver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(driver);
	}
	
	
	
	//占쎄텢筌욊낯苑� 占쎌젫�빊占�
	public void ResignationInsert(ResignationDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		
		try {
			conn = getConnection();
			String sql = "insert into resignation(r_num,deptno,enddate,r_cause,empno)";
			sql += " values (r_num.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getEndDate());
			pstmt.setString(3,dto.getR_cause());
			pstmt.setInt(4, dto.getEmpno());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
		}
	}

	
	//疫꿸퀣釉욑옙苑� 占쎌젫�빊占�
	public void DraftingInsert(DraftingDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt= null;
		try {
			conn = getConnection();
			String sql = "insert into drafting(d_num,deptno,d_subject,d_category,d_content,d_date,empno)";
			sql += " values(d_num.nextval,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getD_subject());
			pstmt.setString(3, dto.getD_category());
			pstmt.setString(4, dto.getD_content());
			pstmt.setString(5, dto.getD_date());
			pstmt.setInt(6, dto.getEmpno());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
		}
	}
	
	
	//占쎌몧揶쏉옙占쎈뻿筌ｏ옙占쎄퐣 占쎌젫�빊占�
	public void requestForVacationInsert(RequestForVacationDTO dto) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "insert into requestForVacation(V_num,deptno,v_type,v_cause,v_start,v_end,v_emergencyNumber,v_otherdetail,empno)";
			sql +=" values(v_num.nextval,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getV_type());
			pstmt.setString(3, dto.getV_cause());
			pstmt.setString(4, dto.getV_start());
			pstmt.setString(5, dto.getV_end());
			pstmt.setString(6, dto.getV_emergencyNumber());
			pstmt.setString(7, dto.getV_otherDetail());
			pstmt.setInt(8, dto.getEmpno());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
		}
	}

	//筌욑옙�빊�뮄猿먲옙�벥占쎄퐣 占쎌젫�빊占�
	public void spendingResolutionInsert(SpendingResolutionDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn =getConnection();
			String sql ="insert into spendingResolution(s_num,deptno,s_category,s_writeDate,s_spendingDate,empno)";
			sql +=" values(s_num.nextval,?,?,sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getS_category());
			pstmt.setString(3, dto.getS_spendingDate());
			pstmt.setInt(4, dto.getEmpno());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
		}
	}
	
	//筌욑옙�빊�뮄猿먲옙�벥占쎄퐣占쎄땀占쎈열占쏙옙占쎌삢
	public void spendingRecordInsert(Spending_RecordDTO dto) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql ="insert into spending_Record";
			sql += " values (s_num.currval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getS_day());
			pstmt.setString(2, dto.getS_goods());
			pstmt.setInt(3, dto.getS_amount());
			pstmt.setInt(4, dto.getS_money());
			pstmt.setString(5, dto.getS_content());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
		}
	}
	
	//野껉퀣�삺占쎈맙 �뵳�딅뮞占쎈뱜 占쎌겱占쎌삺占쎈읂占쎌뵠筌욑옙占쎈퓠 筌띿쉶�뮉 �뵳�딅뮞占쎈뱜 揶쏉옙占쎌죬占쎌궎疫뀐옙
	public List<ListDTO> getList(int start,int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ListDTO dto = null;
		List<ListDTO> list  =  new ArrayList(end);
 		try {
			conn = getConnection();
			String sql ="select documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +=" from (select rownum r,documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +="	from (select documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +=" from approvalList order by a_num desc)) where r >=? and r<=?";
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new ListDTO();
				dto.setDocumentNum(rs.getString("documentnum"));
				dto.setA_num(rs.getInt("a_num"));
				dto.setA_subject(rs.getString("a_subject"));
				dto.setA_check(rs.getString("a_check"));
				dto.setA_checkDate(rs.getString("a_checkdate"));
				dto.setA_date(rs.getString("a_date"));
				dto.setDeptno(rs.getInt("deptno"));
				dto.setEmpno(rs.getInt("empno"));
				dto.setType(rs.getInt("type"));
				dto.setEname(enameSearch(rs.getInt("empno")));
				dto.setDname(dnameSearch(rs.getInt("deptno")));
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
 		return list;
 		
	}
	//占쎌쁽疫꿸퀗占� 占쎈쿀疫뀐옙筌랃옙 癰귣��뮉 �뵳�딅뮞占쎈뱜
	public List<ListDTO> getMy_List(int start,int end,int empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ListDTO dto = null;
		List<ListDTO> list  =  new ArrayList(end);
 		try {
			conn = getConnection();
			String sql ="select documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +=" from (select rownum r,documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +="	from (select documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +=" from approvalList where empno=? order by a_num desc)) where r >= ?and r<=?";
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new ListDTO();
				dto.setDocumentNum(rs.getString("documentnum"));
				dto.setA_num(rs.getInt("a_num"));
				dto.setA_subject(rs.getString("a_subject"));
				dto.setA_check(rs.getString("a_check"));
				dto.setA_checkDate(rs.getString("a_checkdate"));
				dto.setA_date(rs.getString("a_date"));
				dto.setDeptno(rs.getInt("deptno"));
				dto.setEmpno(rs.getInt("empno"));
				dto.setType(rs.getInt("type"));
				dto.setEname(enameSearch(rs.getInt("empno")));
				dto.setDname(dnameSearch(rs.getInt("deptno")));
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
 		return list;
 		
	}
	
	
	//野껉퀣�삺占쎈맙 �눧紐꾧퐣 �룯�빓而삼옙�땾�뤃�뗫릭疫뀐옙
	public int getAcountList() {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count =0;
		try {
			conn = getConnection();
			String sql ="select count(*) from approvalList";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return count;
		
	}
	
	// 占쎌쁽疫꿸퀗占쏙옙踰� 野껉퀣�삺占쎈맙 �눧紐꾧퐣 �룯�빓而삼옙�땾�뤃�뗫릭疫뀐옙
		public int getMy_AcountList(int empno) {
			Connection conn =null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count =0;
			try {
				conn = getConnection();
				String sql ="select count(*) from approvalList where empno=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, empno);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				Util.close(rs);
				Util.close(pstmt);
				Util.close(conn);
			}
			return count;
			
		}
	
	//�겫占쏙옙苑� 占쎌뵠�뵳袁⑹몵嚥∽옙 �겫占쏙옙苑뚩린�뜇�깈 野껓옙占쎄퉳  (dept占쎈�믭옙�뵠�뇡遺용퓠占쎄퐣 揶쏉옙占쎌죬占쎌긾)
	public int deptnoSearch(String dname) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int deptno = 0;
		try {
			conn = getConnection();
			String sql = "select deptno from dept2 where dname=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				deptno = rs.getInt("deptno");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return deptno;
	}
	
	//�겫占쏙옙苑뚩린�뜇�깈嚥∽옙 �겫占쏙옙苑뚳옙�뵠�뵳占� 野껓옙占쎄퉳
	public String dnameSearch(int deptno) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String dname=null;
		try {
			conn = getConnection();
			String sql ="select dname from dept2 where deptno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dname= rs.getString("dname");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(conn);
			Util.close(pstmt);
			Util.close(rs);
		}
		return dname;
	}
			
	//占쎄텢占쎌뜚占쎌뵠�뵳袁⑹몵嚥∽옙 占쎄텢占쎌뜚甕곕뜇�깈 野껓옙占쎄퉳
	public int empnoSearch(String ename) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int empno = 0;
		try {
			conn = getConnection();
			String sql  ="select empno from emp2 where ename =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ename);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				empno = rs.getInt("empno");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return empno;
	}
	
	//占쎄텢占쎌뜚甕곕뜇�깈嚥∽옙 占쎄텢占쎌뜚占쎌뵠�뵳占� 野껓옙占쎄퉳
	public String enameSearch(int empno) {
		Connection conn =null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String ename =null;
		try {
			conn=getConnection();
			String sql ="select ename from emp2 where empno=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ename = rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
			Util.close(rs);
		}
		return ename;
	}
	
	//�눧紐꾧퐣甕곕뜇�깈 揶쏄낮揆占쎄퐣占쎈퓠 占쏙옙占쎌삢
	public void documentUpdate() {
		Connection conn= null;
		Statement stmt =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int type =0;
		String document_num =null;
		int num =0;
		try {
			conn= getConnection();
			String sql = "select documentNum,type from approvalList order by documentNum desc";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			type = rs.getInt("type");
			document_num=rs.getString("documentNum");
			stmt.close();
			rs.close();
			if(type==1) {
				sql ="select v_num from requestforvacation order by v_num desc";
				stmt =conn.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				num =rs.getInt(1);
				sql = "update requestforvacation set documentnum ='"+document_num+"' where v_num="+num;
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}else if(type ==2){
				sql ="select d_num from drafting order by d_num desc";
				stmt =conn.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				num =rs.getInt(1);
				sql = "update drafting set documentnum ='"+document_num+"' where d_num="+num;
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}else if(type==3) {
				sql ="select s_num from spendingresolution order by s_num desc";
				stmt =conn.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				num =rs.getInt(1);
				sql = "update spendingresolution set documentnum ='"+document_num+"' where s_num="+num;
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}else if ( type == 4) {
				
				sql ="select r_num from resignation order by r_num desc";
				stmt =conn.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				num =rs.getInt(1);
				sql = "update resignation set documentnum ='"+document_num+"' where r_num="+num;
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(rs);
			Util.close(stmt);
			Util.close(conn);
		}
	}
	
	
	//野껉퀣�삺占쎈맙 占쎄맒占쎄쉭癰귣떯由� 占쎌몧揶쏉옙占쎈뻿筌ｏ옙占쎄퐣
	public List<RequestForVacationDTO> getV_List(String document){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RequestForVacationDTO dto = new RequestForVacationDTO();
		List<RequestForVacationDTO> list = new ArrayList<RequestForVacationDTO>();
		try {
			conn =getConnection();
			String sql = "select * from requestForVacation where documentNum=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, document);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			dto.setDocumentNum(rs.getString("documentnum"));
			dto.setV_type(rs.getString("v_type"));
			dto.setV_cause(rs.getString("v_cause"));
			dto.setV_start(rs.getString("v_start"));
			dto.setV_end(rs.getString("v_end"));
			dto.setV_emergencyNumber(rs.getString("v_emergencyNumber"));
			dto.setV_otherDetail(rs.getString("v_otherDetail"));
			dto.setEmpno(rs.getInt("empno"));
			dto = getRequestImformation(dto);
			list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return list;
	}
	
	
	//占쎌몧揶쏉옙占쎈뻿筌ｏ옙占쎄퐣 占쎄맒占쎄쉭癰귣떯由� 占쎈툡占쎌뒄占쎈립  emp占쎌젟癰귨옙 占쏙옙占쎌삢 
		public RequestForVacationDTO getRequestImformation(RequestForVacationDTO dto) {
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			try {
				conn =getConnection();
				String sql ="select job ,address, jumin1,jumin2,hiredate from emp2 Natural join approvalList where empno="+dto.getEmpno();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				dto.setJob(rs.getString("job"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
			return dto;

		}
	
	
	//野껉퀣�삺占쎈맙 占쎄맒占쎄쉭癰귣떯由� 疫꿸퀣釉욑옙苑�
	public List<DraftingDTO> getD_List(String document){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DraftingDTO dto = new DraftingDTO();
		List<DraftingDTO> list = new ArrayList<DraftingDTO>();
		
		try {
			conn =getConnection();
			String sql = "select * from Drafting where documentNum=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, document);
			rs = pstmt.executeQuery();
			
			rs.next();
			dto.setDocumentNum(rs.getString("documentnum"));
			dto.setD_date(rs.getString("d_date"));
			dto.setD_subject(rs.getString("d_subject"));
			dto.setD_category(rs.getString("d_category"));
			dto.setD_content(rs.getString("d_content"));
			list.add(dto);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return list;
		
	}
	
	
	//野껉퀣�삺占쎈맙 占쎄맒占쎄쉭癰귣떯由� 筌욑옙�빊�뮄猿먲옙�벥占쎄퐣
	public List<SpendingResolutionDTO> getS_List(String document){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SpendingResolutionDTO dto = new SpendingResolutionDTO();
		List<SpendingResolutionDTO> list = new ArrayList<SpendingResolutionDTO>();
		
		try {
			conn =getConnection();
			String sql = "select * from SpendingResolution natural join approvalList where documentNum=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, document);
			rs = pstmt.executeQuery();
			
			rs.next();
			dto.setDocumentNum(rs.getString("documentnum"));
			dto.setS_spendingDate(rs.getString("s_spendingDate"));
			dto.setS_writeDate(rs.getDate("s_writeDate"));
			dto.setS_category(rs.getString("s_category"));
			dto.setA_checkDate(rs.getString("a_checkDate"));
			dto.setS_num(rs.getInt("s_num"));
			list.add(dto);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return list;
		
	}
	//野껉퀣�삺占쎈맙 占쎄맒占쎄쉭癰귣떯由� 筌욑옙�빊�뮄猿먲옙�벥占쎄퐣 占쎄땀占쎈열
	public List<Spending_RecordDTO> getSpending_Record(int s_num){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Spending_RecordDTO dto = null;
		List<Spending_RecordDTO> list = new ArrayList<Spending_RecordDTO>();
		
		try {
			conn =getConnection();
			String sql = "select * from Spending_Record where s_num=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();
			rs.next();
			do {
				dto = new Spending_RecordDTO();

				dto.setS_day(rs.getString("s_day"));
				dto.setS_goods(rs.getString("s_goods"));
				dto.setS_money(rs.getInt("s_money"));
				dto.setS_amount(rs.getInt("s_amount"));
				dto.setS_content(rs.getString("s_content"));
				list.add(dto);
				
			}while(rs.next());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
	
		
		return list;
		
	}
	
	//野껉퀣�삺占쎈맙 占쎄맒占쎄쉭癰귣떯由� 占쎄텢筌욊낯苑�
	public List<ResignationDTO> getR_List(String document){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResignationDTO dto = new ResignationDTO();
		List<ResignationDTO> list = new ArrayList<ResignationDTO>();
		
		try {
			conn =getConnection();
			String sql = "select * from Resignation where documentNum=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, document);
			rs = pstmt.executeQuery();
			
			rs.next();
			dto.setDocumentNum(rs.getString("documentnum"));
			dto.setEndDate(rs.getString("enddate"));
			dto.setEmpno(rs.getInt("empno"));
			dto.setR_cause(rs.getString("r_cause"));
			dto = get_R_Imformation(dto);
			
			list.add(dto);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return list;
	}
	
	//野껉퀣�삺占쎈맙  占쎄텢筌욊낯苑� 占쎄맒占쎄쉭癰귣떯由�
	public ResignationDTO get_R_Imformation(ResignationDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			conn =getConnection();
			String sql ="select job ,address, jumin1,jumin2,hiredate from emp2 Natural join approvalList where empno="+dto.getEmpno();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setJob(rs.getString("job"));
				dto.setAddress(rs.getString("address"));
				dto.setHireDate(rs.getString("hiredate"));
				dto.setJumin1(rs.getString("jumin1"));
				dto.setJumin2(rs.getString("jumin2"));
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		Util.close(rs);
		Util.close(pstmt);
		Util.close(conn);
	}
		return dto;

	}
	
	
	//野껉퀣�삺占쎈맙 占쎈뱟占쎌뵥,椰꾧퀣�쟿 占쏙옙疫뀐옙 �뿆�뫁�꺖 占쎈씜占쎈쑓占쎌뵠占쎈뱜  
	public void setApprovalCheckUpdate(String document,String value,int type, String ename) {
		Connection conn  =null;
		PreparedStatement pstmt = null;
		Statement stmt= null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn= getConnection();
			sql="update approvalList set a_check=?,a_checkdate=to_char(sysdate,'yyyy-mm-dd') where documentNum=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, value);
			pstmt.setString(2, document);
			pstmt.executeUpdate();
			if(value.equals("승인")) {
				if(type==4) {
					int empno = empnoSearch(ename);
					sql ="select enddate from resignation where empno="+empno;
					stmt =conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()) {
						Date enddate = Date.valueOf(rs.getString(1));
						insertEmp2Enddate(empno,enddate);
					}
					
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
		}
	}
	
	//emp2占쎈퓠占쎄퐣  占쎄텢筌욊낯�뵬 占쏙옙占쎌삢
	public  void insertEmp2Enddate(int empno,Date enddate){
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn =getConnection();
			String sql = "update emp2 set enddate =? where empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, enddate);
			pstmt.setInt(2, empno);
			pstmt.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(pstmt);
			Util.close(conn);
		}
		
	}
	
	
	//野껉퀣�삺 占쎈┷占쎈�占쎈뮉筌욑옙 占쎌넇占쎌뵥占쎈릭占쎈뮉 筌롫뗄苑뚳옙諭�  占쎈┷占쎈�占쎌몵筌롳옙 占쎈뱟占쎌뵥 椰꾧퀣�쟿 占쏙옙疫뀐옙 甕곌쑵�뱣 占쎈씨�⑨옙  �뿆�뫁�꺖 甕곌쑵�뱣筌랃옙
	public int getApprovalCheck(String document) {
		Connection conn  =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x =0; // 野껉퀣�삺占쎈툧占쎈쭡
		String status = null; //野껉퀣�삺占쎄맒占쎄묶
		try {
			conn =getConnection();
			String sql ="select a_check from approvalList where documentnum=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, document);
			rs = pstmt.executeQuery();
			rs.next();
			status = rs.getString("a_check");
			if(status.equals("요청") ) {
				x= 1; // 占쎌뒄筌ｏ옙占쎄맒占쎄묶 
			}else if(status.equals("취소")) {
				x=2;  //�뿆�뫁�꺖占쎄맒占쎄묶
			}else if(status.equals("승인")){
				x=3;  //椰꾧퀣�쟿占쎄맒占쎄묶
			}else if(status.equals("거절")) {
				x=4;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return x;
	}
	
	
	//野껉퀣�삺占쎈맙 野껓옙占쎄퉳 野껉퀗�궢 �뵳�딅뮞占쎈뱜 揶쏆뮇�땾
	public int getSearchCount(int deptno,int empno,String search){
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="";
		ListDTO dto = null;
		int count = 0;
		try {
			conn=getConnection();
			if(empno ==1000) {
				sql ="select count(*)";
				sql +=" from (select rownum r,documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +="	from (select documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +=" from approvalList natural join emp2 natural join dept2 ";
				sql +=" where ename like '%"+search+"%' or dname like '%"+search+"%' or a_subject like '%"+search+"%' or a_check like '%"+search+"%' order by a_num desc))";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}else {
					count = 0; //野껓옙占쎄퉳 野껉퀗�궢 占쎈씨占쎌벉
				}
			}else if(empno != 1000){
				sql ="select count(*)";
				sql +=" from (select rownum r,documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +="	from (select documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +=" from approvalList natural join emp2 natural join dept2 ";
				sql +=" where ename like '%"+search+"%' or dname like '%"+search+"%' or a_subject like '%"+search+"%' or a_check like '%"+search+"%' order by a_num desc)) where empno=?";
					
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, empno);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count =rs.getInt(1);
				}else {
					count = 0;//野껓옙占쎄퉳野껉퀗�궢占쎈씨占쎌벉
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return count;
	}
	
	
	//野껉퀣�삺占쎈맙 野껓옙占쎄퉳 野껉퀗�궢 �뵳�딅뮞占쎈뱜 占쏙옙占쎌삢
	public List<ListDTO> getSearchList(String search , int start,int end,int empno,int deptno){
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		ListDTO dto = null;
		List<ListDTO> list = new ArrayList<ListDTO>();
		try {
			conn=getConnection();
			
			if(empno ==1000) {
				
				String sql ="select documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +=" from (select rownum r,documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +="	from (select documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +=" from approvalList natural join emp2 natural join dept2 ";
				sql +=" where ename like '%"+search+"%' or dname like '%"+search+"%' or a_subject like '%"+search+"%' or a_check like '%"+search+"%' order by a_num desc)) where r >=? and r<=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs=pstmt.executeQuery();
				rs.next();
				
				do {
					dto = new ListDTO();
					dto.setDocumentNum(rs.getString("documentnum"));
					dto.setA_num(rs.getInt("a_num"));
					dto.setA_subject(rs.getString("a_subject"));
					dto.setA_check(rs.getString("a_check"));
					dto.setA_checkDate(rs.getString("a_checkdate"));
					dto.setA_date(rs.getString("a_date"));
					dto.setDeptno(rs.getInt("deptno"));
					dto.setEmpno(rs.getInt("empno"));
					dto.setType(rs.getInt("type"));
					dto.setEname(enameSearch(rs.getInt("empno")));
					dto.setDname(dnameSearch(rs.getInt("deptno")));
					list.add(dto);
				}while(rs.next());
			}else {
				String sql ="select documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +=" from (select rownum r,documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +="	from (select documentnum,a_num,a_subject,a_check,a_checkDate,a_date,deptno,empno,type";
				sql +=" from (select * from approvalList natural join emp2) natural join dept2 ";
				sql +=" where (ename=? or dname=? or a_subject=? or a_check=?) and empno=? order by a_num desc)) where r >=? and r<=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, search);
				pstmt.setString(2, search);
				pstmt.setString(3, search);
				pstmt.setString(4, search);
				pstmt.setInt(5, empno);
				pstmt.setInt(6, start);
				pstmt.setInt(7, end);
				rs=pstmt.executeQuery();
				rs.next();
				
				do {
					dto = new ListDTO();
					dto.setDocumentNum(rs.getString("documentnum"));
					dto.setA_num(rs.getInt("a_num"));
					dto.setA_subject(rs.getString("a_subject"));
					dto.setA_check(rs.getString("a_check"));
					dto.setA_checkDate(rs.getString("a_checkdate"));
					dto.setA_date(rs.getString("a_date"));
					dto.setDeptno(rs.getInt("deptno"));
					dto.setEmpno(rs.getInt("empno"));
					dto.setType(rs.getInt("type"));
					dto.setEname(enameSearch(rs.getInt("empno")));
					dto.setDname(dnameSearch(rs.getInt("deptno")));
					list.add(dto);
				}while(rs.next());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Util.close(rs);
			Util.close(pstmt);
			Util.close(conn);
		}
		return list;
	}
	
	
	 //�눧紐꾧퐣 占쎈염占쎈즲獄쏅뗀占쎈슢�늺 占쎈뻻占쏙옙占쎈뮞 �룯�뜃由곤옙�넅
	   public void resetDocumentNum() {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      Statement stmt =null;
	      try {
	         
	         String date = new Date(System.currentTimeMillis()).toString();
	         String date2 = new Date(System.currentTimeMillis()-86400000).toString();
	         conn =getConnection();
	         
	         if(!date.substring(0,4).equals(date2.substring(0, 4))) {
	            String sql ="drop sequence a_num_d";
	            stmt =conn.createStatement();
	            stmt.executeUpdate(sql);
	            sql ="create sequene a_num_d";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.executeUpdate();
	         }else {
	            System.out.println("占쎈염占쎈즲揶쏉옙揶쏆늿�벉");
	         } 
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         Util.close(pstmt);
	         Util.close(conn); 
	      }
	   } 
	
	   //占쎄텢筌욊낮沅뉛쭪�뮆由븝쭖占� emp2占쎈�믭옙�뵠�뇡遺용퓠占쎄퐣 占쎄텢占쎌뜚 占쎄텣占쎌젫
	   public void deleteEmp(String enddate) {
	      Connection conn = null;
	      PreparedStatement pstmt =null;
	      try {
	         conn = getConnection();
	         String sql="update emp2 set status= 1 where enddate=?";
	         pstmt =conn.prepareStatement(sql);
	         pstmt.setString(1, enddate);
	         pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         Util.close(pstmt);
	         Util.close(conn);
	      }
	   }
	   
	   
}
