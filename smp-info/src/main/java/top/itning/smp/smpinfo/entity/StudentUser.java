package top.itning.smp.smpinfo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author itning
 */
@Data
@Entity(name = "student_user")
public class StudentUser implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(length = 36, columnDefinition = "char(36)")
    private String id;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 性别（true 男； false 女）
     */
    private boolean sex;
    /**
     * 年龄
     */
    private int age;
    /**
     * 学号
     */
    @Column(nullable = false)
    private String studentId;
    /**
     * 身份证号
     */
    @Column(length = 18, columnDefinition = "char(18)")
    private String idCard;
    /**
     * 政治面貌
     */
    private String politicalStatus;
    /**
     * 民族
     */
    private String ethnic;
    /**
     * 公寓信息
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "apartmentId")
    private Apartment apartment;
    /**
     * 床号
     */
    @Column(nullable = false)
    private String bedNum;
    /**
     * 创建时间
     */
    @Column(nullable = false)
    @CreationTimestamp
    private Date gmtCreate;
    /**
     * 更新时间
     */
    @Column(nullable = false)
    @UpdateTimestamp
    private Date gmtModified;
}
