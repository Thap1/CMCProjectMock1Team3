package cmc.global.cms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the semester_exam database table.
 * 
 */
@Entity
@Table(name="semester_exam")
@NamedQuery(name="SemesterExam.findAll", query="SELECT s FROM SemesterExam s")
public class SemesterExam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="semester_exam_id")
	private String semesterExamId;

	@Column(name="create_by")
	private int createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_at")
	private Date endAt;

	@Column(name="semester_description")
	private String semesterDescription;

	@Column(name="semester_exam_name")
	private String semesterExamName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_at")
	private Date startAt;

	private byte status;

	//bi-directional many-to-one association to Candidate
	@OneToMany(mappedBy="semesterExam")
	private List<Candidate> candidates;

	//bi-directional many-to-one association to SemesterExamCode
	@OneToMany(mappedBy="semesterExam")
	private List<SemesterExamCode> semesterExamCodes;

	//bi-directional many-to-one association to Test
	@OneToMany(mappedBy="semesterExam")
	private List<Test> tests;

	public SemesterExam() {
	}

	public String getSemesterExamId() {
		return this.semesterExamId;
	}

	public void setSemesterExamId(String semesterExamId) {
		this.semesterExamId = semesterExamId;
	}

	public int getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public Date getEndAt() {
		return this.endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public String getSemesterDescription() {
		return this.semesterDescription;
	}

	public void setSemesterDescription(String semesterDescription) {
		this.semesterDescription = semesterDescription;
	}

	public String getSemesterExamName() {
		return this.semesterExamName;
	}

	public void setSemesterExamName(String semesterExamName) {
		this.semesterExamName = semesterExamName;
	}

	public Date getStartAt() {
		return this.startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public List<Candidate> getCandidates() {
		return this.candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Candidate addCandidate(Candidate candidate) {
		getCandidates().add(candidate);
		candidate.setSemesterExam(this);

		return candidate;
	}

	public Candidate removeCandidate(Candidate candidate) {
		getCandidates().remove(candidate);
		candidate.setSemesterExam(null);

		return candidate;
	}

	public List<SemesterExamCode> getSemesterExamCodes() {
		return this.semesterExamCodes;
	}

	public void setSemesterExamCodes(List<SemesterExamCode> semesterExamCodes) {
		this.semesterExamCodes = semesterExamCodes;
	}

	public SemesterExamCode addSemesterExamCode(SemesterExamCode semesterExamCode) {
		getSemesterExamCodes().add(semesterExamCode);
		semesterExamCode.setSemesterExam(this);

		return semesterExamCode;
	}

	public SemesterExamCode removeSemesterExamCode(SemesterExamCode semesterExamCode) {
		getSemesterExamCodes().remove(semesterExamCode);
		semesterExamCode.setSemesterExam(null);

		return semesterExamCode;
	}

	public List<Test> getTests() {
		return this.tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public Test addTest(Test test) {
		getTests().add(test);
		test.setSemesterExam(this);

		return test;
	}

	public Test removeTest(Test test) {
		getTests().remove(test);
		test.setSemesterExam(null);

		return test;
	}

}