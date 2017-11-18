package com.itdragon.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itdragon.service.PictureService;

@RestController
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("pic/upload")
	public String pictureUpload(@RequestParam(value = "fileUpload") MultipartFile uploadFile) {
		String json = "";
		try {
			Map result = pictureService.uploadPicture(uploadFile);
			// 浏览器擅长处理json格式的字符串，为了减少因为浏览器内核不同导致的bug，建议用json
			json = new ObjectMapper().writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}
