package com.zfnotes.controller;

import com.zfnotes.entities.User;
import com.zfnotes.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户的控制层
 */
@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public String list(Model model, User user) {
        List<User> users = userMapper.getUser(user);

        model.addAttribute("users", users);
        //搜索之后回显搜索关键字
        model.addAttribute("username", user.getUsername());
        return "user/list";
    }

    /**
     * 如果没有传入type则进入view页，
     * 如果传入type=update则进入update页
     */
    @GetMapping("/user/{uid}")
    public String view(Model model, @PathVariable("uid") Integer uid,
                       @RequestParam(value = "type", defaultValue = "view") String type) {
        User user = userMapper.getUserById(uid);
        model.addAttribute("user", user);
        return "user/"+type;
    }


    @PutMapping("/user")
    public String update(User user){
        userMapper.updateUser(user);
        return "redirect:/users";
    }

    /**
     * 访问添加页面
     * @return
     */
    @GetMapping("/user")
    public String addView(Model model){
        return "user/add";
    }

    /**
     * 添加
     * @return
     */
    @PostMapping("/user")
    public String addBill(User user){
        userMapper.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/user/{uid}")
    public String deleteBill(@PathVariable("uid") Integer uid){
        userMapper.deleteUser(uid);
        return "redirect:/users";
    }

    @GetMapping("/user/pwd")
    public String toChangePwd(){
        return "main/password";
    }

    @ResponseBody
    @GetMapping("/user/pwd/{oldPwd}")
    public Boolean checkOldPwd(@PathVariable("oldPwd") String oldPwd, HttpSession session){
        User user = (User)session.getAttribute("user");
        String password = user.getPassword();
        if (password.equals(oldPwd)){
            return true;
        }
        return false;
    }


    @PostMapping("/user/pwd")
    public String changePwd(HttpSession session, String password){
        User user = (User)session.getAttribute("user");
        user.setPassword(password);
        userMapper.updateUser(user);
        // return "redirect:/logout";
        return "redirect:/index";
    }
}
