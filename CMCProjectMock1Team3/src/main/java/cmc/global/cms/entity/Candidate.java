package cmc.global.cms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the candidate database table.
 * 
 */
@Entity
@NamedQuery(name="Candidate.findAll", query="SELECT c FROM Candidate c")
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="candidate_id")
	private int candidateId;

	//bi-directional many-to-one association to SemesterExam
	@ManyToOne
	@JoinColumn(name="semester_exam_id")
	private SemesterExam semesterExam;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to CandidateTest
	@OneToMany(mappedBy="candidate")
	private List<CandidateTest> candidateTests;

	public Candidate() {
	}

	public int getCandidateId() {
		return this.candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public SemesterExam getSemesterExam() {
		return this.semesterExam;
	}

	public void setSemesterExam(SemesterExam semesterExam) {
		this.semesterExam = semesterExam;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CandidateTest> getCandidateTests() {
		return this.candidateTests;
	}

	public void setCandidateTests(List<CandidateTest> candidateTests) {
		this.candidateTests = candidateTests;
	}

	public CandidateTest addCandidateTest(CandidateTest candidateTest) {
		getCandidateTests().add(candidateTest);
		candidateTest.setCandidate(this);

		return candidateTest;
	}

	public CandidateTest removeCandidateTest(CandidateTest candidateTest) {
		getCandidateTests().remove(candidateTest);
		candidateTest.setCandidate(null);

		return candidateTest;
	}

}