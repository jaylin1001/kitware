package vo;

public class Yeoncha {
	
	private int years;
	private int emp_num;
	private int all_yoncha;
	private int use_yoncha;
	
	public Yeoncha() {
		super();
	}

	public Yeoncha(int years, int emp_num, int all_yoncha, int use_yoncha) {
		super();
		this.years = years;
		this.emp_num = emp_num;
		this.all_yoncha = all_yoncha;
		this.use_yoncha = use_yoncha;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getEmp_num() {
		return emp_num;
	}

	public void setEmp_num(int emp_num) {
		this.emp_num = emp_num;
	}

	public int getAll_yoncha() {
		return all_yoncha;
	}

	public void setAll_yoncha(int all_yoncha) {
		this.all_yoncha = all_yoncha;
	}

	public int getUse_yoncha() {
		return use_yoncha;
	}

	public void setUse_yoncha(int use_yoncha) {
		this.use_yoncha = use_yoncha;
	}

	@Override
	public String toString() {
		return "Yeoncha [years=" + years + ", emp_num=" + emp_num + ", all_yoncha=" + all_yoncha + ", use_yoncha="
				+ use_yoncha + "]";
	}
	
}
