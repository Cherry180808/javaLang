package demo.springboot.mapper;

import demo.springboot.model.TestUserInfo;
import demo.springboot.model.TestUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestUserInfoMapper {
    long countByExample(TestUserInfoExample example);

    int deleteByExample(TestUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestUserInfo record);

    int insertSelective(TestUserInfo record);

    List<TestUserInfo> selectByExample(TestUserInfoExample example);

    TestUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TestUserInfo record, @Param("example") TestUserInfoExample example);

    int updateByExample(@Param("record") TestUserInfo record, @Param("example") TestUserInfoExample example);

    int updateByPrimaryKeySelective(TestUserInfo record);

    int updateByPrimaryKey(TestUserInfo record);
}