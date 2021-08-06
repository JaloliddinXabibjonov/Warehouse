package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.payload.UserDto;
import uz.pdp.warehouse.repository.UserRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    //CREATE
    public Result add(UserDto userDto){
        User user=new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Ushbu telefon raqami avval kiritilgan",false);
        user.setPhoneNumber(userDto.getPhoneNumber());
        Integer maxId = userRepository.maxId();
        if (maxId==null)
            maxId=1;
        else
            maxId+=1;
        user.setCode(maxId.toString());
        user.setPassword(userDto.getPassword());
        HashSet<Warehouse> warehouseHashSet=new HashSet<Warehouse>(warehouseRepository.findAllById(userDto.getWarehouseList()));
        user.setWarehouseSet(warehouseHashSet);
        userRepository.save(user);
        return new Result("User muvaffaqiyatli saqlandi",true);
    }

    //READ ALL
    public List<User> getAll(){
        return userRepository.findAll();
    }

    //GET ONE BY ID
    public Result getById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("Bunday foydalanuvchi mavjud emas", false);
        return new Result("Muvaffaqiyatli bajarildi", true, optionalUser.get());
    }

    //UPDATE
    public Result edit(Integer id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("Bunday foydalanuvchi topilmadi", false);
        User user = optionalUser.get();user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Ushbu telefon raqami avval kiritilgan",false);
        user.setPhoneNumber(userDto.getPhoneNumber());
        Integer maxId = userRepository.maxId();
        if (maxId==null)
            maxId=1;
        else
            maxId+=1;
        user.setCode(maxId.toString());
        user.setPassword(userDto.getPassword());
        HashSet<Warehouse> warehouseHashSet=new HashSet<>(warehouseRepository.findAllById(userDto.getWarehouseList()));
        user.setWarehouseSet(warehouseHashSet);
        userRepository.save(user);
        return new Result("User muvaffaqiyatli saqlandi",true);
    }

    //DELETE
    public Result delete(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("Bunday foydalanuvchi mavjud emas", false);
        userRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }
}
