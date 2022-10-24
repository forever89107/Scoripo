package com.my.core.util.file;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private long size;
}
