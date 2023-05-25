package edu.na.controller;

import edu.na.dto.DeviceDto;
import edu.na.entity.Device;
import edu.na.repository.DeviceRepository;
import edu.na.service.DeviceService;
import edu.na.util.MapperUtil;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;
    private final DeviceRepository deviceRepository;
    private final MapperUtil mapperUtil;

    public DeviceController(DeviceService deviceService, DeviceRepository deviceRepository, MapperUtil mapperUtil) {
        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
        this.mapperUtil = mapperUtil;
    }

    @GetMapping("/add")
    public String addDevice(Model model) {
        model.addAttribute("device", new DeviceDto());
//        model.addAttribute("devices", deviceService.findAll());
        return "/device/add";
    }

    @PostMapping("/add")
    public String saveDevice(@Valid  @ModelAttribute("device") DeviceDto deviceDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/device/add";
        }
        Device newDevice = mapperUtil.convert(deviceDto, new Device());
        newDevice.setCheckMeOut(true);


        deviceRepository.save(newDevice);
        redirectAttributes.addFlashAttribute("successMessage", "Device saved successfully!!!");
        return "redirect:/devices/add";

    }
    @GetMapping("/list")
    public String listDevices(Model model) {
//        model.addAttribute("device", new DeviceDto());
        model.addAttribute("devices", deviceService.listUnDeletedUnCommissionedDevices());
        return "/device/list";
    }

    @GetMapping("/update/{id}")
    public String updateDevice(@PathVariable("id") Long id, Model model) {

        model.addAttribute("device", deviceService.findDevice(id)); // send the devicedto
//        model.addAttribute("devices", deviceService.findAll());
        return "/device/update";
    }

    @PostMapping("/update/{id}")
    public String saveUpdatedDevice(@Valid @ModelAttribute("device") DeviceDto deviceDto,BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/device/update";
        }
        deviceService.update(deviceDto);
        redirectAttributes.addFlashAttribute("successMessage", "Device updated successfully!!!");
        return "redirect:/devices/list";

    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public String deleteDevice(@PathVariable("id")Long id, DeviceDto deviceDto){
        deviceService.delete(id);
        return "redirect:/devices/list";
    }
    @GetMapping("/commission/{id}")
    @PreAuthorize("hasRole('Admin')")
    public String decommissionDevice(@PathVariable("id")Long id, DeviceDto deviceDto){
        deviceService.decommissionDevice(id);
        return "redirect:/devices/list";
    }
    @GetMapping("/charts/bar-chart")
    public String showDeviceBarChart(Model model){

        return "/charts/device-bar-chart";}

    @GetMapping("/charts/bar-chart/assigned")
    public String showAssignedDeviceBarChart(Model model){

        return "/charts/assigned-devices-bar-chart";
    }
    @GetMapping("/device-search")
    public String searchDevice(Model model) {

        model.addAttribute("devices",deviceService.listUnDeletedUnCommissionedDevicesBySerialNo());



        return "/device/search";

    }

    @PostMapping("/device-search")
    public String deviceSearchResults( @RequestParam("serialNumber") String serialNumber,Model model) {
        if(serialNumber.isEmpty()){
            model.addAttribute("errorMessage","Please select a device from the list!!");
            model.addAttribute("devices",deviceService.listUnDeletedUnCommissionedDevicesBySerialNo());
        return "/device/search";
        }
        model.addAttribute("devices",deviceService.listUnDeletedUnCommissionedDevicesBySerialNo());
        model.addAttribute("foundDevice",deviceService.findDeviceBySerialNo(serialNumber) );


        return "/device/search";

    }

//    @GetMapping("/{id}")
//    public ResponseEntity<DeviceDto> findByDeviceId(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(deviceService.findDevice(id));
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<DeviceDto> deleteDevice(@PathVariable("id") Long id){
//        return ResponseEntity.ok(deviceService.delete(id));
//    }

}

