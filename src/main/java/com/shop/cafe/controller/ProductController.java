package com.shop.cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Product;
import com.shop.cafe.service.ProductService;

@RestController //=뷰 템플릿을 따로 갖지 않음
//@CrossOrigin("http://127.0.0.1:8080") //5500은 프론트서버임을 알려줌
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("getAllProducts")
	 public List<Product> getAllProducts() {
		//return "ok"; //프레임워크가 잘 작동하는지 확인하기 위함
		try {
	    	  //System.out.println("getAllProducts 실행됨");
	         return productService.getAllProducts();
	      }catch (Exception e) {
	         e.printStackTrace();
	         return null;
	      }
	}
}
