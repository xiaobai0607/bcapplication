package com.bootcamp.utils.file;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class JavaFile {
	public String uploadFile(MultipartFile file, String fileName,HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, Exception {
        // 参数列表
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("file", file);
        savePic(file.getInputStream(),fileName,request);

        //请求接口
        String apiReturnStr = "";//apiHttpRequest(map, API_HOST_URL + "/image/upload");

        return apiReturnStr;
    }

    private void savePic(InputStream inputStream,String fileName,HttpServletRequest request) {

        OutputStream os = null;
        try {
        	//取得根目录路径  
            String path =  request.getRealPath("/") ;
            System.out.println(path);
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() +File.separator+"image"+File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
