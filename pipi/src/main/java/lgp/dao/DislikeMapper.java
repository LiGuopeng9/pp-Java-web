package lgp.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;


import lgp.vo.*;


	public interface DislikeMapper {
		public void insert(@Param("userId")String userId, @Param("esId") int esId);
		public String  select(@Param("userId")String userId, @Param("esId") int esId);
	}
	
	
