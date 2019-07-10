package lgp.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lgp.service.CommentService;
import lgp.service.ContentService;
import lgp.service.DislikeService;
import lgp.service.EssaryService;
import lgp.service.PPUserService;
import lgp.service.StarService;
import lgp.vo.Comment;
import lgp.vo.Content;
import lgp.vo.Essary;
import lgp.vo.NewEssary;
import lgp.vo.PPUser;

@Controller
public class CommentController {
	@Autowired
	PPUserService ppUserService;
	@Autowired
	ContentService contentService;
	@Autowired
	EssaryService essaryService;
	@Autowired
	StarService starService;
	@Autowired
	DislikeService dislikeService;
	@Autowired
	CommentService commentService;

	// ��������
	@RequestMapping("comment")
	public ModelAndView register(@RequestParam("myfile") MultipartFile myfile,@RequestParam("esId") String esIdstring,
			HttpServletRequest req,
			HttpServletResponse resp) throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		//��ȡ������id
		
		String commenterId;
		HttpSession session = req.getSession();
		commenterId = (String) session.getAttribute("userId");
		

		int esId = Integer.parseInt(esIdstring);
		String mes = req.getParameter("input1");
		String video = null;
		String photo = null;
		int kind = 1;
		int contentId;
		
		
		//��������һ
		Essary essary = essaryService.selectById(esId);
		essaryService.updateComment(essary.getCommentnum()+1, esId);
		
		
		if(myfile!=null)
		{
			// �ϴ��ļ�
			// 1.�����ϴ��ļ�·��
			String uploadPath = req.getServletContext().getRealPath("upload");
			System.out.println("uploadPath:" + uploadPath);
			// ���Ŀ¼�������򴴽�
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			// 2.���������ϴ����ݵ��ļ�
			// a.��ȡ�ļ���չ�� ����.jpg
			String realFileName = myfile.getOriginalFilename();
			String[] tmp = realFileName.split("\\.");
			String type = tmp[tmp.length - 1];
			// b.����Ψһ���ļ��� ������ǰʱ��ĺ���ֵ+a.��ȡ����չ��
			String fileName = "" + System.currentTimeMillis() + "." + type;
			// c.�������ļ�
			String filePath = uploadPath + File.separator + fileName;
			// String dektopPath = "C:\\Users\\93700\\Desktop\\Java Web\\workspace\\file"+
			// File.separator + fileName;
			System.out.println("filepath:" + filePath);
			// 3.���ļ�����д���ļ�
			myfile.transferTo(new File(filePath));
			// ͬ�����浽����
			// userImg.transferTo(new File(dektopPath));
			// 4.���ñ�ʾ�ļ���src
			String myfileSrc = req.getContextPath() + File.separator + "upload" + File.separator + fileName;
			// �ж�����Ƶ����ͼƬ
			if (type.equals("mp4")) {
				kind = 3;
				video = myfileSrc;
			} else if (type.equals("jpg") || type.equals("jpeg")) {
				kind = 2;
				photo = myfileSrc;
			} else {
				kind = 1;
			}

		}


		// �Ȳ���content
		lgp.vo.Content content = new lgp.vo.Content(mes, photo, video);
		contentService.insert(content);
		content.setContentId(contentService.select(content));
		contentId = content.getContentId();
System.out.println("commmenterId:-------------------------"+commenterId);
		// ����comment
		Comment comment = new Comment(esId, commenterId, contentId, kind);
		commentService.insert(comment);
		mv.setViewName("redirect:esDetial?esId="+esId);
		return mv;
	}

}
