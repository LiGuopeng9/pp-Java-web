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
public class MyController {
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
	@RequestMapping("selectmy")
	public ModelAndView selectMy(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView();
		// ��ȡ����
		String option = req.getParameter("option");
		// ��ȡ�û�
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		List<Essary> essaries = new ArrayList<>();
		if (option.equals("publish")) {
			
			System.out.println("publish");
			// ͨ������������������
			essaries = essaryService.selectByPublisherId(userId);
		} else if (option.equals("star")) {
			System.out.println("star");
			// ��ͨ�������ղ� ��ȡesid
			List<Integer> esIds = starService.selectByStarerId(userId);

			// ͨ��esid ��ȡessary
			for (int s : esIds) {
				essaries.add(essaryService.selectById(s));
			}
		} else {
			System.out.println("comment");
			// ͨ���û�����������
			List<Integer> esIds = commentService.selectByCommenterId(userId);
			// ͨ��esid ��ȡessary
			for (int s : esIds) {
				essaries.add(essaryService.selectById(s));
			}
		}

		
		List<NewEssary> newEssaries = new ArrayList<>();
		for (Essary e : essaries) {
			NewEssary newEssary = new NewEssary();
			newEssary.setPublisherId(e.getPublisherId());
			newEssary.setCommentnum(e.getCommentnum());
			newEssary.setDislikenum(e.getDislikenum());
			newEssary.setEsId(e.getEsId());
			newEssary.setKind(e.getKind());
			newEssary.setStarnum(e.getStarnum());

			// ����publisherId�����û�ͷ�� ����
			PPUser publisher = ppUserService.select(newEssary.getPublisherId());
			newEssary.setPublisherName(publisher.getUserName());
			newEssary.setPublisherImg(publisher.getUserImg());

			// ����contentid��������
			Content content = contentService.selectById(e.getContentId());

			newEssary.setMes(content.getMes());
			newEssary.setPhoto(content.getPhoto());
			newEssary.setVideo(content.getVideo());

			// ��ӵ�list
			newEssaries.add(newEssary);

			System.out.println(newEssary.toString());
		}

		mv.addObject("newEssaries", newEssaries);

		mv.setViewName("/pages/my2");
		return mv;
	}
}
