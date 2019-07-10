package lgp.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import lgp.dao.*;
import lgp.vo.PPUser;

@Service
public class PPUserService {
	@Autowired
	PPUserMapper ppUserMapper;
	
	
	public void insert(PPUser ppUser) {
		ppUserMapper.insert(ppUser);		
	}

	public PPUser select( String userid) {
		return ppUserMapper.select(userid);
	}
	public void update(PPUser ppUser) {
		ppUserMapper.update(ppUser);
	}

}
