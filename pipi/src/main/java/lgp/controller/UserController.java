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

	// 用户登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView();
		// 验证用户
		String userId = req.getParameter("userid");
		String userpwd = req.getParameter("userpwd");
		// 判断用户是否存在
		if (ppUserService.select(userId) != null) {
			PPUser ppUser = ppUserService.select(userId);
			// 判断密码是否正确
			if (ppUser.getUserPwd().equals(userpwd)) {
				// 正确
				// 返回用戶的昵称，头像，还有id
				// 用session保存
				HttpSession session = req.getSession();
				session.setAttribute("userId", ppUser.getUserId());
				session.setAttribute("userName", ppUser.getUserName());
				session.setAttribute("userImg", ppUser.getUserImg());
				mv.setViewName("redirect:toMain");
			} else {
				// 不正确
				// 返回登陆界面
				mv.addObject("pwdIsWrong", "用户名或者密码不正确，请重新输入！");
				mv.setViewName("/pages/login");
			}
		}
		return mv;

	}

	// 用户跳转到注册界面
	@RequestMapping("toregister")
	public ModelAndView toRegister() {
		System.out.println("toregister------------------");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pages/register");
		return mv;
	}

	// 用户注册
	@RequestMapping("register")
	public ModelAndView register(@RequestParam("userimg") MultipartFile userImg, HttpServletRequest req,
			HttpServletResponse resp) throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		// 获取输入值
		String userId = req.getParameter("userid");
		String userName = req.getParameter("username");
		String pwd = req.getParameter("userpwd");

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
		String realFileName = userImg.getOriginalFilename();
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
		userImg.transferTo(new File(filePath));
		// 同步保存到电脑
		// userImg.transferTo(new File(dektopPath));
		// 4.设置表示文件的src
		String userImgSrc = req.getContextPath() + File.separator + "upload" + File.separator + fileName;

		// 插入到数据库
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

		// 判断是否登录
		// 已经登录，直接跳转
		if (userId != null) {
			// 查找数据库所有的essary
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

				// 根据publisherId查找用户头像 名称
				PPUser publisher = ppUserService.select(newEssary.getPublisherId());
				newEssary.setPublisherName(publisher.getUserName());
				newEssary.setPublisherImg(publisher.getUserImg());

				// 根据contentid查找内容
				Content content = contentService.selectById(e.getContentId());

				newEssary.setMes(content.getMes());
				newEssary.setPhoto(content.getPhoto());
				newEssary.setVideo(content.getVideo());

				// 添加到list
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

	// 用户注册
	@RequestMapping("changemy")
	public ModelAndView changeMy(@RequestParam("userimg") MultipartFile userImg, HttpServletRequest req,
			HttpServletResponse resp) throws IllegalStateException, IOException {
		System.out.println("changemy");
		ModelAndView mv = new ModelAndView();
		// 获取输入值
		HttpSession session = req.getSession();

		String userId = (String) session.getAttribute("userId");
		String userName = req.getParameter("username");
		String pwd = req.getParameter("userpwd");
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
		String realFileName = userImg.getOriginalFilename();
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
		userImg.transferTo(new File(filePath));
		// 同步保存到电脑
		// userImg.transferTo(new File(dektopPath));
		// 4.设置表示文件的src
		String userImgSrc = req.getContextPath() + File.separator + "upload" + File.separator + fileName;

		// 更新到数据库
		PPUser ppUser = new PPUser(userId, userName, pwd, userImgSrc);

		ppUserService.update(ppUser);

		mv.setViewName("pages/login");
		return mv;
	}

}
