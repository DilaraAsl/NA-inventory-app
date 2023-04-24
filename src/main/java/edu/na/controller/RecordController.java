package edu.na.controller;

import edu.na.dto.DeviceDto;
import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;
import edu.na.repository.RecordRepository;
import edu.na.service.DeviceService;
import edu.na.service.RecordService;
import edu.na.service.TransactionService;
import edu.na.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        recordDto.setDate(LocalDateTime.now());
        model.addAttribute("record",recordDto);
        model.addAttribute("assignees",userService.findAll());
        model.addAttribute("records",recordService.findAll());
        model.addAttribute("transactions",transactionService.listAllTransactions());
        model.addAttribute("devices",deviceService.findAll());
        return "/record/add";
    }

    @PostMapping("/add")
    public String createNewRecord(@ModelAttribute("record") RecordDto recordDto){
        recordService.save(recordDto);
        return "redirect:/records/list";
    }
    @GetMapping("/list")
    public String ListRecords(Model model){

//        model.addAttribute("assignees",userService.findAll());
        model.addAttribute("records",recordService.findAll());
//        model.addAttribute("transactions",transactionService.listAllTransactions());
//        model.addAttribute("devices",deviceService.findAll());
        return "/record/list";
    }
    @GetMapping("/update/{id}")
    public String editRecord(@PathVariable("id") Long id, Model model) {
          RecordDto recordDto = recordService.findById(id);
        model.addAttribute("newRecord", recordDto);
        model.addAttribute("assignees",userService.findAll());
        model.addAttribute("records",recordService.findAll());
        model.addAttribute("transactions",transactionService.listAllTransactions());
        model.addAttribute("devices",deviceService.findAll());


        return "/record/update";

    }

    @PostMapping("/update/{id}")
    public String updateRecord( @ModelAttribute("newRecord") RecordDto record, Model model) {



        recordService.update(record);

        return "redirect:/records/list";

    }
    @GetMapping("/device-search")
    public String searchDeviceRecord(Model model) {


//        model.addAttribute("assignees",userService.findAll());
//        model.addAttribute("records",recordService.findAll());
//        model.addAttribute("transactions",transactionService.listAllTransactions());
//        model.addAttribute("devices",deviceService.findAll());

//        model.addAttribute("device",new DeviceDto());
        model.addAttribute("devices",deviceService.findAll());


        return "/record/device-search";

    }

    @PostMapping("/device-search")
    public String updateDeviceSearchResults( @RequestParam("serialNumber") String serialNumber,Model model) {

        model.addAttribute("records", recordService.listAllRecordsOfDevice(serialNumber));


        return "/record/search-results-device";

    }
    @GetMapping("/assignee-search")
    public String searchAssigneeRecord(Model model) {

        model.addAttribute("assignees",userService.findAll());

        return "/record/assignee-search";

    }

@PostMapping("/assignee-search")
public String updateAssigneeSearchResults(@RequestParam("user_name") String userName, Model model) {
            model.addAttribute("records", recordService.listAllRecordsOfUser(userService.findByUserName(userName)));

        return "/record/search-results-assignee";
}

}
