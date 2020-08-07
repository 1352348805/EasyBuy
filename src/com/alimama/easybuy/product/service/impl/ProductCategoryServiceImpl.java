package com.alimama.easybuy.product.service.impl;

import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.dao.ProductCategoryDao;
import com.alimama.easybuy.product.dao.impl.ProductCategoryDaoImpl;
import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.product.to.ProductCategoryWithSubClass;
import com.alimama.easybuy.util.DatabaseUtil;
import com.alimama.easybuy.util.Page;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author asuk
 * @date 2020/7/21 19:49
 */
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private ProductCategoryDao productCategoryDao;

    @Override
    public Page<ProductCategoryAndParentInfo> getProductCategoryList(Integer pageIndex) {
        Page<ProductCategoryAndParentInfo> page = new Page<>();
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            productCategoryDao = new ProductCategoryDaoImpl(con);
            Integer count = productCategoryDao.getProductCategoryCount();
            page.setPageSize(8);
            page.setTotalCount(count);
            page.setCurrPageNo(pageIndex);
            Integer start = page.getStartIndex();
            List<ProductCategoryAndParentInfo> productCategoryList =
                    productCategoryDao.getProductCategoryList(start, page.getPageSize());
            page.setData(productCategoryList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(con);
        }
        return page;
    }

    @Override
    public Integer deleteProductCategoryById(Integer id) {
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            productCategoryDao = new ProductCategoryDaoImpl(con);
            List<ProductCategory> productCategoryListById = productCategoryDao.getProductCategoryListByParentId(id);
            if (productCategoryListById.size() > 0) {
                return -1;
            }
            return productCategoryDao.deleteProductCategoryById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(con);
        }
        return 0;
    }

    @Override
    public Integer modifyProductCategory(ProductCategory productCategory) {
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            productCategoryDao = new ProductCategoryDaoImpl(con);
            return productCategoryDao.modifyProductCategory(productCategory);
        } catch (SQLException throwables) {
            return -1;
        }
    }

    @Override
    public Integer insertProductCategory(ProductCategory productCategory) {
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            productCategoryDao = new ProductCategoryDaoImpl(con);
            return productCategoryDao.insertProductCategory(productCategory);
        } catch (SQLException throwables) {
            return 0;
        } finally {
            DatabaseUtil.close(con);
        }
    }

    @Override
    public ProductCategoryAndParentInfo getProductCategoryAndParent(Integer id) {
        ProductCategoryAndParentInfo productCategoryAndParentInfo = null;
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            productCategoryDao = new ProductCategoryDaoImpl(con);
            ProductCategory productCategory = productCategoryDao.getProductCategoryById(id);
            if (productCategory == null) {
                return productCategoryAndParentInfo;
            }
            productCategoryAndParentInfo = new ProductCategoryAndParentInfo();
            productCategoryAndParentInfo.setId(productCategory.getId());
            productCategoryAndParentInfo.setName(productCategory.getName());
            productCategoryAndParentInfo.setParentId(productCategory.getParentId());
            productCategoryAndParentInfo.setType(productCategory.getType());

            LinkedList<ProductCategory> parentList = new LinkedList<>();
            do {
                if (productCategory.getType() == 1) {
                    break;
                }
                id = productCategory.getParentId();
                productCategory = productCategoryDao.getProductCategoryById(id);
                if (productCategory == null) {
                    break;
                }
                parentList.addFirst(productCategory);
            } while (true);
            productCategoryAndParentInfo.setParents(parentList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(con);
        }
        return productCategoryAndParentInfo;
    }

    @Override
    public List<ProductCategory> getProductCategoryListByParentId(Integer parentId) {
        List<ProductCategory> productCategoryList = null;
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl(con);
            productCategoryList = productCategoryDao.getProductCategoryListByParentId(parentId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(con);
        }
        return productCategoryList;
    }

    /**
     * 获取分类菜单数据
     * @param i
     * @return
     */
    private List<ProductCategoryWithSubClass> getProductCategoryMenu(Integer i) {
        List<ProductCategoryWithSubClass> menu = null;
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            productCategoryDao = new ProductCategoryDaoImpl(con);

            ProductCategoryWithSubClass productCategoryWithSubClasses = new ProductCategoryWithSubClass();

            fillMenu(0,productCategoryWithSubClasses);
            menu = productCategoryWithSubClasses.getSubClass();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseUtil.close(con);
        }
        return menu;
    }


    @Override
    public List<ProductCategoryWithSubClass> getProductCategoryMenu(HttpServletRequest request) {
        List<ProductCategoryWithSubClass> menu;
        ServletContext application = request.getServletContext();
        Object objMenu =request.getServletContext().getAttribute("menu");
        //判断上下文中是否存在
        if (objMenu != null) {
            menu = (List<ProductCategoryWithSubClass>)objMenu;
        } else {
            menu = getProductCategoryMenu(0);
            //存入上下文
            application.setAttribute("menu",menu);
        }
        return menu;
    }

    /**
     * 递归获取菜单分类
     * @param parentId 父级分类id
     * @param productCategoryWithSubClass 该分类的子分类集合
     */
    private void fillMenu(Integer parentId,ProductCategoryWithSubClass productCategoryWithSubClass)  {
        AtomicReference<List<ProductCategoryWithSubClass>> listAtomicReference = new AtomicReference<>();
        listAtomicReference.set(new ArrayList<>());
        try {
            List<ProductCategory> productCategoryListByParentId = productCategoryDao.getProductCategoryListByParentId(parentId);
            productCategoryListByParentId.forEach(item -> {
                ProductCategoryWithSubClass subClassList = new ProductCategoryWithSubClass();
                subClassList.setId(item.getId());
                subClassList.setName(item.getName());
                subClassList.setParentId(item.getParentId());
                subClassList.setType(item.getType());
                subClassList.setIconClass(item.getIconClass());
                fillMenu(item.getId(), subClassList);
                listAtomicReference.get().add(subClassList);
            });
            productCategoryWithSubClass.setSubClass(listAtomicReference.get());
        } catch (Exception e) {

        }
    }
}
