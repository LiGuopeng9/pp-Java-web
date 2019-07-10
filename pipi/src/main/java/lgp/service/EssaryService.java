package lgp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import lgp.dao.*;
import lgp.vo.Essary;

@Service
public class EssaryService {
	@Autowired
	EssaryMapper essaryMapper;

	public void insert(Essary essary) {
		essaryMapper.insert(essary);
	}

	public int select(Essary essary) {
		return essaryMapper.select(essary);
	}

	public List<Essary> selectAll() {
		return essaryMapper.selectAll();
	}

	public Essary selectById(int esId) {
		return essaryMapper.selectById(esId);
	}
	public List<Essary> selectByPublisherId(String publisherId)
	{
		return essaryMapper.selectByPublisherId(publisherId);
	}
	
	
	
	
	
	
	public void updateStar(int starnum, int esId) {
		essaryMapper.updateStar(starnum, esId);
	}

	public void updateDislike(int dislikenum, int esId) {
		essaryMapper.updateDislike(dislikenum, esId);
	}

	public void updateComment(int commentnum, int esId) {
		essaryMapper.updateComment(commentnum, esId);
	}



}
