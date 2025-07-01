package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yourapp.info.AiRestaurantInfo;
import com.yourapp.info.JsonUtil;
import com.yourapp.util.ChatGPTUtil;


/**
 * Servlet implementation class teeeee
 */
@WebServlet("/t1")
public class teeeee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PROMPT =
			"請列出台北8家熱門美食餐廳，使用 JSON 格式回傳，每筆含 name 和 url";


	private static final String API_KEY = "your gpt金鑰";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("inaiSearch");
		// 1. 準備請求給 OpenAI API（這裡假設你使用 OpenAI GPT）
		String gptResponseJson = ChatGPTUtil.askGpt(PROMPT, API_KEY);
		System.out.println("GPT 回傳 JSON：" + gptResponseJson);

		System.out.println("inaiSearch2");
		// 2. 假設回傳為 JSON 解析成物件
		// 使用 JsonUtil 解析完整的 List
		List<AiRestaurantInfo> aiList = JsonUtil.parseAiRestaurantList(gptResponseJson);
		request.setAttribute("aiList", aiList);

		System.out.println("inaiSearch3");
		// 3. 傳給 JSP
		
		request.setAttribute("aiList", aiList);
		System.out.println("inaiSearch4");
		// 4. 轉交回首頁(先用index2測試)
		request.getRequestDispatcher("index2.jsp").forward(request, response);

	}

}
