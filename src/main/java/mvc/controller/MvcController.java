package mvc.controller;

import mvc.entity.UserInfo;
import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/allen")
public class MvcController {
    @Autowired
    UserService userService;

    @RequestMapping(path="/login.do",method = RequestMethod.POST)
    public String login(@RequestParam("user")String user,@RequestParam("password")String password,Model model){
        String res = "";
        try {
            UserInfo userInfo = userService.login(user,password);
            if(userInfo == null){
                userInfo.setName(user);
                userInfo.setPassword("error");
                model.addAttribute("userInfo",userInfo);
                System.out.println(userInfo.toString());
                res = "login";
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }


}
