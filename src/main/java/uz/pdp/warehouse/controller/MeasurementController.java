package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.MeasurementRepository;
import uz.pdp.warehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    MeasurementService measurementService;
    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement){
        return measurementService.addMeasurementService(measurement);
    }
    @GetMapping
    public List<Measurement> getMeasurement(){
        return  measurementService.getMeasurementService();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        return measurementService.getByIdService(id);
    }
    @PutMapping("/{id}")
    public Result editMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement){
        return measurementService.editMeasurementService(id, measurement);
    }
    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        return measurementService.deleteMeasurementService(id);
    }

}
