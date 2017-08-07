package com.lisheny.mytab.javabeens;

import java.util.List;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/06/28
 *     desc   : test
 *     version: 1.0
 * </pre>
 */
public class Student {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    private String name;//姓名
    private List<Course> coursesList;//所修的课程

    class  Course {
        private String name;//课程名
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
