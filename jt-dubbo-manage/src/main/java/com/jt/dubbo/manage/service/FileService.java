package com.jt.dubbo.manage.service;

import org.springframework.web.multipart.MultipartFile;

import com.jt.dubbo.common.vo.PicUploadResult;

public interface FileService {
	public PicUploadResult upload(MultipartFile uploadFile);
}
