package com.lcz.shop.util;

import com.github.pagehelper.Page;
import com.lcz.shop.entity.Product;

public class GetPage {

	 private Page<Product> l ;

	    public Page<Product> getL() {
	        return l;
	    }

	    public void setL(Page<Product> l) {
	        this.l = l;
	    }
}
