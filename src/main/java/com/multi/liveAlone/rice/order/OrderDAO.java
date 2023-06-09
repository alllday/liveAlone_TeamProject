package com.multi.liveAlone.rice.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class OrderDAO {

	@Autowired
	SqlSessionTemplate my;
	
	
	// 주문 내역을 넣습니다.
	public void insertOrder(OrderVO order) {
		my.insert("order.insertOrder", order);
	}


	public List<OrderVO> selectOrderList(int ticket_ID) {
		return my.selectList("order.selectOrderList", ticket_ID);
	}
	
	
}
