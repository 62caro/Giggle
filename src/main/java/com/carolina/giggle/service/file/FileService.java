package com.carolina.giggle.service.file;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileInfo upload(MultipartFile resource) throws IOException;
}
