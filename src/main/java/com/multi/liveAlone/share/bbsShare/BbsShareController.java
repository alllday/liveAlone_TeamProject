package com.multi.liveAlone.share.bbsShare;

import org.springframework.stereotype.Controller;
import java.util.List;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.multi.liveAlone.MemberDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class BbsShareController {
	@Autowired
	BbsShareDAO dao;
	
	@RequestMapping("share/bbsShare/insert")
	public void insert(BbsShareVO bag, HttpServletRequest request) {
		System.out.println("insert 요청됨");

		
		
		//작성일
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bag.setBbsShareDate((String)formatter.format(date));
		
		//카테고리(110)
		bag.setBbsShareCategory("110");
		//뷰(0)
		bag.setBbsShareView(0);
		//관심(0)
		bag.setBbsShareInterest(0);
		//거래완료여부(0)
		bag.setBbsShareSuccess(false);
		
		// 요청(0)/나눔(1)여부확인, 제목, 내용
		// 글 번호는 mapper에서 null
		// 글쓴이:닉네임

		dao.insert(bag);
	}

	@RequestMapping("share/bbsShare/update")
	public void update(BbsShareVO bag) {
		System.out.println("update 요청됨");
		System.out.println(bag);
		dao.update(bag);
	}

	@RequestMapping("share/bbsShare/delete")
	public void delete(String bbsShareNo) {
		System.out.println("delete 요청됨");
		dao.delete(bbsShareNo);
	}

	@RequestMapping("share/bbsShare/one")
	public void one(String bbsShareNo, Model model) {
		System.out.println("one요청됨.");
		BbsShareVO bag = dao.one(bbsShareNo);
		model.addAttribute("bag", bag);
	}

	@RequestMapping("share/bbsShare/list")
	public String list(String pageno, Model model,RedirectAttributes redirectAttributes) {
		System.out.println("list요청됨.");
		List<BbsShareVO> list = dao.list(pageno);
		int x = Integer.parseInt(pageno) -1;
        
		if (list.isEmpty()) {
            // list가 empty인 경우
            redirectAttributes.addFlashAttribute("message", "잘못된 페이지입니다");
            return "redirect:/share/bbsShare/list?pageno="+x;
        } else {
            // list가 empty가 아닌 경우
            model.addAttribute("list", list);
            return "share/bbsShare/list";
        }
	}
	
	@RequestMapping("share/bbsShare/listcerti")
	public String listcerti(String pageno, Model model,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		System.out.println("listcerti요청됨.");
		HttpSession session = request.getSession();
		String address = (String) session.getAttribute("address");
		
		int firstIndex = address.indexOf('_');
		if (firstIndex != -1) {
		    int secondIndex = address.indexOf('_', firstIndex + 1);
		    if (secondIndex != -1) {
		        address = address.substring(0, secondIndex);
		    }
		}
		
		List<BbsShareVO> list = dao.listcerti(pageno, address);
		System.out.println(address);
        int x = Integer.parseInt(pageno) - 1;
		if (list.isEmpty()) {
            // list가 empty인 경우

    		System.out.println("list empty");
			if (x == 0) {
				
				redirectAttributes.addFlashAttribute("message", "근처지역 게시물이 없습니다");
				return "share/bbsShare/emptylist";
			}
            redirectAttributes.addFlashAttribute("message", "잘못된 페이지입니다");
            return "redirect:/share/bbsShare/listcerti?pageno="+x;
        } else {
            // list가 empty가 아닌 경우

    		System.out.println("list not empty");
            model.addAttribute("list", list);
            return "share/bbsShare/listcerti";
        }
	}
	
	@RequestMapping("share/bbsShare/success")
	public void success(String bbsShareNo, Model model) {
		System.out.println("success요청됨.");
		dao.success(bbsShareNo);
		model.addAttribute("bbsShareNo", bbsShareNo);
	}
	
	@RequestMapping("share/bbsShare/view")
	public void upview(String bbsShareNo) {
		System.out.println("upview요청됨.");
		dao.upview(bbsShareNo);
	}
	
	@RequestMapping("share/bbsShare/interestplus")
	public void upinterestup(String bbsShareNo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		System.out.println("upinterestup요청됨.");
		dao.upinterestup(bbsShareNo,member_id);
	}
	
	@RequestMapping("share/bbsShare/interestminus")
	public void upinterestdown(String bbsShareNo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		System.out.println("upinterestdown요청됨.");
		dao.upinterestdown(bbsShareNo,member_id);
	}
	
	@RequestMapping("share/bbsShare/checkinterest")
	public String checkinterest(String bbsShareNo, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		System.out.println("checkinterest요청됨.");
		model.addAttribute("result", dao.checkinterest(bbsShareNo,member_id));
		return "share/bbsShare/checkinterest";
	}
	
	@RequestMapping("share/bbsShare/sessiondelete")
	public void session_delete() {
		System.out.println("세션 초기화");
	}

}
