package com.jt.dubbo.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.dubbo.common.po.BasePojo;
@Table(name="tb_item_cat")//JPA 注解
public class ItemCat extends BasePojo   {
	//自增主键  
	@Id//主键    /strategy 策略      IDENTITY 自增
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="parent_id")
	private Long parentId;
	@Column(name="name")
	private String name;
	@Column(name="status")
	private Integer status;
	@Column(name="sort_order")
	private Integer sortOrder;
	@Column(name="is_parent")
	private Boolean isParent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	@Override
	public String toString() {
		return "ItemCat [id=" + id + ", parentId=" + parentId + ", name=" + name + ", status=" + status + ", sortOrder="
				+ sortOrder + ", isParent=" + isParent + "]";
	}
	
}
