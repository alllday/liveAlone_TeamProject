package com.multi.liveAlone.party.voyage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller

public class VoyageController {
	 

		@Autowired
		VoyageDAO dao; 
		
		
	
		@RequestMapping("update")
		public void update(VoyageVO bag) {
		    System.out.println(bag);
		    dao.update(bag);
		}
		@RequestMapping("updatebasket")
	    @ResponseBody
	    public void updateBasket(String member_id, String lat, String longi) {
	        VoyageVO bag = new VoyageVO();
	        bag.setMember_id(member_id);
	        bag.setLat(lat);
	        bag.setLongi(longi);
	        dao.updateBasket(bag);
	    }	
		@RequestMapping(value = "deleteselect", method = RequestMethod.POST)
		@ResponseBody
		public void deleteSelect(String member_id, String selection) {
		    VoyageVO bag = new VoyageVO();
		    bag.setMember_id(member_id);
		    bag.setSelection(selection);
		    dao.deleteSelect(bag);
		}
		
	
		@RequestMapping(value = "insertselect", method = RequestMethod.POST)
		@ResponseBody
		public void insertSelect(String member_id, String selection) {
		    VoyageVO bag = new VoyageVO();
		    bag.setMember_id(member_id);
		    bag.setSelection(selection);
		    dao.insertSelect(bag);
		    
		}
		@RequestMapping("oneselect")
		public void oneSelect(String member_id, Model model) {
			System.out.println(member_id);
			VoyageVO vo = dao.oneselect(member_id); //vo
			//views아래까지 전달할 데이터를 model객체를 이용해서
			//속성으로 지정해주세요.
			model.addAttribute("vo", vo); //속성으로 지정
		}
		@RequestMapping("one")
		public void one(String name, Model model) {
			System.out.println(name);
			VoyageVO vo = dao.one(name); //vo
			//views아래까지 전달할 데이터를 model객체를 이용해서
			//속성으로 지정해주세요.
			model.addAttribute("vo", vo); //속성으로 지정
		}
		
		@RequestMapping("list")
		public void list(Model model) {
			List<VoyageVO> list = dao.list();
			//views아래까지 전달할 데이터를 model객체를 이용해서
			//속성으로 지정해주세요.
			model.addAttribute("list", list); //속성으로 지정
		}
		
		
		@RequestMapping("voyageall")
		public String getButtonNameAndRandom(Model model, HttpSession session) {
		    String member_id = (String) session.getAttribute("member_id");
		    System.out.println(member_id);

		    // Round Buttons
		    List<String> selectionnames = dao.getButtonName(member_id);
		    System.out.println(selectionnames);

		    int buttonCount = 5; // 항상 5개의 roundButton 생성

		    List<String> roundButtons = new ArrayList<>();
		    List<VoyageVO> coordinates = new ArrayList<>(); // 좌표를 담을 리스트 추가

		    for (int i = 0; i < buttonCount; i++) {
		        String buttonTag;
		        if (i < selectionnames.size()) {
		            if (!selectionnames.get(i).isEmpty()) {
		                buttonTag = "<button class=\"roundButton\">" + selectionnames.get(i) + "</button>";
		                // 좌표 가져오기
		                VoyageVO coordinate = dao.getpoint(selectionnames.get(i));
		                coordinates.add(coordinate);
		            } else {
		                buttonTag = "<button class=\"roundButton\"></button>";
		            }
		        } else {
		            buttonTag = "<button class=\"roundButton\"></button>";
		        }
		        roundButtons.add(buttonTag);
		    }

		    model.addAttribute("roundButtons", roundButtons);
		    model.addAttribute("coordinates", coordinates); // 좌표를 모델에 추가

		    // Random Buttons
		    List<String> randomNames = dao.getRandomNames(5); // 5개의 이름 가져오기
		    System.out.println("randomNames  : " + randomNames);
		    model.addAttribute("randomNames", randomNames);

		    return "voyage";
		}
		
}



