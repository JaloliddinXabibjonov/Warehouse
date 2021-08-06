package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.ClientDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping
    public Result add(@RequestBody ClientDto clientDto){
        return clientService.addService(clientDto);
    }

    @GetMapping
    public List<Client> getAll(){
        return clientService.getAllService();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        return clientService.getByIdService(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody ClientDto clientDto){
        return clientService.editService(clientDto, id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return clientService.deleteService(id);
    }
}
