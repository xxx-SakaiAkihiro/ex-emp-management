package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * @author sakai 
 * administratorsテーブルを操作するリポジトリ(DAO)
 */
@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	// 管理者情報を挿入するメソッド
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String insertSql = "INSERT INTO administrator(name, mail_address, password) VALUES(:name, :mailAddress, :password) WHERE id = :id";
		template.update(insertSql, param);
	}

	// メールアドレスとパスワードから管理者情報を取得する
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String findSql = "SELECT id, name, mail_address, password FROM administrators WHERE mail_address = :mailAddress && password = :password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		if(mailAddress == null || password == null) {
			Administrator administrator = template.queryForObject(findSql, param, ADMINISTRATOR_ROW_MAPPER);
			return administrator;
		}else {
			return null;
		}
		
	}

}
