package com.java.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Description:	   添加员工实体类<br/>
 * Date:     0021, September 21 22:23 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
@Data
public class Emp {

    @NotNull(message = "*格式错误!")
    @NotBlank(message = "*格式错误!")
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{2,20}", message = "*格式错误!")
    private String name;

    @NotNull(message = "*格式错误!")
    @NotBlank(message = "*格式错误!")
    @Pattern(regexp = "1[356789]\\d{9}", message = "*格式错误!")
    private String tel;

    @NotNull(message = "*格式错误!")
    @NotBlank(message = "*格式错误!")
    @Pattern(regexp = "[男女]", message = "*格式错误!")
    private String gender;

    @NotNull(message = "*格式错误!")
    @NotBlank(message = "*格式错误!")
    @Pattern(regexp = "\\d{17}[0-9X]", message = "*格式错误!")
    private String idCard;

    @NotNull(message = "*格式错误!")
    @NotBlank(message = "*格式错误!")
    @Pattern(regexp = "[1-9]\\d*", message = "*格式错误!")
    private String deptID;

    @NotNull(message = "*格式错误!")
    @NotBlank(message = "*格式错误!")
    @Pattern(regexp = ".{2,200}", message = "*格式错误!")
    private String address;

    @NotNull(message = "*格式错误!")
    @NotBlank(message = "*格式错误!")
    @Pattern(regexp = "\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+", message = "*格式错误!")
    private String email;

    @NotNull(message = "*格式错误!")
    @NotBlank(message = "*格式错误!")
    @Pattern(regexp = "\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}", message = "*格式错误!")
    private String beginDate;

    //不接受表单参数，往yuanggong表中插入数据时，接收主键
    private Long id;

    public Long getId() {
        return id;
    }
}
