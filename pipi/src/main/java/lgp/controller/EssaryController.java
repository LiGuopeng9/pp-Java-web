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

		// �Ȳ���content
		lgp.vo.Content content = new lgp.vo.Content(mes, photo, video);
		contentService.insert(content);
		content.setContentId(contentService.select(content));
		contentid = content.getContentId();

		// ����essary
		Essary essary = new Essary(contentid, publisherId, 0, 0, 0, kind);
		essaryService.insert(essary);
		essary.setEsId(essaryService.select(essary));
		mv.addObject("esId", essary.getEsId());
		mv.setViewName("/pages/publishSuccess");
		return mv;
	}

	@RequestMapping(value = "star", method = RequestMethod.POST)
	public void starEs(HttpServletRequest req, HttpServletResponse resp) {
		// ��ȡ������
		String starerId;
		HttpSession session = req.getSession();
		starerId = (String) session.getAttribute("userId");
		System.out.println("starerid-------" + starerId);
		// ��ȡ�����޵�����id
		int esId = Integer.parseInt(req.getParameter("esId"));

		// ��ѯ�Ƿ���ޣ��Ѿ����������ӣ�δ����������
		System.out.println(starService.select(starerId, esId));
		if (starService.select(starerId, esId) == null) {
			// �������µĵ����ղ���
			Essary essary = essaryService.selectById(esId);
			int starnum = essary.getStarnum();
			starnum++;
			essaryService.updateStar(starnum, esId);

			// insert һ��star��ϵ
			starService.insert(starerId, esId);
		}

	}

	@RequestMapping(value = "dislike", method = RequestMethod.POST)
	public void dislikeEs(HttpServletRequest req, HttpServletResponse resp) {
		// ��ȡ��
		String userId;
		HttpSession session = req.getSession();
		userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		// ��ȡ�����������id
		int esId = Integer.parseInt(req.getParameter("esId"));
		System.out.println(dislikeService.select(userId, esId));
		if(dislikeService.select(userId, esId)==null)
		{
			// �������µ�dislike��
			Essary essary = essaryService.selectById(esId);
			int num = essary.getStarnum();
			num++;
			essaryService.updateDislike(num, esId);
			// insert һ��dislike��ϵ
			dislikeService.insert(userId, esId);
		}
		
	}

	@RequestMapping(value ="esDetial",method=RequestMethod.GET)
	public ModelAndView esDetial(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam("esId") String esIdstring) {
		//��ȡ����id
		int esId = Integer.parseInt(esIdstring) ;
		//���һ��newEssary�����ڷ�����Ҫ�����ӷ����˼�����
		
		//����esId����������Ϣ
		Essary e=essaryService.selectById(esId);
		NewEssary newEssary=new NewEssary();
		newEssary.setPublisherId(e.getPublisherId());
		newEssary.setCommentnum(e.getCommentnum());
		newEssary.setDislikenum(e.getDislikenum());
		newEssary.setEsId(e.getEsId());
		newEssary.setKind(e.getKind());
		newEssary.setStarnum(e.getStarnum());
		//����publisherId�����û�ͷ�� ����
		PPUser publisher = ppUserService.select(newEssary.getPublisherId());
		newEssary.setPublisherName(publisher.getUserName());
		newEssary.setPublisherImg(publisher.getUserImg());
		//����contentid��������
		lgp.vo.Content content = contentService.selectById(e.getContentId());
		newEssary.setMes(content.getMes());
		newEssary.setPhoto(content.getPhoto());
		newEssary.setVideo(content.getVideo());

		//���ҹ��ڸ����ӵ���������
		//ͨ������id����������id����������id
		List<Comment> comments=commentService.selectByEs(esId);
		List<NewComment> newComments = new ArrayList<>();
		for( Comment c:comments)
		{
			NewComment newComment = new NewComment();
			//ͨ��������id��ȡ���� ͷ��
			PPUser ppUser = ppUserService.select(c.getCommenterId());
			//ͨ����������id��ȡ�����mes photo video
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