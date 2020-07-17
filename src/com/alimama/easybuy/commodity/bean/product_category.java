package com.alimama.easybuy.commodity.bean;

public class product_category {
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer type;
    private String iconClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("product_category{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", parentId=").append(parentId);
        sb.append(", type=").append(type);
        sb.append(", iconClass='").append(iconClass).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
