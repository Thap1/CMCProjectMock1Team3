package cmc.global.cms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the question_level database table.
 * 
 */
@Entity
@Table(name="question_level")
@NamedQuery(name="QuestionLevel.findAll", query="SELECT q FROM QuestionLevel q")
public class QuestionLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="level_id")
	private int levelId;

	@Column(name="level_name")
	private String levelName;

	private int status;

	//bi-directional many-to-one association to Question
	@OneToMany(mappedBy="questionLevel")
	private List<Question> questions;

	public QuestionLevel() {
	}

	public int getLevelId() {
		return this.levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setQuestionLevel(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setQuestionLevel(null);

		return question;
	}

}