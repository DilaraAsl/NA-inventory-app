package edu.na.controller;

import edu.na.dto.RecordDto;
import edu.na.repository.RecordRepository;
import edu.na.service.DeviceService;
import edu.na.service.RecordService;
import edu.na.service.TransactionService;
import edu.na.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/records")
public class RecordController {
    private final RecordService recordService;
    private final RecordRepository recordRepository;
    private final UserService userService;
    private final TransactionService transactionService;
    private final DeviceService deviceService;

    public RecordController(RecordService recordService, RecordRepository recordRepository, UserService userService, TransactionService transactionService, DeviceService deviceService) {
        this.recordService = recordService;
        this.recordRepository = recordRepository;
        this.userService = userService;
        this.transactionService = transactionService;
        this.deviceService = deviceService;
    }
    @GetMapping("/add")
    public String addNewRecord(Model model){
        Long id=recordRepository.getLatestRecordedRecordId();
        RecordDto recordDto=new RecordDto();
        recordDto.setId(id);
        recordDto.setDate(LocalDate.now());
        model.addAttribute("record",recordDto);
        model.addAttribute("assignees",userService.findAll());
        model.addAttribute("records",recordService.findAll());
        model.addAttribute("transactions",transactionService.listAllTransactions());
        model.addAttribute("devices",deviceService.findAll());
        return "record/add";
    }

    @PostMapping("/add")
    public String createNewRecord(@ModelAttribute("record") RecordDto recordDto){
        recordService.save(recordDto);
        return "redirect:/records/add";
    }

    @GetMapping("/update/{id}")
    public String editRecord(@PathVariable("id") Long id, Model model) {

        model.addAttribute("record", recordService.findById(id));
        model.addAttribute("assignees",userService.findAll());
        model.addAttribute("records",recordService.findAll());
        model.addAttribute("transactions",transactionService.listAllTransactions());
        model.addAttribute("devices",deviceService.findAll());


        return "/record/update";

    }

    @PostMapping("/update")
    public String updateUser( @ModelAttribute("record") RecordDto record, Model model) {



        recordService.update(record);

        return "redirect:/records/update";

    }

}
