package com.alimama.easybuy.product;

import com.alimama.easybuy.product.bean.Product;
import com.alimama.easybuy.product.dao.ProductDao;
import com.alimama.easybuy.product.dao.impl.ProductDaoImpl;
import com.alimama.easybuy.product.service.ProductService;
import com.alimama.easybuy.product.service.impl.ProductServiceImpl;
import com.alimama.easybuy.util.DatabaseUtil;
import com.alimama.easybuy.util.Page;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

/**
 * @author Jun Xiao
 * @date 2020/7/20 10:43
 */
public class ProductTest1 {
    static String s ;
    @Test
    public void test1()  {
        try{
            ProductService productService = new ProductServiceImpl();
            Page<Product> page = productService.getPageProductIndex(null,1,5);
//            for(Product product :page.getNewsList()){
//                System.out.println(product.getId());
//            }

              List<Product> products=page.getData();

            System.out.println(products.get(0).getId());
            System.out.println( page.getData().get(0).getFileName());

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void f2() throws Exception {
        ProductDao dao = new ProductDaoImpl(DatabaseUtil.getConnection());
        List<Product> products = dao.selectProductInfoById(Arrays.asList(735, 737));
        for (Product p : products) {
            System.out.println(p.getName());
        }
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println(p.getName());
        }
    }
    @Test
    public void test4(){

        TreeSet<String>  s =new TreeSet<String>();
        s.add("one");
        s.add("two");
        SortedSet<String> sorted = s.tailSet(s.first());
        for (String s2 : sorted){
            System.out.println(s2+"");
        }


        HashMap<Integer,String> hmap = new HashMap<Integer , String >();
        hmap.put(1,"java");
        hmap.put(6,"javascript");
        hmap.put(5,"Oracle");
        hmap.put(9,"Mysql");
        for(Integer i : hmap.keySet()){
            System.out.println(hmap.get(i));
        }
        System.out.println(hmap.get(6));

        List list = new ArrayList();
        list.add("2");
        list.add("3");
        list.add("4");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        Hashtable hashtable = new Hashtable();
       // hashtable.put(null,""); hastable 的键不能为空
        hashtable.put("d","6");
        System.out.println(hashtable.get(null));

    }
}
