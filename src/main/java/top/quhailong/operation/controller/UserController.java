package top.quhailong.operation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.quhailong.operation.annotation.OperationLog;
import top.quhailong.operation.entity.User;
import top.quhailong.operation.service.IUserService;

/**
 * 用户操作服务
 *
 * @author: quhailong
 * @date: 2020/7/26
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    @OperationLog(operationDesc = "查询用户", operationModule = "user", operationType = "query")
    public String getUserInfo() {
        return "aaa";
    }

    @RequestMapping(value = "user", method = RequestMethod.PUT)
    @OperationLog(operationDesc = "更新用户", operationModule = "user", operationType = "update")
    public String updateUserInfo(User user) throws InterruptedException {
        userService.updateUserHandle(user);
        return "aaa";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    @OperationLog(operationDesc = "新增用户", operationModule = "user", operationType = "create")
    public String saveUserInfo(@RequestBody User user) {
        userService.saveUserHandle(user);
        return "aaa";
    }
}
