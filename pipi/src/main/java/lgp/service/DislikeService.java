package lgp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import lgp.dao.*;
import lgp.vo.Essary;

@Service
public class DislikeService {
	@Autowired
	DislikeMapper dislikeMapper;
	public void insert(String userId, int esId)
	{
		dislikeMapper.insert(userId, esId);
	}
	
	public String  select(String userId,int esId)
	{
		return dislikeMapper.select(userId,esId);
	}
	
}
