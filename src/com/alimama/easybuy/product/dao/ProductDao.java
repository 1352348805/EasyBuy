package com.alimama.easybuy.product.dao;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.vo.ProductQueryParam;

import java.util.List;

public interface ProductDao {


    /**
     *@Description 后台管理 - 查询所有商品列表接口 - 每页的数据条数
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/8/4
     *@Time 21:02
     */
     List<Product> productSelectPagesize(ProductQueryParam queryParams , int pageIndex ,int pagesize ) throws Exception;

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

    /**
     *@Description 后台管理 - 查询所有商品列表接口 - 总数据条数
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/8/4
     *@Time 21:02
     */
     Integer productSelectTotalCount(ProductQueryParam queryParam) throws Exception;

    /**
     *@Description 后台管理 - 修改商品接口
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/8/4
     *@Time 21:01
     */
     boolean productupdate(Product product) throws Exception;

    /**
     *@Description 后台管理 - 添加商品
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/8/4
     *@Time 19:48
     */
     boolean productadd(Product product) throws Exception;

    /**
     *@Description 后台管理 - 删除商品接口
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/8/4
     *@Time 21:01
     */
     boolean productdelete(Integer id) throws Exception;

    /**
     * 后台管理 - 查询多个商品ID接口
     * @param ids
     * @return
     * @throws Exception
     */
      List<Product> selectProductInfoById(List<Integer> ids) throws Exception;

    /**
     *@Description 在前台页面以商品分级父ID为条件,每个一级分类以特定条数据进行展现
     *@Param
     *@Author Wang.li.ming
     *@Date 2020/8/4
     *@Time 21:10
     */
       List<Product> selectProductParentOne(Integer parentOne , Integer pagesize) throws Exception;


}
