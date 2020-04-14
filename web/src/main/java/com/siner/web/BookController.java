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

import static com.siner.util.FileUtils.deleteDir;

@Controller("BookController")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("findAllBook")
    public String findAll(Model model, HttpSession session){
        List<Book> list = new ArrayList<Book>();
        list = bookService.findAllBook();
        System.out.println(list);
        model.addAttribute("books",list);
        return "admin/product_list";
    }



    @RequestMapping(value = "searchByName", method = {RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    List searchByName(String bookname) {
        System.out.println(bookname);
        List<Book> list = bookService.searchBookByName(bookname);
        System.out.println("controller 关键字结果输出："+list);
        return list;
    }

    @GetMapping(value = "keyWordSearch")
    public String keyWordSearch(String bname,Model model){
        List<Book> list = bookService.searchBookByName(bname);
        System.out.println("controller 类型结果输出："+list);
        model.addAttribute("searchList",list);
        model.addAttribute("world","hello");
        return "bookstore/product";
    }

    @GetMapping(value = "TypeSearch")
    public String TypeSearch(String bookType,Model model){
        System.out.println("类型:"+bookType);
        List<Book> list = bookService.searchByType(bookType);
        System.out.println("controller 类型结果输出："+list);
        model.addAttribute("searchList",list);
        model.addAttribute("world","hello");
        return "bookstore/product";
    }

    @GetMapping(value = "proinfo")
    public String searchByType(String bid,HttpSession session){
        Book b = bookService.searchBookByID(Integer.valueOf(bid));
        session.setAttribute("bookInfo",b);
        return "bookstore/proinfo";
    }

    @RequestMapping("addPage")
    public String addPage() {
        return "admin/product_add";
    }


    @RequestMapping(value = "addBook", method = {RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String addBook(@RequestBody Book book,HttpSession session) {
        System.out.println(book);
        boolean addFlag = bookService.addBook(book);
        JSONObject json = new JSONObject();
        if (addFlag) {
            json.put("msg","success");
            return json.toString();
        } else {
            json.put("msg","error");
            return json.toString();
        }
    }


    //  添加新书 添加图片
    //图片上传测试
    @ResponseBody
    @RequestMapping("/addBook_Pic")
    public Map upload(MultipartFile file,HttpSession session, HttpServletRequest request){

        String path = "";
        String suffix="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String pathRoot = System.getProperty("user.dir");// 获取项目物理地址
                pathRoot += "/src/main/resources/";
                //获取后缀
                String originalName = file.getOriginalFilename();
                suffix=originalName.substring(originalName.lastIndexOf(".")+1);
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                path = "static/images/"+uuid+"."+suffix;
                String srcPath = "/images/"+uuid+"." + suffix;
                File files=new File(path);
                //打印查看上传路径
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
                map2.put("fullPath",pathRoot+path);
                session.setAttribute("currPicPath",srcPath);
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

    //取消上传图片
    @RequestMapping(value = "cancelUpload" , method = {RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String cancelUpload(String src) {
        boolean flag = deleteDir(src);
        JSONObject json = new JSONObject();
        if (flag) {
            json.put("msg","success");
            return json.toString();
        } else {
            json.put("msg","error");
            return json.toString();
        }
    }

    //删除商品
    @PostMapping(value = "delBook" )
    public @ResponseBody String delBook(String bid) {
        //删除图片
        Book b = bookService.searchBookByID(Integer.valueOf(bid));
        boolean flag2 = deleteDir(b.getBpic());

        //删除数据库文件
        boolean flag1 = bookService.delBook(bid);

         JSONObject json = new JSONObject();
            System.out.println("数据库："+flag1+"图片："+flag2);
            if (flag1 && flag2) {
                json.put("del", "success");
                return json.toString();
            } else {
                return json.toString();
            }
    }

    @GetMapping(value = "updatePage")
    public String modifyPage(String bid,HttpSession session){
        Book b = bookService.searchBookByID(Integer.valueOf(bid));
        session.setAttribute("updateBook",b);
        return  "admin/product_detail";
    }

    @PostMapping(value = "updateBook")
    public @ResponseBody String updateBook(@RequestBody Book book){
      //  book.setBpic(session.getAttribute("currPicPath").toString());
        System.out.println(book);
        deleteDir(book.getBpic());
        boolean addFlag = bookService.updateBook(book);
        JSONObject json = new JSONObject();
        if (addFlag) {
            json.put("update","success");
            return json.toString();
        } else {
            json.put("update","error");
            return json.toString();
        }
    }


}
