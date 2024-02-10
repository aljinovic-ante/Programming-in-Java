package hr.antealjinovic.controller;
import hr.antealjinovic.exception.DeviceNotFoundException;
import hr.antealjinovic.exception.SameRecordExistsException;
import hr.antealjinovic.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import hr.antealjinovic.models.Device;
import hr.antealjinovic.models.Record;
import hr.antealjinovic.service.RecordService;
import hr.antealjinovic.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/addRecords")
public class AddRecordController {
    @Autowired
    private RecordService recordService;
    private ClientService clientService;
    private DeviceService deviceService;

    @GetMapping("/add")
    public String getRecords(){
        return "add";
    }

    @PostMapping("/post")
    public String postRecords(@RequestParam Long deviceID, Model model) {
        recordService.addRecord(deviceID);
        List<Record> records = recordService.getAllRecords();
        model.addAttribute("records", records);
        return "created";
    }


    @GetMapping("/add2")
    public String getRecords2(){
        return "add2";
    }

    @PostMapping("/post2")
    public String postRecords2(@RequestParam Long deviceID, @RequestParam int year, @RequestParam int month, @RequestParam Long consumption, Model model) {
        try {
            recordService.addRecord2(deviceID, year, month, consumption);
        } catch (DeviceNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "add2";
        } catch (SameRecordExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "add2";
        }

        List<Record> records = recordService.getAllRecords();
        model.addAttribute("records", records);
        return "created";
    }




    @GetMapping("/allRecords")
    public String allRecords(Model model) {
        List<Record> records = recordService.getAllRecords();
        model.addAttribute("records", records);
        return "created";

    }

    @GetMapping("/pagination")
    public String devicePagination(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(2);

        Page<Record> devicePage = recordService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        List<Device> devices = recordService.devicesByRecord();

        model.addAttribute("devicePage", devicePage);
        model.addAttribute("devices", devices);

        int totalPages = devicePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "pagination";
    }

}
