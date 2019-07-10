package lgp.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;


import lgp.vo.*;


	public interface ContentMapper {
		public void insert(Content content);
		public int select(Content content);
		public Content selectById(int contentId);
		
//		public int insert(User user);
//		public User select(@Param("userid")String userid,@Param("userpwd")String userpwd);
//		public int update(@Param("userid")String userid,@Param("userpwd")String userpwd,@Param("usernewpwd")String usernewpwd);
//		public List<Bookshop_Userage> bar();
//		public List<Bookshop_Gender> pie();
//		public List<Bookshop_Province> map();

	}
