package com.echo.blog.controller;

import com.echo.blog.config.result.Result;
import com.echo.blog.po.ProductPo;
import com.echo.blog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author echo
 * @date 2020/2/15 14:11
 **/
@RestController
@RequestMapping("/productController")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    /**
     * 获取产品信息
     *
     * @return Result<ProductPo>
     */
    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    public Result<ProductPo> getProduct() {
        Result<ProductPo> result = new Result<>();
        ProductPo productPo = productService.getProduct();
        Result.packageResultMethod(result, productPo);
        return result;
    }

    /**
     * 获取产品库存
     *
     * @return Result<String>
     */
    @RequestMapping(value = "/getProductNumber", method = RequestMethod.GET)
    public Result<String> getProductNumber() {
        Result<String> result = new Result<>();
        String productPoNumber = productService.getProductNumber();
        Result.packageResultMethod(result, productPoNumber);
        return result;
    }

}
