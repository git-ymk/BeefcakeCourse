package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamShareRequestVO {
    Long teamShareId;
    MasterCourseVO masterCourse;
}
