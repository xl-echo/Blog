package com.echo.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author echo
 * @date 2020/2/8 14:42
 **/
@RestController
@RequestMapping("/downloadController")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DownloadController {

    /**
     * 下载书籍
     *
     * @param resp     响应
     * @param req      请求
     * @param fileName 文件名称
     */
    @RequestMapping(value = "/downPdf", method = RequestMethod.GET)
    public void downloadExcel(@RequestParam(value = "fileName") String fileName,
                              HttpServletRequest req,
                              final HttpServletResponse resp) throws IOException {
        File file = ResourceUtils.getFile("classpath:template/" + fileName + ".pdf");

        if (!file.exists()) {
            resp.reset();
            resp.setContentType("text/plain;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write("error: file not exist! 文件不存在");
            writer.flush();
            return;
        }
        long length = file.length();
        long start = 0;
        resp.reset();
        resp.setHeader("Accept-Ranges", "byte");
        String range = req.getHeader("Range");
        String mtype = new MimetypesFileTypeMap().getContentType(fileName);
        resp.setHeader("Content-Type", mtype);
        resp.setHeader("Content-Length", new Long(length).toString());
        if (range != null) {
            resp.setHeader("Content-Range", "bytes " + new Long(start).toString() + "-" + new Long(start + length - 1).toString() + "/" + new Long(file.length()).toString());
        }
        resp.setContentType(mtype);
        resp.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes(), "utf-8"));
        long k = 0;
        int ibuffer = 65536;
        byte[] bytes = new byte[ibuffer];
        FileInputStream fileinputstream = new FileInputStream(file);
        try {
            OutputStream os = resp.getOutputStream();
            while (k < length) {
                int j = fileinputstream.read(bytes, 0, (int) (length - k < ibuffer ? length - k : ibuffer));
                if (j < 1) {
                    break;
                }
                os.write(bytes, 0, j);
                k += j;
            }
            os.flush();
        } catch (Exception e) {
            throw new RuntimeException("文件下载异常,{}", e);
        } finally {
            fileinputstream.close();
        }
    }

}
