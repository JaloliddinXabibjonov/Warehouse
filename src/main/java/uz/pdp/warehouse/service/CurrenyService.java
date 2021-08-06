package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.repository.CurrencyRepository;

@Service
public class CurrenyService {

    @Autowired
    CurrencyRepository currencyRepository;

}
