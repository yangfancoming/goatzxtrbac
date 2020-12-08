//package com.goat.zxt.system.dialect;
//
//
//import com.goat.zxt.system.entity.SysDict;
//import com.goat.zxt.system.service.DictService;
//import org.springframework.context.ApplicationContext;
//import org.thymeleaf.context.ITemplateContext;
//import org.thymeleaf.model.IModel;
//import org.thymeleaf.model.IModelFactory;
//import org.thymeleaf.model.IProcessableElementTag;
//import org.thymeleaf.processor.element.AbstractElementTagProcessor;
//import org.thymeleaf.processor.element.IElementTagStructureHandler;
//import org.thymeleaf.spring5.context.SpringContextUtils;
//import org.thymeleaf.templatemode.TemplateMode;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2020/8/25.
// * @ Description: 自定义字典标签处理器
// * @ author  山羊来了
// * @ date 2020/8/25---12:50
// */
//public class SysDictTagProcessor extends AbstractElementTagProcessor {
//
//    // 标签名
//    private static final String TAG_NAME = "dict";
//
//    // 优先级
//    private static final int PRECEDENCE = 10000;
//
//    public SysDictTagProcessor(String dialectPrefix) {
//        super(
//                TemplateMode.HTML,  // 此处理器将仅应用于HTML模式
//                dialectPrefix, // 要应用于名称的匹配前缀
//                TAG_NAME, // 标签名称：匹配此名称的特定标签
//                true, // 将标签前缀应用于标签名称
//                null,  // 无属性名称：将通过标签名称匹配
//                false, // 没有要应用于属性名称的前缀
//                PRECEDENCE  // 优先(内部方言自己的优先)
//        );
//    }
//
//    /**
//     * 处理自定义标签 DOM 结构
//     *
//     * @param iTemplateContext            模板页上下文
//     * @param iProcessableElementTag      待处理标签
//     * @param iElementTagStructureHandler 元素标签结构处理器
//     */
//    @Override
//    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag, IElementTagStructureHandler iElementTagStructureHandler) {
//        // 获取 Spring 上下文
//        ApplicationContext applicationContext = SpringContextUtils.getApplicationContext(iTemplateContext);
//        String name = iProcessableElementTag.getAttributeValue("name");
//        // 注入字典
//        DictService dictService = applicationContext.getBean(DictService.class);
//        //<sys:dict type="article_type" name="type" class="form-control m-input" /><
//        //   article_type
//        String tableName = iProcessableElementTag.getAttributeValue("tableName"); // b_subject
//
//        // 提交表单时的 name     type
//        String fieldName = iProcessableElementTag.getAttributeValue("fieldName"); // type
//
//        // 元素的 class 样式     form-control m-input
//        String dictClass = iProcessableElementTag.getAttributeValue("class");
//        SysDict params = new SysDict();
//        params.setTableName(tableName);
//        params.setFieldName(fieldName);
//        // 根据类型查询出字典列表
//        List<SysDict> dictList = dictService.findDictKV(params);
//
//        // 创建将替换自定义标签的 DOM 结构
//        IModelFactory modelFactory = iTemplateContext.getModelFactory();
//        IModel model = modelFactory.createModel();
//
//        // 这里是将字典的内容拼装成一个下拉框
//        model.add(modelFactory.createOpenElementTag(String.format("select name='%s' id='%s' class='%s'", name, name,dictClass)));
//        // 判断 isAll 属性，如果为true 则下拉框第一项显示 <所有项>。
//        String isAll = iProcessableElementTag.getAttributeValue("isAll");
//        if (Boolean.valueOf(isAll)){
//            model.add(modelFactory.createOpenElementTag(String.format("option value='%s'", "")));
//            model.add(modelFactory.createText("所有"));
//            model.add(modelFactory.createCloseElementTag("option"));
//        }
//
//        for (SysDict dict : dictList) {
//            //  <option value="" selected>所有</option>
//            //  <option value="" selected>
//            model.add(modelFactory.createOpenElementTag(String.format("option value='%s'", dict.getKeyy())));
//            // 所有
//            model.add(modelFactory.createText(dict.getValue()));
//            // </option>
//            model.add(modelFactory.createCloseElementTag("option"));
//        }
//        model.add(modelFactory.createCloseElementTag("select"));
//        // 利用引擎替换整合标签
//        iElementTagStructureHandler.replaceWith(model, false);
//    }
//}
