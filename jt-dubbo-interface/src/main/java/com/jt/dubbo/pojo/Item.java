package com.jt.dubbo.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.dubbo.common.po.BasePojo;
@Table(name="tb_item")//JPA 注解
public class Item extends BasePojo {
/*
 * id                   bigint(10) not null auto_increment comment '商品ID，也是商品编号',
   title                varchar(100),
   sell_point           varchar(150),
   price                bigint(20) comment '单位为：分',
   num                  int(10),
   barcode              varchar(30),
   image                varchar(500) comment '最多5张图片',
   cid                  bigint(10),
   status               int(1) default 1 comment '默认值为1，可选值：1正常，2下架，3删除',
   created              datetime,
   updated              datetime comment '列表排序时按修改时间排序，所以在新增时需要设置此值。',
   primary key (id)
 * */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String sellPoint;
	private Long price;
	private Integer num;
	private String barcode;
	private String image;
	private Long cid;
	private Integer status;
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price + ", num=" + num
				+ ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status=" + status + "]";
	}
	
	//item.images[0]
			public String[] getImages(){
				
				//商品图片上传之后 1.jpg,2.jpg
				return image.split(",");
			}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
