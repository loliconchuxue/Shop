package com.lcz.shop.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcz.shop.common.Result;
import com.lcz.shop.entity.Product;
import com.lcz.shop.entity.vo.CartVO;
import com.lcz.shop.service.ProductCartService;
import com.lcz.shop.service.ProductService;


@Controller
@RequestMapping("/cart")
public class ProductCartController {

	
	@Autowired
    private ProductCartService productCartService;
    @Autowired
    private ProductService productService;

    /**
     * GET 成功加入购物车
     * @return
     */
    @RequestMapping(value = "/{productSpecNumber}")
    public String view(@PathVariable Long productSpecNumber, HttpSession session, Model model) {
        // 用户已登录,查看购物车列表是否有该商品
        Boolean flag = productCartService.getProductExsit(productSpecNumber, session);
        Product Product = productService.getProductBySpecNumber(productSpecNumber);
        model.addAttribute("product", Product);
        if (flag == false) {
            // 重定向到购物车列表
            System.out.println("flag is false");
            return "/product/product_cart_info";
        }
        return "/product/product_cart_info";
    }

    /**
     * POST 添加购物车商品
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestParam(value = "productSpecNumber", required = true) Long productSpecNumber,
                         HttpSession session) {
        if(session.getAttribute("user") != null) {
            CartVO cartVO = productCartService.insertProductCart(productSpecNumber, session);
            session.setAttribute("cart", cartVO);
            System.out.println(cartVO.toString());
            return new Result(1, productSpecNumber.toString());
        } else {
            return new Result(401,"还未登录");
        }
    }

    /**
     * DELETE 删除购物车商品
     *
     * @return
     */
    @RequestMapping(value = "/{productSpecNumber}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteProductNumber(@PathVariable Long productSpecNumber,
                         HttpSession session) {
        if(session.getAttribute("user") != null) {
            CartVO cartVO = productCartService.deleteProductCart(productSpecNumber, session);
            session.setAttribute("cart", cartVO);
            System.out.println(cartVO.toString());
            return new Result(1, productSpecNumber.toString());
        } else {
            return new Result(401,"还未登录");
        }
    }

    
    /**
     * GET 购物车列表
     * @return
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/product/product_cart_list";
    }

    
    /**
     *  修改购物车商品数量 新增
     *
     * @return
     */
    @RequestMapping(value = "/add/{productSpecNumber}", method = RequestMethod.GET)
    @ResponseBody
    public Object addProductNumber(@PathVariable(value = "productSpecNumber") Long productSpecNumber,Integer buyNumber,
                         HttpSession session) {
        if(session.getAttribute("user") != null) {
            CartVO cartVO = productCartService.insertProductCart(productSpecNumber, session, buyNumber);
            session.setAttribute("cart", cartVO);
            System.out.println(cartVO.toString());
            return new Result(1, productSpecNumber.toString());
        } else {
            return new Result(401,"还未登录");
        }
    }
    /**
     *  修改购物车商品数量 去除
     *
     * @return
     */
    @RequestMapping(value = "/delete/{productSpecNumber}", method = RequestMethod.GET)
    @ResponseBody
    public Object deProductNumber(@PathVariable(value = "productSpecNumber") Long productSpecNumber,
                                      Integer buyNumber, HttpSession session) {
        if(session.getAttribute("user") != null) {
            CartVO cartVO = productCartService.deProductCart(productSpecNumber, session, buyNumber);
            session.setAttribute("cart", cartVO);
            System.out.println(cartVO.toString());
            return new Result(1, productSpecNumber.toString());
        } else {
            return new Result(401,"还未登录");
        }
    }
    /*@RequestMapping(value="/cartNumber",method=RequestMethod.GET)
    public Object cartNumber(HttpSession session) {
    	Long productNum=productCartService.getProductNumber(session);
    	if (productNum!=null) {
			return new Result(1, productNum.toString());
		}
		return new Result(0, "0");
	}*/
}
