package lgp.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;


import lgp.vo.*;


	public interface PPUserMapper {
		public void insert(PPUser ppUser);
		public PPUser select(String userid);
		public void update(PPUser ppUser);
	}
