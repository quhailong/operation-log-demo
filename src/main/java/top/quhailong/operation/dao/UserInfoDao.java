package top.quhailong.operation.dao;

import org.springframework.stereotype.Repository;
import top.quhailong.operation.entity.UserInfoDO;
@Repository
public interface UserInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    UserInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfoDO record);

    int updateByPrimaryKey(UserInfoDO record);
}