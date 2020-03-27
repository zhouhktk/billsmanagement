package com.zfnotes.controller;

import com.zfnotes.entities.User;
import com.zfnotes.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author:zhoufeng
 * @Date:2020/3/22
 */
@Controller
public class LoginController {

    @Autowired
    private UserMapper userMapper;


    @PostMapping("/sign")
    public String login(HttpSession session, Model model, String username, String password) {

        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            User user = userMapper.getUserByUserName(username);
            if (user != null && user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                return "redirect:/index";
            }
        }
        model.addAttribute("msg", "用户名或密码错误");
        return "main/login";
    }

    /**
     * 退出登陆
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //清空session中的信息
        session.removeAttribute("user");
        //注销session
        session.invalidate();
        //返回登陆页
        return "redirect:/login";
    }
}
