package uz.pdp.warehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.entity.AttachmentContent;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.AttachmentContentRepository;
import uz.pdp.warehouse.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

                    //CREATE
    @SneakyThrows
    public Result addAttachmentService(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file!=null) {
            Attachment attachment = new Attachment();
            attachment.setName(file.getName());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            Attachment savedAttachment = attachmentRepository.save(attachment);
            byte[] bytes = file.getBytes();

            AttachmentContent attachmentContent=new AttachmentContent(bytes, savedAttachment);
            attachmentContentRepository.save(attachmentContent);
            return new Result("Fayl saqlandi", true);
        }
        return new Result("Fayl saqlanmadi!!!", false);
    }

                //GET ALL
    public List<Attachment> getAllFilesService(){
        return attachmentRepository.findAll();
    }

                //READ BY ID
    @SneakyThrows
    public Result getFileByIdService(Integer id, HttpServletResponse response){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("Bunday fayl topilmadi", false);
        Attachment attachment = optionalAttachment.get();
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(attachment.getId());
        if (!optionalAttachmentContent.isPresent())
            return new Result("Fayl contenti topilmadi", false);
        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        response.setHeader("Content-Disposition", "attachment; fileName=\""+attachment.getName()+"\"");
        response.setContentType(attachment.getContentType());
        FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
        return new Result("Fayl muvaffaqiyatli yuklab olindi", true);

    }

}
