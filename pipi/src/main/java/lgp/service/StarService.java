package lgp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lgp.dao.*;
import lgp.vo.Essary;

@Service
public class StarService {
	@Autowired
	StarMapper starMapper;
	public void insert(String starerid, int esid)
	{
		starMapper.insert(starerid, esid);
	}
	public String select(@Param("starerId") String starerId, @Param("esId") int esId)
	{
		return starMapper.select(starerId, esId);
	}
	public List<Integer> selectByStarerId(String starerId){
		return starMapper.selectByStarerId(starerId);
	}


}
