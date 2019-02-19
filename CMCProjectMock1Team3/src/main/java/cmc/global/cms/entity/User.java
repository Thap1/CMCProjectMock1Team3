package cmc.global.cms.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@NotBlank
	@Column(name = "email")
	private String email;

	@NotBlank
	@Column(name = "fullname")
	private String fullName;

	@NotBlank
	@Column(name = "mobile")
	private String mobile;

	@NotBlank
	@Column(name = "password")
	private String password;
	
	@Column(name = "status")
	private int status;

	// private Set<Role> roles = new HashSet<>();

	public User(@NotBlank String email, @NotBlank String fullName, @NotBlank String mobile, @NotBlank String password,
			int status) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.mobile = mobile;
		this.password = password;
		this.status = status;
	}

	// bi-directional many-to-one association to Candidate
	@OneToMany(mappedBy = "user")
	private List<Candidate> candidates;

	// bi-directional many-to-one association to Category
	@OneToMany(mappedBy = "user")
	private List<Category> categories;

	// bi-directional many-to-one association to Exam
	@OneToMany(mappedBy = "user1")
	private List<Exam> exams1;

	// bi-directional many-to-one association to Exam
	@OneToMany(mappedBy = "user2")
	private List<Exam> exams2;

	// bi-directional many-to-one association to Question
	@OneToMany(mappedBy = "user")
	private List<Question> questions;

	// bi-directional many-to-many association to Group
	@ManyToMany
	@JoinTable(name = "user_group", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "group_id") })
	private List<Group> groups;

	// bi-directional many-to-many association to Role
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles = new HashSet<>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
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
		candidate.setUser(this);

		return candidate;
	}

	public Candidate removeCandidate(Candidate candidate) {
		getCandidates().remove(candidate);
		candidate.setUser(null);

		return candidate;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category addCategory(Category category) {
		getCategories().add(category);
		category.setUser(this);

		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		category.setUser(null);

		return category;
	}

	public List<Exam> getExams1() {
		return this.exams1;
	}

	public void setExams1(List<Exam> exams1) {
		this.exams1 = exams1;
	}

	public Exam addExams1(Exam exams1) {
		getExams1().add(exams1);
		exams1.setUser1(this);

		return exams1;
	}

	public Exam removeExams1(Exam exams1) {
		getExams1().remove(exams1);
		exams1.setUser1(null);

		return exams1;
	}

	public List<Exam> getExams2() {
		return this.exams2;
	}

	public void setExams2(List<Exam> exams2) {
		this.exams2 = exams2;
	}

	public Exam addExams2(Exam exams2) {
		getExams2().add(exams2);
		exams2.setUser2(this);

		return exams2;
	}

	public Exam removeExams2(Exam exams2) {
		getExams2().remove(exams2);
		exams2.setUser2(null);

		return exams2;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setUser(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setUser(null);

		return question;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}