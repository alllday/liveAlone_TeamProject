package com.multi.liveAlone.rice.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.liveAlone.MemberDAO;
import com.multi.liveAlone.MemberVO;
import com.multi.liveAlone.rice.food.FoodDAO;
import com.multi.liveAlone.rice.food.FoodVO;
import com.multi.liveAlone.rice.store.StoreDAO;
import com.multi.liveAlone.rice.store.StoreVO;

//사용자가 메뉴를 주문하는데 필요한 Controller
@Controller
@RequestMapping("rice/order")
public class OrderController {

	@Autowired
	StoreDAO storeDAO;
	
	// 멤버 DAO
	@Autowired
	MemberDAO memberDAO;
	
	// 음식 DAO
	@Autowired
	FoodDAO foodDAO;

	
	// 음식점의 메뉴를 보여줍니다.
	@RequestMapping("menuOrder")
	public void menuOrder(HttpSession session,Model model , int storeNo) {

		StoreVO store = storeDAO.selectOne(storeNo);
		
		List<FoodVO> foodList = foodDAO.showMenu(store.getStore_no());
		
		model.addAttribute("store",store);
		model.addAttribute("list", foodList);
	}

	
	
	// 사용자가 주문한 메뉴들을 결제 확인창에 보내주기 위한 controller
	@RequestMapping("menuPay")
	public void checkMenu(HttpSession session, Model model, OrderVOList list, StoreVO store) {
		
		int totalPrice = 0;	
		
		String memberID = (String)session.getAttribute("memberID");
		MemberVO member = memberDAO.selectOne(memberID);
		
		store = storeDAO.selectOne(store.getStore_no());
		
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		for(int i = 0; i < list.getList().size(); i++) {
			if(list.getList().get(i).getOrder_fCount() != 0) {
				orderList.add( list.getList().get(i) );
				totalPrice += (list.getList().get(i).getOrder_fCount() * list.getList().get(i).getOrder_fPrice());
			}
		}
										
		model.addAttribute("memberInfo", member);
		model.addAttribute("orderList",orderList);
		model.addAttribute("totalPrice",totalPrice);
		model.addAttribute("store", store);
	}
}