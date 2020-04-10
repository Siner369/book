package com.siner.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.siner.entity.Book;
import com.siner.entity.Manager;
import com.siner.entity.User;
import com.siner.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller("BookController")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("findAllBook")
    public String findAll(Model model, HttpSession session) {

        List<Book> list = new ArrayList<Book>();
        list = bookService.findAllBook();
        System.out.println(list);
        model.addAttribute("books",list);
        return "admin/product_list";
    }
    //执行管理员登录方法
    @RequestMapping(value = "searchByName", method = {RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    List searchByName(String bookname,HttpSession session) {
        System.out.println(bookname);
        List<Book> list = bookService.searchBookByName(bookname);
        System.out.println("controller 关键字结果输出："+list);

        return list;
    }

    @RequestMapping("addPage")
    public String addPage() {

        return "admin/product_add";
    }


    @RequestMapping(value = "addBook", method = {RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String addBook(@RequestBody Book book) {
        System.out.println(book);
        boolean addFlag = bookService.addBook(book);
        JSONObject json = new JSONObject();
        if (addFlag) {
            json.put("add","success");
            return json.toString();
        } else {
            json.put("add","error");
            return json.toString();
        }
    }


    //  添加新书 添加图片
    //图片上传测试
    @ResponseBody
    @RequestMapping("/addBook_Pic")
    public Map upload(MultipartFile file, HttpServletRequest request){

        String path = "";
        String prefix="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String pathRoot = System.getProperty("user.dir");// 获取项目物理地址
                System.out.println(pathRoot);
                pathRoot += "/src/main/resources/";
                System.out.println("物理路径："+pathRoot);
                /* String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);*/
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                String contentType = file.getContentType();
                System.out.println("类型："+contentType);
                String ImgName = contentType.substring(contentType.indexOf("/")+1);
                path = "static/upload/"+uuid+"."+ImgName;
                String srcPath = "/upload/"+uuid+"." + contentType;
                File files=new File(path);
                //打印查看上传路径
                System.out.println(path);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(new File(pathRoot+path));
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src",srcPath);
                System.out.println(map2.get("src"));
                return map;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }





}
