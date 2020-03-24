package com.echo.blog.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author echo
 * @date 2020/2/8 14:48
 **/
public class ExcelUtils {

    public static void setResponse(String showName, String fileType, HttpServletResponse response)
            throws UnsupportedEncodingException {
        if (fileType.equalsIgnoreCase("DOC") || fileType.equalsIgnoreCase("DOCX")) {
            response.setContentType("application/msword");
        } else if (fileType.equalsIgnoreCase("PDF")) {
            response.setContentType("application/pdf");
        } else if (fileType.equalsIgnoreCase("TIF")) {
            response.setContentType("image/tiff");
        } else if (fileType.equalsIgnoreCase("XLS") || fileType.equalsIgnoreCase("XLSX")) {
            response.setContentType("application/vnd.ms-excel");
        } else if (fileType.equalsIgnoreCase("PFX")) {
            response.setContentType("pfx");
        } else {
            response.setContentType("application/x-download");
        }

        String codeFileName = URLEncoder.encode(showName, StandardCharsets.UTF_8.toString());
        codeFileName = codeFileName.replace("+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename=" + codeFileName);
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));
    }

}
