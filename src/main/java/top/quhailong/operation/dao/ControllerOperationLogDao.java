package top.quhailong.operation.dao;

import org.springframework.stereotype.Repository;
import top.quhailong.operation.entity.ControllerOperationLogDO;
@Repository
public interface ControllerOperationLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ControllerOperationLogDO record);

    int insertSelective(ControllerOperationLogDO record);

    ControllerOperationLogDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ControllerOperationLogDO record);

    int updateByPrimaryKey(ControllerOperationLogDO record);
}