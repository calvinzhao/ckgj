package com.ckgj.controller;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

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

    @RequestMapping(value="/edit/{statementId}")
    public ModelAndView statement(@PathVariable("statementId") Long statementId) {
        StatementSheet sheet = statementService.getOneSheet(statementId);
        return new ModelAndView("statement/edit", "sheet", sheet);
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editStatement(@Valid @ModelAttribute("statementSheet") StatementSheet sheet, BindingResult bindingResult,
                                HttpServletResponse response) throws IOException {
        // TODO:
        if (bindingResult.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "参数错误");
        }
        try {
            statementService.updateStatement(sheet);
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "参数错误");
        }
        return "保存成功";
    }
    @RequestMapping(value="/list/{companyId}")
    public ModelAndView statementList(@PathVariable("companyId") Long companyId) {
        return null;
    }
}
