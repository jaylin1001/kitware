package vo;

public class Gunte_standard {
	
	private String g_in_time1;
	private String g_in_time2;
	private String g_in_time3;
	private String g_out_time1;
	private String g_out_time2;
	private String g_out_time3;
	
	public Gunte_standard() {
		super();
	}

	public Gunte_standard(String g_in_time1, String g_in_time2, String g_in_time3, String g_out_time1,
			String g_out_time2, String g_out_time3) {
		super();
		this.g_in_time1 = g_in_time1;
		this.g_in_time2 = g_in_time2;
		this.g_in_time3 = g_in_time3;
		this.g_out_time1 = g_out_time1;
		this.g_out_time2 = g_out_time2;
		this.g_out_time3 = g_out_time3;
	}

	public String getG_in_time1() {
		return g_in_time1;
	}

	public void setG_in_time1(String g_in_time1) {
		this.g_in_time1 = g_in_time1;
	}

	public String getG_in_time2() {
		return g_in_time2;
	}

	public void setG_in_time2(String g_in_time2) {
		this.g_in_time2 = g_in_time2;
	}

	public String getG_in_time3() {
		return g_in_time3;
	}

	public void setG_in_time3(String g_in_time3) {
		this.g_in_time3 = g_in_time3;
	}

	public String getG_out_time1() {
		return g_out_time1;
	}

	public void setG_out_time1(String g_out_time1) {
		this.g_out_time1 = g_out_time1;
	}

	public String getG_out_time2() {
		return g_out_time2;
	}

	public void setG_out_time2(String g_out_time2) {
		this.g_out_time2 = g_out_time2;
	}

	public String getG_out_time3() {
		return g_out_time3;
	}

	public void setG_out_time3(String g_out_time3) {
		this.g_out_time3 = g_out_time3;
	}

	@Override
	public String toString() {
		return "Gunte_standard [g_in_time1=" + g_in_time1 + ", g_in_time2=" + g_in_time2 + ", g_in_time3=" + g_in_time3
				+ ", g_out_time1=" + g_out_time1 + ", g_out_time2=" + g_out_time2 + ", g_out_time3=" + g_out_time3
				+ "]";
	}
	
}
