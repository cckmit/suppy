package com.zjjzfy.product.service.impl.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.interfaces.TbProductImgMapper;
import com.zjjzfy.pojo.TbProductImg;
import com.zjjzfy.pojo.TbProductImgExample;
import com.zjjzfy.product.service.impl.ProductImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImgServiceImpl implements ProductImgService {

    @Autowired
    private TbProductImgMapper productImgMapper;

    @Override
    public int insert(TbProductImg productImg) {
        return productImgMapper.insert(productImg);
    }

    @Override
    public int deleteById(int id) {
        return productImgMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SupplyResult selectProductImg(Integer id, String type) {
        TbProductImgExample example = new TbProductImgExample();
        TbProductImgExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(id);
        if (type != null && !type.equals("")) {
            criteria.andTypeEqualTo(type);
        }
        List<TbProductImg> imgs = productImgMapper.selectByExample(example);
        return SupplyResult.ok(imgs);
    }

}
