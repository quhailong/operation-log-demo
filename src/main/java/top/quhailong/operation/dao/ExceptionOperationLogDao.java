package top.quhailong.operation.dao;

import org.springframework.stereotype.Repository;
import top.quhailong.operation.entity.ExceptionOperationLogDO;
@Repository
public interface ExceptionOperationLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ExceptionOperationLogDO record);

    int insertSelective(ExceptionOperationLogDO record);

    ExceptionOperationLogDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExceptionOperationLogDO record);

    int updateByPrimaryKey(ExceptionOperationLogDO record);
}