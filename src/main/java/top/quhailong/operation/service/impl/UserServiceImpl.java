package top.quhailong.operation.service.impl;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.quhailong.operation.dao.UserInfoDao;
import top.quhailong.operation.entity.User;
import top.quhailong.operation.entity.UserInfoDO;
import top.quhailong.operation.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public void updateUserHandle(User user) throws InterruptedException {
        MDC.put("logContent", "用户更新");
        UserInfoDO userInfoDO = userInfoDao.selectByPrimaryKey(user.getId());
        userInfoDO.setUserName(user.getUserName());
        userInfoDO.setUserAge(user.getAge());
        userInfoDao.updateByPrimaryKey(userInfoDO);
        Thread.sleep(5000);
    }

    @Override
    @Transactional
    public void saveUserHandle(User user) {
        MDC.put("logContent", "用户新增");
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserAge(user.getAge());
        userInfoDO.setUserName(user.getUserName());
        userInfoDao.insert(userInfoDO);
        //System.out.println(1/0);  测试异常日志
    }
}
