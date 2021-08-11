package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.payload.DashboardDto;
import uz.pdp.warehouse.service.DashboardService;

import java.util.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;

    @GetMapping
    public DashboardDto get(){
        return dashboardService.getGeneralInfo();
    }

    @GetMapping("/expireDateLess")
    public List<InputProduct> getAllExpireDateNear(){
        return dashboardService.getAllExpireDateLess();
    }


}
