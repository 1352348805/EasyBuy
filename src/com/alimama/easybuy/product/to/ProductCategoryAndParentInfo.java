package com.alimama.easybuy.product.to;

import com.alimama.easybuy.product.bean.ProductCategory;

/**
 * @author asuk
 * @date 2020/7/22 12:15
 */
public class ProductCategoryAndParentInfo extends ProductCategory {

    private String pname;

    private Integer ptype;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }
}
