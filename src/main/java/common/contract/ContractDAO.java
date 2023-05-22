package common.contract;

import java.util.List;
import jdbc.JdbcTemplate;
import jdbc.RowMapper;

public class ContractDAO {
  public void addContract(Contract contract) {
    JdbcTemplate template = new JdbcTemplate();
    // TODO reflect contract_id
    String sql = "insert into CONTRACTS values(?,?,?,?,?,?,?)";
    template.executeUpdate(sql, contract.getCompensationTeams(), contract.getCustomerId(), contract.getFee(), contract.getName(), contract.getRate(), contract.getTeamsOfSubscription(), contract.getUnderwritingReport());
  }

  public Contract findByContractId(String contractId) {
    RowMapper<Contract> rm = rs ->
        new Contract(
            rs.getString("compensation_terms"),
            rs.getInt("customer_id"),
            rs.getInt("fee"),
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("rate"),
            rs.getString("terms_of_subscription"),
            rs.getString("underwriting_report")
        );

    JdbcTemplate template = new JdbcTemplate();
    String sql = "select * from CONTRACTS where id = ?";
    return template.executeQuery(sql, rm, contractId);
  }

  public void removeUser(String contractId) {
    JdbcTemplate template = new JdbcTemplate();
    String sql = "delete from CONTRACTS where id = ?";
    template.executeUpdate(sql, contractId);
  }

  public void updateContract(Contract contract) {
    JdbcTemplate template = new JdbcTemplate();
    String sql = "update CONTRACTS set compensation_terms = ?, customer_id = ?, fee = ?, name = ?, rate = ?, terms_of_subscription = ?, underwriting_report = ? where id = ?";
    template.executeUpdate(sql,
        contract.getCompensationTeams(), contract.getCustomerId(), contract.getFee(),
        contract.getName(), contract.getRate(), contract.getCompensationTeams(),
        contract.getUnderwritingReport(), contract.getId()
    );

  }

  public List<Contract> findContracts() {
    RowMapper<Contract> rm = rs ->
        new Contract(
            rs.getString("compensation_terms"),
            rs.getInt("customer_id"),
            rs.getInt("fee"),
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("rate"),
            rs.getString("terms_of_subscription"),
            rs.getString("underwriting_report")
        );

    JdbcTemplate template = new JdbcTemplate();
    String sql = "select * from CONTRACTS";
    return template.list(sql, rm);
  }
}
