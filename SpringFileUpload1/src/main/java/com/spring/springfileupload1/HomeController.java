package com.spring.springfileupload1;

import java.io.File;
import java.io.FileInputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping("fileUploadForm.bo")
	public String fileUploadForm() {
		return "fileUploadForm";
	}

	@RequestMapping("fileUpload1.bo")
	public ModelAndView fileUpload1(HttpServletRequest request, RequestModel model) throws Exception {
		String name = request.getParameter("name");
		String uploadPath = "C:\\project\\upload\\";

		ModelAndView mav = new ModelAndView();
		mav.setViewName("download");

		ArrayList<String> orgfile_list = new ArrayList<String>();
		ArrayList<String> storedfile_list = new ArrayList<String>();
		ArrayList<Long> filesize_list = new ArrayList<Long>();
		for (MultipartFile mf : model.getFile()) {
			String originFileName = mf.getOriginalFilename();
			long fileSize = mf.getSize();

			System.out.println("originFileName: " + originFileName);
			System.out.println("fileSize: " + fileSize);
			String storedFileName = System.currentTimeMillis() + originFileName;
			System.out.println("storedFileName= " + storedFileName);
			String safeFile = uploadPath + storedFileName;
			try {
				if (mf.getSize() != 0) {
					mf.transferTo(new File(safeFile));
				}
			} catch (Exception e) {
				System.out.println("업로드 에러!!");
			}
			orgfile_list.add(originFileName);
			storedfile_list.add(storedFileName);
			filesize_list.add(fileSize);
		}
		mav.addObject("name", name);
		mav.addObject("orgfile_list", orgfile_list);
		mav.addObject("storedfile_list", storedfile_list);
		mav.addObject("filesize_list", filesize_list);
		mav.addObject("uploadPath", uploadPath);
		return mav;
	}

	@RequestMapping("/fileUpload2.bo")
	public ModelAndView fileUpload2(MultipartHttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
		String uploadPath = "C:\\project\\upload\\";

		List<MultipartFile> fileList = request.getFiles("file");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("download");
		ArrayList<String> orgfile_list = new ArrayList<String>();
		ArrayList<String> storedfile_list = new ArrayList<String>();
		ArrayList<Long> filesize_list = new ArrayList<Long>();

		for (MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename();
			long fileSize = mf.getSize();
			System.out.println("originFileName: " + originFileName);
			System.out.println("fileSize: " + fileSize);
			String storedFileName = System.currentTimeMillis() + originFileName;
			System.out.println("storedFileName= " + storedFileName);
			String safeFile = uploadPath + storedFileName;
			try {
				if (mf.getSize() != 0) {
					mf.transferTo(new File(safeFile));
				}
			} catch (Exception e) {
				System.out.println("업로드 에러!!");
			}
			orgfile_list.add(originFileName);
			storedfile_list.add(storedFileName);
			filesize_list.add(fileSize);
		}
		mav.addObject("name", name);
		mav.addObject("orgfile_list", orgfile_list);
		mav.addObject("storedfile_list", storedfile_list);
		mav.addObject("filesize_list", filesize_list);
		mav.addObject("uploadPath", uploadPath);
		return mav;
	}
	@RequestMapping("/fileDownload.bo")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		
		String of = request.getParameter("of");
		String of2 = request.getParameter("of2");
		
		String uploadPath = "C:\\Project\\upload\\";
		String fullPath = uploadPath + of;
		File downloadFile = new File(fullPath);
		
		response.setContentType("application/download; charset=utf-8");
		response.setContentLength((int)downloadFile.length());
		response.setHeader("Content-Disposition", "attachment; filename="+ new String(of2.getBytes(),"ISO8859_1"));
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		FileInputStream fin = new FileInputStream(downloadFile);
		ServletOutputStream sout = response.getOutputStream();
		
		byte[] buf = new byte[1024];
		int size = -1;
		
		while ((size = fin.read(buf,0,buf.length))!= -1) {
			sout.write(buf,0,size);
		}
		fin.close();
		sout.close();
	}

}
