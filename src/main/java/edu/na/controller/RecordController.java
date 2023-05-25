package edu.na.controller;

import edu.na.dto.DeviceDto;
import edu.na.dto.RecordDto;
import edu.na.dto.UserDto;
import edu.na.entity.User;
import edu.na.repository.RecordRepository;
import edu.na.service.DeviceService;
import edu.na.service.RecordService;
import edu.na.service.TransactionService;
import edu.na.service.UserService;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public String addNewRecord(Model model) {
        Long id = recordRepository.getLatestRecordedRecordId();
        RecordDto recordDto = new RecordDto();

        recordDto.setId(id);
        recordDto.setDate(LocalDateTime.now());
        model.addAttribute("record", recordDto);
        model.addAttribute("assignees", userService.userListOrderedByUserName());
        model.addAttribute("records", recordService.findAll());
        model.addAttribute("transactions", transactionService.listAllTransactions());
        model.addAttribute("devices", deviceService.listUnDeletedUnCommissionedDevices());
        return "/record/add";
    }

    @PostMapping("/add")
    public String createNewRecord(@Valid @ModelAttribute("record") RecordDto recordDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(recordDto.getUser().equals(null)){
            redirectAttributes.addFlashAttribute("errorMessage","Please make all selections correctly!");
            return "redirect:/records/add";
        }
        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("errorMessage","Please make all selections correctly!");
            return "redirect:/records/add";
        }

        recordService.save(recordDto);
        redirectAttributes.addFlashAttribute("successMessage", "Record saved successfully!!!");

        return "redirect:/records/add";
    }

    @GetMapping("/list")
    public String ListRecords(Model model) {

        model.addAttribute("records", recordService.findAll());

        return "/record/list";
    }
    @GetMapping("/list/open-transactions")
    public String ListOpenRecords(Model model) {

        model.addAttribute("records", recordService.findAllRecordsThatAreOpenTransactions());

        return "/record/openTransaction-list";
    }
    @GetMapping("/update/{id}")
    public String editRecord(@PathVariable("id") Long id, Model model) {
        RecordDto recordDto = recordService.findById(id);
        model.addAttribute("newRecord", recordDto);
        model.addAttribute("assignees", userService.userListOrderedByUserName());
        model.addAttribute("records", recordService.findAll());
        model.addAttribute("transactions", transactionService.listAllTransactions());
        model.addAttribute("devices", deviceService.listUnDeletedUnCommissionedDevices());


        return "/record/update";

    }

    @PostMapping("/update/{id}")
    public String updateRecord(@Valid @ModelAttribute("newRecord") RecordDto recordDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "redirect:/records/update/{id}";
        }
        recordService.update(recordDto);
        redirectAttributes.addFlashAttribute("successMessage", "Record updated successfully!!!");
        return "redirect:/records/list";

    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable("id") Long id) {
        recordService.delete(id);
        return "redirect:/records/list";
    }

    @GetMapping("/device-search")
    public String searchDeviceRecord(Model model) {

        model.addAttribute("devices", deviceService.listUnDeletedUnCommissionedDevicesBySerialNo());


        return "/record/device-search";

    }

    @PostMapping("/device-search")
    public String updateDeviceSearchResults(@RequestParam("serialNumber") String serialNumber, Model model) {

        model.addAttribute("records", recordService.listAllRecordsOfDevice(serialNumber));


        return "/record/search-results-device";

    }

    @GetMapping("/assignee-search")
    public String searchAssigneeRecord(Model model) {

        model.addAttribute("assignees", userService.userListOrderedByUserName());

        return "/record/assignee-search";

    }

    @PostMapping("/assignee-search")
    public String updateAssigneeSearchResults(@RequestParam("user_name") String userName, Model model) {
        if (userName.isEmpty()) {
            model.addAttribute("errorMessage", "Please select a username from the list!!!");
            model.addAttribute("assignees", userService.findAll());
            return "/record/assignee-search";
        }

        model.addAttribute("records", recordService.listAllRecordsOfUser(userService.findByUserName(userName)));

        return "/record/search-results-assignee";
    }

}
