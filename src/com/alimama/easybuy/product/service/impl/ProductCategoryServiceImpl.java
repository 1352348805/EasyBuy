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

    private List<ProductCategoryWithSubClass> getProductCategoryMenu(Integer i) {
        List<ProductCategoryWithSubClass> menu = new ArrayList<>();
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            productCategoryDao = new ProductCategoryDaoImpl(con);
            List<ProductCategory> oneType = productCategoryDao.getProductCategoryListByParentId(0);
            //遍历一级分类
            oneType.forEach(one -> {
                ProductCategoryWithSubClass oneSubClassList = new ProductCategoryWithSubClass();
                oneSubClassList.setId(one.getId());
                oneSubClassList.setName(one.getName());
                oneSubClassList.setParentId(one.getParentId());
                oneSubClassList.setType(one.getType());
                oneSubClassList.setIconClass(one.getIconClass());
                try {
                    //遍历二级分类
                    List<ProductCategory> twoType = productCategoryDao.getProductCategoryListByParentId(one.getId());
                    List<ProductCategoryWithSubClass> twoSubClassList = new ArrayList<>();
                    twoType.forEach(two -> {
                        ProductCategoryWithSubClass twoSubClass = new ProductCategoryWithSubClass();
                        twoSubClass.setId(two.getId());
                        twoSubClass.setName(two.getName());
                        twoSubClass.setParentId(two.getParentId());
                        twoSubClass.setType(two.getType());
                        twoSubClass.setIconClass(two.getIconClass());
                        try {
                            List<ProductCategory> threeType = productCategoryDao.getProductCategoryListByParentId(two.getId());
                            List<ProductCategoryWithSubClass> threeSubClassList = new ArrayList<>();
                            threeType.forEach(three -> {
                                ProductCategoryWithSubClass threeSubClass = new ProductCategoryWithSubClass();
                                threeSubClass.setId(three.getId());
                                threeSubClass.setName(three.getName());
                                threeSubClass.setParentId(three.getParentId());
                                threeSubClass.setType(three.getType());
                                threeSubClass.setIconClass(three.getIconClass());
                                threeSubClassList.add(threeSubClass);
                            });
                            //封装二级菜单的子菜单
                            twoSubClass.setSubClass(threeSubClassList);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        twoSubClassList.add(twoSubClass);
                    });
                    //封装一级菜单的子菜单
                    oneSubClassList.setSubClass(twoSubClassList);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                menu.add(oneSubClassList);
            });

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
}
