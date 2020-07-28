package com.alimama.easybuy.product.dao;

import com.alimama.easybuy.product.bean.Product;

import java.util.List;

public interface ProductDao {

     // 后台管理 - 查询所有商品列表接口 - 每页的数据条数
     List<Product> productSelectPagesize(int pageIndex ,int pagesize) throws Exception;

      /**
       *@Description  后台管理 - 以商品id查询商品
       *@Param
       *@Author Wang.li.ming
       *@Date 2020/7/27
       *@Time 16:46
       */
      Product selectProductByid(Integer id)throws Exception;

      /**
       *@Description 后台管理 -  查询一级分类的所有商品
       *@Param
       *@Author Wang.li.ming
       *@Date 2020/7/27
       *@Time 19:30
       */
      List<Product> selectProductParentOneinfo(Integer categoryLeveOneId) throws Exception;

    // 后台管理 - 查询所有商品列表接口 - 总数据条数
     Integer productSelectTotalCount() throws Exception;

    // 后台管理 - 修改商品接口
     boolean productupdate(int type) throws Exception;

    // 后台管理 - 添加商品接口
     boolean productadd() throws Exception;

    // 后台管理 - 删除商品接口
     boolean productdelete(Integer id) throws Exception;

    /**
     * 后台管理 - 查询多个商品ID接口
     * @param ids
     * @return
     * @throws Exception
     */
      List<Product> selectProductInfoById(List<Integer> ids) throws Exception;


}
