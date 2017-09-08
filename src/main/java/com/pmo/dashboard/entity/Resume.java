package com.pmo.dashboard.entity;

/**
 * 候选人实体类
 * 
 * @author dilu
 * @version 1.0 2017-8-28 08:59:23
 */
public class Resume {

	//候选人编号
	private String id;
	
	//姓名
	private String candidateName;
	
	//年龄
	private String age;
	
	//性别
	private String gender;
	
	//电话
	private String tel;
	
	//学历 0:博士 1：研究生，2：本科，3：大专，4：高中
	private String education;
	
	//学校名称
	private String college;
	
	//是否是计算机专业   0代表是，1代表否
	private String major;
	
	//工作年限
	private String experience_years;
	
	//技能
	private String skill;
	
	//毕业时间
	private String graduate_date;
	
	//英语水平  0代表 CET4，1代表CET6,2代表.....
	private String English_level;
	
	//候选人现状    0：招聘中，1：offer中，2：已入职 ，3：闲置 ， 4：黑名单
	private String candidate_status;
	
	//简历路径
	private String resume_path;
	
	//HR
	private String hr;
	
	//期望薪资
	private String expected_salary;
	
	//实际薪资
	private String real_salary;
	
	//到岗日期
	private String arrival_date;
	
	
	//上家公司
	private String old_company;
	
	//备注
	private String remark;
	
	//创建人
	private String create_user;
	
	//面试者状态
	private String interview_status;
	
	
	
	public String getInterview_status() {
		return interview_status;
	}

	public void setInterview_status(String interview_status) {
		this.interview_status = interview_status;
	}

	public String getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}

	
	public String getExpected_salary() {
		return expected_salary;
	}

	public void setExpected_salary(String expected_salary) {
		this.expected_salary = expected_salary;
	}

	public String getReal_salary() {
		return real_salary;
	}

	public void setReal_salary(String real_salary) {
		this.real_salary = real_salary;
	}

	public String getOld_company() {
		return old_company;
	}

	public void setOld_company(String old_company) {
		this.old_company = old_company;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	public String getHr() {
		return hr;
	}

	public void setHr(String hr) {
		this.hr = hr;
	}

	//创建时间
	private String create_date;
	
	//更新时间
	private String update_date;
	
	//简历来源
	private String source;
	
	//角色
	private String role;
	
	//入职时间
	private String entry_date;
	
	//邮箱
	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getExperience_years() {
		return experience_years;
	}

	public void setExperience_years(String experience_years) {
		this.experience_years = experience_years;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getGraduate_date() {
		return graduate_date;
	}

	public void setGraduate_date(String graduate_date) {
		this.graduate_date = graduate_date;
	}

	public String getEnglish_level() {
		return English_level;
	}

	public void setEnglish_level(String english_level) {
		English_level = english_level;
	}

	public String getCandidate_status() {
		return candidate_status;
	}

	public void setCandidate_status(String candidate_status) {
		this.candidate_status = candidate_status;
	}

	public String getResume_path() {
		return resume_path;
	}

	public void setResume_path(String resume_path) {
		this.resume_path = resume_path;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
