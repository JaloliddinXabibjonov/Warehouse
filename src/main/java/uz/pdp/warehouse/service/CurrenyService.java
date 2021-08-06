package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrenyService {

    @Autowired
    CurrencyRepository currencyRepository;

            //CREATE
    public Result addCurrency(Currency currency){
        boolean existsCurrencyByName = currencyRepository.existsCurrencyByName(currency.getName());
        if (existsCurrencyByName){
                Currency currencyRepositoryAllByName = currencyRepository.findAllByName(currency.getName());
                if (currencyRepositoryAllByName.isActive())
                    return new Result("Bu valyuta avval kiritilgan", false);
            currencyRepositoryAllByName.setActive(true);
                currencyRepository.save(currencyRepositoryAllByName);
                return new Result("Valyuta mavaffaqiyatli saqlandi", true);
        }
        currencyRepository.save(currency);
        return new Result("Valyuta muvaffaqiyatli saqlandi", true);
    }

            //GET ALL CURRENCY
    public List<Currency> getAll(){
        return currencyRepository.findAll();
    }

            //GET BY ID
    public Result getOne(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent())
            return new Result("Muvaffaqiyatli bajarildi", true, optionalCurrency.get());
        return new Result("Bunday valyuta topilmadi",false);
    }

            //UPDATE
    public Result edit(Integer id, Currency currency){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()){
            Currency editingCurrency = optionalCurrency.get();
            if (!editingCurrency.getName().equals(currency.getName())) {
                boolean byName = currencyRepository.existsCurrencyByName(currency.getName());
                if (byName)
                    return new Result("Bu valyuta avval kiritilgan", false);
                editingCurrency.setName(currency.getName());
            }

        }
        currencyRepository.save(currency);
        return new Result("Muvaffaqiyatli tahrirlandi", true);

    }

            //DELETE
    public Result delete(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()){
            currencyRepository.deleteById(id);
            return new Result("Valyuta o`chirildi", true);
        }
        return new Result("Valyuta topilmadi", false);
    }
}
