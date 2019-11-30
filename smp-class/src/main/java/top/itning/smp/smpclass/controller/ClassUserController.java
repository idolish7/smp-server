package top.itning.smp.smpclass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.itning.smp.smpclass.entity.RestModel;
import top.itning.smp.smpclass.security.LoginUser;
import top.itning.smp.smpclass.security.MustStudentLogin;
import top.itning.smp.smpclass.security.MustTeacherLogin;
import top.itning.smp.smpclass.service.ClassUserService;

import java.util.Date;

/**
 * @author itning
 */
@RestController
public class ClassUserController {
    private final ClassUserService classUserService;

    @Autowired
    public ClassUserController(ClassUserService classUserService) {
        this.classUserService = classUserService;
    }

    /**
     * 获取所有学生班级
     *
     * @param pageable 分页
     * @return 学生班级
     */
    @GetMapping("/student_class_users")
    public ResponseEntity<?> getAllStudentClassUser(@PageableDefault(size = 20, sort = {"gmtCreate"}, direction = Sort.Direction.DESC)
                                                            Pageable pageable,
                                                    @MustStudentLogin LoginUser loginUser) {
        return RestModel.ok(classUserService.getAllStudentClassUsers(loginUser, pageable));
    }

    /**
     * 加入班级
     *
     * @param classNum 班号
     * @return ResponseEntity
     */
    @PostMapping("/join_class")
    public ResponseEntity<?> joinClass(@MustStudentLogin LoginUser loginUser, @RequestParam String classNum) {
        return RestModel.created(classUserService.joinClass(loginUser, classNum));
    }

    /**
     * 学生退出班级
     *
     * @param studentClassId 班级ID
     * @return ResponseEntity
     */
    @PostMapping("/quit_class")
    public ResponseEntity<?> quitClass(@MustStudentLogin LoginUser loginUser, @RequestParam String studentClassId) {
        classUserService.quitClass(loginUser, studentClassId);
        return RestModel.noContent();
    }

    /**
     * 创建班级
     *
     * @param className 班级名
     * @return 创建的班级
     */
    @PostMapping("/new_class")
    public ResponseEntity<?> newClass(@MustTeacherLogin LoginUser loginUser, @RequestParam String className) {
        return RestModel.created(classUserService.newClass(loginUser, className));
    }

    /**
     * 删除班级
     *
     * @param studentClassId 班级ID
     * @return ResponseEntity
     */
    @PostMapping("/del_class")
    public ResponseEntity<?> delClass(@MustTeacherLogin LoginUser loginUser, @RequestParam String studentClassId) {
        classUserService.delClass(loginUser, studentClassId);
        return RestModel.noContent();
    }

    /**
     * 获取教师创建的班级
     *
     * @param pageable 分页
     * @return ResponseEntity
     */
    @GetMapping("/student_class")
    public ResponseEntity<?> studentClass(@PageableDefault(size = 20, sort = {"gmtCreate"}, direction = Sort.Direction.DESC)
                                                  Pageable pageable,
                                          @MustTeacherLogin LoginUser loginUser) {
        return RestModel.ok(classUserService.getAllStudentClass(loginUser, pageable));
    }

    /**
     * 获取所有签到元数据
     *
     * @param studentClassId 班级ID
     * @param pageable       分页
     * @return ResponseEntity
     */
    @GetMapping("/student_class_check_meta_data/{studentClassId}")
    public ResponseEntity<?> getAllStudentClassCheckMetaData(@PageableDefault(size = 20, sort = {"gmtCreate"}, direction = Sort.Direction.DESC)
                                                                     Pageable pageable,
                                                             @MustTeacherLogin LoginUser loginUser,
                                                             @PathVariable("studentClassId") String studentClassId) {
        return RestModel.ok(classUserService.getAllStudentClassCheckMetaData(studentClassId, loginUser, pageable));
    }

    /**
     * 获取班级请假信息
     *
     * @param studentClassId 班级ID
     * @param whereDay       哪天
     * @return ResponseEntity
     */
    @GetMapping("/student_class_leave")
    public ResponseEntity<?> getStudentClassLeave(@MustTeacherLogin LoginUser loginUser,
                                                  @RequestParam String studentClassId,
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  @RequestParam("whereDay") Date whereDay) {
        return RestModel.ok(classUserService.getStudentClassLeave(loginUser, studentClassId, whereDay));
    }
}