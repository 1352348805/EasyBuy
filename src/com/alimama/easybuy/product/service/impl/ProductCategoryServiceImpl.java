package com.alimama.easybuy.product.service.impl;

import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.dao.ProductCategoryDao;
import com.alimama.easybuy.product.dao.impl.ProductCategoryDaoImpl;
import com.alimama.easybuy.product.service.ProductCategoryService;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.util.DatabaseUtil;
import com.alimama.easybuy.util.Page;

import java.sql.Connection;
import java.sql.SQLException;
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
}
