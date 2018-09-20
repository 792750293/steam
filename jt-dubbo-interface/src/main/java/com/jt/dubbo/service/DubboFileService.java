package com.jt.dubbo.service;

import org.springframework.web.multipart.MultipartFile;

import com.jt.dubbo.common.vo.PicUploadResult;

public interface DubboFileService {
	public PicUploadResult upload(MultipartFile uploadFile);
}
