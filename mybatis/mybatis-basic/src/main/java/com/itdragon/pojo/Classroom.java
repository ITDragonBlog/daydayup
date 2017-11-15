package com.itdragon.pojo;

import java.io.Serializable;
import java.util.List;

// 学习 表的关联关系所用字段，一个教室关联一个老师（一对一），一个教室关联一群学生（一对多）
public class Classroom implements Serializable {

	private Integer id;
	private String room;
	private Teacher teacher;
	private List<Student> students;

	public Classroom() {
	}

	public Classroom(Integer id, String room, Teacher teacher, List<Student> students) {
		this.id = id;
		this.room = room;
		this.teacher = teacher;
		this.students = students;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Classroom [id=" + id + ", room=" + room + ", teacher=" + teacher + ", students=" + students + "]";
	}

}
