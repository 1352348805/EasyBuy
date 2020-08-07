package com.alimama.easybuy.admin.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.odps.udf.JSONTuple;
import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.service.ProductService;
import com.alimama.easybuy.product.service.impl.ProductCategoryServiceImpl;
import com.alimama.easybuy.product.service.impl.ProductServiceImpl;
import com.alimama.easybuy.to.CommonResult;
import com.alimama.easybuy.util.Page;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author asuk
 * @date 2020/7/17 23:30
 */

@WebServlet("/admin/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        // 查询入口
        if ("index".equals(action)) {
            ProductService productService = new ProductServiceImpl();
            try {
               String currentPage = req.getParameter("currentPage");
               Integer pageIndex = null;
               if(currentPage != null){
                   try{
                       pageIndex = new Integer(currentPage);
                   }catch (NumberFormatException e){
                       pageIndex = 1;
                   }
               }else{
                   pageIndex = 1;
               }
               req.getSession().setAttribute("currentPage",pageIndex);
                Page<Product> page = productService.getPageProductIndex(null,pageIndex,5);
                List<Product> product = page.getData();
                req.setAttribute("products",product);
                req.setAttribute("page",page);
            } catch (Exception e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/page/admin/product/product_list.jsp").forward(req,resp);
        }
        // 修改入口
        else if("toUpdateProduct".equals(action)){
            ProductService productService = new ProductServiceImpl();
            ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();
            try{
                Integer productid = Integer.parseInt(req.getParameter("productid"));
                 // 拿到要修改商品的id，进行把数据填充到jsp页面表单中
                Product product = productService.getProductById(productid);
                req.setAttribute("productbyinfo",product);
                 // 三级分类
                List<ProductCategory> oneType = productCategoryService.getProductCategoryListByParentId(0);
                req.setAttribute("oneType",oneType);

                List<ProductCategory> twoType = productCategoryService.getProductCategoryListByParentId(product.getCategoryLevel1Id());
                req.setAttribute("twoType",twoType);

                List<ProductCategory> threeType = productCategoryService.getProductCategoryListByParentId(product.getCategoryLevel2Id());
                req.setAttribute("threeType",threeType);

            }catch(Exception e){
             e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/page/admin/product/product_update.jsp").forward(req,resp);
        }
        // 异步刷新产品分类数据
        else if("queryProductCategoryList".equals(action)){
            ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();
            try{
                Integer ProductParentId = Integer.parseInt(req.getParameter("parentId"));
                List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryListByParentId(ProductParentId);
                PrintWriter writer = resp.getWriter();
                writer.print(JSON.toJSONString(new CommonResult().success(productCategoryList))); // 给用户端返回一个状态码 ，并把数据封装进去
                writer.flush();
                writer.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
       doPost(req,resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonResult commonResult = new CommonResult();
        try{
            ProductService productService = new ProductServiceImpl();
         // ProductService productService = (ProductServiceImpl)Class.forName("com.alimama.easybuy.product.service.impl.ProductServiceImpl").newInstance(); //类反射
            int productid = Integer.parseInt(req.getParameter("productid"));
            boolean bool = productService.productInfoByIdDelete(productid);
            if(bool){
                commonResult.setMessage("删除成功");
            }
            else{
                commonResult.setMessage("删除失败");
            }
            PrintWriter writer = resp.getWriter();
            writer.print(JSON.toJSONString(commonResult));
            writer.flush();
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *@Description 对用户请求修改操作
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/7/23
     *@Time 11:04
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析器对象
        ServletFileUpload sfu=new ServletFileUpload(factory);
        sfu.setFileSizeMax(2000*1024);
        ProductService productService = new ProductServiceImpl();
        Product product = new Product();
        Integer productid = Integer.parseInt(req.getParameter("productid"));
        product.setId(productid);
        PrintWriter out = resp.getWriter();
        try {
            //解析request对象，得到用户请求对象中的所有数据，返回一个List<FileItem>
            List<FileItem> list=sfu.parseRequest(req);
            for(FileItem item:list) {
                if (item.isFormField()) {
                    // getFieldName() K 表单属性的名字   getString() V 表单属性的值
                    if (item.getFieldName().equals("categoryLevel1Id")) {
                        product.setCategoryLevel1Id(Integer.parseInt(item.getString("UTF-8")));
                    }
                    if(item.getFieldName().equals("categoryLevel2Id")){
                       product.setCategoryLevel2Id(Integer.parseInt(item.getString("UTF-8")));
                    }
                    if(item.getFieldName().equals("categoryLevel3Id")){
                       product.setCategoryLevel3Id(Integer.parseInt(item.getString("UTF-8")));
                    }
                    if(item.getFieldName().equals("name")){
                        product.setName(item.getString("UTF-8"));
                    }
                    if(item.getFieldName().equals("price")){
                        product.setPrice(Float.parseFloat(item.getString("UTF-8")));
                    }
                    if(item.getFieldName().equals("stock")){
                        product.setStock(Integer.parseInt(item.getString("UTF-8")));
                    }
                    if(item.getFieldName().equals("description")){
                        product.setDescription(item.getString("UTF-8"));
                    }

                } else {
                    String hzm =null;
                    try{
                        // item.getName() 获取文件名（包括文件格式）indexOf() 包含当前下标
                        hzm = item.getName().substring(item.getName().indexOf(".")); // 截取文件的后缀名（格式）
                        if(!hzm.equals(".jpg") && !hzm.equals(".png")){
                           // throw new Exception("文件名格式不对！");
                           // <script>alert('文件名格式不对');location.href=' url '</script>
                            out.print("<script>alert('文件名格式不对');location.href='"+req.getHeader("Referer")+"'</script>");
                            out.flush();
                            out.close();
                            return;
                        }
                    }catch (Exception e){
                      //  throw new Exception("你还没选择要上传的文件！");
                        out.print("<script>alert('你还没选择要上传的文件');location.href='"+req.getHeader("Referer")+"'</script>");
                        out.flush();
                        out.close();
                        return;
                    }
                    // 替换文件名称后的文件名称（得到一个新的文件名）
                    String fileName = UUID.randomUUID().toString().replace("-","")+hzm;
                    // filePath 文件要存储的相对路径
                    String filePath = req.getServletContext().getRealPath("/")+"images/"+ fileName;
                    File file = new File(filePath);
                    //   if(!file.exists() && !file.isDirectory()){
                    //       file.mkdirs(); //不存在该目录则创建该目录
                    //    }
                    try {
                        // 把文件流写到 filePath 路径里去
                        item.write(file);
                        product.setFileName(fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
                Boolean bool = productService.productupdate(product);
                if(bool){
                    String currentPage = req.getSession().getAttribute("currentPage").toString();
                    resp.sendRedirect(req.getContextPath()+"/admin/product?action=index&currentPage="+currentPage);
                    return;
                }
                else{
                    out.print("<script>alert('修改失败');location.href='"+req.getHeader("Referer")+"'</script>");
                    out.flush();
                    out.close();
                    return;
                }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *@Description 对用户请求添加操作
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/7/23
     *@Time 11:05
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("UpdateProduct".equals(action)) {
            doPut(req,resp);
        }
        // 对商品添加进行处理
        else if ("productAdd".equals(action)){
            ProductService productService = new ProductServiceImpl();
            ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();
            Product product = new Product();
            PrintWriter writer = resp.getWriter();
            try{
                // 一级表单进行展示
                List<ProductCategory> oneType = productCategoryService.getProductCategoryListByParentId(0);
                req.setAttribute("oneTypeadd",oneType);
                // 创建工厂
                DiskFileItemFactory factory = new DiskFileItemFactory();
                //创建解析器对象
                ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
                //设置这个容器的容量
                servletFileUpload.setFileSizeMax(1000*1024);
                // 解析request 对象， 得到用户请求对象中的所有数据，返回一个List<FileItem>
                List<FileItem> list = servletFileUpload.parseRequest(req);
                for(FileItem item : list){
                    // 判断是表单字段还是文件字段
                    if(item.isFormField()){
                        if (item.getFieldName().equals("categoryLevel1Id")) {
                            product.setCategoryLevel1Id(Integer.parseInt(item.getString("UTF-8")));
                        }
                        if(item.getFieldName().equals("categoryLevel2Id")){
                            product.setCategoryLevel2Id(Integer.parseInt(item.getString("UTF-8")));
                        }
                        if(item.getFieldName().equals("categoryLevel3Id")){
                            product.setCategoryLevel3Id(Integer.parseInt(item.getString("UTF-8")));
                        }
                        if(item.getFieldName().equals("name")){
                            product.setName(item.getString("UTF-8"));
                        }
                        if(item.getFieldName().equals("price")){
                            product.setPrice(Float.parseFloat(item.getString("UTF-8")));
                        }
                        if(item.getFieldName().equals("stock")){
                            product.setStock(Integer.parseInt(item.getString("UTF-8")));
                        }
                        if(item.getFieldName().equals("description")){
                            product.setDescription(item.getString("UTF-8"));
                        }
                    }
                    else{
                        String fileSuffix = null ;
                        try {
                             fileSuffix = item.getName().substring(item.getName().indexOf("."));
                             if(!fileSuffix.equals(".jpg")  && !fileSuffix.equals(".png")){
                                  // Referer 当前请求的url路径
                                  writer.print("<script>alert('文件名格式不对');location.href='"+req.getHeader("Referer")+"'</script>");
                                  writer.flush();
                                  writer.close();
                                  return;
                             }
                        }catch(Exception e){
                               writer.print("<script>alert('你还没选择要上传的文件');location.href='"+req.getHeader("Referer")+"'</script>");
                               writer.flush();
                               writer.close();
                               return;
                        }
                        String fileName = UUID.randomUUID().toString().replace("-","")+fileSuffix;
                        String filePath = req.getServletContext().getRealPath("/")+"images"+fileName;
                        File file = new File(filePath);
                        item.write(file);
                        product.setFileName(fileName);
                    }
                }
                Boolean bool = productService.productInfoMationiAdd(product);
                if(bool){
                   resp.sendRedirect(req.getContextPath()+"/admin/product?action=index");
                   return ;
                }
                else{
                    writer.print("<script>alert('添加失败');location.href='"+req.getHeader("Referer")+"'</script>");
                    writer.flush();
                    writer.close();
                    return;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/page/admin/product/product_add.jsp").forward(req,resp);
        }

    }
}
