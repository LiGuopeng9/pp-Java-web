package lgp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import lgp.vo.*;

public interface StarMapper {
	public void insert(@Param("starerid") String starerid, @Param("esid") int esid);

	public String select(@Param("starerId") String starerId, @Param("esId") int esId);
	
	public List<Integer> selectByStarerId(String starerId);
}


