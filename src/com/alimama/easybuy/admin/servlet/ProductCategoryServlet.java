package com.alimama.easybuy.admin.servlet;

import com.alibaba.fastjson.JSON;
import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.service.impl.ProductCategoryServiceImpl;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.to.CommonResult;
import com.alimama.easybuy.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/21 11:00
 */
@WebServlet("/admin/productCategory")
public class  ProductCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();

        if ("index".equals(action))
        {
            Integer i = Page.parseIntPageIndex(req, "index");
            Page<ProductCategoryAndParentInfo> page = productCategoryService.getProductCategoryList(i);
            req.setAttribute("page",page);
            req.getRequestDispatcher("/WEB-INF/page/admin/product/product_category_list.jsp").forward(req,resp);
        }
        else if ("toUpdateProductCategory".equals(action))
        {
            String id = req.getParameter("id");
            PrintWriter out = resp.getWriter();
            StringBuilder resultHTML = new StringBuilder();
            Integer i;
            try {
                i = Integer.parseInt(id);
                ProductCategoryAndParentInfo productCategoryAndParentInfo = productCategoryService.getProductCategoryAndParent(i);
                resultHTML.append("<table border=\"0\" class=\"add_tab\" style=\"width:930px;\" cellspacing=\"0\" cellpadding=\"0\">");
                resultHTML.append("<tr>");
                resultHTML.append("<td width=\"135\" align=\"right\">分类级别</td>");
                resultHTML.append("<td colspan=\"3\" style=\"font-family:'宋体';\" >");
                resultHTML.append("<select class=\"jj\" name=\"type\" style=\"background-color:#f6f6f6;\" id=\"type\"");
                resultHTML.append("onchange=\"selectProductCategoryLevel(this);\" disabled=\"disabled\">");
                resultHTML.append("<option value=\"\" \">请选择...</option>");
                resultHTML.append("<option value=\"1\"");
                if (productCategoryAndParentInfo.getType() == 1) {
                    resultHTML.append("selected=\"selected\"");
                }
                resultHTML.append(" >一级分类</option>");
                resultHTML.append("<option value=\"2\"");
                if (productCategoryAndParentInfo.getType() == 2) {
                    resultHTML.append("selected=\"selected\"");
                }
                resultHTML.append(" >二级分类</option>");
                resultHTML.append("<option value=\"3\"");
                if (productCategoryAndParentInfo.getType() == 3) {
                    resultHTML.append(" selected=\"selected\"");
                }
                resultHTML.append(" >三级分类</option>");

                resultHTML.append("</select>");
                resultHTML.append("</td>");
                resultHTML.append("</tr>");

                //显示父级分类列表并绑定相应的分类
                productCategoryAndParentInfo.getParents().forEach(parent -> {
                    //resultHTML.append("<tr  style=\"display:none\">");
                    resultHTML.append("<tr>");
                    switch (parent.getType()) {
                        case 1:
                            resultHTML.append("<td width=\"135\" align=\"right\">一级分类</td>");
                            break;
                        case 2:
                            resultHTML.append("<td width=\"135\" align=\"right\">二级分类</td>");
                            break;
                        case 3:
                            resultHTML.append("<td width=\"135\" align=\"right\">三级分类</td>");
                    }
                    resultHTML.append("<td colspan=\"3\" style=\"font-family:'宋体';\">");
                    resultHTML.append("<select class=\"jj\" name=\"productCategoryLevel1\" style=\"background-color:#f6f6f6;\" ");
                    if (parent.getType() == 1) {
                        resultHTML.append("id=\"productCategoryLevel" +parent.getType()+"\" onchange=\"queryProductCategoryList(this,'productCategoryLevel"+(parent.getType()+1)+"');\">");

                    } else {
                        resultHTML.append("id=\"productCategoryLevel" +parent.getType()+"\">");
                    }
                    //resultHTML.append("<option value=\"0\">请选择...</option>");
                    List<ProductCategory> productgoryListByType = productCategoryService.getProductCategoryListByParentId(parent.getParentId());
                    productgoryListByType.forEach(productCategory -> {
                        resultHTML.append(" <option value=\""+productCategory.getId()+"\" ");
                        if (parent.getId().equals(productCategory.getId())) {
                            resultHTML.append("selected=\"selected\"");
                        }
                        resultHTML.append(">"+productCategory.getName()+"</option>");
                    });
                    resultHTML.append("</select>");
                    resultHTML.append("</td>");
                    resultHTML.append("</tr>");
                });
                resultHTML.append("<tr>");
                resultHTML.append("<td align=\"right\">分类名称</td>");
                resultHTML.append("<td style=\"font-family:'宋体';\">");
                resultHTML.append("<input type=\"text\" value=\""+ productCategoryAndParentInfo.getName() +"\" class=\"add_ipt\" id=\"name\"/>（必填）");
                resultHTML.append("<input type=\"hidden\" name=\"id\" value=\""+ productCategoryAndParentInfo.getId() +"\" id=\"id\">");
                resultHTML.append("</td>");
                resultHTML.append("</tr>");
                resultHTML.append("</table>");
                resultHTML.append("<p align=\"right\">");
                resultHTML.append("<br>");
                resultHTML.append("<a href=\"javascript:void(0);\" onclick=\"saveOrUpdate();\" class=\"add_b\">确认修改</a>");
                resultHTML.append("</p>");

            } catch (Exception e) {
                e.printStackTrace();
            }
            out.print(resultHTML);
            out.flush();
            out.close();

        }
        else if ("queryProductCategoryList".equals(action))
        {
            String parentId = req.getParameter("parentId");
            List<ProductCategory> productCategoryListByParentId = productCategoryService.getProductCategoryListByParentId(Integer.parseInt(parentId));
            PrintWriter out = resp.getWriter();
            out.print(JSON.toJSONString(new CommonResult().success(productCategoryListByParentId)));
            out.flush();
            out.close();
        }
        else if ("toAddProductCategory".equals(action))
        {
            List<ProductCategory> productCategoryListByOneType = productCategoryService.getProductCategoryListByParentId(0);
            PrintWriter out = resp.getWriter();
            out.print(JSON.toJSONString(new CommonResult().success(productCategoryListByOneType)));
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        PrintWriter out = resp.getWriter();
        ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();
        CommonResult commonResult = null;
        if ("".equals(name)) {
            commonResult = new CommonResult().validateFailed("分类名称不能为空");
        } else {
            String type = req.getParameter("type");
            String productCategoryLevel1 = req.getParameter("productCategoryLevel1");
            String productCategoryLevel2 = req.getParameter("productCategoryLevel2");
            ProductCategory productCategory = new ProductCategory();
            productCategory.setName(name);
            productCategory.setType(Integer.parseInt(type));
            if (productCategory.getType() == 1) {
                productCategory.setParentId(0);
            } else if (productCategory.getType() == 2) {
                productCategory.setParentId(Integer.parseInt(productCategoryLevel1));
            } else {
                productCategory.setParentId(Integer.parseInt(productCategoryLevel2));
            }
            Integer result = 0;
            if ("modifyProductCategory".equals(action)) {
                String id = req.getParameter("id");
                productCategory.setId(Integer.parseInt(id));
                result = productCategoryService.modifyProductCategory(productCategory);
            } else if ("addProductCategory".equals(action)) {
                result = productCategoryService.insertProductCategory(productCategory);
            }
            if (result == 1) {
                commonResult = new CommonResult().success(null);
                //使菜单缓存失效
                req.getServletContext().setAttribute("menu",null);
            } else {
                commonResult = new CommonResult().failed();
            }
        }
        out.print(JSON.toJSONString(commonResult));
        out.flush();
        out.close();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();
        CommonResult commonResult = null;
        PrintWriter out = resp.getWriter();
        Integer i;
        try {
            i = Integer.parseInt(id);
            Integer result = productCategoryService.deleteProductCategoryById(i);
            if (result == 1) {
                commonResult = new CommonResult().success(null);
                //使菜单缓存失效
                req.getServletContext().setAttribute("menu",null);
            } else if (result == -1) {
                commonResult = new CommonResult().validateFailed("无法删除,该分类下还有子分类");
            }
        } catch (Exception e) {
            commonResult = new CommonResult().validateFailed("id格式错误");
        }
        String jsonString = JSON.toJSONString(commonResult);
        out.print(jsonString);
        out.flush();
        out.close();
    }

}
