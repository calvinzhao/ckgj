package com.ckgj.controller;

import com.ckgj.services.company.CompanyService;
import com.ckgj.services.statement.StatementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/statement")
public class StatementController {
    private static final Logger logger = LoggerFactory.getLogger(StatementController.class);
    @Autowired
    StatementService statementService;
    @Autowired
    CompanyService companyService;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("companyId") Long companyId,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
//        if (name.contains("/")) {
//            redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
//            return "redirect:upload";
//        }
//        if (name.contains("/")) {
//            redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
//            return "redirect:upload";
//        }

//        if (!file.isEmpty()) {
//            try {
//                BufferedOutputStream stream = new BufferedOutputStream(
//                        new FileOutputStream(new File(Application.ROOT + "/" + name)));
//                FileCopyUtils.copy(file.getInputStream(), stream);
//                stream.close();
//                redirectAttributes.addFlashAttribute("message",
//                        "You successfully uploaded " + name + "!");
//            }
//            catch (Exception e) {
//                redirectAttributes.addFlashAttribute("message",
//                        "You failed to upload " + name + " => " + e.getMessage());
//            }
//        }
//        else {
//            redirectAttributes.addFlashAttribute("message",
//                    "You failed to upload " + name + " because the file was empty");
//        }

        return "redirect:upload";
    }
}
