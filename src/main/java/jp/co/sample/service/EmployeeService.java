package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報を検索するサービス.
 * 
 * @author sakai
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員一覧情報を入社日順で取得する.
	 * 
	 * @return 従業員一覧情報　従業員が存在しない場合は0件の従業員一覧を返す
	 */
	public List<Employee> showList(){
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}
	
}
