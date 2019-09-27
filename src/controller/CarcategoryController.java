package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *** 카테고리별 차 검색 ***
 */
@WebServlet("/CarcategoryController.do")
public class CarcategoryController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
												throws ServletException, IOException {
		requestPro(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
												throws ServletException, IOException {
		requestPro(request,response);
	}
	
	//doGet, doPost 방식으로 데이터가 넘어오면 requestPro메소드에서 처리
	private void requestPro (HttpServletRequest request, HttpServletResponse reponse)
												throws ServletException, IOException {

		//CarList.jsp 또는 CarReservation에서 사용자가 선택한 카테고리 값을 읽어들임
		String carCategory = request.getParameter("carcategory");
		
		
	}

}
