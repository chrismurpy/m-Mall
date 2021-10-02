package com.murphy.mall.order.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.order.config.TokenDecode;
import com.murphy.mall.order.po.Address;
import com.murphy.mall.order.service.IAddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 收货地址 - Controller
 * @Author murphy
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController<IAddressService, Address> {

    @Autowired
    private TokenDecode tokenDecode;

    /**
     * 根据实体条件查询
     * @return
     */
    @ApiOperation(value="查询", notes="根据实体条件查询")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @Override
    public List<Address> list(Address entity) {
        String username = null;
        try {
            username = tokenDecode.getUserInfo().get("user_name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        entity.setUsername(username);

        //根据当前用户查询收件人地址
        return service.list(entity);
    }
}
