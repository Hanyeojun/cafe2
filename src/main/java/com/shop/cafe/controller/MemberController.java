package com.shop.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Member;
import com.shop.cafe.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
//@CrossOrigin("http://127.0.0.1:8080/")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("login")
	public Map<String, String> login(@RequestBody Member m, HttpServletRequest request) {
		System.out.println(m);
		Map<String, String> responseData = new HashMap();
		try {
			m = memberService.login(m);
			if(m != null) { //login ok 로그인 성공
				HttpSession session = request.getSession();
				System.out.println(session.getId());
				session.setAttribute("member", m);
				responseData.put("msg", "ok");
			}
			else { //login fail 로그인 실패
				responseData.put("msg", "다시 로그인 해주세요");
			}
		} 
		catch (Exception e) { //login error 로그인 하다가 에러
			e.printStackTrace();
			responseData.put("msg", "다시 로그인 해주세요");
		}
		return responseData;
	} //Post할 때만 Request 가능. GetMapping은 불가능.
	
	@PostMapping("insertMember")
	public Map<String, String> insertMember(@RequestBody Member m) {
		
		Map<String, String> responseData = new HashMap();
		
		try {
			memberService.insertMember(m);
			responseData.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			responseData.put("msg", e.getMessage());
		}

		return responseData;
	}
}
