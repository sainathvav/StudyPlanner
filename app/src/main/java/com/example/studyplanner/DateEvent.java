package com.example.studyplanner;

public class DateEvent {
    Integer lectureCount,assignCount,examsCount,studyCount;

    public DateEvent(Integer lectureCount, Integer assignCount, Integer examsCount, Integer studyCount) {
        this.lectureCount = lectureCount;
        this.assignCount = assignCount;
        this.examsCount = examsCount;
        this.studyCount = studyCount;
    }

    public Integer getLectureCount() {
        return lectureCount;
    }

    public void setLectureCount(Integer lectureCount) {
        this.lectureCount = lectureCount;
    }

    public Integer getAssignCount() {
        return assignCount;
    }

    public void setAssignCount(Integer assignCount) {
        this.assignCount = assignCount;
    }

    public Integer getExamsCount() {
        return examsCount;
    }

    public void setExamsCount(Integer examsCount) {
        this.examsCount = examsCount;
    }

    public Integer getStudyCount() {
        return studyCount;
    }

    public void setStudyCount(Integer studyCount) {
        this.studyCount = studyCount;
    }
}
