package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.payload.UserDto;
import uz.pdp.warehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Result add(@RequestBody UserDto userDto){
        return userService.add(userDto);
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.edit(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return userService.delete(id);
    }
}
