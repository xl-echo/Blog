package com.echo.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.echo.blog.dao.ProductDao;
import com.echo.blog.po.ProductPo;
import com.echo.blog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author echo
 * @date 2020/2/15 14:15
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final RedisTemplate<String, String> redisTemplate;

    private final ProductDao productDao;

    @Override
    public ProductPo getProduct() {
        ProductPo productPo = null;
        String string = redisTemplate.opsForValue().get("productPo");
        if (StringUtils.isEmpty(string)) {
            productPo = productDao.selectByPrimaryKey(1L);
            redisTemplate.opsForValue().set("productPo", JSON.toJSONString(productPo));
            redisTemplate.opsForValue().set("productNumber", productPo.getProductNumber() + "");
        } else {
            productPo = JSON.parseObject(string, ProductPo.class);
        }
        return productPo;
    }

    @Override
    public String getProductNumber() {
        return redisTemplate.opsForValue().get("productNumber");
    }

}
