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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lgp.service.ContentService;
import lgp.service.EssaryService;
import lgp.service.PPUserService;
import lgp.vo.Content;
import lgp.vo.Essary;
import lgp.vo.NewEssary;
import lgp.vo.PPUser;

@Controller
public class UserController {
	@Autowired
	PPUserService ppUserService;
	@Autowired
	EssaryService essaryService;
	@Autowired
	ContentService contentService;

	// �û���¼
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView();
		// ��֤�û�
		String userId = req.getParameter("userid");
		String userpwd = req.getParameter("userpwd");
		// �ж��û��Ƿ����
		if (ppUserService.select(userId) != null) {
			PPUser ppUser = ppUserService.select(userId);
			// �ж������Ƿ���ȷ
			if (ppUser.getUserPwd().equals(userpwd)) {
				// ��ȷ
				// �����Ñ����ǳƣ�ͷ�񣬻���id
				// ��session����
				HttpSession session = req.getSession();
				session.setAttribute("userId", ppUser.getUserId());
				session.setAttribute("userName", ppUser.getUserName());
				session.setAttribute("userImg", ppUser.getUserImg());
				mv.setViewName("redirect:toMain");
			} else {
				// ����ȷ
				// ���ص�½����
				mv.addObject("pwdIsWrong", "�û����������벻��ȷ�����������룡");
				mv.setViewName("/pages/login");
			}
		}
		return mv;

	}

	// �û���ת��ע�����
	@RequestMapping("toregister")
	public ModelAndView toRegister() {
		System.out.println("toregister------------------");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pages/register");
		return mv;
	}

	// �û�ע��
	@RequestMapping("register")
	public ModelAndView register(@RequestParam("userimg") MultipartFile userImg, HttpServletRequest req,
			HttpServletResponse resp) throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		// ��ȡ����ֵ
		String userId = req.getParameter("userid");
		String userName = req.getParameter("username");
		String pwd = req.getParameter("userpwd");

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
		String realFileName = userImg.getOriginalFilename();
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
		userImg.transferTo(new File(filePath));
		// ͬ�����浽����
		// userImg.transferTo(new File(dektopPath));
		// 4.���ñ�ʾ�ļ���src
		String userImgSrc = req.getContextPath() + File.separator + "upload" + File.separator + fileName;

		// ���뵽���ݿ�
		PPUser ppUser = new PPUser(userId, userName, pwd, userImgSrc);
		ppUserService.insert(ppUser);

		mv.setViewName("pages/login");
		return mv;
	}

	@RequestMapping("toMain")
	public ModelAndView toMain(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		// �ж��Ƿ��¼
		// �Ѿ���¼��ֱ����ת
		if (userId != null) {
			// �������ݿ����е�essary
			List<Essary> essaries = essaryService.selectAll();
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

			mv.setViewName("/pages/main");
			return mv;

		} else {
			mv.setViewName("pages/login");
		}

		return mv;
	}

	@RequestMapping("tochangemy")
	public ModelAndView toChangeMy() {
		System.out.println("tochangemy");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/pages/changemy");
		return mv;
	}

	// �û�ע��
	@RequestMapping("changemy")
	public ModelAndView changeMy(@RequestParam("userimg") MultipartFile userImg, HttpServletRequest req,
			HttpServletResponse resp) throws IllegalStateException, IOException {
		System.out.println("changemy");
		ModelAndView mv = new ModelAndView();
		// ��ȡ����ֵ
		HttpSession session = req.getSession();

		String userId = (String) session.getAttribute("userId");
		String userName = req.getParameter("username");
		String pwd = req.getParameter("userpwd");
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
		String realFileName = userImg.getOriginalFilename();
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
		userImg.transferTo(new File(filePath));
		// ͬ�����浽����
		// userImg.transferTo(new File(dektopPath));
		// 4.���ñ�ʾ�ļ���src
		String userImgSrc = req.getContextPath() + File.separator + "upload" + File.separator + fileName;

		// ���µ����ݿ�
		PPUser ppUser = new PPUser(userId, userName, pwd, userImgSrc);

		ppUserService.update(ppUser);

		mv.setViewName("pages/login");
		return mv;
	}

}
