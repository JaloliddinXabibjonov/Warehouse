package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

                        //UPLOAD FILE
    @PostMapping("/uploadFile")
    public Result addFile(MultipartHttpServletRequest request){
        return attachmentService.addAttachmentService(request);
    }

                        // GET ALL FILES
    @GetMapping("/getAll")
    public List<Attachment> getAllFiles(){
        return attachmentService.getAllFilesService();
    }

                    //GET FILE BY ID
    @GetMapping("/{id}")
    public Result downloadFileById(@PathVariable Integer id, HttpServletResponse response){
        return attachmentService.getFileByIdService(id, response);
    }

}
