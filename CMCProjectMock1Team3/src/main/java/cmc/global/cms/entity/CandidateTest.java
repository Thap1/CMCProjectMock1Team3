package cmc.global.cms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the candidate_test database table.
 * 
 */
@Entity
@Table(name="candidate_test")
@NamedQuery(name="CandidateTest.findAll", query="SELECT c FROM CandidateTest c")
public class CandidateTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="candidate_test_id")
	private int candidateTestId;

	@Column(name="correct_number")
	private int correctNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	private Date endTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime;

	//bi-directional many-to-one association to Candidate
	@ManyToOne
	@JoinColumn(name="candidate_id")
	private Candidate candidate;

	//bi-directional many-to-one association to Test
	@ManyToOne
	@JoinColumn(name="test_id")
	private Test test;

	//bi-directional many-to-one association to TestResult
	@OneToMany(mappedBy="candidateTest")
	private List<TestResult> testResults;

	public CandidateTest() {
	}

	public int getCandidateTestId() {
		return this.candidateTestId;
	}

	public void setCandidateTestId(int candidateTestId) {
		this.candidateTestId = candidateTestId;
	}

	public int getCorrectNumber() {
		return this.correctNumber;
	}

	public void setCorrectNumber(int correctNumber) {
		this.correctNumber = correctNumber;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public List<TestResult> getTestResults() {
		return this.testResults;
	}

	public void setTestResults(List<TestResult> testResults) {
		this.testResults = testResults;
	}

	public TestResult addTestResult(TestResult testResult) {
		getTestResults().add(testResult);
		testResult.setCandidateTest(this);

		return testResult;
	}

	public TestResult removeTestResult(TestResult testResult) {
		getTestResults().remove(testResult);
		testResult.setCandidateTest(null);

		return testResult;
	}

}