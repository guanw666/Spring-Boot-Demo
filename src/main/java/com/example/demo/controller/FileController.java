package com.example.demo.controller;

import com.example.demo.dto.FileDTO;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@Slf4j
public class FileController {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @PostMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(@RequestParam("editormd-image-file") MultipartFile file) {

        String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String fileType = split[split.length - 1];
        String fileName = UUID.randomUUID() + "." + fileType;
        try {
            File destFile = new File(fileUploadPath + fileName);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdir();
            }
            file.transferTo(destFile);
        } catch (IOException e) {
            log.error("file [{}] upload fail,{}", file.getOriginalFilename(), e);
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
        String fileReadPath = "/photos/" + fileName;

        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("上传成功");
        fileDTO.setUrl(fileReadPath);
        return fileDTO;
    }
}
