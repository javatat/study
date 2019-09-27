package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CarDAO {

	//필수 세가지
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//커넥션풀을 얻고 커넥션풀로부터 커넥션 객체를 얻는 메소드
	public void getCon(){
		try{
			//1.WAS서버와 읽어들일 CarProject의 모든 정보를 지니고 있는 컨텍스트 객체 생성
			Context init = new InitialContext();
			
			//2.커넥션풀 얻기
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
			
			//3.커넥션풀로부터 커넥션 객체 얻기
			con = ds.getConnection();
		
		}catch (Exception err){
			err.printStackTrace();
		}
	}//getCon()==
	
	/* DB에 존재하는 전체차량 검색을 위한 메소드 */
	public Vector<CarListBean> getAllCarlist(){
		Vector<CarListBean> v = new Vector<CarListBean>();
		
		//검색한 차 정보 하나를 저장할 용도의 CarListBean객체를 저장할 변수 선언
		CarListBean bean = null;
		
		try{
			//커넥션풀로부터 커넥션 얻기(DB접속)
			getCon();
			
			//전체차량을 검색할 SQL문
			String sql = "select * from carlist";
			//SQL문을 실행할 pstmt객체 얻기
			pstmt = con.prepareStatement(sql);
			
			//SQL문(select)을 실행한 후 검색한 정보 얻기
			rs = pstmt.executeQuery();
			
			//while문을 이용하여 각각의 CarListBean객체에 검색한 차량정보를 저장
			while (rs.next()){
				bean = new CarListBean();
				bean.setCarno(rs.getInt(1)); //차번호 담기
				bean.setCarname(rs.getString(2));//차량명 담기
				bean.setCarcompany(rs.getString(3));//차제조사
				bean.setCarprice(rs.getInt(4));//차 한대당 가격 담기
				bean.setCarusepeople(rs.getInt(5));//차인승 정보 담기
				bean.setCarinfo(rs.getString(6));//차정보 담기
				bean.setCarimg(rs.getString(7));//차이미지명 담기
				bean.setCarcategory(rs.getString(8));//차유형(소형,중형,대형)중 하나 담기 
				
				//벡터에 CarListBean 추가
				v.add(bean);
			}
			
				//Connection객체 종료
				con.close();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return v;
	}
}
