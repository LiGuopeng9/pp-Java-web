package lgp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lgp.dao.*;
import lgp.vo.Content;
import lgp.vo.PPUser;

@Service
public class ContentService {
	@Autowired
	ContentMapper contentMapper;

	public void insert(Content content) {
		contentMapper.insert(content);
	}
	public int  select(Content content) {
		return contentMapper.select(content);
	}
	public Content selectById(int contentId) {
		return contentMapper.selectById(contentId);
	}
//	public PPUser select( String userid) {
//		return ppUserMapper.select(userid);
//		
//	}

}
