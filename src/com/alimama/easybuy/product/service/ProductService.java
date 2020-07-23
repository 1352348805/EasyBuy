package com.alimama.easybuy.product.service;

import com.alimama.easybuy.product.bean.Product;
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
    Page<Product> getPageProductIndex(int pageIndex) throws Exception;

    /**
     *@Description 以id删除商品
     *@Param id
     *@Author Wang.li.ming
     *@Date 2020/7/22
     *@Time 17:16
     */
    boolean productInfoByIdDelete (Integer id);

}
