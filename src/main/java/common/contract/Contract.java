package common.contract;


import java.io.File;
import java.util.List;

/**
 * @author imseongbin
 * @version 1.0
 * @created 01-5-2023 ?? 4:49:58
 */
public class Contract {

	private String compensationTeams;
	private int condition;
	private int customerId;
	private int fee;
	private int id;
	private String name;
	private int rate;
	private String teamsOfSubscription;
	private int type;
	private String underwritingReport;

	public Contract(){

	}

	public Contract(String compensationTeams, int customerId, int fee, int id, String name, int rate, String teamsOfSubscription, String underwritingReport) {
		this.compensationTeams = compensationTeams;
		this.customerId = customerId;
		this.fee = fee;
		this.id = id;
		this.name = name;
		this.rate = rate;
		this.teamsOfSubscription = teamsOfSubscription;
		this.underwritingReport = underwritingReport;
	}

	public void finalize() throws Throwable {

	}

	public boolean calculateRate(){
		return false;
	}

	public String getCompensationTeams() {
		return compensationTeams;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getFee() {
		return fee;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getRate() {
		return rate;
	}

	public String getTeamsOfSubscription() {
		return teamsOfSubscription;
	}

	public int getType() {
		return type;
	}

	public String getUnderwritingReport() {
		return underwritingReport;
	}
}