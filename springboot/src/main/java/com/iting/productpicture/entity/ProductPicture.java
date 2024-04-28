package com.iting.productpicture.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ren.product.entity.Product;

import javax.persistence.*;


@Entity
@Table(name = "productpicture")
public class ProductPicture implements java.io.Serializable {
    @Id
    @Column(name = "ppicno", updatable = false)
    private Integer pPicNo;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "pno", referencedColumnName = "pno")
    private Product product;
    @Column(name = "ppic", columnDefinition = "longblob")
    private byte[] pPic;

    public Integer getpPicNo() {
        return pPicNo;
    }

    public void setpPicNo(Integer pPicNo) {
        this.pPicNo = pPicNo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public byte[] getpPic() {
        return pPic;
    }

    public void setpPic(byte[] pPic) {
        this.pPic = pPic;
    }
}
