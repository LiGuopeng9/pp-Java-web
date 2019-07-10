package lgp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import lgp.dao.*;
import lgp.vo.Comment;
import lgp.vo.Essary;

@Service
public class CommentService {
	@Autowired
	CommentMapper commentMapper;
	public List<Comment> selectByEs(int esId) {
		return commentMapper.selectByEs(esId);
	}
	public void insert(Comment comment) {
		commentMapper.insert(comment);
	}
	public List<Integer> selectByCommenterId(String userId)
	{
		return commentMapper.selectByCommenterId(userId);
	}
}
