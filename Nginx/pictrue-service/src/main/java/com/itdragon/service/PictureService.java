package com.itdragon.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

	/**
	 * 上传，批量上传接口
	 * @param uploadFile
	 * @return
	 */
	Map uploadPicture(MultipartFile uploadFile);
}
