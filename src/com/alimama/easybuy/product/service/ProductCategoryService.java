package com.alimama.easybuy.product.service;

import com.alimama.easybuy.product.bean.ProductCategory;
import com.alimama.easybuy.product.to.ProductCategoryAndParentInfo;
import com.alimama.easybuy.product.to.ProductCategoryWithSubClass;
import com.alimama.easybuy.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/21 19:49
 */
public interface ProductCategoryService {

    //获取分类列表
    Page<ProductCategoryAndParentInfo> getProductCategoryList(Integer pageIndex);

    //删除分类
    Integer deleteProductCategoryById(Integer id);

    //修改分类
    Integer modifyProductCategory(ProductCategory productCategory);

    //添加分类
    Integer insertProductCategory(ProductCategory productCategory);

    //根据id获取分类及其父分类信息
    ProductCategoryAndParentInfo getProductCategoryAndParent(Integer id);

    //获取指定分类
    List<ProductCategory> getProductCategoryListByParentId(Integer parentId);

    //获取菜单数据
    List<ProductCategoryWithSubClass> getProductCategoryMenu(HttpServletRequest request);
}
