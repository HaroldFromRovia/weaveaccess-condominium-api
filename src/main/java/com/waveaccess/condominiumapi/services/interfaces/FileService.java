package com.waveaccess.condominiumapi.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {

    String save(MultipartFile multipartFile);

    Resource download(String path);

}
