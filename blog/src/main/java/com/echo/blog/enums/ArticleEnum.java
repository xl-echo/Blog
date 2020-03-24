package com.echo.blog.enums;

/**
 * @author echo
 * @date 2020-01-20
 */
public enum ArticleEnum {
    /**
     * 未签约
     */
    ORIGINAL(0, "原创"),
    /**
     * 签约中
     */
    REPRODUCED(1, "转载"),
    /**
     * 签约成功
     */
    TRANSLATION(2, "翻译");

    private Integer value;
    private String desc;

    ArticleEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }
}
