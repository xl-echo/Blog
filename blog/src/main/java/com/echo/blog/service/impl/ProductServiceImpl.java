package com.echo.blog.service.impl;

import com.echo.blog.dao.ProductDao;
import com.echo.blog.po.ProductPo;
import com.echo.blog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品服务实现类
 * 使用Spring Cache替代Redis
 * @author echo
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    /**
     * 根据ID获取产品
     * 使用Spring Cache缓存
     * @param id 产品ID
     * @return 产品信息
     */
    @Override
    @Cacheable(value = "products", key = "#id", unless = "#result == null")
    public ProductPo getProductById(Long id) {
        return productDao.selectByPrimaryKey(id);
    }

    /**
     * 获取所有产品
     * 使用Spring Cache缓存
     * @return 产品列表
     */
    @Override
    @Cacheable(value = "products", key = "'all'")
    public List<ProductPo> getAllProducts() {
        return productDao.selectAll();
    }

    /**
     * 创建产品
     * @param product 产品信息
     * @return 创建的产品
     */
    @Override
    @CachePut(value = "products", key = "#result.id")
    public ProductPo createProduct(ProductPo product) {
        productDao.insert(product);
        return product;
    }

    /**
     * 更新产品
     * @param product 产品信息
     * @return 更新后的产品
     */
    @Override
    @CachePut(value = "products", key = "#product.id")
    public ProductPo updateProduct(ProductPo product) {
        productDao.updateByPrimaryKey(product);
        return product;
    }

    /**
     * 删除产品
     * @param id 产品ID
     */
    @Override
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        productDao.deleteByPrimaryKey(id);
    }

    /**
     * 按分类获取产品
     * 使用Spring Cache缓存
     * @param categoryId 分类ID
     * @return 产品列表
     */
    @Override
    @Cacheable(value = "products", key = "'category:' + #categoryId")
    public List<ProductPo> getProductsByCategory(Long categoryId) {
        return productDao.selectByCategoryId(categoryId);
    }

    /**
     * 按关键词搜索产品
     * @param keyword 关键词
     * @return 产品列表
     */
    @Override
    public List<ProductPo> searchProducts(String keyword) {
        // 搜索结果不缓存，因为关键词可能很多
        return productDao.searchByKeyword(keyword);
    }

    /**
     * 清空所有产品缓存
     */
    @CacheEvict(value = "products", allEntries = true)
    public void clearAllProductCache() {
        // 清空缓存
    }
}
