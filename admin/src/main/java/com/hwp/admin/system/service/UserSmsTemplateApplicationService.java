package com.hwp.admin.system.service;

import com.hwp.common.model.userSmsTemplateApplication.bean.UserSmsTemplateApplication;

import java.util.List;
import java.util.Map;

public interface UserSmsTemplateApplicationService {

    void addUserSmsTemplateApplication(UserSmsTemplateApplication userSmsTemplateApplication);

    UserSmsTemplateApplication getUserSmsTemplateApplicationById(Integer id);

    void updateUserSmsTemplateApplication(UserSmsTemplateApplication userSmsTemplateApplication);

    List<UserSmsTemplateApplication> listUserSmsTemplateApplicationsByParams(Map<String, Object> params);

    int countUserSmsTemplateApplicationsByParams(Map<String, Object> params);
}