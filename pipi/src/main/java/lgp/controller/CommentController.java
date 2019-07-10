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

	// 发表评论
	@RequestMapping("comment")
	public ModelAndView register(@RequestParam("myfile") MultipartFile myfile,@RequestParam("esId") String esIdstring,
			HttpServletRequest req,
			HttpServletResponse resp) throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		//获取评论者id
		
		String commenterId;
		HttpSession session = req.getSession();
		commenterId = (String) session.getAttribute("userId");
		

		int esId = Integer.parseInt(esIdstring);
		String mes = req.getParameter("input1");
		String video = null;
		String photo = null;
		int kind = 1;
		int contentId;
		
		
		//评论数加一
		Essary essary = essaryService.selectById(esId);
		essaryService.updateComment(essary.getCommentnum()+1, esId);
		
		
		if(myfile!=null)
		{
			// 上传文件
			// 1.创建上传文件路径
			String uploadPath = req.getServletContext().getRealPath("upload");
			System.out.println("uploadPath:" + uploadPath);
			// 如果目录不存在则创建
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			// 2.创建保存上传内容的文件
			// a.获取文件扩展名 例：.jpg
			String realFileName = myfile.getOriginalFilename();
			String[] tmp = realFileName.split("\\.");
			String type = tmp[tmp.length - 1];
			// b.定义唯一的文件名 例：当前时间的毫秒值+a.获取的扩展名
			String fileName = "" + System.currentTimeMillis() + "." + type;
			// c.创建新文件
			String filePath = uploadPath + File.separator + fileName;
			// String dektopPath = "C:\\Users\\93700\\Desktop\\Java Web\\workspace\\file"+
			// File.separator + fileName;
			System.out.println("filepath:" + filePath);
			// 3.把文件内容写入文件
			myfile.transferTo(new File(filePath));
			// 同步保存到电脑
			// userImg.transferTo(new File(dektopPath));
			// 4.设置表示文件的src
			String myfileSrc = req.getContextPath() + File.separator + "upload" + File.separator + fileName;
			// 判断是视频还是图片
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


		// 先插入content
		lgp.vo.Content content = new lgp.vo.Content(mes, photo, video);
		contentService.insert(content);
		content.setContentId(contentService.select(content));
		contentId = content.getContentId();
System.out.println("commmenterId:-------------------------"+commenterId);
		// 插入comment
		Comment comment = new Comment(esId, commenterId, contentId, kind);
		commentService.insert(comment);
		mv.setViewName("redirect:esDetial?esId="+esId);
		return mv;
	}

}
