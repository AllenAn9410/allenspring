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
public class MvcController {
    @Autowired(required = false)
    public UserService userService;

    @RequestMapping(path = "/login.do", method = RequestMethod.POST)
    public String login(@RequestParam("user") String user, @RequestParam("password") String password, Model model) {
        String res = "";
        try {
            UserInfo userInfo = userService.login(user, password);
            if (isEmpty(userInfo.getPassword())) {
                userInfo.setName(user);
                userInfo.setPassword("error");
                model.addAttribute("userInfo", userInfo);
                System.out.println(userInfo.toString());
                res = "login";
            } else {
                model.addAttribute("userInfo", userInfo);
                res = "welcome";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("/index.do")
    public String index() {
        System.out.println("welcome");
        return "login";
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }


}
