package com.lcz.shop.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lcz.shop.entity.Category;
import com.lcz.shop.entity.Product;
import com.lcz.shop.entity.ProductCategory;
import com.lcz.shop.entity.ProductDetail;
import com.lcz.shop.entity.ProductImage;
import com.lcz.shop.entity.ProductParameter;
import com.lcz.shop.entity.ProductSpecification;
import com.lcz.shop.entity.vo.CategoryVO;
import com.lcz.shop.entity.vo.HotCategoryVO;
import com.lcz.shop.entity.vo.KindAttribute;
import com.lcz.shop.entity.vo.KindVO;
import com.lcz.shop.mapper.CategoryMapper;
import com.lcz.shop.mapper.ProductCategoryMapper;
import com.lcz.shop.mapper.ProductDetailMapper;
import com.lcz.shop.mapper.ProductImageMapper;
import com.lcz.shop.mapper.ProductMapper;
import com.lcz.shop.mapper.ProductParameterMapper;
import com.lcz.shop.mapper.ProductSpecificationMapper;
import com.lcz.shop.mapper.SpecificationAttributeMapper;
import com.lcz.shop.mapper.SpecificationMapper;
import com.lcz.shop.service.CategoryService;
import com.lcz.shop.service.ProductService;

import net.sf.json.JSONObject;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMappert;
    @Autowired
    private ProductDetailMapper productDetailMapper;
    @Autowired
    private ProductImageMapper productImageMapper;
    @Autowired
    private ProductParameterMapper productParameterMapper;
    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;
    @Autowired
    private SpecificationAttributeMapper specificationAttributeMapper;
    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryService categoryService;
	
	@Override
	public ArrayList<HotCategoryVO> getHotCategory() {
		
		ArrayList<HotCategoryVO> categorys = productMapper.getAllCategory();
        for (HotCategoryVO hot : categorys) {
            List<CategoryVO> child = hot.getChildrenCategorys();
            for (CategoryVO vo : child) {
                List<Product> list = productMapper.getHotProduct(vo);
                vo.setProducts(list);
            }
        }
        return categorys;
	}

	@Override
	public Product getProduct(Long productNumber) {
		Product product = productMapper.selectByProductNumber(productNumber);
        return product;
	}

	@Override
	public List<ProductImage> getProductImages(Long productId) {
		List<ProductImage> product =productImageMapper.selectByProductId(productId);
        return product;
	}

	@Override
	public List<ProductParameter> getProductParameter(Long productId) {
		List<ProductParameter> product = productParameterMapper.selectByProductId(productId);
		return product;
	}

	@Override
	public ProductDetail getProductDetail(Long productId) {
		ProductDetail detail = productDetailMapper.selectByProductId(productId);
		return detail;
	}

	@Override
	public Map<String, Object> getProductSpecification(Long productId) {
		Map<String, Object> resultMap = new HashMap<>();
        // 商品规格进行拆分
        List<ProductSpecification> productSpecifications = productSpecificationMapper.selectByProductId(productId);
        for(ProductSpecification ps : productSpecifications) {
            if(ps.getSpec() != null && (!ps.getSpec().equals(""))){
                String[] sp = ps.getSpec().split(",");
                String s = changeArray(sp);
                resultMap.put(s, JSONObject.fromObject(ps));
            } else {
                resultMap.put("default", JSONObject.fromObject(ps));
            }
        }
        return resultMap;
	}

	@Override
	public List<KindVO> getProductKind(Long productId) {
		 //先根据商品找出父类
        Category category = categoryMapper.selectParentCategoryByProductId(productId);
        //父类查找所有的规格组合
        List<KindVO> kindVOs = productSpecificationMapper.selectSpecByCategoryId(category.getCategoryId());
        //查找当前商品的规格组合 去除不存在的规格
        List<ProductSpecification> list = productSpecificationMapper.selectByProductId(productId);
        Set<String> sset = new HashSet<String>();
        Set<String> set = Collections.synchronizedSet(sset);
        for(ProductSpecification ops : list) {
            if(ops.getSpec() != null && (!"".equals(ops.getSpec()))) {
                String[] sp = ops.getSpec().split(",");
                for(String s : sp) {
                    set.add(s);
                }
            }
        }
        for(KindVO k : kindVOs) {
            List<KindAttribute> l = k.getKindAttributes();
            List<KindAttribute> ll = new ArrayList<KindAttribute>();
            int[] index = null;
            for(int i= 0; i < l.size(); i++ ) {
                KindAttribute ka = l.get(i);
                Boolean flag = false;
                for(String s : set) {
                    if(Long.parseLong(s) == ka.getSpecAttrId()) {
                        flag = true;
                        break;
                    }
                }
                if(flag == false) {
                    ll.add(ka);
                }
            }
            for(int i= 0; i < ll.size(); i++) {
                l.remove(ll.get(i));
            }
        }
        return kindVOs;
	}

	@Override
	public Product getProductBySpecNumber(Long productSpecNumber) {
		Product product = productMapper.getProductBySpecNumber(productSpecNumber);
		return product;
	}

	@Override
	public List<Product> pageProductInfo(Long categoryId, Integer sort, Integer page, Integer limit) {
		if(categoryId == 1) {
            PageHelper.startPage(page, 8);
            List<Product> list=null;
            switch (sort) {
			case 1:
				list=productMapper.selectAllOrderByCreateTime();
				break;
			case 2:
				list=productMapper.selectAllOrderBySaleVolumeDesc();
				break;
			case 6:
				list=productMapper.selectAllOrderByPriceAsc();
				break;
			case 7:
				list=productMapper.selectAllOrderByPriceDesc();
				break;
				
			default:
				list=productMapper.selectAll();
				break;
			}
            this.l=(Page<Product>)list;
            return list;
        }
        // 根据类目ID查找子类目
        List<Category> lowerCategories = categoryService.listLowerCategories(categoryId);
        List<String> categoryIds = new ArrayList<String>();
        //如果有子目录
        if(lowerCategories != null || lowerCategories.size() != 0) {
            for(Category os : lowerCategories) {
                categoryIds.add(String.valueOf(os.getCategoryId()));
            }
        }
        if(lowerCategories.size() == 0){
            //没有子目录
            categoryIds.add(String.valueOf(categoryId));
        }
        PageHelper.startPage(page, limit);
        List<Product> list = null;
        switch (sort) {
		case 1:
			list=productMapper.listByPageOrderByCreateTime(categoryIds);
			break;
		case 2:
			list=productMapper.listByPageOrderBySaleVolumeDesc(categoryIds);
			break;
		case 6:
			list=productMapper.listByPageOrderByPriceAsc(categoryIds);
			break;
		case 7:
			list=productMapper.listByPageOrderByPriceDesc(categoryIds);
			break;
			
		default:
			list=productMapper.listByPage(categoryIds);
			break;
		}
        
        this.l=(Page<Product>)list;
        return list;
	}
	
	private static void reverseArray(String[] Array) {
        ArrayList<String> array_list = new ArrayList<String>();
        for (int i = 0; i < Array.length; i++) {
            array_list.add(Array[Array.length - i - 1]);
        }
        Array = array_list.toArray(Array);
    }

    private static String changeArray(String[] Array) {
        String s = "";
        for(int i =0; i < Array.length; i++) {

            if(i == Array.length-1) {
                s = s + Array[i];
                break;
            }
            s = s + Array[i] + ",";
        }
        return s;
    }


	@Override
	public List<Product> pageSearchProduct(String search, Integer sort, Integer page, Integer limit) {
		PageHelper.startPage(page, 8);
        List<Product> list=null;
        switch (sort) {
		case 1:
			list=productMapper.searchAllOrderByCreateTime(search);
			break;
		case 2:
			list=productMapper.searchAllOrderBySaleVolumeDesc(search);
			break;
		case 6:
			list=productMapper.searchAllOrderByPriceAsc(search);
			break;
		case 7:
			list=productMapper.searchAllOrderByPriceDesc(search);
			break;
			
		default:
			list=productMapper.searchAll(search);
			break;
		}
        this.l=(Page<Product>)list;
        return list;
	}

	private Page<Product> l ;
	
	@Override
	public Page<Product> getL() {
		return this.l;
	}
	
	@Override
	public void setL(Page<Product> l) {
		this.l=l;
	}

	@Override
	public Boolean addProduct(Product product) {
		Long Id = productMapper.insert(product);
		return Id>0;
	}

	@Override
	public Boolean addProductCategory(ProductCategory productCategory) {
		int insert = productCategoryMappert.insert(productCategory);
		return insert>0;
	}

	@Override
	public Boolean addProductDetail(ProductDetail productDetail) {
		int insert = productDetailMapper.insert(productDetail);
		return insert>0;
	}

	@Override
	public Boolean addProductImages(List<ProductImage> list) {
		Integer insertImages = productImageMapper.insertImages(list);
		return insertImages==list.size();
	}

	@Override
	public Boolean addProductParameter(List<ProductParameter> list) {
		Integer insertParameters = productParameterMapper.insertParameters(list);
		return insertParameters==list.size();
	}

	@Override
	public List<Product> allProductOnShelves(Long categoryId) {
		 List<Product> onShelvesProduct = productMapper.selectAllOnShelvesProduct();
		return onShelvesProduct;
	}

	@Override
	public List<Product> allProductOutShelves(Long categoryId) {
		List<Product> outShelvesProduct = productMapper.selectAllOutShelvesProduct();
		return outShelvesProduct;
	}

	@Override
	public Boolean shelvesProduct(ProductSpecification productSpecification) {
		Integer updateByProductId = productSpecificationMapper.updateByProductId(productSpecification);
		return updateByProductId>0;
	}

	@Override
	public Boolean addProductSpecification(ProductSpecification productSpecification) {
		productSpecificationMapper.insert(productSpecification);
		return null;
	}

	@Override
	public Boolean updateProductSpecification(List<ProductSpecification> productSpecifications) {
		boolean flag=true;
		for (ProductSpecification productSpecification : productSpecifications) {
			Integer updateByProductId = productSpecificationMapper.updateByProductId(productSpecification);
			if (updateByProductId==0) {
				flag=false;
			}
		}
		return flag;
	}

	@Override
	public Integer getSalesVolume(Long productId) {
		Integer saleVolume = productSpecificationMapper.getSaleVolumeByProductId(productId);
		return saleVolume;
	}

}
