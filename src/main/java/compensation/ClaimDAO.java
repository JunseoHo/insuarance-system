package compensation;

import java.util.List;
import jdbc.JdbcTemplate;
import jdbc.RowMapper;

public class ClaimDAO {
  public void addClaim(Claim claim) {
    JdbcTemplate template = new JdbcTemplate();
    // TODO reflect claim_id
    String sql = "insert into CLAIMS (compensation, customer_id, date, description, employee_id, is_paid, location, report, reviewer, review) values(?,?,?,?,?,?,?,?,?,?)";
    template.executeUpdate(sql,
        claim.getCompensation(), claim.getCustomerId(), claim.getDate(), claim.getDescription(),
        claim.getEmployeeId(), claim.isPaid(), claim.getLocation(), claim.getReport(),
        claim.getReviewer(), claim.getReview()
    );
  }

  public Claim findByClaimId(String claimId) {
    RowMapper<Claim> rm = rs ->
        new Claim(
            rs.getInt("id"),
            rs.getInt("customer_id"),
            rs.getInt("employee_id"),
            rs.getString("date"),
            rs.getString("type"),
            rs.getString("description"),
            rs.getString("location"),
            rs.getString("report"),
            rs.getInt("compensation"),
            rs.getInt("reviewer"),
            rs.getBoolean("is_paid"),
            rs.getString("review")
        );

    JdbcTemplate template = new JdbcTemplate();
    String sql = "select * from CLAIMS where id = ?";
    return template.executeQuery(sql, rm, claimId);
  }

  public void removeClaim(int claimId) {
    JdbcTemplate template = new JdbcTemplate();
    String sql = "delete from CLAIMS where id = ?";
    template.executeUpdate(sql, claimId);
  }

  public void updateClaim(Claim claim) {
    JdbcTemplate template = new JdbcTemplate();
    String sql = "update CLAIMS set customer_id = ?, employee_id = ?, date = ?, type = ?, description = ?, location = ?, report = ?, compensation = ?, reviewer = ?, is_paid = ?, review = ? where id = ?";
    template.executeUpdate(sql,
        claim.getCustomerId(), claim.getEmployeeId(), claim.getDate(),
        claim.getType(), claim.getDescription(), claim.getLocation(),
        claim.getReview(), claim.getReport(), claim.getCompensation(),
        claim.getReviewer(), claim.isPaid(), claim.getReview(), claim.getClaimId()
    );
  }

  public List<Claim> findClaims() {
    RowMapper<Claim> rm = rs ->
        new Claim(
            rs.getInt("id"),
            rs.getInt("customer_id"),
            rs.getInt("employee_id"),
            rs.getString("date"),
            rs.getString("type"),
            rs.getString("description"),
            rs.getString("location"),
            rs.getString("report"),
            rs.getInt("compensation"),
            rs.getInt("reviewer"),
            rs.getBoolean("is_paid"),
            rs.getString("review")
        );

    JdbcTemplate template = new JdbcTemplate();
    String sql = "select * from CLAIMS";
    return template.list(sql, rm);
  }
}


