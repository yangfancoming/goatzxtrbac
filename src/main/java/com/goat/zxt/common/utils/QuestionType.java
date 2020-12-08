package com.goat.zxt.common.utils;

import java.util.HashMap;
import java.util.Map;


public class QuestionType {

    public static Map<String,String> kv = new HashMap<>(16);

    // 根据前端2个下拉框中的key值(字典表中的key)  来获取要查询哪张试题表
    // 根据试题类型 获取 对应的表名
    // AnswerType 问答题
    // ClozeType 完形填空题
    // CompletionType 理解题
    // JudgeType 判断题
    // MultiType 多选题
    // SingleType 单选题
    static {
        kv.put("0","b_single_question"); // 单选
        kv.put("1","b_single_question"); // 多选
        kv.put("2","b_single_question"); // 判断
        kv.put("3","b_fill_question"); // 填空
        kv.put("4","b_single_question"); // 问答
        kv.put("5","b_single_question"); // 理解
        kv.put("6","b_single_question"); // 不定项选择
        kv.put("7","b_single_question"); // 排序
        kv.put("8","b_single_question"); // 匹配
        kv.put("9","b_single_question"); // 完形填空
    }

}
