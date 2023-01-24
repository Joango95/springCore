package com.joango.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpResponseUtils {

    static public HttpEntity<byte[]> generateHttpResponseForPdf(byte[] pdfByteArray){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);

        header.set(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=tickets.pdf");
        header.setContentLength(pdfByteArray.length);

        return new HttpEntity<byte[]>(pdfByteArray, header);
    }
}
