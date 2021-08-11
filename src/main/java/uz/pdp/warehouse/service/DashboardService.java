package uz.pdp.warehouse.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.*;
import uz.pdp.warehouse.payload.DashboardDto;
import uz.pdp.warehouse.payload.DashboardInputProductDto;
import uz.pdp.warehouse.payload.DashboardOutputProductDto;
import uz.pdp.warehouse.repository.InputProductRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.OutputProductRepository;
import uz.pdp.warehouse.repository.OutputRepository;
import uz.pdp.warehouse.entity.InputProduct;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class DashboardService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;

    Integer timeSetByAdmin=3;

    //GET GENERAL INFO
    public DashboardDto getGeneralInfo() {

        DashboardDto dashboardDto = new DashboardDto();

        List<InputProduct> inputProducts = new ArrayList<>();
        List<DashboardInputProductDto> dashboardInputProductDtoList = new ArrayList<>();

        LocalDate date = LocalDate.now(ZoneId.of("Asia/Tashkent"));
        Timestamp start = Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIN));
        Timestamp end = Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MAX));

        //INPUT INPUT PRODUCT ALL INFORMATION START
        //GET ALL INPUTS OF CURRENT DAY
        List<Input> allInputOfDay = inputRepository.findAllByDateBetween(start, end);
        for (Input input : allInputOfDay) {
            //GET ALL INPUT PRODUCTS OF CURRENT INPUT
            List<InputProduct> allInputProductOfInput = inputProductRepository.findAllByInputId(input.getId());
            if (allInputProductOfInput != null) {
                inputProducts.addAll(allInputProductOfInput);
            }
        }

        for (InputProduct inputProduct : inputProducts) {
            Product product = inputProduct.getProduct();
            Double price = inputProduct.getPrice() * inputProduct.getAmount();
            //CHECK INPUT PRODUCT ALREADY EXIST IN LIST
            for (int i = 0; i < dashboardInputProductDtoList.size(); i++) {
                if (dashboardInputProductDtoList.get(i).getName().equals(product.getName())) {
                    DashboardInputProductDto currentDashboardDto = dashboardInputProductDtoList.get(i);
                    currentDashboardDto.setAmount(currentDashboardDto.getAmount() + inputProduct.getAmount());
                    currentDashboardDto.setPrice(currentDashboardDto.getPrice() + price);
                    currentDashboardDto.setHowManyTimes(currentDashboardDto.getHowManyTimes() + 1);
                    dashboardInputProductDtoList.set(i, currentDashboardDto);
                    break;
                }
                //LAST INPUT PRODUCT
                if (i == (dashboardInputProductDtoList.size() - 1)) {
                    dashboardInputProductDtoList.add(new DashboardInputProductDto(1, product.getName(), inputProduct.getAmount(), price));
                    break;
                }
            }
            //FIRST ADD INFO
            if (dashboardInputProductDtoList.isEmpty()) {
                dashboardInputProductDtoList.add(new DashboardInputProductDto(1, product.getName(), inputProduct.getAmount(), price));
            }
        }
        //SORT BY PRICE DESCENDING
        dashboardInputProductDtoList.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
        //INPUT INPUT PRODUCT ALL INFORMATION END


        //TOP OUTPUT PRODUCT ALL INFORMATION START
        ArrayList<OutputProduct> outputProducts = new ArrayList<>();
        ArrayList<DashboardOutputProductDto> dashboardOutputProductDtoList = new ArrayList<>();

        //GET ALL OUTPUT CURRENT DAY
        List<Output> allOutputOfDay = outputRepository.findAllByDateBetween(start, end);
        for (Output output : allOutputOfDay) {
            //GET ALL OUTPUT PRODUCT CURRENT OUTPUT
            List<OutputProduct> allOutputProductOfOutput = outputProductRepository.findAllByOutputId(output.getId());
            if (allOutputProductOfOutput != null) {
                outputProducts.addAll(allOutputProductOfOutput);
            }
        }

        //ADD ALL OUTPUT PRODUCT TO LIST
        for (OutputProduct outputProduct : outputProducts) {
            Product product = outputProduct.getProduct();
            Double price = outputProduct.getPrice() * outputProduct.getAmount();
            //CHECK OUTPUT PRODUCT ALREADY EXIST IN LIST
            for (int i = 0; i < dashboardOutputProductDtoList.size(); i++) {
                if (dashboardOutputProductDtoList.get(i).getName().equals(product.getName())) {
                    DashboardOutputProductDto currentDashboardOutputDto = dashboardOutputProductDtoList.get(i);
                    currentDashboardOutputDto.setPrice(currentDashboardOutputDto.getPrice() + price);
                    currentDashboardOutputDto.setAmount(currentDashboardOutputDto.getAmount() + outputProduct.getAmount());
                    currentDashboardOutputDto.setHowManyTimes(currentDashboardOutputDto.getHowManyTimes() + 1);
                    break;
                }
                if (i == (dashboardOutputProductDtoList.size() - 1)) {
                    dashboardOutputProductDtoList.add(new DashboardOutputProductDto(1, product.getName(), outputProduct.getAmount(), price));
                    break;
                }
            }
            //FIRST OUTPUT PRODUCT ADD TO LIST
            if (dashboardOutputProductDtoList.isEmpty()) {
                dashboardOutputProductDtoList.add(new DashboardOutputProductDto(1, product.getName(), outputProduct.getAmount(), price));
            }
        }

        //SORT BY HOW MANY TIMES DESCENDING
        dashboardOutputProductDtoList.sort((o1, o2) -> o2.getHowManyTimes().compareTo(o1.getHowManyTimes()));
        //TOP OUTPUT PRODUCT ALL INFORMATION END


        //CHECK EXPIRE DATE START
        Date beforeDate=Date.valueOf(date.plusDays(timeSetByAdmin));
        List<InputProduct> allInputProductWithExpireDate = inputProductRepository.findAllByExpireDateBeforeAndActive(beforeDate,true);
        //CHECK EXPIRE DATE END


        dashboardDto.setOutputProductDtos(dashboardOutputProductDtoList);
        dashboardDto.setInputProductDtos(dashboardInputProductDtoList);
        dashboardDto.setProductListWithExpireDate(allInputProductWithExpireDate.size());

        return dashboardDto;
    }


    //GET EXPIRE DATE LESS THAN ANY DATE
    public List<InputProduct> getAllExpireDateLess(){
        Date beforeDate =Date.valueOf(LocalDate.now(ZoneId.of("Asia/Tashkent")).plusDays(timeSetByAdmin));
        return inputProductRepository.findAllByExpireDateBeforeAndActive(beforeDate,true);
    }
}