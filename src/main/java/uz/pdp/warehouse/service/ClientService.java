package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.ClientDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    //CREATE
    public Result addService(ClientDto clientDto){
        boolean existsByPhoneNumber = clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Bu raqam avval kiritilgan", false);
        Client client=new Client();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(client);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    //GET ALL
    public List<Client> getAllService(){
        return clientRepository.findAll();
    }

    //GET BY ID
    public Result getByIdService(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("Bunday xaridor mavjud emas", false);
        return new Result("Muvaffaqiyatli bajarildi", true, optionalClient.get());
    }

    //UPDATE
    public Result editService(ClientDto clientDto, Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("Bunday xaridor topilmadi", true);
        Client client = optionalClient.get();
        boolean existsByPhoneNumber = clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Bu raqam avval kiritilgan", false);
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(client);
        return new Result("Muvaffaqiyatli tahrirlandi", true);

    }

    //DELETE
    public Result deleteService(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("Bunday xaridor topilmadi", false);
        clientRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }

}
