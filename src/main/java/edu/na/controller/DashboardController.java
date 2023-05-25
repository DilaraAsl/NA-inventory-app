package edu.na.controller;

import edu.na.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
   private final DeviceService deviceService;

    public DashboardController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    String showDashBoard(Model model){
        Integer inStockTotal=deviceService.getTotalCountOfAvailableDevicesToCheckOut();
        Integer checkedOutTotal=deviceService.getTotalCountOfDevicesCheckedOut();
        model.addAttribute("InStock",inStockTotal);
        model.addAttribute("CheckedOut",checkedOutTotal);
        model.addAttribute("Total",inStockTotal+checkedOutTotal);
        return "dashboard";
    }
}
