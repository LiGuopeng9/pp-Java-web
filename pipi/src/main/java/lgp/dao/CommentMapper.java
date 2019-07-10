package lgp.dao;
import java.util.List;


import org.apache.ibatis.annotations.Param;


import lgp.vo.*;


	public interface CommentMapper {
		
		public List<Comment> selectByEs(int esId);
		public void insert(Comment comment);
		public List<Integer> selectByCommenterId(String userId);
	}
