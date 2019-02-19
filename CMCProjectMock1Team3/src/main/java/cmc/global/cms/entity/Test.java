package cmc.global.cms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the test database table.
 * 
 */
@Entity
@NamedQuery(name="Test.findAll", query="SELECT t FROM Test t")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="test_id")
	private int testId;

	@Column(name="candidate_id")
	private int candidateId;

	private int status;

	@Column(name="test_name")
	private String testName;

	//bi-directional many-to-one association to Exam
	@ManyToOne
	@JoinColumn(name="exam_id")
	private Exam exam;

	//bi-directional many-to-one association to SemesterExam
	@ManyToOne
	@JoinColumn(name="semester_exam_id")
	private SemesterExam semesterExam;

	//bi-directional many-to-one association to CandidateTest
	@OneToMany(mappedBy="test")
	private List<CandidateTest> candidateTests;

	public Test() {
	}

	public int getTestId() {
		return this.testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getCandidateId() {
		return this.candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTestName() {
		return this.testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Exam getExam() {
		return this.exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public SemesterExam getSemesterExam() {
		return this.semesterExam;
	}

	public void setSemesterExam(SemesterExam semesterExam) {
		this.semesterExam = semesterExam;
	}

	public List<CandidateTest> getCandidateTests() {
		return this.candidateTests;
	}

	public void setCandidateTests(List<CandidateTest> candidateTests) {
		this.candidateTests = candidateTests;
	}

	public CandidateTest addCandidateTest(CandidateTest candidateTest) {
		getCandidateTests().add(candidateTest);
		candidateTest.setTest(this);

		return candidateTest;
	}

	public CandidateTest removeCandidateTest(CandidateTest candidateTest) {
		getCandidateTests().remove(candidateTest);
		candidateTest.setTest(null);

		return candidateTest;
	}

}