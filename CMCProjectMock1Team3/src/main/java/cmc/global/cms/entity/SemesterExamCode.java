package cmc.global.cms.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the semester_exam_code database table.
 * 
 */
@Entity
@Table(name="semester_exam_code")
@NamedQuery(name="SemesterExamCode.findAll", query="SELECT s FROM SemesterExamCode s")
public class SemesterExamCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="semester_exam_codecol")
	private String semesterExamCodecol;

	//bi-directional many-to-one association to SemesterExam
	@ManyToOne
	@JoinColumn(name="semester_exam_id")
	private SemesterExam semesterExam;

	public SemesterExamCode() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSemesterExamCodecol() {
		return this.semesterExamCodecol;
	}

	public void setSemesterExamCodecol(String semesterExamCodecol) {
		this.semesterExamCodecol = semesterExamCodecol;
	}

	public SemesterExam getSemesterExam() {
		return this.semesterExam;
	}

	public void setSemesterExam(SemesterExam semesterExam) {
		this.semesterExam = semesterExam;
	}

}