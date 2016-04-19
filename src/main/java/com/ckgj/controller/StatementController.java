package com.ckgj.controller;

import com.ckgj.models.MyBadRequestException;
import com.ckgj.models.company.Company;
import com.ckgj.models.statement.Statement;
import com.ckgj.models.statement.StatementSheet;
import com.ckgj.services.company.CompanyService;
import com.ckgj.services.statement.StatementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/admin/statement")
public class StatementController {
    private static final Logger logger = LoggerFactory.getLogger(StatementController.class);
    @Autowired
    StatementService statementService;
    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String fileUploadView(@RequestParam(value = "companyId", defaultValue = "0") Long companyId, Model model) {
        model.addAttribute("companies", companyService.findAll());
        if (companyId > 0) {
            model.addAttribute("companyId", companyId);
        }
        return "statement/upload";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("companyId") Long companyId, @RequestParam("year") int year,
                                   @RequestParam("month") int month, @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            InputStream inputStream = file.getInputStream();
            Company company = companyService.findCompany(companyId);
            Statement statement = statementService.saveStatement(inputStream, company, year, month);
            redirectAttributes.addFlashAttribute("companyId", company.getId());
            redirectAttributes.addFlashAttribute("message", "成功保存");
            redirectAttributes.addFlashAttribute("statementId", statement.getId());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "保存失败: " + e.getMessage());
        }

        return "redirect:/admin/statement/upload";
    }

    @RequestMapping("/json_sheet/{statementId}")
    @ResponseBody
    public StatementSheet jsonSheet(@PathVariable("statementId") Long statementId) {
        Optional<StatementSheet> sheetOptional = statementService.getOneSheet(statementId);
        if (sheetOptional.isPresent()) {
            return sheetOptional.get();
        } else {
            return null;
        }
    }

    @RequestMapping(value="/edit/{statementId}")
    public ModelAndView statement(@PathVariable("statementId") Long statementId) {
        Optional<StatementSheet> sheetOptional = statementService.getOneSheet(statementId);
        ModelAndView modelAndView = new ModelAndView("statement/edit");
        if (sheetOptional.isPresent()) {
            modelAndView.addObject("sheet", sheetOptional.get());
        } else {
            modelAndView.addObject("sheet", new StatementSheet());
            modelAndView.addObject("message", "该报表不存在");
        }
        return modelAndView;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editStatement(@Valid @ModelAttribute("statementSheet") StatementSheet sheet,
                                BindingResult bindingResult) throws MyBadRequestException {
        if (bindingResult.hasErrors()) {
            throw new MyBadRequestException("参数错误: " + bindingResult.getAllErrors());
        }
        try {
            statementService.updateStatement(sheet);
        } catch (Exception e) {
            throw new MyBadRequestException("参数错误: " + e.getMessage());
        }
        return "{\"msg\":\"保存成功\"}";
    }
    @RequestMapping(value="/list/{companyId}")
    public ModelAndView statementList(@PathVariable("companyId") Long companyId) {
        Company company = companyService.findCompany(companyId);
        ModelAndView modelAndView = new ModelAndView("statement/list");
        if (company == null) {
            modelAndView.addObject("message", "公司不存在");
            modelAndView.addObject("sheets", Collections.EMPTY_LIST);
        } else {
            modelAndView.addObject("sheets", statementService.sortedSheet(company));
        }
        return modelAndView;
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Statement statement = statementService.deleteOne(id);
        return "redirect:/admin/statement/list/" + statement.getCompany().getId();
    }
}
