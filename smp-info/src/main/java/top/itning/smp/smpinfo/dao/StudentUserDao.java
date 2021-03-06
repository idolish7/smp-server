package top.itning.smp.smpinfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import top.itning.smp.smpinfo.entity.Apartment;
import top.itning.smp.smpinfo.entity.StudentUser;

import java.util.List;

/**
 * @author itning
 */
public interface StudentUserDao extends JpaRepository<StudentUser, String>, JpaSpecificationExecutor<StudentUser> {
    /**
     * 计算公寓人数
     *
     * @param apartmentId 公寓ID
     * @return 人数
     */
    @Query(value = "select count(*) from student_user where apartment_id = ?", nativeQuery = true)
    long countByApartmentId(String apartmentId);

    /**
     * 查看该学号是否重复
     *
     * @param studentId 学号
     * @return 重复
     */
    boolean existsByStudentId(String studentId);

    /**
     * 查看某公寓某寝室床铺是谁
     *
     * @param apartment 公寓
     * @param roomNum   寝室
     * @param bedNum    床号
     * @return 信息
     */
    @Query(value = "select * from student_user s where s.apartment_id=?1 and s.room_num = ?2 and s.bed_num = ?3 limit 1", nativeQuery = true)
    StudentUser findByApartmentAndRoomNumAndBedNum(Apartment apartment, String roomNum, String bedNum);

    /**
     * 根据导员ID计算学生人数
     *
     * @param belongCounselorId 导员ID
     * @return 学生人数
     */
    long countAllByBelongCounselorId(String belongCounselorId);

    /**
     * 根据导员ID查询全部学生信息
     *
     * @param belongCounselorId 导员ID
     * @return 全部学生信息
     */
    List<StudentUser> findAllByBelongCounselorId(String belongCounselorId);

    /**
     * 根据学号查询学生
     *
     * @param studentId 学号
     * @return 学生信息
     */
    StudentUser findByStudentId(String studentId);

    /**
     * 根据身份证号查询学生是否存在
     *
     * @param idCard 身份证号
     * @return 存在返回<code>true</code>
     */
    boolean existsByIdCard(String idCard);
}