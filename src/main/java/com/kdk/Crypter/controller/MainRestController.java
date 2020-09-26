package com.kdk.Crypter.controller;

import com.kdk.Crypter.model.CrypterFileRequestModel;
import com.kdk.Crypter.model.CrypterTextRequestModel;
import com.kdk.Crypter.model.CrypterFileResponseModel;
import com.kdk.Crypter.service.CrypterTextService;
import com.kdk.Crypter.service.CrypterFileService;
import com.kdk.Crypter.utils.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController

public class MainRestController {

    @Autowired
    CrypterTextService crypterTextService;

    @Autowired
    CrypterFileService crypterFileService;

    @GetMapping(value = "/encrypt/text/")
    public ResponseEntity getEncryptedText(@ModelAttribute CrypterTextRequestModel crypterTextRequestModel){
        return ResponseEntity.ok().body(crypterTextService.encryptText(crypterTextRequestModel));
    }

    @GetMapping(value = "/decrypt/text/" )
    public ResponseEntity getDecryptedText(@ModelAttribute CrypterTextRequestModel crypterTextRequestModel){
        return ResponseEntity.ok().body(crypterTextService.decryptText(crypterTextRequestModel));
    }

8
    @PostMapping("/encrypt/file")
    public ResponseEntity getEncryptedFile(@ModelAttribute CrypterFileRequestModel crypterFileRequestModel) {
        String fileName = crypterFileService.encryptFile(crypterFileRequestModel.getFile(), KeyGenerator.generateKeyForAes128(crypterFileRequestModel.getUserKey()));
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/").path(fileName).toUriString();
        return  ResponseEntity.ok().body(new CrypterFileResponseModel(fileName, fileDownloadUri,
                crypterFileRequestModel.getFile().getContentType(), crypterFileRequestModel.getFile().getSize()));
    }

    @PostMapping("/decrypt/file")
    public ResponseEntity getDecryptedFile(@ModelAttribute CrypterFileRequestModel crypterFileRequestModel) {
        String fileName = crypterFileService.decryptFile(crypterFileRequestModel.getFile(), KeyGenerator.generateKeyForAes128(crypterFileRequestModel.getUserKey()));
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/").path(fileName).toUriString();
        return  ResponseEntity.ok().body(new CrypterFileResponseModel(fileName, fileDownloadUri,
                crypterFileRequestModel.getFile().getContentType(), crypterFileRequestModel.getFile().getSize()));
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = null;
        try {
            resource = crypterFileService.loadFileAsResource(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return (ResponseEntity<Resource>) ResponseEntity.notFound();
        }
        String contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
