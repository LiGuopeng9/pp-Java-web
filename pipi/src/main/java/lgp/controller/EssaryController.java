package lgp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.AbstractDocument.Content;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import lgp.service.CommentService;
import lgp.service.ContentService;
import lgp.service.DislikeService;
import lgp.service.EssaryService;
import lgp.service.PPUserService;
import lgp.service.StarService;
import lgp.vo.Comment;
import lgp.vo.Essary;
import lgp.vo.NewComment;
import lgp.vo.NewEssary;
import lgp.vo.PPUser;

@Controller
public class EssaryController {
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

	@RequestMapping("/publish")
	public ModelAndView myPublish(@RequestParam("myfile") MultipartFile myfile, HttpServletRequest req,
			HttpServletResponse resp) throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		String publisherId;
		HttpSession session = req.getSession();
		publisherId = (String) session.getAttribute("userId");
		System.out.println(publisherId);
		System.out.println(session.getAttribute("userId"));

		String mes = req.getParameter("input1");
		String video = null;
		String photo = null;
		int kind;
		int contentid;
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

		// 先插入content
		lgp.vo.Content content = new lgp.vo.Content(mes, photo, video);
		contentService.insert(content);
		content.setContentId(contentService.select(content));
		contentid = content.getContentId();

		// 插入essary
		Essary essary = new Essary(contentid, publisherId, 0, 0, 0, kind);
		essaryService.insert(essary);
		essary.setEsId(essaryService.select(essary));
		mv.addObject("esId", essary.getEsId());
		mv.setViewName("/pages/publishSuccess");
		return mv;
	}

	@RequestMapping(value = "star", method = RequestMethod.POST)
	public void starEs(HttpServletRequest req, HttpServletResponse resp) {
		// 获取点赞人
		String starerId;
		HttpSession session = req.getSession();
		starerId = (String) session.getAttribute("userId");
		System.out.println("starerid-------" + starerId);
		// 获取被点赞的文章id
		int esId = Integer.parseInt(req.getParameter("esId"));

		// 查询是否点赞，已经点赞则不增加，未点赞则增加
		System.out.println(starService.select(starerId, esId));
		if (starService.select(starerId, esId) == null) {
			// 增加文章的点赞收藏数
			Essary essary = essaryService.selectById(esId);
			int starnum = essary.getStarnum();
			starnum++;
			essaryService.updateStar(starnum, esId);

			// insert 一个star关系
			starService.insert(starerId, esId);
		}

	}

	@RequestMapping(value = "dislike", method = RequestMethod.POST)
	public void dislikeEs(HttpServletRequest req, HttpServletResponse resp) {
		// 获取人
		String userId;
		HttpSession session = req.getSession();
		userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		// 获取被点击的文章id
		int esId = Integer.parseInt(req.getParameter("esId"));
		System.out.println(dislikeService.select(userId, esId));
		if(dislikeService.select(userId, esId)==null)
		{
			// 增加文章的dislike数
			Essary essary = essaryService.selectById(esId);
			int num = essary.getStarnum();
			num++;
			essaryService.updateDislike(num, esId);
			// insert 一个dislike关系
			dislikeService.insert(userId, esId);
		}
		
	}

	@RequestMapping(value ="esDetial",method=RequestMethod.GET)
	public ModelAndView esDetial(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam("esId") String esIdstring) {
		//获取帖子id
		int esId = Integer.parseInt(esIdstring) ;
		//打包一个newEssary，用于返回需要的帖子发布人及内容
		
		//根据esId查找帖子信息
		Essary e=essaryService.selectById(esId);
		NewEssary newEssary=new NewEssary();
		newEssary.setPublisherId(e.getPublisherId());
		newEssary.setCommentnum(e.getCommentnum());
		newEssary.setDislikenum(e.getDislikenum());
		newEssary.setEsId(e.getEsId());
		newEssary.setKind(e.getKind());
		newEssary.setStarnum(e.getStarnum());
		//根据publisherId查找用户头像 名称
		PPUser publisher = ppUserService.select(newEssary.getPublisherId());
		newEssary.setPublisherName(publisher.getUserName());
		newEssary.setPublisherImg(publisher.getUserImg());
		//根据contentid查找内容
		lgp.vo.Content content = contentService.selectById(e.getContentId());
		newEssary.setMes(content.getMes());
		newEssary.setPhoto(content.getPhoto());
		newEssary.setVideo(content.getVideo());

		//查找关于该帖子的所有评论
		//通过帖子id查找评论人id和评论内容id
		List<Comment> comments=commentService.selectByEs(esId);
		List<NewComment> newComments = new ArrayList<>();
		for( Comment c:comments)
		{
			NewComment newComment = new NewComment();
			//通过评论人id获取名字 头像
			PPUser ppUser = ppUserService.select(c.getCommenterId());
			//通过评论内容id获取具体的mes photo video
			lgp.vo.Content  CommentContent = contentService.selectById(c.getContentId());
			newComment.setCommmenterId(c.getCommenterId());
			newComment.setContentid(c.getContentId());
			newComment.setEsId(c.getEsId());
			newComment.setKind(c.getKind());
			newComment.setMes(CommentContent.getMes());
			newComment.setPhoto(CommentContent.getPhoto());
			newComment.setCommenterImg(ppUser.getUserImg());
			newComment.setCommenterName(ppUser.getUserName());
			newComment.setVideo(CommentContent.getVideo());
			newComments.add(newComment);
			

		}
		ModelAndView mv= new ModelAndView();
		mv.addObject("newEssary", newEssary);
		mv.addObject("newComments", newComments);
		mv.setViewName("/pages/comment");
		return mv;
	}

}