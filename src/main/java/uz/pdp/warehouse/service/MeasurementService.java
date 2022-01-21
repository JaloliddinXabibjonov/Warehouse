package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

                    //CREATE
    public Result addMeasurementService(Measurement measurement){
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName)
            return new Result("Ushbu o`lchov birligi mavjud", false);
        measurementRepository.save(measurement);
        return new Result("Muvaffaqiyatli qo`shildi", true);

    }
                    //READ
    public List<Measurement> getMeasurementService(){
        return measurementRepository.findAll();
    }

    //GET ONE BY ID
    public  Result getByIdService(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o`lchov birligi topilmadi", false);
        return new Result("Muvaffaqiyatli bajarildi", true, optionalMeasurement.get());
    }
                    //UPDATE
    public Result editMeasurementService(Integer id, Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o`lchov birligi mavjud emas", false);
        Measurement measurement1 = optionalMeasurement.get();
        measurement1.setName(measurement.getName());
        measurement1.setActive(measurement.isActive());
        measurementRepository.save(measurement1);
        return new Result("Muvaffaqiyatli tahrirlandi", true);

    }
                    //DELETE
    public Result deleteMeasurementService(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o`lchov birligi mavjud emas", false);
        measurementRepository.deleteById(id);
        return  new Result("Muvaffaqiyatli o`chirildi", true);
    }
}
