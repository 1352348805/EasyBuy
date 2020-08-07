package com.alimama.easybuy.product.service;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.vo.ProductQueryParam;
import com.alimama.easybuy.util.Page;

import java.util.List;

/**
 * @author Jun Xiao
 * @date 2020/7/20 8:53
 */
public interface ProductService {

    /**
     *@Description 查询数据并进行分页
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/7/22
     *@Time 17:17
     */
    Page<Product> getPageProductIndex(ProductQueryParam queryParam, int pageIndex, int pageSize);

    /**
     *@Description 以id删除商品
     *@Param id
     *@Author Wang.li.ming
     *@Date 2020/7/22
     *@Time 17:16
     */
    boolean productInfoByIdDelete (Integer id) throws Exception;

    /**
     *@Description 后台管理 - 以商品id查询商品
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/7/27
     *@Time 17:12
     */
    Product getProductById(Integer id) throws Exception;

    /**
     *@Description 后台管理 -  查询一级分类的所有商品
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/7/27
     *@Time 19:48
     */
    List<Product> getProductParentOneinfo(Integer categoryLeveOneId) throws Exception;

    /**
     *@Description  后台管理 - 修改商品接口
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/7/30
     *@Time 12:24
     */
    boolean productupdate(Product product) throws Exception;

    /**
     *@Description  对商品相关信息数据进行添加
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/8/5
     *@Time 15:35
     */
    boolean productInfoMationiAdd(Product product) throws Exception;

    /**
     *@Description  查询一级分类下的商品 以一级分类条件进行筛选并显示前面n条数据，
     *@Param parentid  &  pagesize
     *@Author Wang.li.ming
     *@Date 2020/8/7
     *@Time 10:03
     */
    List<Product> getselectProductParentIdInfo(Integer parentid , Integer pagesize) throws Exception;

}
