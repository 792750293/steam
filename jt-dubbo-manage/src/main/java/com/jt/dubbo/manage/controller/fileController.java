package com.jt.dubbo.manage.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.dubbo.common.vo.PicUploadResult;
import com.jt.dubbo.manage.service.FileService;

@Controller
public class fileController {
	@Autowired
	FileService fileService;
	
	@RequestMapping("/file")
private String file(MultipartFile file) throws IllegalStateException, IOException{
	String path="E:/jt-upload";
	File filePath=new File(path);
	if(!filePath.exists()){
		filePath.mkdirs();
	}
	String filename=file.getOriginalFilename();
	file.transferTo(new File(path+"/"+filename));
	return "redirect:/page/file";
}//function
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult uploadFile(MultipartFile uploadFile){
		System.out.println("===============================================================================");
		return fileService.upload(uploadFile);
	}

}//class
