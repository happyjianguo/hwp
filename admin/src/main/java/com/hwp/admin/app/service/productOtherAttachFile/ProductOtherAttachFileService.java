package com.hwp.admin.app.service.productOtherAttachFile;

import com.hwp.common.model.product.bean.Product;
import com.hwp.common.model.productOtherAttachFile.bean.ProductOtherAttachFile;

import java.util.List;

public interface ProductOtherAttachFileService {
    /**
     * @Description 动态参数添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    ProductOtherAttachFile addProductOtherAttachFile(ProductOtherAttachFile record);

    /**
     * @Description 同时向产品信息表，产品附件表中添加数据
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int addProductAndProductOtherAttachFile(Product product, ProductOtherAttachFile record);

    int addProductAndFile(Product product, ProductOtherAttachFile record);

    /**
     * @Description 根据Id查找产品其他附件信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    ProductOtherAttachFile selectProductOtherAttachFileById(String id);

    /**
     * @Description 根据产品Id查找产品其他附件信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    ProductOtherAttachFile selectProductOtherAttachFileByProductId(String productId);

    /**
     * @Description 获取产品其他附件信息列表
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    List<ProductOtherAttachFile> selectProductOtherAttachFileList(ProductOtherAttachFile record);

    /**
     * @Description 根据id修改产品其他附件信息
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateProductOtherAttachFileById(ProductOtherAttachFile record);

    /**
     * @Description 根据id修改产品信息及附件信息
     * @Author 吕剑
     * @UpdateDate 2019/08/02 10:06
     */
    int updateProductAndProductOtherAttachFileById(Product product, ProductOtherAttachFile record);

    /**
     * @Description 修改产品其他附件信息有效性
     * @Author 吕剑
     * @UpdateDate 2019/07/18 10:12
     */
    int updateDataStatusById(ProductOtherAttachFile record);
}
