package com.lcz.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcz.shop.entity.ProductSpecification;
import com.lcz.shop.entity.SpecificationAttribute;
import com.lcz.shop.entity.vo.CartVO;
import com.lcz.shop.entity.vo.ShoppingCartVO;
import com.lcz.shop.mapper.ProductMapper;
import com.lcz.shop.mapper.ProductSpecificationMapper;
import com.lcz.shop.mapper.SpecificationAttributeMapper;
import com.lcz.shop.service.ProductCartService;

@Service
public class ProductCartServiceImpl implements ProductCartService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductSpecificationMapper productSpecificationMapper;
	@Autowired
	private SpecificationAttributeMapper specificationAttributeMapper;
	
	@Override
	public CartVO insertProductCart(Long productSpecNumber, HttpSession session) {
		ProductSpecification productSpecification = productSpecificationMapper.selectByProductSpecNumber(productSpecNumber);

        CartVO cartVO = (CartVO) session.getAttribute("cart");
        //如果当前商品有规格，规格商品已在购物车 数量加1
        if(productSpecification != null && (!"".equals(productSpecification.getSpec()))) {
            Boolean flag = false;
            List<ShoppingCartVO> shoppingCartVOs = cartVO.getShoppingCartVOs();
            for (ShoppingCartVO vo : shoppingCartVOs) {
                if (productSpecification.getProductSpecNumber().longValue()==(vo.getProductSpecNumber().longValue())) {
                    vo.setBuyNumber(vo.getBuyNumber() + 1);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                //如果当前商品不在购物车 新建vo并加入
                ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
                shoppingCartVO.setProductSpecNumber(productSpecification.getProductSpecNumber());
                shoppingCartVO.setBuyNumber(1);
                shoppingCartVO.setStock(productSpecification.getStock());
                shoppingCartVO.setSalesVolume(productSpecification.getSalesVolume());
                shoppingCartVO.setPrice(productSpecification.getPrice());
                shoppingCartVO.setSpec(productSpecification.getSpec());

                shoppingCartVO.setProductNumber(productMapper.selectProductNumberByProductId(productSpecification.getProductId()));
                shoppingCartVO.setName(productMapper.selectNameByProductId(productSpecification.getProductId()));
                shoppingCartVO.setPicImg(productMapper.selectPicImgByProductId(productSpecification.getProductId()));
                //规格名称处理
                String s = productSpecification.getSpec();
                List<String> list = new ArrayList<String>();
                // 商品规格进行拆分
                if (s != null && (!s.equals(""))) {
                    String[] sp = s.split(",");
                    for (String ss : sp) {
                        SpecificationAttribute specificationAttribute = specificationAttributeMapper.selectByPrimaryKey(Long.parseLong(ss));
                        list.add(specificationAttribute.getName());
                    }
                }
                shoppingCartVO.setSpecificationName(list);

                cartVO.getShoppingCartVOs().add(shoppingCartVO);
            }
        } else {
            //如果商品没有规格
            Boolean flag = false;
            List<ShoppingCartVO> shoppingCartVOs = cartVO.getShoppingCartVOs();
            for(ShoppingCartVO shop : shoppingCartVOs) {
                if (productSpecNumber.longValue()==(shop.getProductNumber().longValue())) {
                    shop.setBuyNumber(shop.getBuyNumber() + 1);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                //如果当前商品不在购物车 新建vo并加入
                ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
                shoppingCartVO.setProductSpecNumber(productSpecification.getProductSpecNumber());
                shoppingCartVO.setBuyNumber(1);
                shoppingCartVO.setStock(productSpecification.getStock());
                shoppingCartVO.setSalesVolume(productSpecification.getSalesVolume());
                shoppingCartVO.setPrice(productSpecification.getPrice());

                shoppingCartVO.setProductNumber(productMapper.selectProductNumberByProductId(productSpecification.getProductId()));
                shoppingCartVO.setName(productMapper.selectNameByProductId(productSpecification.getProductId()));
                shoppingCartVO.setPicImg(productMapper.selectPicImgByProductId(productSpecification.getProductId()));
                cartVO.getShoppingCartVOs().add(shoppingCartVO);
            }

        }
        return cartVO;
	}

	@Override
	public Boolean getProductExsit(Long productSpecNumber, HttpSession session) {
		ProductSpecification productSpecification = productSpecificationMapper.selectByProductSpecNumber(productSpecNumber);
        Boolean flag = false;
        CartVO cartVO = (CartVO) session.getAttribute("cart");
        //如果当前规格商品已在购物车 数量加1
        List<ShoppingCartVO> shoppingCartVOs = cartVO.getShoppingCartVOs();
        for (ShoppingCartVO vo : shoppingCartVOs) {
            if (productSpecification.getProductSpecNumber().longValue()==(vo.getProductSpecNumber().longValue())) {
                flag = true;
                break;
            }
        }
        return flag;
	}

	@Override
	public CartVO insertProductCart(Long productSpecNumber, HttpSession session, Integer buyNumber) {
		ProductSpecification productSpecification = productSpecificationMapper.selectByProductSpecNumber(productSpecNumber);

        CartVO cartVO = (CartVO) session.getAttribute("cart");
        //如果当前商品有规格，规格商品已在购物车 数量加1
        if(productSpecification != null && (!"".equals(productSpecification.getSpec()))) {
            Boolean flag = false;
            List<ShoppingCartVO> shoppingCartVOs = cartVO.getShoppingCartVOs();
            for (ShoppingCartVO vo : shoppingCartVOs) {
                if (productSpecification.getProductSpecNumber().longValue()==(vo.getProductSpecNumber().longValue())) {
                    vo.setBuyNumber(buyNumber);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                //如果当前商品不在购物车 新建vo并加入
                ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
                shoppingCartVO.setProductSpecNumber(productSpecification.getProductSpecNumber());
                shoppingCartVO.setBuyNumber(1);
                shoppingCartVO.setStock(productSpecification.getStock());
                shoppingCartVO.setSalesVolume(productSpecification.getSalesVolume());
                shoppingCartVO.setPrice(productSpecification.getPrice());
                shoppingCartVO.setSpec(productSpecification.getSpec());

                shoppingCartVO.setProductNumber(productMapper.selectProductNumberByProductId(productSpecification.getProductId()));
                shoppingCartVO.setName(productMapper.selectNameByProductId(productSpecification.getProductId()));
                shoppingCartVO.setPicImg(productMapper.selectPicImgByProductId(productSpecification.getProductId()));
                //规格名称处理
                String s = productSpecification.getSpec();
                List<String> list = new ArrayList<String>();
                // 商品规格进行拆分
                if (s != null && (!s.equals(""))) {
                    String[] sp = s.split(",");
                    for (String ss : sp) {
                        SpecificationAttribute osSpecificationAttribute = specificationAttributeMapper.selectByPrimaryKey(Long.parseLong(ss));
                        list.add(osSpecificationAttribute.getName());
                    }
                }
                shoppingCartVO.setSpecificationName(list);

                cartVO.getShoppingCartVOs().add(shoppingCartVO);
            }


        } else {
            //如果商品没有规格
            Boolean flag = false;
            List<ShoppingCartVO> shoppingCartVOs = cartVO.getShoppingCartVOs();
            for(ShoppingCartVO shop : shoppingCartVOs) {
                if (productSpecNumber.longValue()==(shop.getProductNumber().longValue())) {
                    shop.setBuyNumber(buyNumber);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                //如果当前商品不在购物车 新建vo并加入
                ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
                shoppingCartVO.setProductSpecNumber(productSpecification.getProductSpecNumber());
                shoppingCartVO.setBuyNumber(1);
                shoppingCartVO.setStock(productSpecification.getStock());
                shoppingCartVO.setSalesVolume(productSpecification.getSalesVolume());
                shoppingCartVO.setPrice(productSpecification.getPrice());

                shoppingCartVO.setProductNumber(productMapper.selectProductNumberByProductId(productSpecification.getProductId()));
                shoppingCartVO.setName(productMapper.selectNameByProductId(productSpecification.getProductId()));
                shoppingCartVO.setPicImg(productMapper.selectPicImgByProductId(productSpecification.getProductId()));
                cartVO.getShoppingCartVOs().add(shoppingCartVO);
            }

        }
        return cartVO;
	}

	@Override
	public CartVO deProductCart(Long productSpecNumber, HttpSession session, Integer buyNumber) {
		ProductSpecification ProductSpecification = productSpecificationMapper.selectByProductSpecNumber(productSpecNumber);

        CartVO cartVO = (CartVO) session.getAttribute("cart");
        //如果当前商品有规格，规格商品已在购物车 数量-1
        if(ProductSpecification != null && (!"".equals(ProductSpecification.getSpec()))) {
            List<ShoppingCartVO> shoppingCartVOs = cartVO.getShoppingCartVOs();
            for (ShoppingCartVO vo : shoppingCartVOs) {
                if (ProductSpecification.getProductSpecNumber().longValue()==(vo.getProductSpecNumber().longValue())) {
                    vo.setBuyNumber(buyNumber);
                    break;
                }
            }
        } else {
            //如果商品没有规格
            List<ShoppingCartVO> shoppingCartVOs = cartVO.getShoppingCartVOs();
            for(ShoppingCartVO shop : shoppingCartVOs) {
                if (productSpecNumber.longValue()==(shop.getProductNumber().longValue())) {
                    shop.setBuyNumber(buyNumber);
                    break;
                }
            }
        }
        return cartVO;
	}

	@Override
	public CartVO deleteProductCart(Long productSpecNumber, HttpSession session) {
		ProductSpecification productSpecification = productSpecificationMapper.selectByProductSpecNumber(productSpecNumber);

        CartVO cartVO = (CartVO) session.getAttribute("cart");
        //如果当前商品有规格，规格商品已在购物车
        if(productSpecification != null && (!"".equals(productSpecification.getSpec()))) {
            List<ShoppingCartVO> shoppingCartVOs = cartVO.getShoppingCartVOs();
            for (ShoppingCartVO vo : shoppingCartVOs) {
                if (productSpecification.getProductSpecNumber().longValue()==(vo.getProductSpecNumber().longValue())) {
                    shoppingCartVOs.remove(vo);
                    break;
                }
            }
        } else {
            //如果商品没有规格
            List<ShoppingCartVO> shoppingCartVOs = cartVO.getShoppingCartVOs();
            for(ShoppingCartVO shop : shoppingCartVOs) {
                if (productSpecNumber.longValue()==(shop.getProductNumber().longValue())) {
                    shoppingCartVOs.remove(shop);
                    break;
                }
            }
        }
        return cartVO;
	}

	/*@Override
	public Long getProductNumber(HttpSession session) {
		CartVO cartVO = (CartVO) session.getAttribute("cart");
		List<ShoppingCartVO> shoppingCartVOs = cartVO.getShoppingCartVOs();
		Long productNum=Long.valueOf(shoppingCartVOs.size());
		return productNum;
	}*/

}
