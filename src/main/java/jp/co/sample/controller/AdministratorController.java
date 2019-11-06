package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者登録画面に表示する処理を記述する.
 * 
 * @author sakai
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	/**
	 * InsertAdministratorFormをインスタンス化しそのままreturnする処理.
	 * 
	 * @return Modelオブジェクトに格納されるInsertAdministratorFormオブジェクト
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * 「administrator/insert.html」にフォワードする処理
	 * 
	 * @return 「administrator/insert.html」にフォワード
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "/administrator/insert";
	}
	
	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form 登録時の管理者情報
	 * @return 「/」にリダイレクトする
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
	}
	

}
