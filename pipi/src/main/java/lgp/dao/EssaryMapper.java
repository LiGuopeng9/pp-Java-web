package lgp.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;


import lgp.vo.*;


	public interface EssaryMapper {
		public void insert(Essary essary);
		public int select(Essary essary);
		public List<Essary> selectAll();
		public List<Essary> selectByPublisherId(String publisherId);
		

		public Essary selectById(int esId);
		public void updateStar(@Param("starnum") int starnum,@Param ("esId") int esId);
		public void updateDislike(@Param("dislikenum") int dislikenum,@Param ("esId") int esId);
		public void updateComment(@Param("commentnum") int commentnum,@Param ("esId") int esId);
		
		
		
//		public int insert(User user);
//		public User select(@Param("userid")String userid,@Param("userpwd")String userpwd);
//		public int update(@Param("userid")String userid,@Param("userpwd")String userpwd,@Param("usernewpwd")String usernewpwd);
//		public List<Bookshop_Userage> bar();
//		public List<Bookshop_Gender> pie();
//		public List<Bookshop_Province> map();

	}
