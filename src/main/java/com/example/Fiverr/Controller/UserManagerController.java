package com.example.Fiverr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fiverr.Model.Users;
import com.example.Fiverr.Response.APIResponse;
import com.example.Fiverr.Service.UserService;

@RestController
@RequestMapping("/user-manager")
public class UserManagerController {
    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public APIResponse getAllUsers() {
        List<Users> Userlist = userService.getAllUsers();
        APIResponse response = new APIResponse(true, Userlist, "danh sach User đã được lấy thành công");
        return response;
    }

    @PutMapping("/sua/{id}")
    public APIResponse SuaUser(@PathVariable Long id, @RequestBody Users user) {
        try{
        Users UpdateUser = userService.getUserById(id);
        System.out.println("aaaaaaaaaaaaaa" + UpdateUser);
            if(UpdateUser==null) {
                APIResponse response = new APIResponse(false, null, "User khong co");
                return response;
            }else {
                if (user.getEmail() != null) {
                    UpdateUser.setEmail(user.getEmail());
                }
                userService.updateUser(id, UpdateUser);
                APIResponse response = new APIResponse(true, UpdateUser, "ok");
                return response;
            }
        }
        catch (Exception e) {
            APIResponse response = new APIResponse(false, e, null);
            return response;
        }
}
}
