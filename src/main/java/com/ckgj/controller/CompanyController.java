/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.ckgj.controller;

import javax.validation.Valid;

import com.ckgj.models.Company;

import com.ckgj.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin/company")
public class CompanyController {
	@Autowired
	private final CompanyService companyService;

	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@RequestMapping
	public ModelAndView list() {
		Iterable<Company> companies = this.companyService.findAll();
		return new ModelAndView("company/list", "companies", companies);
	}

	@RequestMapping("{id}")
	public ModelAndView view(@PathVariable("id") Company company) {
		return new ModelAndView("company/view", "company", company);
	}

	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String createForm(@ModelAttribute Company company) {
		return "company/form";
	}

	@RequestMapping(value="form", method = RequestMethod.POST)
	public ModelAndView create(@Valid Company company, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("company/form", "formErrors", result.getAllErrors());
		}
		company = companyService.create(company);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new company");
		return new ModelAndView("redirect:{company.id}", "company.id", company.getId());
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

	@RequestMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		this.companyService.deleteMessage(id);
		Iterable<Company> companies = this.companyService.findAll();
		return new ModelAndView("company/list", "companies", companies);
	}

	@RequestMapping(value = "modify/{id}", method = RequestMethod.GET)
	public ModelAndView modifyForm(@PathVariable long id) {
		return new ModelAndView("user", "user", companyService.findCompany(id));
	}

}
