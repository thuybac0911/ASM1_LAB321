/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.dtos;

import java.io.Serializable;

/**
 *
 * @author Thúy Bắc
 */
public class ProductErrorDTO  implements Serializable{
    private String productIDError;
    private String productNameError;
    private String priceError;
    private String quantityError;
    private String descriptionError;

    public ProductErrorDTO() {
    }

    public ProductErrorDTO(String productIDError, String productNameError, String priceError, String quantityError, String descriptionError) {
        this.productIDError = productIDError;
        this.productNameError = productNameError;
        this.priceError = priceError;
        this.quantityError = quantityError;
        this.descriptionError = descriptionError;
    }

    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }
    
    
}
